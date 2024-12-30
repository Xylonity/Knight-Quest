package net.xylonity.knightquest.registry;

import dev.xylonity.knightlib.compat.registry.KnightLibBlocks;
import dev.xylonity.knightlib.compat.registry.KnightLibItems;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.xylonity.knightquest.KnightQuest;
import org.jetbrains.annotations.NotNull;

public class KnightQuestCreativeModeTabs extends CreativeModeTab {

    private static final String PATH = "textures/gui/container/creative_inventory/";

    public KnightQuestCreativeModeTabs(String label) {
        super(label);
        this.setBackgroundImage(new ResourceLocation(KnightQuest.MOD_ID, PATH + "tab_knightquest.png"));
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(KnightQuestWeapons.PALADIN_SWORD.get());
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("itemgroup.knightquest");
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> pItems) {
        pItems.add(new ItemStack(KnightLibBlocks.GREAT_CHALICE.get()));
        pItems.add(new ItemStack(KnightLibItems.SMALL_ESSENCE.get()));
        pItems.add(new ItemStack(KnightLibItems.GREAT_ESSENCE.get()));

        super.fillItemList(pItems);
    }
}