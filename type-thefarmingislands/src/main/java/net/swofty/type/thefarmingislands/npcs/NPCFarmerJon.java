package net.swofty.type.thefarmingislands.npcs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.minestom.server.coordinate.Pos;
import net.swofty.types.generic.entity.npc.NPCParameters;
import net.swofty.types.generic.entity.npc.SkyBlockNPC;
import net.swofty.types.generic.user.SkyBlockPlayer;

public class NPCFarmerJon extends SkyBlockNPC {
    public NPCFarmerJon() {
        super(new NPCParameters() {
            @Override
            public String[] holograms(SkyBlockPlayer player) {
                return new String[]{"Farmer Jon", "§e§lCLICK"};
            }

            @Override
            public String signature(SkyBlockPlayer player) {
                return "mMYzzBenXtp5+yXMe+LfMOcdM10S4syIrBZEXy4EkmbPyCGNSCvd2MxUZbF78Fm8oFsleUHdQDqXIGKgW2FaI7krPhIgGYV48txZUqNE/0FVNwQK6I0Ld/uxWnQxb3CWpdU/4A3HAhtShzlbeLiFqwpZzmkqH+NzGzldW4rRTidc1OG4lQnWoDcr1mM5nv3/7ZwDFLEGK//QoW0qTwQN47FWyEHd6TDZ7lrAes/AlbW9WS/XeNEio97TXaYshggXBMksq4umoWULC9nvN+0Nub2tdI7g1yDw6GRyMSS0CIYaJdsVgpJYwHOz5/IBU1tIRhOvk/vCIzjRpWLpqeqNgGv/ErYtkwbxnPzC4KgOkjvr/2UUYmfV2WMOo8fDVyKIHVaptQn4hszPxWHTgAO8hFV2SKWMVtygjywxGWhs4i0+gJZMkUm784F/oHQOM2vepyGbpfgrhzt1zVwbsXw0eYrsEkc7EPgcx8iS9eUUvN9xDZzLcLrjab0iJ9fBiLgcZk0B+maxSIIPdu7iewuI622j1y7DHRriRpKRNqBPwpVLZvvHw6kGWlf9TdxRJecta8i8BlfdqcC4LKkhLIw19vwVJidYCTWUAndyzSE4rYnpLrOPQc+7vBR2/oRahLsc7AsxvGI+W0qw82LnEGrbqYCHu1O45lfPvY3pBcVkurA=";
            }

            @Override
            public String texture(SkyBlockPlayer player) {
                return "ewogICJ0aW1lc3RhbXAiIDogMTYxMjQ3MzI5NDY2MSwKICAicHJvZmlsZUlkIiA6ICJkYmNlZjMyZjI5ZDc0Y2UzOTUzOWMwYjBhMTE1YjZiZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJyYW1waXJlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzI4MjkwNGQzYzRmY2U4MjkxNzE4ZGFlOTA5OTVlMTM3Mzg3MWNiNjllMjI4Y2MyMDQ0OTU2MjBhMWEwZWM1MGEiCiAgICB9CiAgfQp9";
            }

            @Override
            public Pos position(SkyBlockPlayer player) {
                return new Pos(167.5, 91, -598, 0, 0);
            }

            @Override
            public boolean looking() {
                return true;
            }
        });
    }

    @Override
    public void onClick(PlayerClickNPCEvent e) {
        e.player().sendMessage(Component.text("§cThis Feature is not there yet. §aOpen a Pull request HERE to get it added quickly!")
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Swofty-Developments/HypixelSkyBlock")));
    }
}
