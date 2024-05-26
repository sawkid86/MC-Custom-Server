package net.swofty.type.hub.npcs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.minestom.server.coordinate.Pos;
import net.swofty.types.generic.entity.npc.NPCParameters;
import net.swofty.types.generic.entity.npc.SkyBlockNPC;
import net.swofty.types.generic.user.SkyBlockPlayer;

public class NPCMaxwell extends SkyBlockNPC {

    public NPCMaxwell() {
        super(new NPCParameters() {
            @Override
            public String[] holograms(SkyBlockPlayer player) {
                return new String[]{"§9Maxwell", "§e§lCLICK"};
            }

            @Override
            public String signature(SkyBlockPlayer player) {
                return "CALy0nHX/3+BBjSHm3DEfAUEkDJKHU+jgvi3iS9Z3SCxwTGUaP9DiL0Izyc2MJth3B4Qxe9CwYzJ2l5KFBpHu52KTGWyv/cwyJn4GxZ+rDeOkCqeK1aWN7hzOQsZH1kYQ5Xf/KgeF0oIhOgCpexYtIUsU+bERMcl4QCVeogZ6Ewzvyux3tdLTw/TjYDpuAgW+594QtcjKYKE1lFb51DrHxl/c38nddaAPi8Ss4rPe/O3gUp5NVLNnaJ3+hZ8rsOhkEk3YwFCEBLMZ2xlAFm3s8yyILCzT4huZ8lqmZDj3xUM5bFKwl+iGdjfvZyrZUDNr/+zf2ZwhGllzIfttWm6y8xwzwv6JIqu/XTo+taohn7MPZGmJVt0nkrpmrXNBeZAK/g6pvJzNvqiNugXIaokcLsNYzTSKsV++/qbz7ZIaWqsImB7efH6/7fcz30JUga+wdnOMauISONzjvsQOzcGKHTHpaNP402IBjVa3hu0lIDxLwfhm4JYa/RfNGdQwJV8L3G7Vu3EdGMmu28MhfRtmge8CTI5cCf5Jt40HSr4rlTPM+PG2uq6iruOCoK1vtNKbqJubcuNO0AYWfXInlHB/NzrwZ9blemPe4/Lmack8skjrsTxzoM/X6Ze3cBIvOCLtUN8s+v0V4RAkyiA38eR4Z6cLNqVLiGbc3zCTBic34s=";
            }

            @Override
            public String texture(SkyBlockPlayer player) {
                return "ewogICJ0aW1lc3RhbXAiIDogMTY0OTM1MTIyNzU1NywKICAicHJvZmlsZUlkIiA6ICIwNjNhMTc2Y2RkMTU0ODRiYjU1MjRhNjQyMGM1YjdhNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJkYXZpcGF0dXJ5IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQ0Y2RmNGE4NDJjMWRjZTNhZDJjZmFlNDAzNjZlNTM5ZTlmNGYwNTdhMzliZGI3NzdlM2MxOWYyZWE2ODZlODIiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";
            }

            @Override
            public Pos position(SkyBlockPlayer player) {
                return new Pos(46.5, 69, -34.5, 180, 0);
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
