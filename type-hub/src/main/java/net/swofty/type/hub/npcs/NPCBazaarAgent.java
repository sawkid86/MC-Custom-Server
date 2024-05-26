package net.swofty.type.hub.npcs;

import net.minestom.server.coordinate.Pos;
import net.swofty.types.generic.bazaar.BazaarCategories;
import net.swofty.types.generic.entity.npc.NPCDialogue;
import net.swofty.types.generic.entity.npc.NPCParameters;
import net.swofty.types.generic.gui.inventory.inventories.bazaar.GUIBazaar;
import net.swofty.types.generic.levels.SkyBlockLevelRequirement;
import net.swofty.types.generic.user.SkyBlockPlayer;

import java.util.stream.Stream;

public class NPCBazaarAgent extends NPCDialogue {
    public NPCBazaarAgent() {
        super(new NPCParameters() {
            @Override
            public String[] holograms(SkyBlockPlayer player) {
                return new String[]{"§6Bazaar Agent", "§e§lCLICK"};
            }

            @Override
            public String signature(SkyBlockPlayer player) {
                return "crsR20Z1FnmwTw6/+3L7dCo+DlaJ0U9GzcJvE7XDAPTFLshlnBDjBTv3wgHn1T8JHHckhy9WkBCw8E8JSyAyFZslZZorKab1v3JqYZn3yEiqe7sQoEuP9X549aiaFtPGluDFyXQHS2hh4YJ8JRJuJD70pQQqeCm/VSO2ACczr4ZaA5mkFQBoyd7Z1UD4gLtr8i+Pj5IA0DgyILw5E2lxQpWLFN2+YGM7Dd/U6Ncy3qzANLO4GGHfzY1at8HBS558UhO+m3KKlg5whs8rvterIgSJmbu2znV0pZIAUa6momUXefFqYbkLhRoLwYIlxh0ehi6cQHiAiBaMup2zvXyDxdPt3rpcTZJGG9R3skdnGeFlgLsPlT7vUd11WqNzVPUlk77s+9u4MLb3GfN4H2lBOuat+dQkl2TAa63xs0HOK+PDGa3nVt/qLte5NdZ6gVmjZ6NIJsB0BFlAZvzQ/yqwtsGyWlByHTVzlw/1Wzm4NThf/HCelT4p1B6z/1/Jsnv7B8e9d9g38o1Wnm5Tll4H/Wjkyq3yYwy9x5gpNgPQjHoKpJFEQz7/Ojm7d7Wsgs8QLuncVH+hY3giQM/d46eyijMQWxKnKEWLSIDnVwM5agcrLTKSXX3ecU/RT+Yp2BxgGxa3RGii0NjHTspVeaKGG2r4Tgq9fa+CEtqD7Og+gV8=";
            }

            @Override
            public String texture(SkyBlockPlayer player) {
                return "ewogICJ0aW1lc3RhbXAiIDogMTU4ODM0NzYxNTAxOSwKICAicHJvZmlsZUlkIiA6ICJiZWNkZGIyOGEyYzg0OWI0YTliMDkyMmE1ODA1MTQyMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdFR2IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzZjOTQ1M2I1ODllMTc3NjcwZDg2ZjNlNWQ0ZjE3MTMxMzNhZDc5MmJmOGM1NGZhNjFkMDdiNTA0NGQ1N2RiOTkiCiAgICB9CiAgfQp9";
            }

            @Override
            public Pos position(SkyBlockPlayer player) {
                return new Pos(-39.5, 70, -79, 0, 0);
            }

            @Override
            public boolean looking() {
                return true;
            }
        });
    }

    @Override
    public void onClick(PlayerClickNPCEvent e) {
        if (isInDialogue(e.player())) return;
        SkyBlockLevelRequirement lvl = e.player().getSkyBlockExperience().getLevel();
        if (lvl.asInt() >= 7) {
            new GUIBazaar(BazaarCategories.FARMING).open(e.player());
            return;
        }
        setDialogue(e.player(), "hello");
    }
    @Override
    public NPCDialogue.DialogueSet[] getDialogueSets(SkyBlockPlayer player) {
        return Stream.of(
                NPCDialogue.DialogueSet.builder()
                        .key("hello").lines(new String[]{
                                "§cYou need SkyBlock Level 7 to access this feature!"
                        }).build()
        ).toArray(NPCDialogue.DialogueSet[]::new);
    }
}
