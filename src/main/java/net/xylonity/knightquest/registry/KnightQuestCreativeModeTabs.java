package net.xylonity.knightquest.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.xylonity.knightquest.KnightQuest;

public class KnightQuestCreativeModeTabs extends CreativeModeTab {

    private static final String PATH = "textures/gui/container/creative_inventory/";

    public KnightQuestCreativeModeTabs(String label) {
        super(label);
        this.setBackgroundImage(new ResourceLocation(KnightQuest.MOD_ID, PATH + "tab_knightquest.png"));
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(KnightQuestItems.PALADIN_SWORD.get());
    }

}