package net.swofty.type.thefarmingislands.npcs;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.minestom.server.coordinate.Pos;
import net.swofty.types.generic.entity.npc.NPCParameters;
import net.swofty.types.generic.entity.npc.SkyBlockNPC;
import net.swofty.types.generic.user.SkyBlockPlayer;

public class NPCTrevorTheTrapper extends SkyBlockNPC {
    public NPCTrevorTheTrapper() {
        super(new NPCParameters() {
            @Override
            public String[] holograms(SkyBlockPlayer player) {
                return new String[]{"Trevor The Trapper", "§e§lCLICK"};
            }

            @Override
            public String signature(SkyBlockPlayer player) {
                return "QoIYLQ7lIDDC4ujL6ZM2UjGlCxlz8eIf1DRQK/RSrOSOnP0Yy+D2YVS4+GdB585ZN5BfAOKFOY3xxwq0EkfZAbV03yF1eES401WMIp3RDYFskiTMAdZtS+naoZiPoL2RUbtnv6qOO7KeNwI7OZCM5jH6lIfrrducqcbL4CR7OLUFnB15KMdx6R8akC6eXNOU2PhNYyWXnJiAljilZq97bYkIYXkTetsv7Y6AX1NJWiFDPMy7cSsVZsJFHaOyJB4iyla9wTFvkeJR27EL3Ayal5a3ogbU7vBuC+njOny+63630WGCEpFZkpQOGxuzgjOPOXnvpy/cIi9R99RufUXNlArwzz7vkVq+azcha+WRvPJLEXSubfRTjvYGmxW+b5EORa3wVt+22aLpI4S0uVFgtFeqyOvpmMy85PTCft7nGi+miGFKaOE0kDDzC3mSuSTGnWnBxU03zMFprQCknBvwpz7hP3FfPbLf3rPTQxK02Za5DFb0RwA7NjqRHgJ6PwykK0YujDSaFl/6QKxxth4Pg0FkOiJezBxJjgO+oARPYVu7cCKk6ytV6m99O+IZ57/8lOQ7r6h/CKNY2hr1jpLw9Upvi14AIyIKY5FLdbLUZ5kzAdksGZ2ITm8D+4Sfby8Mb+nErP06qqZPLU4Tno5Of96DFKrRIkBlRDLQjtnlbHk=";
            }

            @Override
            public String texture(SkyBlockPlayer player) {
                return "ewogICJ0aW1lc3RhbXAiIDogMTYxNzgxMTUyOTk0MCwKICAicHJvZmlsZUlkIiA6ICJhMjk1ODZmYmU1ZDk0Nzk2OWZjOGQ4ZGE0NzlhNDNlZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJWaWVydGVsdG9hc3RpaWUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjEwMmY4MjE0ODQ2MWNlZDFmN2I2MmUzMjZlYjJkYjNhOTRhMzNjYmE4MWQ0MjgxNDUyYWY0ZDhhZWNhNDk5MSIKICAgIH0KICB9Cn0=";
            }

            @Override
            public Pos position(SkyBlockPlayer player) {
                return new Pos(281.5, 104, -542.5, 180, 0);
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
