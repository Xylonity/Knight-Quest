package dev.xylonity.knightquest.common.event;

import dev.xylonity.knightquest.common.entity.entities.SamhainEntity;
import dev.xylonity.knightquest.common.item.KQArmorItem;
import dev.xylonity.knightquest.registry.KnightQuestEntities;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;

/**
 * Alternative class for extra event definitions. Check KQArmorItem::ArmorStatusManagerEvents.
 * @see KQArmorItem
 */

public class KQExtraEvents implements UseBlockCallback {

    @Override
    public InteractionResult interact(Player player, Level world, InteractionHand hand, BlockHitResult hitResult) {

        if (world.isClientSide()) {
            return InteractionResult.PASS;
        }

        BlockPos pos = hitResult.getBlockPos();
        if (world.getBlockState(pos).getBlock() == Blocks.JACK_O_LANTERN) {
            BlockPos belowPos = pos.below();
            if (world.getBlockState(belowPos).getBlock() == Blocks.GOLD_BLOCK) {
                SamhainEntity samhain = new SamhainEntity(KnightQuestEntities.SAMHAIN, world);
                samhain.moveTo(pos.getX() + 0.5, pos.getY() - 1, pos.getZ() + 0.5, world.getRandom().nextFloat() * 360.0F, 0.0F);
                world.removeBlock(pos, false);
                world.removeBlock(belowPos, false);
                world.playSound(null, pos, SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM, SoundSource.BLOCKS, 1f, 1f);
                world.addFreshEntity(samhain);

                return InteractionResult.SUCCESS;
            }
        }

        if (world.getBlockState(pos).getBlock() == Blocks.GOLD_BLOCK) {
            BlockPos abovePos = pos.above();
            if (world.getBlockState(abovePos).getBlock() == Blocks.JACK_O_LANTERN) {
                SamhainEntity samhain = new SamhainEntity(KnightQuestEntities.SAMHAIN, world);
                samhain.moveTo(abovePos.getX() + 0.5, abovePos.getY() - 1, abovePos.getZ() + 0.5, world.getRandom().nextFloat() * 360.0F, 0.0F);
                world.removeBlock(abovePos, false);
                world.removeBlock(pos, false);
                world.playSound(null, abovePos, SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM, SoundSource.BLOCKS, 1f, 1f);
                world.addFreshEntity(samhain);

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}