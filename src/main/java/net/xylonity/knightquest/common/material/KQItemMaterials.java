package net.xylonity.knightquest.common.material;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.registry.KnightQuestItems;
import net.xylonity.knightquest.common.tags.KQTags;

import java.util.List;

public class KQItemMaterials {

    public static final Tier PALADIN = TierSortingRegistry.registerTier(new ForgeTier(4, 2131, 0.5f, 3.5f, 15,
        KQTags.Blocks.KNIGHTQUEST_TOOLS, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
        new ResourceLocation(KnightQuest.MOD_ID, "paladin_sword"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier NAIL = TierSortingRegistry.registerTier(new ForgeTier(4, 1821, 0.5f, 2.5f, 15,
        KQTags.Blocks.KNIGHTQUEST_TOOLS, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
        new ResourceLocation(KnightQuest.MOD_ID, "nail_glaive"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier UCHIGATANA = TierSortingRegistry.registerTier(new ForgeTier(4, 1080, 0.5f, 3f, 14,
        KQTags.Blocks.KNIGHTQUEST_TOOLS, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
        new ResourceLocation(KnightQuest.MOD_ID, "uchigatana_katana"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier KUKRI = TierSortingRegistry.registerTier(new ForgeTier(4, 200, 0.5f, 1f, 14,
        KQTags.Blocks.KNIGHTQUEST_TOOLS, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
        new ResourceLocation(KnightQuest.MOD_ID, "kukri_dagger"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier KHOPESH = TierSortingRegistry.registerTier(new ForgeTier(4, 1831, 0.5f, 3.5f, 15,
        KQTags.Blocks.KNIGHTQUEST_TOOLS, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
        new ResourceLocation(KnightQuest.MOD_ID, "khopesh_claymore"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier CLEAVER = TierSortingRegistry.registerTier(new ForgeTier(4, 1831, 0.5f, 3.2f, 15,
        KQTags.Blocks.KNIGHTQUEST_TOOLS, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
        new ResourceLocation(KnightQuest.MOD_ID, "cleaver_heavy_axe"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier CRIMSON_SWORD = TierSortingRegistry.registerTier(new ForgeTier(4, 1450, 0.5f, 2.5f, 14,
        KQTags.Blocks.KNIGHTQUEST_TOOLS, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
        new ResourceLocation(KnightQuest.MOD_ID, "crimson_sword"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier WATER_SWORD = TierSortingRegistry.registerTier(new ForgeTier(4, 850, 0.5f, 2f, 14,
        KQTags.Blocks.KNIGHTQUEST_TOOLS, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
        new ResourceLocation(KnightQuest.MOD_ID, "water_sword"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier STEEL_SWORD = TierSortingRegistry.registerTier(new ForgeTier(4, 300, 0.5f, 2.5f, 14,
        KQTags.Blocks.KNIGHTQUEST_TOOLS, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
        new ResourceLocation(KnightQuest.MOD_ID, "steel_sword"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier WATER_AXE = TierSortingRegistry.registerTier(new ForgeTier(4, 850, 6.0f, 1.5f, 14,
        KQTags.Blocks.KNIGHTQUEST_TOOLS, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
        new ResourceLocation(KnightQuest.MOD_ID, "water_axe"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier STEEL_AXE = TierSortingRegistry.registerTier(new ForgeTier(4, 300, 6.5f, 1.5f, 14,
        KQTags.Blocks.KNIGHTQUEST_TOOLS, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
        new ResourceLocation(KnightQuest.MOD_ID, "steel_axe"), List.of(Tiers.NETHERITE), List.of());


}
