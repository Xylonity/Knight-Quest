package net.xylonity.knightquest.common.entity.boss;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.xylonity.knightquest.config.values.KQConfigValues;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class NethermanProjectileChargeEntity extends AbstractNethermanProjectile implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private int explosionTimer;
    private boolean shouldGoDown;

    public NethermanProjectileChargeEntity(EntityType<? extends AbstractNethermanProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
        this.explosionTimer = this.random.nextInt(100) + 20;
        if (!level.isClientSide) this.shouldGoDown = this.random.nextBoolean();
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.explosionTimer--;

            if (this.explosionTimer <= 0) {
                this.explode();
            }
        }

        if (shouldGoDown && !level.isClientSide) this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.05, 0.0));
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!level.isClientSide) explode();
    }

    private void explode() {
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float) KQConfigValues.NETHERMAN_PROJECTILE_EXPLOSION_RADIUS, Explosion.BlockInteraction.NONE);
        this.discard();
    }

    @Override
    public void registerControllers(AnimationData controllers) {
        controllers.addAnimationController(new AnimationController<>(this, "rotateController", 0, this::rotatePredicate));
        controllers.addAnimationController(new AnimationController<>(this, "coreController", 0, this::corePredicate));
    }

    private PlayState corePredicate(AnimationEvent<?> event) {

        if (tickCount < 10)
            event.getController().setAnimation(new AnimationBuilder().addAnimation("summon", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
        else if (explosionTimer < 11)
            event.getController().setAnimation(new AnimationBuilder().addAnimation("die", ILoopType.EDefaultLoopTypes.PLAY_ONCE));

        return PlayState.CONTINUE;
    }

    private PlayState rotatePredicate(AnimationEvent<?> event) {

        event.getController().setAnimation(new AnimationBuilder().addAnimation("rotate", ILoopType.EDefaultLoopTypes.LOOP));

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

}
