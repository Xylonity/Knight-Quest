package net.xylonity.knightquest.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.xylonity.knightquest.KnightQuest;
import org.jetbrains.annotations.NotNull;

public class KnightQuestCreativeModeTabs {

    public static CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab("knightquest_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(KnightQuestItems.PALADIN_SWORD.get());
        }

        @Override
        public @NotNull CreativeModeTab setBackgroundImage(@NotNull ResourceLocation texture) {
            return super.setBackgroundImage(new ResourceLocation(KnightQuest.MOD_ID, "textures/gui/container/creative_inventory/tab_knightquest.png"));
        }
    };

}