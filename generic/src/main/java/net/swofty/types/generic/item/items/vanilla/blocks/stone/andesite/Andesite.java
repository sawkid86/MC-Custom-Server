package net.swofty.types.generic.item.items.vanilla.blocks.stone.andesite;

import net.swofty.types.generic.block.BlockType;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.PlaceableCustomSkyBlockItem;
import net.swofty.types.generic.item.impl.Sellable;
import net.swofty.types.generic.user.statistics.ItemStatistics;
import org.jetbrains.annotations.Nullable;

public class Andesite implements PlaceableCustomSkyBlockItem, Sellable {
    @Override
    public ItemStatistics getStatistics(SkyBlockItem instance) {
        return ItemStatistics.empty();
    }

    @Override
    public @Nullable BlockType getAssociatedBlockType() {
        return null;
    }

    @Override
    public double getSellValue() {
        return 2;
    }
}
