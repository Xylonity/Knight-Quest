package net.xylonity.knightquest.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.xylonity.knightquest.common.entity.entities.SamhainEntity;
import net.xylonity.knightquest.registry.KnightQuestEntities;

public class KQExtraEvents {

    /**
     * Alternative class for extra event definitions. Check KQArmorItem::ArmorStatusManagerEvents.
     * @see net.xylonity.knightquest.common.item.KQArmorItem
     */

    @SubscribeEvent
    public static void entitySamhainSpawnHandler(BlockEvent.EntityPlaceEvent event) {
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();
        Block block = event.getPlacedBlock().getBlock();

        if (block == Blocks.JACK_O_LANTERN && level.getBlockState(pos.below()).getBlock() == Blocks.GOLD_BLOCK) {
            SamhainEntity samhain = new SamhainEntity(KnightQuestEntities.SAMHAIN.get(), level);
            samhain.moveTo(pos.getX() + 0.5, pos.getY() - 1, pos.getZ() + 0.5, level.random.nextFloat() * 360.0F, 0.0F);
            level.destroyBlock(pos,false);
            level.destroyBlock(pos.below(), false);
            level.playSound(null, pos, SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM, SoundSource.BLOCKS, 1f, 1f);
            level.addFreshEntity(samhain);
        }
    }
}
