package dev.xylonity.knightquest.common.item.weapons;

import dev.xylonity.knightquest.common.item.KQWeaponItem;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CleaverWeapon extends KQWeaponItem {

    private static final String TAG_PARTICLE_ANGLE = "ParticleAngle";
    private static final String TAG_PARTICLE_ACTIVE = "ParticleActive";

    public CleaverWeapon(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void interaction(Level level, Player player, InteractionHand hand) {

    }

    @Override
    public int getCooldownTicks() {
        return 100;
    }

    @Override
    public String getName() {
        return "cleaver";
    }

}
