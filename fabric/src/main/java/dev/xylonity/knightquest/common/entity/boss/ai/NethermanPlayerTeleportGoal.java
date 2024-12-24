package dev.xylonity.knightquest.common.entity.boss.ai;

import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.List;

public class NethermanPlayerTeleportGoal  extends Goal {
    private final NethermanEntity netherman;
    public int chargeTime;

    public NethermanPlayerTeleportGoal(NethermanEntity netherman) {
        this.netherman = netherman;
    }

    public boolean canUse() {
        return this.netherman.getTarget() != null
                && this.netherman.getHealth() > this.netherman.getMaxHealth() * 0.1
                && (this.netherman.getPhase() == 1
                    || (this.netherman.getPhase() == 2 && this.netherman.getCounterSwitchPhase2() == 130)
                    || (this.netherman.getPhase() == 3 && this.netherman.getCounterSwitchPhase3() == 160));
    }

    public void start() {
        this.chargeTime = 400;
    }

    public void stop() {
        this.chargeTime = 0;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        if (this.chargeTime > 0) {
            --this.chargeTime;
            return;
        }

        List<ServerPlayer> nearbyPlayers = netherman.level().getEntitiesOfClass(
                ServerPlayer.class,
                netherman.getBoundingBox().inflate(50.0)
        );

        for (ServerPlayer player : nearbyPlayers) {
            double randomX = netherman.getX() + (netherman.getRandom().nextDouble() - 0.5) * 25.0;
            double randomZ = netherman.getZ() + (netherman.getRandom().nextDouble() - 0.5) * 25.0;
            double randomY = netherman.level()
                    .getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, new BlockPos((int) randomX, (int) netherman.getY(), (int) randomZ)).getY();

            player.teleportTo(randomX, randomY, randomZ);
            this.netherman.level().gameEvent(GameEvent.TELEPORT, player.position(), GameEvent.Context.of(player));
            this.netherman.level().playSound(null, player.xo, player.yo, player.zo, SoundEvents.ENDERMAN_TELEPORT, player.getSoundSource(), 1.0F, 1.0F);
            this.netherman.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
        }

        this.chargeTime = 400;

    }

}