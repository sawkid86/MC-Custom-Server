package net.swofty.types.generic.particle;

import lombok.Getter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.network.packet.server.play.ParticlePacket;
import net.minestom.server.particle.Particle;
import net.minestom.server.timer.TaskSchedule;
import net.swofty.types.generic.SkyBlockConst;
import net.swofty.types.generic.particle.shapes.ParticleShape;
import net.swofty.types.generic.user.SkyBlockPlayer;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public class ParticleEngine
{
      private int particleID;
      private Pos position;
      private Vec offsets;
      private int particleCount;
      private ParticleShape shape;

      private Duration displayTime;

      public ParticleEngine() {
            // Setting default values
            particleID = Particle.SPLASH.id();
            offsets = Vec.ZERO;
            position = new Pos(0, 0, 0);
            shape = null;
            particleCount = 1;
            displayTime = Duration.ofSeconds(3);
      }

      public ParticleEngine pos(Pos pos) {
            this.position = pos;
            return this;
      }

      public ParticleEngine type(Particle particle) {
            this.particleID = particle.id();
            return this;
      }

      public ParticleEngine duration(Duration dur) {
            this.displayTime = dur;
            return this;
      }

      public ParticleEngine offsets(Vec offsets) {
            if (shape != null)
                  throw new IllegalStateException("Cannot add a offset to the particle if there is a pre-set shape!");
            this.offsets = offsets;
            return this;
      }

      public ParticleEngine shape(ParticleShape shape) {
            if (offsets != Vec.ZERO)
                  System.out.println("[WARNING] Particle offsets overridden by shape preset.");

            this.shape = shape;
            return this;
      }

      /**
       * Shows the particle to TARGET
       * @param target TARGET
       */
      @SuppressWarnings("deprecation")
      public void show(SkyBlockPlayer target) {
            if (shape == null) {
                  // If there is no shape just send it
                  target.sendPacket(new ParticlePacket(
                          this.particleID,
                          false, // Long distance must always be false to not cause lag
                          position.x(),
                          position.y(),
                          position.z(),
                          (float) offsets.x(),
                          (float) offsets.y(),
                          (float) offsets.z(),
                          0f,
                          this.particleCount,
                          null
                  ));
                  return;
            }

            AtomicLong ticks = new AtomicLong(displayTime.getSeconds() * 20);
            shape.setParticleID(particleID);
            MinecraftServer.getSchedulerManager().submitTask(() -> {
                  if (ticks.get() <= 0) {
                        return TaskSchedule.stop();
                  }
                  shape.internalUpdate(this.position);
                  target.sendPackets(shape.getPacketsAsSendable());

                  ticks.getAndDecrement();
                  return TaskSchedule.tick(1);
            });
      }

      /**
       * Shows the particle to every player online
       */
      public void show() {
            SkyBlockConst.getInstanceContainer().getPlayers().forEach(player -> show((SkyBlockPlayer) player));
      }
}
