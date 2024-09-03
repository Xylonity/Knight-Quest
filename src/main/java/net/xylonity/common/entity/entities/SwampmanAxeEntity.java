package net.xylonity.common.entity.entities;

import com.google.common.collect.Sets;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.EntityEffectParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import net.xylonity.config.values.KQConfigValues;
import net.xylonity.registry.KnightQuestEntities;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Iterator;
import java.util.Set;

public class SwampmanAxeEntity extends AbstractSwampmanAxeEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final int MAX_POTION_DURATION_TICKS = 600;
    private static final int NO_POTION_COLOR = -1;
    private static final TrackedData<Integer> COLOR;
    private static final byte PARTICLE_EFFECT_STATUS = 0;

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // Your controller registration logic
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public SwampmanAxeEntity(EntityType<? extends AbstractSwampmanAxeEntity> entityType, World world) {
        super(entityType, world);
    }

    public SwampmanAxeEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(KnightQuestEntities.SWAMPMAN_AXE, x, y, z, world, stack, shotFrom);
    }

    public SwampmanAxeEntity(World world, LivingEntity shooter) {
        super(KnightQuestEntities.SWAMPMAN_AXE, shooter, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(COLOR, -1);
    }

    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.spawnParticles(1);
                }
            } else {
                this.spawnParticles(2);
            }
        }
    }

    private void spawnParticles(int amount) {
        int i = this.getColor();
        if (i != -1 && amount > 0) {
            for (int j = 0; j < amount; ++j) {
                this.getWorld().addParticle(EntityEffectParticleEffect.create(ParticleTypes.ENTITY_EFFECT, i), this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), 0.0, 0.0, 0.0);
            }
        }
    }

    public int getColor() {
        return this.dataTracker.get(COLOR);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        if (KQConfigValues.POISON_PHASE_2_SWAMPMAN) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0, true, true, true));
        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == PARTICLE_EFFECT_STATUS) {
            int i = this.getColor();
            if (i != -1) {
                float f = (float) (i >> 16 & 255) / 255.0F;
                float g = (float) (i >> 8 & 255) / 255.0F;
                float h = (float) (i & 255) / 255.0F;

                for (int j = 0; j < 20; ++j) {
                    this.getWorld().addParticle(EntityEffectParticleEffect.create(ParticleTypes.ENTITY_EFFECT, f, g, h), this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), 0.0, 0.0, 0.0);
                }
            }
        } else {
            super.handleStatus(status);
        }
    }

    static {
        COLOR = DataTracker.registerData(SwampmanAxeEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }
}