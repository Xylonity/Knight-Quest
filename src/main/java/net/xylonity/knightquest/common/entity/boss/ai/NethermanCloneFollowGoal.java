package net.xylonity.knightquest.common.entity.boss.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;

import java.util.EnumSet;

public class NethermanCloneFollowGoal extends Goal {
    private final NethermanCloneEntity entity;
    private Player targetPlayer;

    public NethermanCloneFollowGoal(NethermanCloneEntity entity) {
        this.entity = entity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        this.targetPlayer = this.entity.level().getNearestPlayer(this.entity, 10.0D);
        return this.targetPlayer != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetPlayer != null && this.targetPlayer.isAlive() && this.entity.distanceToSqr(this.targetPlayer) > 1.0D;
    }

    @Override
    public void start() {
        this.entity.getNavigation().moveTo(this.targetPlayer, 0.63D);
    }

    @Override
    public void stop() {
        this.targetPlayer = null;
        this.entity.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (this.targetPlayer != null) {
            this.entity.getNavigation().moveTo(this.targetPlayer, 1.0D);
        }
    }
}
