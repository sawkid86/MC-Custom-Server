package net.swofty.type.hub.events;

import lombok.SneakyThrows;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.entity.GameMode;
import net.swofty.types.generic.event.EventNodes;
import net.swofty.types.generic.event.SkyBlockEvent;
import net.swofty.types.generic.event.SkyBlockEventClass;
import net.swofty.types.generic.region.SkyBlockRegion;
import net.swofty.types.generic.user.SkyBlockPlayer;

public class ActionMineStone implements SkyBlockEventClass {

    @SneakyThrows
    @SkyBlockEvent(node = EventNodes.PLAYER, requireDataLoaded = true, isAsync = true)
    public void run(PlayerBlockBreakEvent event) {
        SkyBlockPlayer player = (SkyBlockPlayer) event.getPlayer();
        GameMode gameMode = player.getGameMode();
        
        System.out.println("PlayerBlockBreakEvent triggered for player: " + player.getUsername());
        System.out.println("Player game mode: " + gameMode);

        // Allow bypass build players to break blocks
        if (player.isBypassBuild()) {
            System.out.println("Player has bypass build permissions");
            event.setCancelled(false);
            return;
        }

        // Check if the block being broken is STONE
        if (event.getBlock().compare(Block.STONE)) {
            System.out.println("Block being broken is STONE");
            
            // Get the region of the player's position
            SkyBlockRegion region = SkyBlockRegion.getRegionOfPosition(event.getPlayer().getPosition());
            System.out.println("Region: " + (region != null ? region.getName() : "null"));

            // Ensure the region is not null and is the "hub" region
            if (region != null && "hub".equalsIgnoreCase(region.getName())) {
                // Allow block break in the hub by setting the event to not be cancelled
                event.setCancelled(false);
                
                // Get the instance and block position
                Instance instance = event.getInstance();
                Pos blockPosition = Pos.fromPoint(event.getBlockPosition());
                
                // Replace the broken block with COBBLESTONE
                instance.setBlock(blockPosition, Block.COBBLESTONE);
                System.out.println("Block replaced with COBBLESTONE");
            }
        }
    }
}
