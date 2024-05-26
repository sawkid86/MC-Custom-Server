package net.swofty.types.generic.region.mining.configurations;

import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;
import net.swofty.types.generic.region.SkyBlockMiningConfiguration;

import java.util.List;

public class MineCoalConfiguration extends SkyBlockMiningConfiguration {

    @Override
    public MiningTask handleStageOne(MiningTask task, Pos brokenBlock) {
        task.setIntermediaryBlock(Block.COBBLESTONE);

        Block regenerationBlock;

        if (task.getInitialMinedBlock() == Block.ANDESITE) {
            regenerationBlock = Block.ANDESITE;
        } else {
            regenerationBlock = getRandomBlock(
                    new RegenerationConfig(30, Block.COAL_ORE),
                    new RegenerationConfig(70, Block.STONE));
        }

        task.setReviveBlock(regenerationBlock);

        return task;
    }

    @Override
    public MiningTask handleStageTwo(MiningTask task, Pos brokenBlock) {
        task.setIntermediaryBlock(Block.BEDROCK);
        task.setReviveBlock(Block.COBBLESTONE);

        return task;
    }

    @Override
    public List<Material> getMineableBlocks(Instance instance, Point point) {
        return List.of(Material.COAL_ORE, Material.COBBLESTONE, Material.STONE, Material.ANDESITE);
    }

    @Override
    public long getRegenerationTime() {
        return 3000;
    }
}
