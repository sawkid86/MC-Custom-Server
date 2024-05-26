package net.swofty.types.generic.item.items.vanilla.items.armor;

import net.swofty.types.generic.item.ItemType;
import net.swofty.types.generic.item.MaterialQuantifiable;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.CustomSkyBlockItem;
import net.swofty.types.generic.item.impl.DefaultCraftable;
import net.swofty.types.generic.item.impl.SkyBlockRecipe;
import net.swofty.types.generic.item.impl.StandardItem;
import net.swofty.types.generic.item.impl.recipes.ShapedRecipe;
import net.swofty.types.generic.user.statistics.ItemStatistic;
import net.swofty.types.generic.user.statistics.ItemStatistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeatherLeggings implements CustomSkyBlockItem, StandardItem, DefaultCraftable {
    @Override
    public ItemStatistics getStatistics(SkyBlockItem instance) {
        return ItemStatistics.builder()
                .withBase(ItemStatistic.DEFENSE, 10D)
                .build();
    }

    @Override
    public StandardItemType getStandardItemType() {
        return StandardItemType.LEGGINGS;
    }
    @Override
    public SkyBlockRecipe<?> getRecipe() {
        Map<Character, MaterialQuantifiable> ingredientMap = new HashMap<>();
        ingredientMap.put('A', new MaterialQuantifiable(ItemType.LEATHER, 1));
        ingredientMap.put(' ', new MaterialQuantifiable(ItemType.AIR, 1));
        List<String> pattern = List.of(
                "AAA",
                "A A",
                "A A");

        return new ShapedRecipe(SkyBlockRecipe.RecipeType.NONE, new SkyBlockItem(ItemType.LEATHER_LEGGINGS), ingredientMap, pattern);
    }
}
