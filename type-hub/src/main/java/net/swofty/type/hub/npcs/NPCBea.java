package net.swofty.type.hub.npcs;

import net.minestom.server.coordinate.Pos;
import net.swofty.type.hub.gui.GUIShopBea;
import net.swofty.types.generic.data.datapoints.DatapointToggles;
import net.swofty.types.generic.entity.npc.NPCDialogue;
import net.swofty.types.generic.entity.npc.NPCParameters;
import net.swofty.types.generic.user.SkyBlockPlayer;

import java.util.stream.Stream;

public class NPCBea extends NPCDialogue {

    public NPCBea() {
        super(new NPCParameters() {
            @Override
            public String[] holograms(SkyBlockPlayer player) {
                return new String[]{"Bea", "§e§lCLICK"};
            }

            @Override
            public String signature(SkyBlockPlayer player) {
                return "MPtpX6k3Fq268ck6LZRdvOY+bA8hsNnGNuJ/TlKryB1nPXuwFOHDLBLyGh4jMLC9F7LiZV/9V3/zdbG35ROJl4KHW2Fgeu+M30mc1hDjwiwt30SX7THyUusaeY3EBFpMR9pmKHpV76yT2c7nWS/uT9iLLqZuH5aaUdstXhDMaNghtixROfXZ56t2vdEMM+AqvR1MmoVZTaiazj/AibUN3di9edRWmrOWSoDskJCVuUo5VYGnUyuAqjFjqrvprxQOtBd59PHnUiNDvv1BDO0GgHUutW5nc6FQnQHnW8PTyvf1otP/6BFPCswmqziRzkUQWRCTGU2VL4iglQM40Wuxryx/bfrDs4wwQIWur1kmRKoWRcaIlnr7F52U9IXJoHP9ONYXEhf6tQnWTODPOAftezOlXzo/Z5HR5PSengUY1bxTSsnm81hRPmq5vAsFgcbFTLFcmEALA0rsosbuza2UgiFsg4MbZqu0pemY0o3EWDDNYqKBA/VlBfIG5NINVtNRTDkB12voGraVSuao4MB9oxRAwOQ1W6WNXnfaEbZqCMbFszbaMjVZlYfNBhUuIPbQvvx82R3D0ose6RXbi2qv62loPj7alCxJ0qgNFwAajTloYRn/dkZe+cflMJrZ0GgstKl7wQOjUo1B/ZxnX5v29if0eKaDG5Z/s1CjlPiRPcs=";
            }

            @Override
            public String texture(SkyBlockPlayer player) {
                return "ewogICJ0aW1lc3RhbXAiIDogMTU5NjEyNjk0ODMxMCwKICAicHJvZmlsZUlkIiA6ICJhNmE3MzI2NjZhZTI0YjIwYWQyNmIzYWZkZWZjNmM1MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJNeXN0aWNHYW1lck1hbiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lM2MzNGI5ZTUxZjM1MmQ2YzY5YzgyMWExZmMyOWY1MDA1MDk4MDNhMWVlYzAyNGMzODcwMTViNWM0M2M3NzNhIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";
            }

            @Override
            public Pos position(SkyBlockPlayer player) {
                return new Pos(31.5, 70, -94.5, 0, 0);
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
        DatapointToggles.Toggles toggle = e.player().getToggles();

        if (!toggle.get(DatapointToggles.Toggles.ToggleType.HAS_SPOKEN_TO_BEA)) {
            setDialogue(e.player(), "hello");
            toggle.set(DatapointToggles.Toggles.ToggleType.HAS_SPOKEN_TO_BEA, true);
            return;
        }

        new GUIShopBea().open(e.player());
    }

    @Override
    public DialogueSet[] getDialogueSets(SkyBlockPlayer player) {
        return Stream.of(
                NPCDialogue.DialogueSet.builder()
                        .key("hello").lines(new String[]{
                                "Hello! Do you have a pet?",
                                "Pets are little companions for your adventures in SkyBlock!",
                                "Personally, I prefer the §ebee §fpet!"
                        }).build()
        ).toArray(NPCDialogue.DialogueSet[]::new);
    }
}
