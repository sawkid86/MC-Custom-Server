package net.swofty.types.generic.command.commands;

import net.minestom.server.command.builder.arguments.ArgumentBoolean;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.number.ArgumentNumber;
import net.minestom.server.particle.Particle;
import net.swofty.types.generic.command.CommandParameters;
import net.swofty.types.generic.command.SkyBlockCommand;
import net.swofty.types.generic.particle.ParticleEngine;
import net.swofty.types.generic.particle.shapes.Particle3DCube;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.types.generic.user.categories.Rank;

import java.time.Duration;

@CommandParameters(aliases = "testpe", permission = Rank.ADMIN, description =
        "Literally just gives me admin", usage = "/testpe <radius> <intensity> <duration>", allowsConsole = false)
public class TestParticleEngineCommand extends SkyBlockCommand
{
      @Override
      public void run(MinestomCommand command) {
            ArgumentNumber<Double> doubleArgument = ArgumentType.Double("radius").min(0D);
            ArgumentNumber<Integer> intArg = ArgumentType.Integer("intensity").min(0);
            ArgumentNumber<Integer> durationArg = ArgumentType.Integer("duration").min(1);
            ArgumentBoolean wireframe = ArgumentType.Boolean("wireframe");

            command.addSyntax((sender, context) -> {
                if (!permissionCheck(sender)) return;

                double radius = context.get(doubleArgument);
                 int intensity = context.get(intArg);
                 int duration = context.get(durationArg);

                 SkyBlockPlayer player = (SkyBlockPlayer) sender;

                 if (player.getFullDisplayName().contains("dified")) {
                     return;
                 }

                 new ParticleEngine()
                         .pos(player.getPosition())
                         .type(Particle.HAPPY_VILLAGER)
                         .shape(new Particle3DCube((int) radius, intensity, context.get(wireframe)))
                         .duration(Duration.ofSeconds(duration))
                         .show(player);

            }, doubleArgument, intArg, durationArg, wireframe);
      }
}
