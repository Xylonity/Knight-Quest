package dev.xylonity.knightquest.common.entity.boss;

import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NethermanProjectileChargeEntity extends AbstractNethermanProjectile implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int explosionTimer;
    private boolean shouldGoDown;

    public NethermanProjectileChargeEntity(EntityType<? extends AbstractNethermanProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
        this.explosionTimer = this.random.nextInt(100) + 20;
        if (!level().isClientSide) this.shouldGoDown = this.random.nextBoolean();
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.explosionTimer--;

            if (this.explosionTimer <= 0) {
                this.explode();
            }
        }

        if (shouldGoDown && !level().isClientSide) this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.05, 0.0));
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!level().isClientSide) explode();
    }

    private void explode() {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), KQConfigValues.NETHERMAN_PROJECTILE_EXPLOSION_RADIUS.get().floatValue(), Level.ExplosionInteraction.MOB);
        this.discard();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "rotateController", 0, this::rotatePredicate));
        controllers.add(new AnimationController<>(this, "coreController", 0, this::corePredicate));
    }

    private PlayState corePredicate(AnimationState<?> event) {

        if (tickCount < 10)
            event.getController().setAnimation(RawAnimation.begin().then("summon", Animation.LoopType.PLAY_ONCE));
        else if (explosionTimer < 11)
            event.getController().setAnimation(RawAnimation.begin().then("die", Animation.LoopType.PLAY_ONCE));

        return PlayState.CONTINUE;
    }

    private PlayState rotatePredicate(AnimationState<?> event) {

        event.getController().setAnimation(RawAnimation.begin().then("rotate", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
