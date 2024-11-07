package dev.xylonity.knightquest.common.api.explosiveenhancement;

import net.minecraft.world.level.Level;

public class ExplosiveConfig {

    static void spawnParticles(Level world, double x, double y, double z, float power) {
        spawnParticles(world, x, y, z, power, false, false, 1);
    }

    /**
     * Spawn Explosive Enhancement's particles while still keeping the user's config options
     *
     * @param world The world to spawn the effect in
     * @param x The effect's x coordinate
     * @param y The effect's y coordinate
     * @param z The effect's z coordinate
     * @param power The effect's size
     * @param isUnderWater Show the underwater effect
     * @param didDestroyBlocks Helps to determine the particle type for the vanilla particles
     */
    public static void spawnParticles(Level world, double x, double y, double z, float power, boolean isUnderWater, boolean didDestroyBlocks) {
        spawnParticles(world, x, y, z, power, isUnderWater, didDestroyBlocks, 1);
    }

    /**
     * Spawn Explosive Enhancement's particles while still keeping the user's config options
     * @param world The world to spawn the effect in
     * @param x The effect's x coordinate
     * @param y The effect's y coordinate
     * @param z The effect's z coordinate
     * @param power The effect's size
     * @param isUnderWater Show the underwater effect
     * @param didDestroyBlocks Helps to determine the particle type for the vanilla particles
     */
    public static void spawnParticles(Level world, double x, double y, double z, float power, boolean isUnderWater, boolean didDestroyBlocks, int phase) {
        ExplosiveHandler.spawnParticles(world, x, y, z, power, isUnderWater, didDestroyBlocks, true, phase);
    }

}
