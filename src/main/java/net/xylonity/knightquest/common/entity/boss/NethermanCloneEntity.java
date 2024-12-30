package net.xylonity.knightquest.common.entity.boss;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.xylonity.knightquest.common.ai.navigator.GroundNavigator;
import net.xylonity.knightquest.common.item.KQFullSetChecker;
import net.xylonity.knightquest.common.material.KQArmorMaterials;
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

public class NethermanCloneEntity extends Monster implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public NethermanCloneEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level pLevel) {
        return new GroundNavigator(this, pLevel);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 0.1F)
                .add(Attributes.ATTACK_DAMAGE, 8f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 1f)
                .add(Attributes.FOLLOW_RANGE, 100.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 5.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.4f, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void die(@NotNull DamageSource pDamageSource) {
        super.die(pDamageSource);

        for (Player player : this.level.players()) {
            if (player instanceof ServerPlayer serverPlayer) {
                for (int u = 0; u < 30; ++u) {
                    double speed = 0.5 + this.getRandom().nextDouble() * 0.2;
                    double x = this.getX() + (this.getRandom().nextDouble() - 0.5) * 0.2;
                    double y = this.getY() + this.getEyeHeight() + (this.getRandom().nextDouble() - 0.5) * 0.2;
                    double z = this.getZ() + (this.getRandom().nextDouble() - 0.5) * 0.2;

                    Vec3 look = this.getLookAngle();
                    double vx = look.x * speed;
                    double vy = look.y * speed;
                    double vz = look.z * speed;

                    serverPlayer.connection.send(new ClientboundLevelParticlesPacket(
                            ParticleTypes.SNOWFLAKE,
                            true,
                            x, y, z,
                            (float) vx, (float) vy, (float) vz,
                            0.2f,
                            2
                    ));
                }
            }

            if (this.distanceTo(player) <= 3 && !KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.POLAR)) {
                player.setTicksFrozen(player.getTicksFrozen() + KQConfigValues.CLONE_EXPLOSION_FREEZE_TICKS);
            }

        }

    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.AMETHYST_BLOCK_BREAK;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level.isClientSide) {
            for (Player player : this.level.players()) {
                if (this.distanceTo(player) <= 2.0) {
                    this.kill();
                    break;
                }
            }
        }

    }


    @Override
    protected void tickDeath() {
        ++this.deathTime;

        if (this.deathTime >= 1 && !this.level.isClientSide() && !this.isRemoved()) {
            this.level.broadcastEntityEvent(this, (byte)60);
            this.remove(RemovalReason.KILLED);
        }

    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "coreController", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}