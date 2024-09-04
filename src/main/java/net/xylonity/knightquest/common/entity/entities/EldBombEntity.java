package net.xylonity.knightquest.common.entity.entities;

import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Collection;

public class EldBombEntity extends Creeper implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private int oldSwell;
    private int swell;
    private int maxSwell = 30;
    private int explosionRadius = 3;

    public EldBombEntity(EntityType<? extends Creeper> entityType, Level world) {
        super(entityType, world);
    }

    public static AttributeSupplier setAttributes() {
        return Creeper.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 7.0D)
                .add(Attributes.ATTACK_DAMAGE, 0.5f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.5f).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SwellGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, PolarBear.class, 35.0F, 1.0, 0.5));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 0.5, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.5));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (this.swell > 10) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sneak", ILoopType.EDefaultLoopTypes.LOOP));
        } else if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        }

        return PlayState.CONTINUE;
    }

    public int getSwell() {
        return this.swell;
    }

    @Override
    public void tick() {
        if (this.isAlive()) {
            this.oldSwell = this.swell;
            if (this.isIgnited()) {
                this.setSwellDir(1);
            }

            int $$0 = this.getSwellDir();
            if ($$0 > 0 && this.swell == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
                this.gameEvent(GameEvent.PRIME_FUSE);
            }

            this.swell += $$0;
            if (this.swell < 0) {
                this.swell = 0;
            }

            if (this.swell >= this.maxSwell) {
                this.swell = this.maxSwell;

                this.poisonNearbyPlayers();

                this.explode();
            }
        }

        super.tick();
    }

    private void poisonNearbyPlayers() {
        this.level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(3.5)).forEach(player -> {
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 160, 0));
        });
    }

    private void explode() {
        if (!this.level.isClientSide) {
            float power = this.isPowered() ? 2.5F : 1.0F;
            this.dead = true;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * power, Explosion.BlockInteraction.DESTROY);

            if (this.level instanceof ServerLevel serverLevel) {
                for (int i = 0; i < 6; i++) {
                    double particleX = this.getX();
                    double particleY = this.getY() + 1;
                    double particleZ = this.getZ();

                    ClientboundLevelParticlesPacket packet = new ClientboundLevelParticlesPacket(
                            KnightQuestParticles.POISON_PARTICLE.get(),
                            true,
                            particleX, particleY, particleZ,
                            1.2F, 1.2F, 1.2F,
                            0.05F, 1
                    );

                    serverLevel.getServer().getPlayerList().broadcast(null, this.getX(), this.getY(), this.getZ(), 50, serverLevel.dimension(), packet);
                }

                float[] arrayX = {0.5F, -1, 1};
                float[] arrayZ = {1, 0, -0.5F};

                for (int i = 0; i < 3; i++) {
                    double particleX = this.getX() + arrayX[i];
                    double particleY = this.getY() - 0.2;
                    double particleZ = this.getZ() + arrayZ[i];

                    ClientboundLevelParticlesPacket packet = new ClientboundLevelParticlesPacket(
                            KnightQuestParticles.POISON_CLOUD_PARTICLE.get(),
                            true,
                            particleX, particleY, particleZ,
                            0, 0.15F, 0,
                            1F, 1
                    );

                    serverLevel.getServer().getPlayerList().broadcast(null, this.getX(), this.getY(), this.getZ(), 50, serverLevel.dimension(), packet);
                }
            }

            this.discard();
        }

    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 1, EasingType.Linear, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

}
