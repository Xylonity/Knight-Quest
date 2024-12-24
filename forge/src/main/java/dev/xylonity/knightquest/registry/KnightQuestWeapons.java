package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.item.weapons.*;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class KnightQuestWeapons {

    public static final RegistryObject<Item> PALADIN_SWORD = register("paladin_sword", () -> new PaladinWeapon(KQItemMaterials.PALADIN, -1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> NAIL = register("nail_glaive", () -> new NailWeapon(KQItemMaterials.NAIL, -1, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> UCHIGATANA = register("uchigatana_katana", () -> new UchigatanaWeapon(KQItemMaterials.UCHIGATANA, -1, -2.2f, new Item.Properties()));
    public static final RegistryObject<Item> KUKRI = register("kukri_dagger", () -> new KukriWeapon(KQItemMaterials.KUKRI, -1, -1f, new Item.Properties()));
    public static final RegistryObject<Item> KHOPESH = register("khopesh_claymore", () -> new KhopeshWeapon(KQItemMaterials.KHOPESH, -1, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> CLEAVER = register("cleaver_heavy_axe", () -> new CleaverWeapon(KQItemMaterials.CLEAVER, -1, -3.2f, new Item.Properties()));

    private static RegistryObject<Item> register(String id, Supplier<Item> item) {
        return KnightQuest.ITEMS.register(id, item);
    }

}
