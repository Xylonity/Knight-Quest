package dev.xylonity.knightquest.common.entity.entities;

import com.google.common.collect.Sets;
import dev.xylonity.knightquest.registry.KnightQuestEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Set;

public class SwampmanAxeEntity extends AbstractSwampmanAxeEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(SwampmanAxeEntity.class, EntityDataSerializers.INT);
    private Potion potion = Potions.EMPTY;
    private final Set<MobEffectInstance> effects = Sets.newHashSet();
    private boolean fixedColor;

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

    private void updateColor() {
        this.fixedColor = false;
        if (this.potion == Potions.EMPTY && this.effects.isEmpty()) {
            this.entityData.set(ID_EFFECT_COLOR, -1);
        } else {
            this.entityData.set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
        }

    }

    public void addEffect(MobEffectInstance pEffectInstance) {
        this.effects.add(pEffectInstance);
        this.getEntityData().set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_EFFECT_COLOR, -1);
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
            this.potion = Potions.EMPTY;
            this.effects.clear();
            this.entityData.set(ID_EFFECT_COLOR, -1);
        }

    }

    private void makeParticle(int pParticleAmount) {
        int i = this.getColor();
        if (i != -1 && pParticleAmount > 0) {
            double d0 = (double)(i >> 16 & 255) / 255.0D;
            double d1 = (double)(i >> 8 & 255) / 255.0D;
            double d2 = (double)(i & 255) / 255.0D;

            for(int j = 0; j < pParticleAmount; ++j) {
                this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), d0, d1, d2);
            }

        }
    }

    public int getColor() {
        return this.entityData.get(ID_EFFECT_COLOR);
    }

    private void setFixedColor(int pFixedColor) {
        this.fixedColor = true;
        this.entityData.set(ID_EFFECT_COLOR, pFixedColor);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.potion != Potions.EMPTY) {
            pCompound.putString("Potion", BuiltInRegistries.POTION.getKey(this.potion).toString());
        }

        if (this.fixedColor) {
            pCompound.putInt("Color", this.getColor());
        }

        if (!this.effects.isEmpty()) {
            ListTag listtag = new ListTag();

            for(MobEffectInstance mobeffectinstance : this.effects) {
                listtag.add(mobeffectinstance.save(new CompoundTag()));
            }

            pCompound.put("CustomPotionEffects", listtag);
        }

    }

    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Potion", 8)) {
            this.potion = PotionUtils.getPotion(pCompound);
        }

        for(MobEffectInstance mobeffectinstance : PotionUtils.getCustomEffects(pCompound)) {
            this.addEffect(mobeffectinstance);
        }

        if (pCompound.contains("Color", 99)) {
            this.setFixedColor(pCompound.getInt("Color"));
        } else {
            this.updateColor();
        }

    }

    protected void doPostHurtEffects(LivingEntity pLiving) {
        super.doPostHurtEffects(pLiving);
        Entity entity = this.getEffectSource();

        for(MobEffectInstance mobeffectinstance : this.potion.getEffects()) {
            pLiving.addEffect(new MobEffectInstance(mobeffectinstance.getEffect(), Math.max(mobeffectinstance.mapDuration((x) -> x / 8), 1), mobeffectinstance.getAmplifier(), mobeffectinstance.isAmbient(), mobeffectinstance.isVisible()), entity);
        }

        if (!this.effects.isEmpty()) {
            for(MobEffectInstance mobeffectinstance : this.effects) {
                pLiving.addEffect(mobeffectinstance, entity);
            }
        }

    }

    public void handleEntityEvent(byte pId) {
        if (pId == 0) {
            int i = this.getColor();
            if (i != -1) {
                double d0 = (double)(i >> 16 & 255) / 255.0D;
                double d1 = (double)(i >> 8 & 255) / 255.0D;
                double d2 = (double)(i & 255) / 255.0D;

                for(int j = 0; j < 20; ++j) {
                    this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), d0, d1, d2);
                }
            }
        } else {
            super.handleEntityEvent(pId);
        }

    }
}