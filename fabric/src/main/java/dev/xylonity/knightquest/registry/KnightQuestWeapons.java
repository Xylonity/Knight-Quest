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

    public static final Item PALADIN_SWORD = register("paladin_sword", () -> new PaladinWeapon(KQItemMaterials.PALADIN, -1, -2.6f, new Item.Properties()));
    public static final Item NAIL = register("nail_glaive", () -> new NailWeapon(KQItemMaterials.NAIL, -1, -2.4f, new Item.Properties()));
    public static final Item UCHIGATANA = register("uchigatana_katana", () -> new UchigatanaWeapon(KQItemMaterials.UCHIGATANA, -1, -2.2f, new Item.Properties()));
    public static final Item KUKRI = register("kukri_dagger", () -> new KukriWeapon(KQItemMaterials.KUKRI, -1, -1f, new Item.Properties()));
    public static final Item KHOPESH = register("khopesh_claymore", () -> new KhopeshWeapon(KQItemMaterials.KHOPESH, -1, -2.6f, new Item.Properties()));
    public static final Item CLEAVER = register("cleaver", () -> new CleaverWeapon(KQItemMaterials.CLEAVER, -1, -3.2f, new Item.Properties()));

    private static Item register(String id, Supplier<Item> item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(KnightQuest.MOD_ID, id), item.get());
    }

}
