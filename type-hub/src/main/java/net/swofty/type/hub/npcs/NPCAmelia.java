package net.swofty.type.hub.npcs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.minestom.server.coordinate.Pos;
import net.swofty.types.generic.entity.npc.NPCParameters;
import net.swofty.types.generic.entity.npc.SkyBlockNPC;
import net.swofty.types.generic.user.SkyBlockPlayer;

public class NPCAmelia extends SkyBlockNPC {

    public NPCAmelia() {
        super(new NPCParameters() {
            @Override
            public String[] holograms(SkyBlockPlayer player) {
                return new String[]{"§9Amelia", "§e§lCLICK"};
            }

            @Override
            public String signature(SkyBlockPlayer player) {
                return "QMZr7iKG5E74dUHwnnPYC36dM5pbiOx2rpjfbH3nzA+3yTntqWY873eHU/JHFXFfYwSKOufprcLqH2KOGjAfAhn7ez24bq4KKRfDWH1HJ0KYmo07gAjgZrYKTUrFf4dEN3mPwL8A2w8QeHLu/l95gEWx/eLYS8al+vhiomFyXJjmSjKLzrrIiQVrMa2Kaf9Ev9x/0UWWmNji2Jvhaf0NPRUXshWrLONXGUcgTZv3B8T1IPRGDPdw04L1kSuL92N5SZa2l216bJRzl4op2PQmSsGch/UP5g6jzMWx/GensNuw8X0ZmiFCi/UAvV6Ry3hH9Ce0S0FU6FMjbfeuq/r+6zp0UC1br2Nf9inLDUt0Fc4CHzLjD3Q5EkC/crj4EbaPvBMQ+AaN6n80LdlbxRESc/XlQ2U+anYSB9PPGn3cqOPOlLxkhWVLQ6IRxCehR73LQdoblIaKRc/VfWU7xjxhybSsO3muLcl2U8AAbRsqtuFlf3s6764lLdHluDZl992nWZYJMBysddpU9tVs/PGFscdJw1KC69Q3/UmNZO2HtW7B13sJw8JIFPvk2mqgCQvMfnkFJ/lHWfgITphpk6mWqe1IokA5ZCnqhmkWwUAxQaP9q7at2LbPO1gb5R/5XKhmakQZ7lShw8/blENqRe6qYpm3qWgBEtjdfQnZd9ZCo3k=";
            }

            @Override
            public String texture(SkyBlockPlayer player) {
                return "ewogICJ0aW1lc3RhbXAiIDogMTYwMTU1MTcxMzA0NSwKICAicHJvZmlsZUlkIiA6ICJkZTE0MGFmM2NmMjM0ZmM0OTJiZTE3M2Y2NjA3MzViYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTUlRlYW0iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQ1OTU1Y2M1YWY2ZmEzMzkwZGYzNmM5YTIzYTMyNjRjODQyNjk5MWU3NTMzYWQyZDA4MWY0MWVhMTMwYzYzYiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";
            }

            @Override
            public Pos position(SkyBlockPlayer player) {
                return new Pos(-44.5, 85, -4.5, 0, 0);
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
