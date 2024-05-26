package net.swofty.types.generic.event.actions.player;

import net.minestom.server.event.Event;
import net.minestom.server.event.player.PlayerMoveEvent;
import net.swofty.types.generic.SkyBlockConst;
import net.swofty.types.generic.event.EventNodes;
import net.swofty.types.generic.event.SkyBlockEvent;
import net.swofty.types.generic.event.SkyBlockEventClass;
import net.swofty.types.generic.region.SkyBlockRegion;
import net.swofty.types.generic.user.SkyBlockPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class ActionPlayerStrayTooFar implements SkyBlockEventClass {
    private static final Logger logger = Logger.getLogger(ActionPlayerStrayTooFar.class.getName());
    public static Map<UUID, Long> startedStray = new HashMap<>();

    @SkyBlockEvent(node = EventNodes.PLAYER, requireDataLoaded = true)
    public void run(PlayerMoveEvent event) {
        SkyBlockPlayer player = (SkyBlockPlayer) event.getPlayer();
        SkyBlockRegion region = player.getRegion();

        logger.info("Player " + player.getUsername() + " moved to: " + player.getPosition());

        if (region != null) {
            logger.info("Player " + player.getUsername() + " is in region: " + region.getName());
            startedStray.remove(player.getUuid());
            return;
        } else {
            logger.info("Player " + player.getUsername() + " is not in any region.");
        }

        if (startedStray.containsKey(player.getUuid())) {
            if (System.currentTimeMillis() - startedStray.get(player.getUuid()) > 5000) {
                logger.info("Player " + player.getUsername() + " has strayed too far for over 5 seconds. Teleporting back to spawn.");
                player.teleport(SkyBlockConst.getTypeLoader().getLoaderValues().spawnPosition().apply(
                        player.getOriginServer()
                ));
                startedStray.remove(player.getUuid());
                player.sendMessage("§cYou have strayed too far from the spawn! Teleporting you back...");
            }
        } else {
            logger.info("Player " + player.getUsername() + " started straying at: " + System.currentTimeMillis());
            startedStray.put(player.getUuid(), System.currentTimeMillis());
        }
    }
}
