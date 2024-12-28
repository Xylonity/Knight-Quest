package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.item.weapons.*;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class KnightQuestWeapons {

    public static void init() { ;; }

    public static final Item PALADIN_SWORD = register("paladin_sword", () -> new PaladinWeapon(KQItemMaterials.PALADIN, new Item.Properties()));
    public static final Item NAIL = register("nail_glaive", () -> new NailWeapon(KQItemMaterials.NAIL, new Item.Properties()));
    public static final Item UCHIGATANA = register("uchigatana_katana", () -> new UchigatanaWeapon(KQItemMaterials.UCHIGATANA, new Item.Properties()));
    public static final Item KUKRI = register("kukri_dagger", () -> new KukriWeapon(KQItemMaterials.KUKRI, new Item.Properties()));
    public static final Item KHOPESH = register("khopesh_claymore", () -> new KhopeshWeapon(KQItemMaterials.KHOPESH, new Item.Properties()));
    public static final Item CLEAVER = register("cleaver_heavy_axe", () -> new CleaverWeapon(KQItemMaterials.CLEAVER, new Item.Properties()));

    private static Item register(String id, Supplier<Item> item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, id), item.get());
    }

}
