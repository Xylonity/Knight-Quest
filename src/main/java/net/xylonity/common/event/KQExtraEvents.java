package net.xylonity.common.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.xylonity.registry.KnightQuestEntities;
import net.xylonity.common.entity.entities.SamhainEntity;

/**
 * Alternative class for extra event definitions. Check KQArmorItem::ArmorStatusManagerEvents.
 * @see net.xylonity.common.item.KQArmorItem
 */

public class KQExtraEvents implements UseBlockCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {

        if (world.isClient) {
            return ActionResult.PASS;
        }

        BlockPos pos = hitResult.getBlockPos();
        if (world.getBlockState(pos).getBlock() == Blocks.JACK_O_LANTERN) {
            BlockPos belowPos = pos.down();
            if (world.getBlockState(belowPos).getBlock() == Blocks.GOLD_BLOCK) {
                SamhainEntity samhain = new SamhainEntity(KnightQuestEntities.SAMHAIN, world);
                samhain.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() - 1, pos.getZ() + 0.5, world.random.nextFloat() * 360.0F, 0.0F);
                world.removeBlock(pos, false);
                world.removeBlock(belowPos, false);
                world.playSound(null, pos, SoundEvents.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM, SoundCategory.BLOCKS, 1f, 1f);
                world.spawnEntity(samhain);

                return ActionResult.SUCCESS;
            }
        }

        if (world.getBlockState(pos).getBlock() == Blocks.GOLD_BLOCK) {
            BlockPos abovePos = pos.up();
            if (world.getBlockState(abovePos).getBlock() == Blocks.JACK_O_LANTERN) {
                SamhainEntity samhain = new SamhainEntity(KnightQuestEntities.SAMHAIN, world);
                samhain.refreshPositionAndAngles(abovePos.getX() + 0.5, abovePos.getY() - 1, abovePos.getZ() + 0.5, world.random.nextFloat() * 360.0F, 0.0F);
                world.removeBlock(abovePos, false);
                world.removeBlock(pos, false);
                world.playSound(null, abovePos, SoundEvents.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM, SoundCategory.BLOCKS, 1f, 1f);
                world.spawnEntity(samhain);

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}
