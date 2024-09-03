package net.xylonity.common.entity.entities.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.xylonity.common.entity.entities.GremlinEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.Predicate;

public class NearestAttackableTargetGoal<T extends LivingEntity> extends TrackTargetGoal {
    protected final Class<T> targetClass;
    protected final int reciprocalChance;
    @Nullable
    protected LivingEntity targetEntity;
    protected TargetPredicate targetPredicate;
    private final GremlinEntity gremlinEntity;

    public NearestAttackableTargetGoal(GremlinEntity mob, Class<T> targetClass, boolean checkVisibility) {
        this(mob, targetClass, 10, checkVisibility, false, null);
    }

    public NearestAttackableTargetGoal(GremlinEntity mob, Class<T> targetClass, int reciprocalChance, boolean checkVisibility, boolean checkCanNavigate, @Nullable Predicate<LivingEntity> targetPredicate) {
        super(mob, checkVisibility, checkCanNavigate);
        this.gremlinEntity = mob;
        this.targetClass = targetClass;
        this.reciprocalChance = toGoalTicks(reciprocalChance);
        this.setControls(EnumSet.of(Control.TARGET));
        this.targetPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(this.getFollowRange()).setPredicate(targetPredicate);
    }

    public boolean canStart() {
        if (this.reciprocalChance > 0 && this.mob.getRandom().nextInt(this.reciprocalChance) != 0) {
            return false;
        } else {
            this.findClosestTarget();
            return this.targetEntity != null && !this.gremlinEntity.getIsPassive();
        }
    }

    protected Box getSearchBox(double distance) {
        return this.mob.getBoundingBox().expand(distance, 4.0, distance);
    }

    protected void findClosestTarget() {
        if (this.targetClass != PlayerEntity.class && this.targetClass != ServerPlayerEntity.class) {
            this.targetEntity = this.mob.getWorld().getClosestEntity(this.mob.getWorld().getEntitiesByClass(this.targetClass, this.getSearchBox(this.getFollowRange()), (livingEntity) -> {
                return true;
            }), this.targetPredicate, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        } else {
            this.targetEntity = this.mob.getWorld().getClosestPlayer(this.targetPredicate, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        }

    }

    public void start() {
        this.mob.setTarget(this.targetEntity);
        super.start();
    }

    public void setTargetEntity(@Nullable LivingEntity targetEntity) {
        this.targetEntity = targetEntity;
    }
}