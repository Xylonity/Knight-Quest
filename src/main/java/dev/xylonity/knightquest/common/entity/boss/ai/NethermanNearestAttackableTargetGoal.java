package dev.xylonity.knightquest.common.entity.boss.ai;

import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import java.util.function.Predicate;

public class NethermanNearestAttackableTargetGoal<T extends LivingEntity> extends TargetGoal {
    private static final int DEFAULT_RANDOM_INTERVAL = 10;
    protected final Class<T> targetType;
    protected final int randomInterval;
    protected LivingEntity target;
    protected TargetingConditions targetConditions;

    public NethermanNearestAttackableTargetGoal(Mob pMob, Class<T> pTargetType, boolean pMustSee) {
        this(pMob, pTargetType, 10, pMustSee, false, null);
    }

    public NethermanNearestAttackableTargetGoal(Mob pMob, Class<T> pTargetType, boolean pMustSee, Predicate<LivingEntity> pTargetPredicate) {
        this(pMob, pTargetType, 10, pMustSee, false, pTargetPredicate);
    }

    public NethermanNearestAttackableTargetGoal(Mob pMob, Class<T> pTargetType, boolean pMustSee, boolean pMustReach) {
        this(pMob, pTargetType, 10, pMustSee, pMustReach, null);
    }

    public NethermanNearestAttackableTargetGoal(Mob pMob, Class<T> pTargetType, int pRandomInterval, boolean pMustSee, boolean pMustReach, Predicate<LivingEntity> pTargetPredicate) {
        super(pMob, pMustSee, pMustReach);
        this.targetType = pTargetType;
        this.randomInterval = reducedTickDelay(pRandomInterval);
        this.setFlags(EnumSet.of(Flag.TARGET));
        this.targetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector(pTargetPredicate);
    }

    public boolean canUse() {
        if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0 && this.mob instanceof NethermanEntity netherman && netherman.getShouldSearchTarget()) {
            return false;
        } else {
            this.findTarget();
            return this.target != null;
        }
    }

    protected AABB getTargetSearchArea(double pTargetDistance) {
        return this.mob.getBoundingBox().inflate(pTargetDistance, 4.0, pTargetDistance);
    }

    protected void findTarget() {
        if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {
            this.target = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance()), (p_148152_) -> {
                return true;
            }), this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        } else {
            this.target = this.mob.level().getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        }

    }

    public void start() {
        if (this.mob instanceof NethermanEntity netherman && netherman.getShouldSearchTarget()) this.mob.setTarget(this.target);
        super.start();
    }

    public void setTarget(LivingEntity pTarget) {
        this.target = pTarget;
    }
}
