package dev.xylonity.knightquest.common.entity.entities.ai;

import dev.xylonity.knightquest.common.entity.entities.GremlinEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class NearestAttackableTargetGoal<T extends LivingEntity> extends TargetGoal {
    protected final Class<T> targetType;
    protected final int randomInterval;
    @Nullable
    protected LivingEntity target;
    protected TargetingConditions targetConditions;
    private final GremlinEntity gremlinEntity;

    public NearestAttackableTargetGoal(GremlinEntity pMob, Class<T> pTargetType, boolean pMustSee) {
        this(pMob, pTargetType, 10, pMustSee, false, null);
    }

    public NearestAttackableTargetGoal(GremlinEntity pMob, Class<T> pTargetType, int pRandomInterval, boolean pMustSee, boolean pMustReach, @Nullable Predicate<LivingEntity> pTargetPredicate) {
        super(pMob, pMustSee, pMustReach);
        this.gremlinEntity = pMob;
        this.targetType = pTargetType;
        this.randomInterval = reducedTickDelay(pRandomInterval);
        this.setFlags(EnumSet.of(Flag.TARGET));
        this.targetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector(pTargetPredicate);
    }

    public boolean canUse() {
        if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
            return false;
        } else {
            this.findTarget();
            return this.target != null && !this.gremlinEntity.getIsPassive();
        }
    }

    protected AABB getTargetSearchArea(double pTargetDistance) {
        return this.mob.getBoundingBox().inflate(pTargetDistance, 4.0D, pTargetDistance);
    }

    protected void findTarget() {
        if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {
            this.target = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance()), (p_148152_) -> true), this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        } else {
            this.target = this.mob.level().getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        }

    }

    public void start() {
        this.mob.setTarget(this.target);
        super.start();
    }

    public void setTarget(@Nullable LivingEntity pTarget) {
        this.target = pTarget;
    }
}