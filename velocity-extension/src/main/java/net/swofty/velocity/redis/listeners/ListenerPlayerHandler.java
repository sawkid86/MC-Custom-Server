package net.swofty.velocity.redis.listeners;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.viaversion.viaversion.api.Via;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import net.swofty.commons.ServerType;
import net.swofty.velocity.SkyBlockVelocity;
import net.swofty.velocity.gamemanager.BalanceConfiguration;
import net.swofty.velocity.gamemanager.BalanceConfigurations;
import net.swofty.velocity.gamemanager.GameManager;
import net.swofty.velocity.gamemanager.TransferHandler;
import net.swofty.velocity.redis.ChannelListener;
import net.swofty.velocity.redis.RedisListener;
import net.swofty.velocity.redis.RedisMessage;
import org.json.JSONObject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ChannelListener(channel = "player-handler")
public class ListenerPlayerHandler extends RedisListener {
    @Override
    public String receivedMessage(String message, UUID serverUUID) {
        JSONObject json = new JSONObject(message);
        UUID uuid = UUID.fromString(json.getString("uuid"));
        String action = json.getString("actions");

        Optional<Player> potentialPlayer = SkyBlockVelocity.getServer().getPlayer(uuid);
        if (potentialPlayer.isEmpty()) {
            return "false";
        }
        Player player = potentialPlayer.get();
        Optional<ServerConnection> potentialServer = player.getCurrentServer();

        switch (action) {
            case "transfer" -> {
                ServerType type = ServerType.valueOf(json.getString("type"));
                if (!GameManager.hasType(type) || TransferHandler.playersInLimbo.contains(player)) {
                    return "true";
                }
                ServerType playersCurrentServer = GameManager.getTypeFromUUID(UUID.fromString(player.getCurrentServer()
                        .get()
                        .getServer()
                        .getServerInfo()
                        .getName()));

                new Thread(() -> {
                    List<GameManager.GameServer> gameServers = GameManager.getFromType(type);
                    List<BalanceConfiguration> configurations = BalanceConfigurations.configurations.get(type);
                    if (configurations == null) {
                        configurations = List.of(); // Initialize with an empty list if null
                    }
                    GameManager.GameServer toSendTo = gameServers.get(0);

                    for (BalanceConfiguration configuration : configurations) {
                        GameManager.GameServer server = configuration.getServer(player, gameServers);
                        if (server != null) {
                            toSendTo = server;
                            break;
                        }
                    }

                    RedisMessage.sendMessageToServer(toSendTo.internalID(),
                            "origin-server",
                            player.getUniqueId().toString() + ":" + playersCurrentServer.name());

                    new TransferHandler(player).transferTo(player.getCurrentServer().get().getServer(), toSendTo.server());
                }).start();
            }
            case "teleport" -> {
                UUID target = UUID.fromString(json.getString("uuid"));
                Double x = json.getDouble("x");
                Double y = json.getDouble("y");
                Double z = json.getDouble("z");
                Float yaw = json.getFloat("yaw");
                Float pitch = json.getFloat("pitch");

                Optional<Player> targetPlayer = SkyBlockVelocity.getServer().getPlayer(target);
                if (targetPlayer.isEmpty()) {
                    return "false";
                }

                return RedisMessage.sendMessageToServer(UUID.fromString(targetPlayer.get().getCurrentServer().get().getServer().getServerInfo().getName()),
                        "teleport",
                        json.toString()).join();
            }
            case "bank-hash" -> {
                if (potentialServer.isEmpty()) {
                    return "false";
                }
                UUID playerServer = UUID.fromString(potentialServer.get().getServer().getServerInfo().getName());

                return RedisMessage.sendMessageToServer(playerServer,
                        "bank-hash",
                        player.getUniqueId().toString()).join();
            }
            case "version" -> {
                return String.valueOf(Via.getAPI().getPlayerVersion(player.getUniqueId()));
            }
            case "event" -> {
                String event = json.getString("event");
                String data = json.getString("data");

                if (potentialServer.isEmpty()) {
                    return "false";
                }
                UUID playerServer = UUID.fromString(potentialServer.get().getServer().getServerInfo().getName());

                RedisMessage.sendMessageToServer(playerServer, "run-event",
                        player.getUniqueId().toString() + "," +
                        event + "," +
                        data
                );
            }
            case "refresh-coop-data" -> {
                String datapoint = json.getString("datapoint");

                if (potentialServer.isEmpty()) {
                    return "false";
                }
                UUID playerServer = UUID.fromString(potentialServer.get().getServer().getServerInfo().getName());

                RedisMessage.sendMessageToServer(playerServer, "refresh-data",
                        player.getUniqueId().toString() + "," +
                        datapoint
                );
            }
            case "message" -> {
                String messageToSend = json.getString("message");
                player.sendMessage(JSONComponentSerializer.json().deserialize(messageToSend));
            }
        }

        return "true";
    }
}
