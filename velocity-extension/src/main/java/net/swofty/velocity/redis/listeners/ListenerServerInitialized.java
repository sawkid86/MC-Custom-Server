package net.swofty.velocity.redis.listeners;

import net.swofty.commons.ServerType;
import net.swofty.velocity.gamemanager.GameManager;
import net.swofty.velocity.redis.ChannelListener;
import net.swofty.velocity.redis.RedisListener;
import org.json.JSONObject;

import java.util.UUID;
import java.util.logging.Logger;

@ChannelListener(channel = "server-initialized")
public class ListenerServerInitialized extends RedisListener {

    private static final Logger logger = Logger.getLogger(ListenerServerInitialized.class.getName());

    @Override
    public String receivedMessage(String message, UUID serverUUID) {
        logger.info("Received message: " + message);

        try {
            // Parse the message to extract server type and port
            JSONObject json = new JSONObject(message);
            String type = json.getString("type");
            int port = json.getInt("port");

            logger.info("Parsed type: " + type + ", port: " + port);

            // Validate and handle the server type
            if (!ServerType.isServerType(type)) {
                throw new IllegalArgumentException("Invalid server type: " + type);
            }

            ServerType serverType = ServerType.valueOf(type.toUpperCase());
            GameManager.GameServer server = GameManager.addServer(serverType, serverUUID, port);

            return String.valueOf(server.server().getServerInfo().getAddress().getPort());
        } catch (IllegalArgumentException e) {
            logger.severe("Invalid server type received: " + message);
            e.printStackTrace();
            return "error";
        } catch (Exception e) {
            logger.severe("Error processing server-initialized message: " + message);
            e.printStackTrace();
            return "error";
        }
    }
}
