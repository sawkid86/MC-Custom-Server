package net.swofty.types.generic.user.fairysouls;

import lombok.Getter;
import lombok.Setter;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;
import net.swofty.types.generic.data.DataHandler;
import net.swofty.types.generic.data.datapoints.DatapointFairySouls;
import net.swofty.types.generic.data.mongodb.FairySoulDatabase;
import net.swofty.types.generic.entity.EntityFairySoul;
import net.swofty.types.generic.user.SkyBlockPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FairySoul {
    private static final Map<Integer, FairySoul> SOULS_CACHE = new HashMap<>();

    @Getter
    private int id;

    @Setter
    private Pos location;
    @Setter
    private FairySoulZone zone;

    public FairySoul(int id, Pos location, FairySoulZone zone) {
        this.id = id;
        this.location = location;
        this.zone = zone;
    }

    public void delete() {
        SOULS_CACHE.remove(id);
    }

    public void spawnEntity(Instance instance) {
        new EntityFairySoul(this).spawn(instance);
    }

    public void collect(SkyBlockPlayer player) {
        DatapointFairySouls.PlayerFairySouls fairySouls = player.getFairySouls();
        if (!fairySouls.getAllFairySouls().contains(id)) {
            fairySouls.addCollectedFairySouls(id);

            player.sendMessage("§d§lSOUL! §fYou found a §dFairy Soul§f!");
            player.sendMessage("§7Go to Tia the Fairy in the §eHub§7 to exchange it for rewards!");
            player.getDataHandler()
                    .get(DataHandler.Data.FAIRY_SOULS, DatapointFairySouls.class)
                    .setValue(fairySouls);
            return;
        }

        player.sendMessage("§dYou have already found that Fairy Soul!");
        if (!fairySouls.getExchangedFairySouls().contains(id))
            player.sendMessage("§7Go to Tia the Fairy in the §eHub§7 to exchange it for rewards!");
    }

    public static List<FairySoul> getFairySouls() {
        return new ArrayList<>(SOULS_CACHE.values());
    }

    public static FairySoul getFairySoul(int id) {
        return SOULS_CACHE.get(id);
    }

    public static void cacheFairySouls() {
        for (FairySoul soul : FairySoulDatabase.getAllSouls()) {
            if (soul.getZone() == null) {
                soul.delete();
            } else {
                SOULS_CACHE.put(soul.getId(), soul);
            }
        }
    }

    public static void spawnEntities(Instance instance) {
        getFairySouls().forEach(soul -> soul.spawnEntity(instance));
    }

    public static void spawnEntities(Instance instance, FairySoulZone zone) {
        getFairySouls().forEach(soul -> {
            if (soul.zone == zone)
                soul.spawnEntity(instance);
        });
    }

}
