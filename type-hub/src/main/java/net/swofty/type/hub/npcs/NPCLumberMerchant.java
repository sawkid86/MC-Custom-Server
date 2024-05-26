package net.swofty.type.hub.npcs;

import net.minestom.server.coordinate.Pos;
import net.swofty.type.hub.gui.GUIShopLumberMerchant;
import net.swofty.types.generic.data.datapoints.DatapointToggles;
import net.swofty.types.generic.entity.npc.NPCDialogue;
import net.swofty.types.generic.entity.npc.NPCParameters;
import net.swofty.types.generic.user.SkyBlockPlayer;

public class NPCLumberMerchant extends NPCDialogue {
    public NPCLumberMerchant() {
        super(new NPCParameters() {
            @Override
            public String[] holograms(SkyBlockPlayer player) {
                return new String[]{"Lumber Merchant", "§e§lCLICK"};
            }

            @Override
            public String signature(SkyBlockPlayer player) {
                return "lwPRbrzB8SaKqLXaKIHWZlaCObG1stxtEyq79YYpJpz+YwUN+L9vnpjRatgjUIrjTBP+MRXaijlXbmq56FO7lC2O+6731YNjw3DNQfjrei93CZw7jzKGrydRhV8q9AEttwrt6TpiFXdLfhbV1Oh2Em/uvBVwNGLSkntlAf4r0x1HELPVfs/hHS435zCr5Lf4Ja0ko797HYhbtO8UheQUXsueXkuorhMLtuFg+OpE5hxU0/53X2MtVgoF9f1OAz2qFO6B+h7eqWpouim2P3PvnZNZCWFz2cEMJ2yzI8R4EeNnd9cKj+qWik8BKO2BiMJ8ydmAfvlDr7Qq/yUXufUTysffbeLTj4Q6CcDESz3/c8Tr2l2V52eTu/d8bMWIYhyezG2pk/0UCvBhro8eOluDXzEMamClqv7u1Aqj/E2TQcGS/zjx37Vqia635m7gC659sMLltQuVyiRKcD43Xq/Vd9UPAEKNVCgPQRbE8pzA4gTszPNLOdU521F8O6xG9UygYsqyeMJtr04n+FKOMJ8qoQxHP6RLUIcDS9z6JD9x6cww9n3nUPtl5XPelmEqQZTUb/Ijo+vXAT4Iu3pp3xxKHFBHkKaRoKvo/X0B9Zidlb59Te2b9uOwbl1II9gRtW3fuOKVJyjIIViuftMSDiysGCjrTM77sxKXLF5FbIc2AnY=";
            }

            @Override
            public String texture(SkyBlockPlayer player) {
                return "ewogICJ0aW1lc3RhbXAiIDogMTU4ODQyNjI5ODI1NywKICAicHJvZmlsZUlkIiA6ICIyM2YxYTU5ZjQ2OWI0M2RkYmRiNTM3YmZlYzEwNDcxZiIsCiAgInByb2ZpbGVOYW1lIiA6ICIyODA3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y5ODhiNTRkMTFiOWVkMWMwZGI2ZmQ4NWJkM2Y4ZDNjNDIxY2RmZWM2ZmNjN2I0MzdlMTYwMmJjOGZjNWE4NzAiCiAgICB9CiAgfQp9";
            }

            @Override
            public Pos position(SkyBlockPlayer player) {
                return new Pos(-49.5, 70, -68.5, -90, 0);
            }

            @Override
            public boolean looking() {
                return true;
            }
        });
    }

    @Override
    public void onClick(PlayerClickNPCEvent e) {
        SkyBlockPlayer player = e.player();
        if (isInDialogue(player)) return;
        boolean hasSpokenBefore = player.getToggles().get(DatapointToggles.Toggles.ToggleType.HAS_SPOKEN_TO_LUMBER_MERCHANT);

        if (!hasSpokenBefore) {
            setDialogue(player, "hello").thenRun(() -> {
                player.getToggles().set(DatapointToggles.Toggles.ToggleType.HAS_SPOKEN_TO_LUMBER_MERCHANT, true);
            });
            return;
        }

        new GUIShopLumberMerchant().open(player);
    }

    @Override
    public NPCDialogue.DialogueSet[] getDialogueSets(SkyBlockPlayer player) {
        return new NPCDialogue.DialogueSet[] {
                NPCDialogue.DialogueSet.builder()
                        .key("hello").lines(new String[]{
                                "Buy and sell wood and axes with me!",
                                "Click me again to open the Lumberjack Shop!"
                        }).build(),
        };
    }
}
