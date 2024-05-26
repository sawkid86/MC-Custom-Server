package net.swofty.types.generic.item.impl;

import lombok.Getter;
import net.swofty.types.generic.user.statistics.ItemStatistic;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface HotPotatoable {

    @Nullable PotatoType getHotPotatoType();

    @Getter
    enum PotatoType {
        ARMOR(List.of("§7When applied to armor, grants §a+2" + ItemStatistic.DEFENSE.getSymbol(),
                "§aDefense §7and §c+4" + ItemStatistic.HEALTH.getSymbol() + " Health§7.",
                " "), Map.of(ItemStatistic.DEFENSE, 2.0, ItemStatistic.HEALTH, 4.0)),
        WEAPONS(List.of("§7When applied to weapons, grants",
                "§c+2" + ItemStatistic.STRENGTH.getSymbol() + " Strength §7and §c+2" + ItemStatistic.DAMAGE.getSymbol() + " Damage§7.",
                " "), Map.of(ItemStatistic.STRENGTH, 2.0, ItemStatistic.DAMAGE, 2.0)),
        ;

        public List<String> display;
        public Map<ItemStatistic, Double> stats;

        PotatoType(List<String> display, Map<ItemStatistic, Double> stats) {
            this.display = display;
            this.stats = stats;
        }

        public static List<String> allLores() {
            List<String> lores = new ArrayList<>(ARMOR.display);
            lores.addAll(WEAPONS.display);
            return lores;
        }
    }
}
