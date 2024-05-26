package net.swofty.types.generic.event.actions.player.authentication;

import net.minestom.server.event.Event;
import net.minestom.server.event.player.PlayerChatEvent;
import net.swofty.commons.ServerType;
import net.swofty.types.generic.data.mongodb.AuthenticationDatabase;
import net.swofty.types.generic.event.EventNodes;
import net.swofty.types.generic.event.SkyBlockEvent;
import net.swofty.types.generic.event.SkyBlockEventClass;
import net.swofty.types.generic.user.SkyBlockPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class PlayerChatAuthentication implements SkyBlockEventClass {
    private static final Map<UUID, Long> cooldowns = new HashMap<>();
    private static final Logger logger = Logger.getLogger(PlayerChatAuthentication.class.getName());

    @SkyBlockEvent(node = EventNodes.PLAYER, requireDataLoaded = false)
    public void run(PlayerChatEvent event) {
        SkyBlockPlayer player = (SkyBlockPlayer) event.getPlayer();
        if (player.hasAuthenticated) {
            logger.info("Player " + player.getUsername() + " is already authenticated.");
            return;
        }

        if (cooldowns.containsKey(player.getUuid())) {
            if (System.currentTimeMillis() - cooldowns.get(player.getUuid()) < 1000) {
                player.sendMessage("§cPlease wait before sending another message.");
                return;
            }
        }
        cooldowns.put(player.getUuid(), System.currentTimeMillis());

        event.setCancelled(true);
        String[] args = event.getMessage().split(" ");

        AuthenticationDatabase.AuthenticationData data = new AuthenticationDatabase(player.getUuid()).getAuthenticationData();
        if (data == null) {
            if (args.length != 3) {
                player.sendMessage("§cYou must first sign-up to play this server!");
                player.sendMessage("§cIn the Minecraft chat, type §6signup <password> <password>§c.");
                player.sendMessage("§cIt is not a command, it's just a message. Nobody else can see it.");
                return;
            }

            if (!args[1].equals(args[2])) {
                player.sendMessage("§cYour passwords do not match.");
                return;
            }

            AuthenticationDatabase.AuthenticationData newData = AuthenticationDatabase.makeFromPassword(args[1]);
            new AuthenticationDatabase(player.getUuid()).setAuthenticationData(newData);

            player.sendMessage("§aYou have successfully signed up!");
            player.sendMessage("§aNow, in the Minecraft chat, type §6login <password>§a.");
            player.sendMessage("§aIt is not a command, it's just a message. Nobody else can see it.");
            player.sendMessage("§8Salt: §7" + newData.salt());
        } else {
            if (args.length != 2) {
                player.sendMessage("§cIn the Minecraft chat, type §6login <password>§c.");
                player.sendMessage("§cIt is not a command, it's just a message. Nobody else can see it.");
                return;
            }

            if (data.matches(args[1])) {
                player.sendMessage("§aYou have successfully logged in!");
                player.hasAuthenticated = true; // Update authentication status
                player.sendTo(ServerType.HUB, true, true);
                logger.info("Player " + player.getUsername() + " has successfully logged in.");
            } else {
                player.sendMessage("§cYour password is incorrect.");
                player.sendMessage("§8Salt: §7" + data.salt());
                logger.warning("Failed login attempt for player " + player.getUsername() + " with incorrect password.");
            }
        }
    }
}