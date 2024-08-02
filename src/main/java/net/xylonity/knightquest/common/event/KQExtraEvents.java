package net.xylonity.knightquest.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.block.ChaliceBlock;
import net.xylonity.knightquest.common.item.KQArmorItem;
import net.xylonity.knightquest.registry.KnightQuestBlocks;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.common.entity.entities.SamhainEntity;

import java.util.Arrays;
import java.util.Map;

/**
 * Alternative class for extra event definitions. Check KQArmorItem::ArmorStatusManagerEvents.
 * @see net.xylonity.knightquest.common.item.KQArmorItem
 */

@Mod.EventBusSubscriber(modid = KnightQuest.MOD_ID)
public class KQExtraEvents {

    /**
     * Handles whenever a player tries to summon a Samhain placing its respective blocks.
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

    /**
     * Inherits enchantment effects of netherite armor items to KQArmorItem instances after a craft is done
     */

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        ItemStack craftedItem = event.getCrafting();
        Container craftMatrix = event.getInventory();

        if (craftedItem.getItem() instanceof KQArmorItem) {
            for (int i = 0; i < craftMatrix.getContainerSize(); i++) {
                ItemStack stackInSlot = craftMatrix.getItem(i);
                if (isNetheriteArmor(stackInSlot) && stackInSlot.isEnchanted()) {
                    Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stackInSlot);

                    EnchantmentHelper.setEnchantments(enchantments, craftedItem);

                    break;
                }
            }
        }

    }

    private static boolean isNetheriteArmor(ItemStack stack) {
        return stack.getItem() == Items.NETHERITE_HELMET ||
                stack.getItem() == Items.NETHERITE_CHESTPLATE ||
                stack.getItem() == Items.NETHERITE_LEGGINGS ||
                stack.getItem() == Items.NETHERITE_BOOTS;
    }

}
