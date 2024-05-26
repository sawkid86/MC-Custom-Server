package net.swofty.types.generic.loottable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.swofty.types.generic.item.ItemType;
import net.swofty.types.generic.user.SkyBlockPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class SkyBlockLootTable {
    public abstract @NonNull List<LootRecord> getLootTable();
    public abstract @NonNull CalculationMode getCalculationMode();

    public int makeAmountBetween(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public List<ItemType> getLootTableItems() {
        List<ItemType> items = new java.util.ArrayList<>();
        for (LootRecord record : getLootTable()) {
            items.add(record.itemType);
        }
        return items;
    }

    /**
     * Function will always return a LootRecord for every itemtype
     * @param player The player to run the chances for
     * @return A map of the loot that the player will receive
     */
    public @NonNull Map<ItemType, LootRecord> runChances(SkyBlockPlayer player, LootAffector... affectors) {
        Map<ItemType, LootRecord> loot = new HashMap<>();
        CalculationMode mode = getCalculationMode();

        if (mode == CalculationMode.PICK_ONE) {
            double cumulativeChance = 0;

            for (LootRecord record : getLootTable()) {
                if (record.shouldCalculate.apply(player)) {
                    cumulativeChance += record.chancePercent;
                }
            }

            int random = (int) (Math.random() * cumulativeChance);
            double current = 0;

            for (LootRecord record : getLootTable()) {
                if (record.shouldCalculate.apply(player)) {
                    current += record.chancePercent;
                    if (random < current) {
                        loot.put(record.itemType, record);
                        break;
                    }
                }
            }
        } else if (mode == CalculationMode.CALCULATE_INDIVIDUAL) {
            for (LootRecord record : getLootTable()) {
                if (record.shouldCalculate.apply(player)) {
                    double adjustedChancePercent = record.chancePercent;

                    // Apply LootAffectors to the chance percentage
                    for (LootAffector affector : affectors) {
                        adjustedChancePercent = affector.getAffector().apply(player, adjustedChancePercent);
                    }

                    if (Math.random() * 100 < adjustedChancePercent) {
                        loot.put(record.itemType, record);
                    }
                }
            }
        }

        return loot;
    }

    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class LootRecord {
        private final ItemType itemType;
        private final int amount;
        private final double chancePercent;
        private Function<SkyBlockPlayer, Boolean> shouldCalculate = player -> true;

        public static LootRecord none(int chance) {
            return new LootRecord(ItemType.AIR, 0, chance, player -> true);
        }

        public static LootRecord none(int chance, Function<SkyBlockPlayer, Boolean> shouldCalculate) {
            return new LootRecord(ItemType.AIR, 0, chance, shouldCalculate);
        }

        public static boolean isNone(LootRecord lootRecord) {
            return lootRecord.itemType == ItemType.AIR && lootRecord.amount == 0;
        }
    }

    public enum CalculationMode {
        // Adds up all of the chances and picks one based on that
        PICK_ONE,
        // Calculates each item individually, and gives the player each item that they have a chance to get
        CALCULATE_INDIVIDUAL
    }
}
