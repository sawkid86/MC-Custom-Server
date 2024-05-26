package net.swofty.types.generic.command.commands;

import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;
import net.minestom.server.instance.SharedInstance;
import net.swofty.types.generic.command.CommandParameters;
import net.swofty.types.generic.command.SkyBlockCommand;
import net.swofty.types.generic.structure.structures.IslandPortal;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.types.generic.user.categories.Rank;

@CommandParameters(aliases = "structure",
        description = "Places a test debug structure",
        usage = "/structure",
        permission = Rank.ADMIN,
        allowsConsole = false)
public class PlaceStructureCommand extends SkyBlockCommand {
    @Override
    public void run(MinestomCommand command) {
        ArgumentInteger rotation = ArgumentType.Integer("rotation");

        command.addSyntax((sender, context) -> {
            if (!permissionCheck(sender)) return;

            SkyBlockPlayer player = (SkyBlockPlayer) sender;

            IslandPortal portal = new IslandPortal(context.get(rotation),
                    player.getPosition().blockX(),
                    player.getPosition().blockY(),
                    player.getPosition().blockZ());

            portal.setType(IslandPortal.PortalType.HUB);
            portal.build((SharedInstance) player.getInstance());
        }, rotation);
    }
}
