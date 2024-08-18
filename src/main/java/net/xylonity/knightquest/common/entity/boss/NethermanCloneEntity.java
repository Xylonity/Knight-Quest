package net.xylonity.knightquest.common.entity.boss;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.xylonity.knightquest.common.api.explosiveenhancement.ExplosiveConfig;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class NethermanCloneEntity extends Monster implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private boolean shouldExplode = true;
    private int shouldDieNextTick = 0;

    public NethermanCloneEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 0.5F)
                .add(Attributes.ATTACK_DAMAGE, 8f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.80f)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 5.0).build();
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.isExplosion()) {
            return false;
        }

        return super.hurt(pSource, pAmount);
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.4f, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();

        if (isDeadOrDying()) {
            if (shouldExplode) {
                if (level.isClientSide) {
                    this.createCustomExplosionParticles();
                }
                explode();
                shouldExplode = !shouldExplode;
            }
            shouldDieNextTick++;
        }

        if (shouldDieNextTick == 2) {
            this.remove(RemovalReason.KILLED);
        }

    }

    private void createCustomExplosionParticles() {
        ExplosiveConfig.spawnParticles(level, getX(), getY(), getZ(), 2, false, false, 1);
    }

    private void explode() {
        if (!this.level.isClientSide) {
            this.level.explode(this, DamageSource.GENERIC, null, this.getX(), this.getY(), this.getZ(), 2F, false, Explosion.BlockInteraction.NONE);
            level.playSound(null, getOnPos(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1f, 1f);
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
        animationData.addAnimationController(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
    }

    private <E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event) {

        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation((new AnimationBuilder()).addAnimation("attack", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        }

        if (this.dead) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", ILoopType.EDefaultLoopTypes.LOOP));
        }

        return PlayState.CONTINUE;
    }

}