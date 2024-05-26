package net.swofty.type.hub.npcs;

import net.minestom.server.coordinate.Pos;
import net.swofty.types.generic.entity.npc.NPCParameters;
import net.swofty.types.generic.entity.npc.SkyBlockNPC;
import net.swofty.types.generic.gui.inventory.inventories.auction.GUIAuctionHouse;
import net.swofty.types.generic.user.SkyBlockPlayer;

public class NPCAuctionAgent3 extends SkyBlockNPC {
    public NPCAuctionAgent3() {
        super(new NPCParameters() {
            @Override
            public String[] holograms(SkyBlockPlayer player) {
                return new String[]{"§6Auction Agent", "§e§lCLICK"};
            }

            @Override
            public String signature(SkyBlockPlayer player) {
                return "JCjHwKZ4NdNHwiEPWQ8WES3g5oMbLIPt7m7nwWR+Zqm3tUEmtubxQvzkVhT7yaKzPJednqeP1tIjehXX43CV9O4Tn0TheruzjnjyI7ohWUItcP4pFJRW/pCGFkVtZ+Y2brULYDAG5+hGrVK/3TkFJAUIaEwZ9uPw6hBzBpBVSR7Ch1HcjI9vvADAnkrOp/Ohn/lQ2RffSc/pjE5/LN+7IFqwYeD9PuHCQx8fOLeETZqCczrhzGjyFQNYmkdCUnyw8EB16sOeNW0aDLAHQgC1ezXxouaaj0BWXB5DPUNBBcCC6U0h2qQSvB7vLPANI4DgDs2OGPKO4S8m4QIN+JN7as4kITxqzjAiGT9r4pqkG5z2HAZr7IZTxBfQislVB33Clpv8ofpRGLbJOTlUzMU1vHTH425UA+zHw6XjIPQFcXKvuxhEPQ6nkQVFSV82G2/cHldPXe1WOvrcjNw+ZtErsDVvtkMDxbET86Nd2Aoj//GeMjVebJaY+ikLGSxbEplfxolhtWprsM+X1q9PtVYcFx4Qg2W7ithzq2IUaeQdKnbIHq9wKL6mxBnOes9t02LM4b+VGfPjGfXsxlM/f4hRUCG9lg2zUa8XBIuEi5zdtVz1plSiIHj3SnjgDCs94KaiHBkKwZPDSzQ7xFHfNytoLy46AlPqt6IgFnm/zKiE3Yc=";
            }

            @Override
            public String texture(SkyBlockPlayer player) {
                return "ewogICJ0aW1lc3RhbXAiIDogMTU5OTQ1NDk3MjcxNCwKICAicHJvZmlsZUlkIiA6ICJjNTBhZmE4YWJlYjk0ZTQ1OTRiZjFiNDI1YTk4MGYwMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJOb3Rab2duaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84ZTJlMTgyZTI0ZjNlMzY2NTMzOGRiZGM2MmUyNDU1MWE1Y2NjNDExOWZlZjNiNWY4ZDRlYTRlOWVkZjQ1MGMxIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";
            }

            @Override
            public Pos position(SkyBlockPlayer player) {
                return new Pos(-31, 73, -85.5, 180, 0);
            }

            @Override
            public boolean looking() {
                return true;
            }
        });
    }

    @Override
    public void onClick(PlayerClickNPCEvent e) {
        new GUIAuctionHouse().open(e.player());
    }
}
