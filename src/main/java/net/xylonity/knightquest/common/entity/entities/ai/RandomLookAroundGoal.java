package net.xylonity.knightquest.common.entity.entities.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.xylonity.knightquest.common.entity.entities.GremlinEntity;

import java.util.EnumSet;

public class RandomLookAroundGoal extends Goal {
    private final GremlinEntity mob;
    private double relX;
    private double relZ;
    private int lookTime;

    public RandomLookAroundGoal(GremlinEntity pMob) {
        this.mob = pMob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        return this.mob.getRandom().nextFloat() < 0.02F && !this.mob.getIsPassive();
    }

    public boolean canContinueToUse() {
        return this.lookTime >= 0;
    }

    public void start() {
        double $$0 = 6.283185307179586 * this.mob.getRandom().nextDouble();
        this.relX = Math.cos($$0);
        this.relZ = Math.sin($$0);
        this.lookTime = 20 + this.mob.getRandom().nextInt(20);
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        --this.lookTime;
        this.mob.getLookControl().setLookAt(this.mob.getX() + this.relX, this.mob.getEyeY(), this.mob.getZ() + this.relZ);
    }
}
