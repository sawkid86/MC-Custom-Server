package net.swofty.type.thefarmingislands.npcs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.minestom.server.coordinate.Pos;
import net.swofty.types.generic.entity.npc.NPCParameters;
import net.swofty.types.generic.entity.npc.SkyBlockNPC;
import net.swofty.types.generic.user.SkyBlockPlayer;

public class NPCTammy extends SkyBlockNPC {
    public NPCTammy() {
        super(new NPCParameters() {
            @Override
            public String[] holograms(SkyBlockPlayer player) {
                return new String[]{"Tammy", "§e§lCLICK"};
            }

            @Override
            public String signature(SkyBlockPlayer player) {
                return "abVlN1Rr2ZZ5E+DPk813ZptPubFtFpyCjYn7LywsabUW0rH8JWDJytBLC9X48haC8Xyu46Pp0GSmkQfqC6l5+e7CQxZSPiGyx10Ma42Drn8gylgwrI302OvkpLKZ5TzFUHpYuVW4CpGWEMssyEOVvIJFsSoDb0dplXtTwFvl/qkwzhUHzZ9yKHRh+weEj0ZkTJgxoJlverfU4SKvbHWlyRuGEgwpgm1eezz41pb/VaZSiz+Yi+TcvWn8nBxpD9zf9/h3xx2GbidL4KLzFJ7H/7chwdb9Acvx2NdYLIr7flEQjlOC3QI3kkc4zlKPsK2OcB/OL1LFxZS6vdJxHfOzmxvBjE8ZRVKLCOK8BLG4+KYMfItVgFmdI/wRYLKu6YizjPBiBRk2nDJSPMj3TGHzqT4AqV5Bwpg9DJKj4PiRsSWDBjmbXC1YBNHuGpuomVGPOkm3W8oeTSCOwuELYooV6/uxFLSNO2lD+bmyxU+uA1DMO0iC+WmfvZnXDqH0WDeMucQsUDGQhEq5t74db9iIiqExVW9EO08MnwNejGBC+h1aFnPoodQbyIO6uqqb9FGTCHl4omzQCYrG8WPUbL1QEAZkyjfXV9BU6p49S2vFKPTzBVWqKkfJ19hPJ3ca+9hAOiEbrSK5oRd51kuT/SMMm0S2SEX9uYyLhmW4F7Lk28c=";
            }

            @Override
            public String texture(SkyBlockPlayer player) {
                return "ewogICJ0aW1lc3RhbXAiIDogMTYxODI2MjU5NzI4MywKICAicHJvZmlsZUlkIiA6ICIzOTg5OGFiODFmMjU0NmQxOGIyY2ExMTE1MDRkZGU1MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJNeVV1aWRJcyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jZWRjNmU3NzJkNTBlNjM1NTZiZmRlOTE0ZTc4NWM3NTQ4OTM2MWZjYzc5ODUxNjRjNDBiMzAwZTdmOTFiYzA4IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";
            }

            @Override
            public Pos position(SkyBlockPlayer player) {
                return new Pos(284.5, 104, -544.5, -160, 0);
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
