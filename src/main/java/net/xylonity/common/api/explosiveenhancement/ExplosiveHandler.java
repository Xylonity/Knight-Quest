package net.xylonity.common.api.explosiveenhancement;

import net.minecraft.world.World;
import net.xylonity.registry.KnightQuestParticles;

import java.util.Random;

public class ExplosiveHandler {
    public static void spawnParticles(World world, double x, double y, double z, float power, boolean isUnderWater, boolean didDestroyBlocks, boolean isImportant, int phase) {

        if(isUnderWater) {
            power = ExplosiveValues.dynamicUnderwater ? power : 4;
        } else {
            power = ExplosiveValues.dynamicSize ? power : 4;
        }
        y = ExplosiveValues.attemptBetterSmallExplosions && power == 1 ? y + ExplosiveValues.smallExplosionYOffset : y;
        isImportant = isImportant || ExplosiveValues.alwaysShow;
        float blastwavePower = power * 1.75f;
        float fireballPower = power * 1.25f;
        float smokePower = power * 0.4f;
        if (isUnderWater) {
            if(ExplosiveValues.showUnderwaterBlastWave) {
                world.addParticle(KnightQuestParticles.UNDERWATERBLASTWAVE.get(), isImportant, x, y + 0.5, z, blastwavePower, 0, 0);
            }
            if(ExplosiveValues.showShockwave) {
                world.addParticle(KnightQuestParticles.SHOCKWAVE.get(), isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
            } else if (ExplosiveValues.showUnderwaterSparks) {
                world.addParticle(KnightQuestParticles.BLANK_SHOCKWAVE.get(), isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
            }
            for(int total = ExplosiveValues.bubbleAmount; total >= 1; total--) {
                world.addParticle(KnightQuestParticles.BUBBLE.get(), isImportant, x, y, z, nextBetween(1, 7) * 0.3 * nextBetween(-1, 1), nextBetween(1, 10) * 0.1, nextBetween(1, 7) * 0.3 * nextBetween(-1, 1));
            }
        } else {

            if(ExplosiveValues.showBlastWave) {
                if (phase == 0)
                    world.addParticle(KnightQuestParticles.REDBLASTWAVE.get(), isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
                else if (phase == 1)
                    world.addParticle(KnightQuestParticles.BLUEBLASTWAVE.get(), isImportant, x, y, z, blastwavePower, 0, 0);
                else
                    world.addParticle(KnightQuestParticles.BLASTWAVE.get(), isImportant, x, y, z, blastwavePower, 0, 0);
            }
            if(ExplosiveValues.showFireball) {
                if (phase == 0)
                    world.addParticle(KnightQuestParticles.REDFIREBALL.get(), isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
                else if (phase == 1)
                    world.addParticle(KnightQuestParticles.BLUEFIREBALL.get(), isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
                else
                    world.addParticle(KnightQuestParticles.FIREBALL.get(), isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
            } else if (ExplosiveValues.showSparks) {
                world.addParticle(KnightQuestParticles.BLANK_FIREBALL.get(), isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
            }
            if(ExplosiveValues.showMushroomCloud && phase == 2) {
                world.addParticle(KnightQuestParticles.SMOKE.get(), isImportant, x, y, z, power, power * 0.25, 0);
                world.addParticle(KnightQuestParticles.SMOKE.get(), isImportant, x, y, z, power, smokePower, 0);

                world.addParticle(KnightQuestParticles.SMOKE.get(), isImportant, x, y, z, 0.15, smokePower, power);
                world.addParticle(KnightQuestParticles.SMOKE.get(), isImportant, x, y, z, -0.15, smokePower, power);
                world.addParticle(KnightQuestParticles.SMOKE.get(), isImportant, x, y, z, power, smokePower, 0.15);
                world.addParticle(KnightQuestParticles.SMOKE.get(), isImportant, x, y, z, power, smokePower, -0.15);
            }
        }
    }

    private static int nextBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt(min, max);
    }

}