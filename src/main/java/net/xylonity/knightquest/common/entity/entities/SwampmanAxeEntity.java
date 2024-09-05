package net.xylonity.knightquest.common.entity.entities;

import com.google.common.collect.Sets;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.xylonity.knightquest.config.values.KQConfigValues;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Set;

public class SwampmanAxeEntity extends AbstractSwampmanAxeEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(SwampmanAxeEntity.class, EntityDataSerializers.INT);
    private final Set<MobEffectInstance> effects = Sets.newHashSet();

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public SwampmanAxeEntity(EntityType<? extends AbstractSwampmanAxeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SwampmanAxeEntity(Level pLevel, LivingEntity pShooter) {
        super(KnightQuestEntities.SWAMPMAN_AXE.get(), pShooter, pLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(ID_EFFECT_COLOR, -1);
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.makeParticle(1);
                }
            } else {
                this.makeParticle(2);
            }
        } else if (this.inGround && this.inGroundTime != 0 && !this.effects.isEmpty() && this.inGroundTime >= 600) {
            this.level().broadcastEntityEvent(this, (byte)0);
            this.effects.clear();
            this.entityData.set(ID_EFFECT_COLOR, -1);
        }

    }

    private void makeParticle(int pParticleAmount) {
        int i = this.getColor();
        if (i != -1 && pParticleAmount > 0) {
            for (int j = 0; j < pParticleAmount; j++) {
                this.level()
                        .addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, i), this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), 0.0, 0.0, 0.0);
            }
        }
    }

    public int getColor() {
        return this.entityData.get(ID_EFFECT_COLOR);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity pLiving) {
        super.doPostHurtEffects(pLiving);
        Entity entity = this.getEffectSource();

        if (!this.effects.isEmpty()) {
            for(MobEffectInstance mobeffectinstance : this.effects) {
                pLiving.addEffect(mobeffectinstance, entity);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        if (entity instanceof LivingEntity livingEntity && KQConfigValues.POISON_PHASE_2_SWAMPMAN) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0, true, true, true));
        }
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 0) {
            int i = this.getColor();
            if (i != -1) {
                float d0 = (float)(i >> 16 & 0xFF) / 255.0F;
                float d1 = (float)(i >> 8 & 0xFF) / 255.0F;
                float d2 = (float)(i & 0xFF) / 255.0F;

                for(int j = 0; j < 20; ++j) {
                    this.level().addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, d0, d1, d2), this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), d0, d1, d2);
                }
            }
        } else {
            super.handleEntityEvent(pId);
        }

    }
}