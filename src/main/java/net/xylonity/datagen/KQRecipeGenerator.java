package net.xylonity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.xylonity.registry.KnightQuestItems;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Recipe generator. The jsons created here are also used for the forge/neoforge builds
 */

public class KQRecipeGenerator extends FabricRecipeProvider {
    public KQRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

    }
/*
    Item[] APPLE = {KnightQuestItems.APPLE_HELMET, KnightQuestItems.APPLE_CHESTPLATE, KnightQuestItems.APPLE_LEGGINGS, KnightQuestItems.APPLE_BOOTS};
    Item[] BAMBOO_BLUE = {KnightQuestItems.BAMBOO_BLUE_HELMET, KnightQuestItems.BAMBOO_BLUE_CHESTPLATE, KnightQuestItems.BAMBOO_BLUE_LEGGINGS, KnightQuestItems.BAMBOO_BLUE_BOOTS};
    Item[] BAMBOO_GREEN = {KnightQuestItems.BAMBOO_GREEN_HELMET, KnightQuestItems.BAMBOO_GREEN_CHESTPLATE, KnightQuestItems.BAMBOO_GREEN_LEGGINGS, KnightQuestItems.BAMBOO_GREEN_BOOTS};
    Item[] BAMBOO = {KnightQuestItems.BAMBOO_HELMET, KnightQuestItems.BAMBOO_CHESTPLATE, KnightQuestItems.BAMBOO_LEGGINGS, KnightQuestItems.BAMBOO_BOOTS};
    Item[] BAT = {KnightQuestItems.BAT_HELMET, KnightQuestItems.BAT_CHESTPLATE, KnightQuestItems.BAT_LEGGINGS, KnightQuestItems.BAT_BOOTS};
    Item[] BLAZE = {KnightQuestItems.BLAZE_HELMET, KnightQuestItems.BLAZE_CHESTPLATE, KnightQuestItems.BLAZE_LEGGINGS, KnightQuestItems.BLAZE_BOOTS};
    Item[] BOW = {KnightQuestItems.BOW_HELMET, KnightQuestItems.BOW_CHESTPLATE, KnightQuestItems.BOW_LEGGINGS, KnightQuestItems.BOW_BOOTS};
    Item[] HORN = {KnightQuestItems.HORN_HELMET, KnightQuestItems.HORN_CHESTPLATE, KnightQuestItems.HORN_LEGGINGS, KnightQuestItems.HORN_BOOTS};
    Item[] CREEPER = {KnightQuestItems.CREEPER_HELMET, KnightQuestItems.CREEPER_CHESTPLATE, KnightQuestItems.CREEPER_LEGGINGS, KnightQuestItems.CREEPER_BOOTS};
    Item[] DEEPSLATE = {KnightQuestItems.DEEPSLATE_HELMET, KnightQuestItems.DEEPSLATE_CHESTPLATE, KnightQuestItems.DEEPSLATE_LEGGINGS, KnightQuestItems.DEEPSLATE_BOOTS};
    Item[] DRAGON = {KnightQuestItems.DRAGON_HELMET, KnightQuestItems.DRAGON_CHESTPLATE, KnightQuestItems.DRAGON_LEGGINGS, KnightQuestItems.DRAGON_BOOTS};
    Item[] ENDERMAN = {KnightQuestItems.ENDERMAN_HELMET, KnightQuestItems.ENDERMAN_CHESTPLATE, KnightQuestItems.ENDERMAN_LEGGINGS, KnightQuestItems.ENDERMAN_BOOTS};
    Item[] EVOKER = {KnightQuestItems.EVOKER_HELMET, KnightQuestItems.EVOKER_CHESTPLATE, KnightQuestItems.EVOKER_LEGGINGS, KnightQuestItems.EVOKER_BOOTS};
    Item[] FORZE = {KnightQuestItems.FORZE_HELMET, KnightQuestItems.FORZE_CHESTPLATE, KnightQuestItems.FORZE_LEGGINGS, KnightQuestItems.FORZE_BOOTS};
    Item[] HOLLOW = {KnightQuestItems.HOLLOW_HELMET, KnightQuestItems.HOLLOW_CHESTPLATE, KnightQuestItems.HOLLOW_LEGGINGS, KnightQuestItems.HOLLOW_BOOTS};
    Item[] NETHER = {KnightQuestItems.NETHER_HELMET, KnightQuestItems.NETHER_CHESTPLATE, KnightQuestItems.NETHER_LEGGINGS, KnightQuestItems.NETHER_BOOTS};
    Item[] VETERAN = {KnightQuestItems.VETERAN_HELMET, KnightQuestItems.VETERAN_CHESTPLATE, KnightQuestItems.VETERAN_LEGGINGS, KnightQuestItems.VETERAN_BOOTS};
    Item[] PATH = {KnightQuestItems.PATH_HELMET, KnightQuestItems.PATH_CHESTPLATE, KnightQuestItems.PATH_LEGGINGS, KnightQuestItems.PATH_BOOTS};
    Item[] PHANTOM = {KnightQuestItems.PHANTOM_HELMET, KnightQuestItems.PHANTOM_CHESTPLATE, KnightQuestItems.PHANTOM_LEGGINGS, KnightQuestItems.PHANTOM_BOOTS};
    Item[] SEA = {KnightQuestItems.SEA_HELMET, KnightQuestItems.SEA_CHESTPLATE, KnightQuestItems.SEA_LEGGINGS, KnightQuestItems.SEA_BOOTS};
    Item[] SHIELD = {KnightQuestItems.SHIELD_HELMET, KnightQuestItems.SHIELD_CHESTPLATE, KnightQuestItems.SHIELD_LEGGINGS, KnightQuestItems.SHIELD_BOOTS};
    Item[] SILVER = {KnightQuestItems.SILVER_HELMET, KnightQuestItems.SILVER_CHESTPLATE, KnightQuestItems.SILVER_LEGGINGS, KnightQuestItems.SILVER_BOOTS};
    Item[] SILVERFISH = {KnightQuestItems.SILVERFISH_HELMET, KnightQuestItems.SILVERFISH_CHESTPLATE, KnightQuestItems.SILVERFISH_LEGGINGS, KnightQuestItems.SILVERFISH_BOOTS};
    Item[] SKELETON = {KnightQuestItems.SKELETON_HELMET, KnightQuestItems.SKELETON_CHESTPLATE, KnightQuestItems.SKELETON_LEGGINGS, KnightQuestItems.SKELETON_BOOTS};
    Item[] SPIDER = {KnightQuestItems.SPIDER_HELMET, KnightQuestItems.SPIDER_CHESTPLATE, KnightQuestItems.SPIDER_LEGGINGS, KnightQuestItems.SPIDER_BOOTS};
    Item[] WARLORD = {KnightQuestItems.WARLORD_HELMET, KnightQuestItems.WARLORD_CHESTPLATE, KnightQuestItems.WARLORD_LEGGINGS, KnightQuestItems.WARLORD_BOOTS};
    Item[] STRAWHAT = {KnightQuestItems.STRAWHAT_HELMET, KnightQuestItems.STRAWHAT_CHESTPLATE, KnightQuestItems.STRAWHAT_LEGGINGS, KnightQuestItems.STRAWHAT_BOOTS};
    Item[] PIRATE = {KnightQuestItems.PIRATE_HELMET, KnightQuestItems.PIRATE_CHESTPLATE, KnightQuestItems.PIRATE_LEGGINGS, KnightQuestItems.PIRATE_BOOTS};
    Item[] CONQUISTADOR = {KnightQuestItems.CONQUISTADOR_HELMET, KnightQuestItems.CONQUISTADOR_CHESTPLATE, KnightQuestItems.CONQUISTADOR_LEGGINGS, KnightQuestItems.CONQUISTADOR_BOOTS};
    Item[] ZOMBIE = {KnightQuestItems.ZOMBIE_HELMET, KnightQuestItems.ZOMBIE_CHESTPLATE, KnightQuestItems.ZOMBIE_LEGGINGS, KnightQuestItems.ZOMBIE_BOOTS};
    Item[] HUSK = {KnightQuestItems.HUSK_HELMET, KnightQuestItems.HUSK_CHESTPLATE, KnightQuestItems.HUSK_LEGGINGS, KnightQuestItems.HUSK_BOOTS};
    Item[] WITHER = {KnightQuestItems.WITHER_HELMET, KnightQuestItems.WITHER_CHESTPLATE, KnightQuestItems.WITHER_LEGGINGS, KnightQuestItems.WITHER_BOOTS};
    Item[] SQUIRE = {KnightQuestItems.SQUIRE_HELMET, KnightQuestItems.SQUIRE_CHESTPLATE, KnightQuestItems.SQUIRE_LEGGINGS, KnightQuestItems.SQUIRE_BOOTS};

    public KQRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private ItemConvertible getEquivalentNetheriteItem(Item item) {
        ArmorItem.Type armorType = getArmorType(item);

        return switch (armorType) {
            case HELMET -> Items.NETHERITE_HELMET;
            case LEGGINGS -> Items.NETHERITE_LEGGINGS;
            case BOOTS -> Items.NETHERITE_BOOTS;
            default -> Items.NETHERITE_CHESTPLATE;
        };
    }

    private ArmorItem.Type getArmorType(Item item) {
        String translationKey = item.asItem().getTranslationKey();

        if (translationKey.contains("helmet")) {
            return ArmorItem.Type.HELMET;
        } else if (translationKey.contains("leggings")) {
            return ArmorItem.Type.LEGGINGS;
        } else if (translationKey.contains("boots")) {
            return ArmorItem.Type.BOOTS;
        } else {
            return ArmorItem.Type.CHESTPLATE;
        }

    }

    private static void createShapelessRecipe(Item k, List<ItemConvertible> inputs, RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder builder = ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, k, 1);

        for (ItemConvertible input : inputs) {
            builder.input(input)
                    .criterion(hasItem(input), conditionsFromItem(input));
        }

        builder.offerTo(exporter, Identifier.of(getRecipeName(k)));
    }

    public void createShapedRecipe(Item k, String[] pattern, Ingredient[] ingredients, RecipeExporter exporter) {
        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, k, 1);

        builder.pattern(Arrays.toString(pattern));
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length(); j++) {
                builder.input(pattern[i].charAt(j), ingredients[i * pattern[0].length() + j]);
            }
        }

        for (Ingredient ingredient : ingredients) {
            builder.criterion(hasItem(ingredient.getMatchingStacks()[0].getItem()), conditionsFromItem(ingredient.getMatchingStacks()[0].getItem()));
        }

        builder.offerTo(exporter, Identifier.of(getRecipeName(k)));
    }

    @Override
    public void generate(RecipeExporter exporter) {

        for (Item k : APPLE) {
            List<ItemConvertible> inputs = Arrays.asList(Items.GOLDEN_APPLE, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : BAMBOO_BLUE) {
            List<ItemConvertible> inputs = Arrays.asList(Items.BLUE_DYE, Items.BAMBOO, Items.GOLD_INGOT, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : BAMBOO_GREEN) {
            List<ItemConvertible> inputs = Arrays.asList(Items.SLIME_BALL, Items.BAMBOO, Items.EMERALD, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : BAMBOO) {
            List<ItemConvertible> inputs = Arrays.asList(Items.RED_DYE, Items.BAMBOO, Items.COAL, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : BAT) {
            List<ItemConvertible> inputs = Arrays.asList(Items.RED_DYE, Items.DRIPSTONE_BLOCK, Items.INK_SAC, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : BLAZE) {
            List<ItemConvertible> inputs = Arrays.asList(Items.BLAZE_ROD, KnightQuestItems.RATMAN_EYE, Items.BLAZE_POWDER, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : BOW) {
            List<ItemConvertible> inputs = Arrays.asList(Items.ARROW, Items.WATER_BUCKET, Items.CROSSBOW, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : HORN) {
            List<ItemConvertible> inputs = Arrays.asList(Items.GOAT_HORN, Items.LAPIS_LAZULI, Items.SAND, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : CREEPER) {
            List<ItemConvertible> inputs = Arrays.asList(Items.TNT, Items.GREEN_DYE, Items.GUNPOWDER, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : DEEPSLATE) {
            List<ItemConvertible> inputs = Arrays.asList(Items.COBBLED_DEEPSLATE, Items.CHISELED_DEEPSLATE, Items.DEEPSLATE_BRICKS, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : DRAGON) {
            List<ItemConvertible> inputs = Arrays.asList(Items.ENDER_EYE, Items.SHULKER_SHELL, Items.END_STONE_BRICKS, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : ENDERMAN) {
            List<ItemConvertible> inputs = Arrays.asList(Items.ENDER_PEARL, Items.GRASS_BLOCK, Items.AMETHYST_SHARD, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : EVOKER) {
            List<ItemConvertible> inputs = Arrays.asList(Items.TOTEM_OF_UNDYING, Items.DARK_OAK_LOG, Items.LAPIS_LAZULI, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : FORZE) {
            List<ItemConvertible> inputs = Arrays.asList(Items.LIME_DYE, Items.SPIDER_EYE, Items.DEEPSLATE_IRON_ORE, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : HOLLOW) {
            List<ItemConvertible> inputs = Arrays.asList(Items.ENCHANTED_GOLDEN_APPLE, Items.MAGMA_CREAM, Items.RED_MUSHROOM, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : NETHER) {
            List<ItemConvertible> inputs = Arrays.asList(Items.NETHER_WART, Items.MAGMA_CREAM, KnightQuestItems.RATMAN_EYE, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : VETERAN) {
            List<ItemConvertible> inputs = Arrays.asList(Items.IRON_HORSE_ARMOR, Items.DIAMOND_SWORD, Items.ENCHANTED_GOLDEN_APPLE, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : PATH) {
            List<ItemConvertible> inputs = Arrays.asList(Items.BLACK_DYE, Items.COARSE_DIRT, Items.LEATHER, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : PHANTOM) {
            List<ItemConvertible> inputs = Arrays.asList(Items.CYAN_BED, Items.PHANTOM_MEMBRANE, Items.LIME_DYE, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : SEA) {
            List<ItemConvertible> inputs = Arrays.asList(Items.CYAN_BED, Items.PHANTOM_MEMBRANE, Items.HEART_OF_THE_SEA, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : SHIELD) {
            List<ItemConvertible> inputs = Arrays.asList(Items.ORANGE_DYE, Items.SHIELD, Items.COPPER_INGOT, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : SILVER) {
            List<ItemConvertible> inputs = Arrays.asList(Items.FEATHER, Items.IRON_BLOCK, Items.BLACK_DYE, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : SILVERFISH) {
            List<ItemConvertible> inputs = Arrays.asList(Items.BEACON, Items.STONE, Items.CHISELED_DEEPSLATE, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : SKELETON) {
            List<ItemConvertible> inputs = Arrays.asList(Items.BONE_BLOCK, Items.BONE, Items.BONE_MEAL, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : SPIDER) {
            List<ItemConvertible> inputs = Arrays.asList(Items.PISTON, Items.SPIDER_EYE, Items.BONE_MEAL, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : WARLORD) {
            List<ItemConvertible> inputs = Arrays.asList(Items.RED_WOOL, Items.CLOCK, Items.GOLD_INGOT, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : STRAWHAT) {
            List<ItemConvertible> inputs = Arrays.asList(Items.WHITE_WOOL, Items.CLOCK, Items.INK_SAC, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : PIRATE) {
            List<ItemConvertible> inputs = Arrays.asList(Items.MAP, Items.CLOCK, Items.BONE, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : CONQUISTADOR) {
            List<ItemConvertible> inputs = Arrays.asList(Items.YELLOW_DYE, Items.LEATHER, Items.INK_SAC, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : ZOMBIE) {
            List<ItemConvertible> inputs = Arrays.asList(Items.ROTTEN_FLESH, Items.POISONOUS_POTATO, Items.GREEN_DYE, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : HUSK) {
            List<ItemConvertible> inputs = Arrays.asList(Items.SANDSTONE, Items.LAPIS_LAZULI, Items.YELLOW_DYE, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : WITHER) {
            List<ItemConvertible> inputs = Arrays.asList(Items.WITHER_SKELETON_SKULL, Items.WITHER_ROSE, Items.BONE, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        for (Item k : SQUIRE) {
            List<ItemConvertible> inputs = Arrays.asList(Items.DEEPSLATE_IRON_ORE, Items.LAPIS_LAZULI, Items.RED_BANNER, KnightQuestItems.FILLED_GOBLET, getEquivalentNetheriteItem(k));
            createShapelessRecipe(k, inputs, exporter);
        }

        createShapelessRecipe(
                KnightQuestItems.HUSK_HELMET2,
                Arrays.asList(Items.SANDSTONE, Items.EMERALD, Items.YELLOW_DYE, KnightQuestItems.FILLED_GOBLET, Items.NETHERITE_HELMET),
                exporter
        );

        createShapelessRecipe(
                KnightQuestItems.HUSK_HELMET3,
                Arrays.asList(Items.SANDSTONE, Items.PUFFERFISH, Items.YELLOW_DYE, KnightQuestItems.FILLED_GOBLET, Items.NETHERITE_HELMET),
                exporter
        );

        createShapedRecipe(
                KnightQuestItems.KUKRI,
                new String[] { "I", "S" },
                new Ingredient[] {
                        Ingredient.ofItems(Items.IRON_INGOT),
                        Ingredient.ofItems(Items.DARK_OAK_LOG)
                },
                exporter
        );

        createShapedRecipe(
                KnightQuestItems.PALADIN_SWORD,
                new String[] { "X", "I", "S" },
                new Ingredient[] {
                        Ingredient.ofItems(KnightQuestItems.RATMAN_EYE),
                        Ingredient.ofItems(KnightQuestItems.FILLED_GOBLET),
                        Ingredient.ofItems(Items.NETHERITE_SWORD)
                },
                exporter
        );

        createShapedRecipe(
                KnightQuestItems.STEEL_AXE,
                new String[] { "XXX", " I ", " S " },
                new Ingredient[] {
                        Ingredient.ofItems(Items.CHARCOAL),
                        Ingredient.ofItems(Items.IRON_AXE),
                        Ingredient.ofItems(Items.FEATHER)
                },
                exporter
        );

        createShapedRecipe(
                KnightQuestItems.KHOPESH,
                new String[] { "X", "I", "S" },
                new Ingredient[] {
                        Ingredient.ofItems(Items.MAP),
                        Ingredient.ofItems(KnightQuestItems.FILLED_GOBLET),
                        Ingredient.ofItems(Items.NETHERITE_SWORD)
                },
                exporter
        );

        createShapedRecipe(
                KnightQuestItems.NAIL_SWORD,
                new String[] { "X", "I", "S" },
                new Ingredient[] {
                        Ingredient.ofItems(Items.IRON_BLOCK),
                        Ingredient.ofItems(KnightQuestItems.FILLED_GOBLET),
                        Ingredient.ofItems(Items.NETHERITE_SWORD)
                },
                exporter
        );

        createShapedRecipe(
                KnightQuestItems.STEEL_SWORD,
                new String[] { "XXX", " I ", " S " },
                new Ingredient[] {
                        Ingredient.ofItems(Items.CHARCOAL),
                        Ingredient.ofItems(Items.IRON_SWORD),
                        Ingredient.ofItems(Items.FEATHER)
                },
                exporter
        );

        createShapedRecipe(
                KnightQuestItems.CLEAVER,
                new String[] { "X", "I", "S" },
                new Ingredient[] {
                        Ingredient.ofItems(Items.INK_SAC),
                        Ingredient.ofItems(KnightQuestItems.RATMAN_EYE),
                        Ingredient.ofItems(Items.DIAMOND_AXE)
                },
                exporter
        );

        createShapedRecipe(
                KnightQuestItems.UCHIGATANA,
                new String[] { "X", "I", "S" },
                new Ingredient[] {
                        Ingredient.ofItems(Items.DIAMOND),
                        Ingredient.ofItems(KnightQuestItems.LIZZY_SCALE),
                        Ingredient.ofItems(Items.DIAMOND_SWORD)
                },
                exporter
        );

        createShapelessRecipe(
                KnightQuestItems.TUNIC_BLUE_LEGGINGS,
                Arrays.asList(Items.BLUE_DYE, Items.LEATHER, Items.APPLE, KnightQuestItems.FILLED_GOBLET, Items.IRON_LEGGINGS),
                exporter
        );

        createShapelessRecipe(
                KnightQuestItems.TUNIC_RED_LEGGINGS,
                Arrays.asList(Items.RED_DYE, Items.LEATHER, Items.APPLE, KnightQuestItems.FILLED_GOBLET, Items.IRON_LEGGINGS),
                exporter
        );

        createShapelessRecipe(
                KnightQuestItems.TUNIC_YELLOW_LEGGINGS,
                Arrays.asList(Items.YELLOW_DYE, Items.LEATHER, Items.APPLE, KnightQuestItems.FILLED_GOBLET, Items.IRON_LEGGINGS),
                exporter
        );

        createShapelessRecipe(
                KnightQuestItems.TUNIC_GREEN_LEGGINGS,
                Arrays.asList(Items.GREEN_DYE, Items.LEATHER, Items.APPLE, KnightQuestItems.FILLED_GOBLET, Items.IRON_LEGGINGS),
                exporter
        );

        createShapelessRecipe(
                KnightQuestItems.TUNIC_SEA_LEGGINGS,
                Arrays.asList(Items.CYAN_DYE, Items.LEATHER, Items.APPLE, KnightQuestItems.FILLED_GOBLET, Items.IRON_LEGGINGS),
                exporter
        );

        createShapelessRecipe(
                KnightQuestItems.CHAINMAIL_HELMET,
                Arrays.asList(Items.IRON_AXE, Items.MELON_SLICE, Items.APPLE, KnightQuestItems.FILLED_GOBLET, Items.IRON_HELMET),
                exporter
        );

        createShapelessRecipe(
                KnightQuestItems.CHAINMAIL_HELMET2,
                Arrays.asList(Items.IRON_AXE, Items.WHEAT_SEEDS, Items.APPLE, KnightQuestItems.FILLED_GOBLET, Items.IRON_HELMET),
                exporter
        );

    }*/
}
