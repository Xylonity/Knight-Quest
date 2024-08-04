package net.xylonity.common.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.random.Random;

public class ParticleGenerator {

    public static void specialAttackParticles(Entity entity, int particleCount, double particleSpeed, double maxRadius, double ySpeed, ParticleEffect particleType) {

        Random random = entity.getWorld().getRandom();

        for (int i = 0; i < particleCount; i++) {
            if (random.nextInt(2) == 0) {
                double baseAngle = 2 * Math.PI * i / particleCount;

                double angleVariation = (random.nextDouble() - 0.5) * (Math.PI / 8);
                double angle = baseAngle + angleVariation;

                double radiusVariation = 1 + (random.nextDouble() - 0.5) * 0.5;
                double radius = maxRadius * radiusVariation;

                double offsetX = radius * Math.cos(angle);
                double offsetZ = radius * Math.sin(angle);

                double velocityVariation = (random.nextDouble() - 0.5) * 0.1;
                double velocityX = (particleSpeed + velocityVariation) * Math.cos(angle);
                double velocityZ = (particleSpeed + velocityVariation) * Math.sin(angle);

                double heightVariation = (random.nextDouble() - 0.5) * 0.5;

                double particleX = entity.getX() + offsetX;
                double particleY = entity.getY() + 0.2 + heightVariation;
                double particleZ = entity.getZ() + offsetZ;

                entity.getWorld().addParticle(particleType, particleX, particleY, particleZ, velocityX, ySpeed, velocityZ);
            }
        }
    }

}
