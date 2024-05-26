package net.swofty.types.generic.user;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.swofty.types.generic.data.DataHandler;
import net.swofty.types.generic.data.datapoints.DatapointRank;
import net.swofty.types.generic.user.categories.Rank;

import java.util.function.Supplier;

public record LogHandler(SkyBlockPlayer player) {
    public void debug(Object message) {
        debug(Component.text(String.valueOf(message)));
    }

    public void debug(TextComponent message) {
        debug(message, () -> true);
    }

    public void debug(Object message, Supplier<Boolean> condition) {
        debug(Component.text(String.valueOf(message)), condition);
    }

    public void debug(TextComponent message, Supplier<Boolean> condition) {
        if (player.getDataHandler().get(DataHandler.Data.RANK, DatapointRank.class).getValue().isEqualOrHigherThan(Rank.ADMIN)) {
            if (!condition.get()) return;
            player.sendMessage(Component.text("§9[HELPER DEBUG] §f").append(message));
        }
    }
}
