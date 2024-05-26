package net.swofty.types.generic.region;

import lombok.Getter;
import lombok.SneakyThrows;
import net.swofty.commons.Songs;
import net.swofty.types.generic.region.mining.configurations.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum RegionType {
    PUBLIC_HUB("Server Hub"),
    PRIVATE_ISLAND("Your Island", "§a"),
    VILLAGE("Village", WheatAndFlowersConfiguration.class),
    MOUNTAIN("Mountain"),
    FOREST("Forest", MineLogsConfiguration.class),
    FARM("Farm", MineWheatConfiguration.class),
    RUINS("Ruins"),
    COLOSSEUM("Colosseum"),
    GRAVEYARD("Graveyard", "§c"),
    COAL_MINE("Coal Mine", MineCoalConfiguration.class),
    WILDERNESS("Wilderness", "§2", Songs.WILDERNESS),
    HIGH_LEVEL("High Level", "§4"),
    BAZAAR_ALLEY("Bazaar Alley", "§e"),
    AUCTION_HOUSE("Auction House", "§6"),
    ARCHERY_RANGE("Archery Range", "§9"),
    BANK("Bank", "§6"),
    BLACKSMITH("Blacksmith"),
    LIBRARY("Library"),
    THE_BARN("The Barn", "§b", BarnConfiguration.class),
    MUSHROOM_DESERT("Mushroom Desert"),
    GOLD_MINE("Gold Mine", "§6"),
    DEEP_CAVERN("Deep Caverns", "§b"),
    GUNPOWDER_MINES("Gunpowder Mines"),
    LAPIS_QUARRY("Lapis Quarry"),
    PIGMENS_DEN("Pigmen's Den"),
    SLIMEHILL("Slimehill"),
    BIRCH_PARK("Birch Park", "§a"),
    SPRUCE_WOODS("Spruce Woods", "§a"),
    DARK_THICKET("Dark Thicket", "§a"),
    SAVANNA_WOODLAND("Savanna Woodland", "§a"),
    JUNGLE_ISLAND("Jungle Island", "§a"),
    HOWLING_CAVE("Howling Cave"),
    DIAMOND_RESERVE("Diamond Reserve"),
    OBSIDIAN_SANCTUARY("Obsidian Sanctuary"),
    SPIDERS_DEN("Spider's Den", "§4"),
    COMMUNITY_CENTER("Community Center"),
    SPIDERS_DEN_HIVE("Spider's Den", "§4"),
    BLAZING_FORTRESS("Blazing Fortress", "§4"),
    DWARVEN_VILLAGE("Dwarven Village"),
    DWARVEN_MINES("Dwarven Mines", "§2"),
    GOBLIN_BURROWS("Goblin Burrows"),
    THE_MIST("The Mist", "§8"),
    GREAT_ICE_WALL("Great Ice Wall"),
    GATES_TO_THE_MINES("Gates to the Mines"),
    RAMPARTS_QUARRY("Rampart's Quarry"),
    FORGE_BASIN("Forge Basin"),
    THE_FORGE("The Forge"),
    CLIFFSIDE_VEINS("Cliffside Veins"),
    ROYAL_MINES("Royal Mines"),
    DIVANS_GATEWAY("Divan's Gateway"),
    FAR_RESERVE("Far Reserve"),
    THE_END("The End", "§d"),
    THE_END_NEST("The End", "§d"),
    DESERT_SETTLEMENT("Desert Settlement", "§e"),
    OASIS("Oasis"),
    ARCHAEOLOGICAL_SITE("Archaeological Site", "§a"),
    MUSHROOM_GORGE("Mushroom Gorge"),
    OVERGROWN_MUSHROOM_CAVE("Overgrown Mushroom Cave", "§2"),
    GLOWING_MUSHROOM_CAVE("Glowing Mushroom Cave", "§3"),
    BURNING_BRIDGE("Burning Bridge", "§4"),
    VOID_SEPULTURE("Void Sepulture", "§d"),
    DRAGONS_NEST("Dragon's Nest", "§5");

    private final String name;
    private final String color;
    private final SkyBlockMiningConfiguration miningHandler;
    private final List<Songs> songs;

    @SneakyThrows
    RegionType(String name, String color, Class<? extends SkyBlockMiningConfiguration> miningHandler, Songs... songs) {
        this.name = name;
        this.color = color;

        if (miningHandler != null)
            this.miningHandler = miningHandler.newInstance();
        else
            this.miningHandler = null;
        this.songs = new ArrayList<>();
    }

    RegionType(String name, String color, Class<? extends SkyBlockMiningConfiguration> miningHandler) {
        this(name, color, miningHandler, new Songs[0]);
    }

    RegionType(String name, Class<? extends SkyBlockMiningConfiguration> miningHandler) {
        this(name, "§b", miningHandler);
    }

    RegionType(String name, String color) {
        this(name, color, new Songs[0]);
    }

    RegionType(String name, String color, Songs... songs) {
        this.name = name;
        this.color = color;
        this.miningHandler = null;
        this.songs = new ArrayList<>(List.of(songs));
    }

    RegionType(String name) {
        this(name, "§b", new Songs[0]);
    }

    @SneakyThrows
    public SkyBlockMiningConfiguration getMiningHandler() {
        return miningHandler;
    }

    @Override
    public String toString() {
        return color + name;
    }

    public static RegionType getByID(int id) {
        return RegionType.values()[id];
    }
}