package net.swofty.commons;

public enum CustomWorlds {
    ISLAND("hypixel_island"),
    HUB("hypixel_hub"),
    LOBBY("hypixel_lobby"),
    LIMBO("hypixel_limbo")
    ;

    private final String folderName;

    CustomWorlds(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return "./configuration/" + folderName;
    }
}
