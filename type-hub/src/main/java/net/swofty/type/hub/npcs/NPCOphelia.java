package net.swofty.type.hub.npcs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.minestom.server.coordinate.Pos;
import net.swofty.types.generic.entity.npc.NPCParameters;
import net.swofty.types.generic.entity.npc.SkyBlockNPC;
import net.swofty.types.generic.user.SkyBlockPlayer;

public class NPCOphelia extends SkyBlockNPC {

    public NPCOphelia() {
        super(new NPCParameters() {
            @Override
            public String[] holograms(SkyBlockPlayer player) {
                return new String[]{"§9Ophelia", "§e§lCLICK"};
            }

            @Override
            public String signature(SkyBlockPlayer player) {
                return "WF5PB9Dmgn0KOMtTPb2MNIZNK4/qC2sHJijsTNSlPpbzJUw6VklsuMmoPQJS9agq47p84yWIniciikZMN5ChvTgWmgCt2/m0n4UWByymYSLSARUFaVZ4Tjgc0iA5RlADTTHTTUDHXdGv3gl4bfypuo5Z+/DYZyuWZMyM8y2BmRGDgeIa1aXoLRFjFf9z7EZlXZAv1ZWLStBKybqtRP53kh8prDKFq8r9qfpr9kQnrJfDZyvgJr7ObEeuslYB0Z4B1Nh+yVCVA8TGr+BXLZ6AfEuynlEt5TW86ramZn1ZrzL2n27jzBmnoWilHc0UqnN1SD27Z0ocgHUr/8QTjYVyi9EQH/jkGAddZLjzOatNpgV28pnyXk+eI5K9ftiq85ovOGNmSQpHrSv0RBesquPAWlaxmbml6ieAEk1iK2Dqe+2RYhuA35YG5H9+L5E9rPvIm1abjSSxaCBZse1fZnXukzdgS10aefC+y7BAqN8EqHxj/UTljmZNhsW43p4wI+jp/6hbP1LPEBRVL8jg89B8J3B1+u2mALzlVRJq/nTDRrVTutDwv1H+9t8fCUJvigDHi4RkbpsfXVxdy4MCtwvH8yOHrsy4Mc64WEGs9J20NgXLcOJqvtm48mDuNv36j0D0U7uSFfAfTYZrBu7xWDc87PElSqCTgBp+kxQwXvV31yQ=";
            }

            @Override
            public String texture(SkyBlockPlayer player) {
                return "eyJ0aW1lc3RhbXAiOjE1NTUwOTgzNjY3NTYsInByb2ZpbGVJZCI6IjU3MGIwNWJhMjZmMzRhOGViZmRiODBlY2JjZDdlNjIwIiwicHJvZmlsZU5hbWUiOiJMb3JkU29ubnkiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRhYTRiODI3YTlhODVjNGVmYzE0N2ZjNTlkMGRhNjg4ZWJlN2UyNmQyNThiNTUzYzA0MWRhMmVkNmVjOGI2YjgifX19";
            }

            @Override
            public Pos position(SkyBlockPlayer player) {
                return new Pos(-85.5, 55, -139.5, 0, 0);
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
