package net.xylonity.common.entity.entities.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.xylonity.common.entity.entities.GremlinEntity;

import java.util.EnumSet;

public class LookAroundGoal extends Goal {
    private final GremlinEntity mob;
    private double deltaX;
    private double deltaZ;
    private int lookTime;

    public LookAroundGoal(GremlinEntity mob) {
        this.mob = mob;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    public boolean canStart() {
        return this.mob.getRandom().nextFloat() < 0.02F && !this.mob.getIsPassive();
    }

    public boolean shouldContinue() {
        return this.lookTime >= 0;
    }

    public void start() {
        double d = 6.283185307179586 * this.mob.getRandom().nextDouble();
        this.deltaX = Math.cos(d);
        this.deltaZ = Math.sin(d);
        this.lookTime = 20 + this.mob.getRandom().nextInt(20);
    }

    public boolean shouldRunEveryTick() {
        return true;
    }

    public void tick() {
        --this.lookTime;
        this.mob.getLookControl().lookAt(this.mob.getX() + this.deltaX, this.mob.getEyeY(), this.mob.getZ() + this.deltaZ);
    }
}