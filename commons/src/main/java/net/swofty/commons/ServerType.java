package net.swofty.commons;

import lombok.Getter;
@Getter
public enum ServerType {
    LOBBY,
    ISLAND,
    HUB,
    THE_FARMING_ISLANDS,
    LIMBO;

    public static boolean isServerType(String type) {
        for (ServerType a : values())
            if (type.equalsIgnoreCase(a.name())) return true;

        return false;
    }
}
