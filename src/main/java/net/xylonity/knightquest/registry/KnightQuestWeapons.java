package net.xylonity.knightquest.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.item.weapons.*;
import net.xylonity.knightquest.common.material.KQItemMaterials;

import java.util.function.Supplier;

public class KnightQuestWeapons {

    public static void init() { ;; }

    public static final RegistryObject<Item> PALADIN_SWORD = register("paladin_sword", () -> new PaladinWeapon(KQItemMaterials.PALADIN, -1, -2.6f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> NAIL = register("nail_glaive", () -> new NailWeapon(KQItemMaterials.NAIL, -1, -2.4f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> UCHIGATANA = register("uchigatana_katana", () -> new UchigatanaWeapon(KQItemMaterials.UCHIGATANA, -1, -2.2f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> KUKRI = register("kukri_dagger", () -> new KukriWeapon(KQItemMaterials.KUKRI, -1, -1f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> KHOPESH = register("khopesh_claymore", () -> new KhopeshWeapon(KQItemMaterials.KHOPESH, -1, -2.6f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CLEAVER = register("cleaver", () -> new CleaverWeapon(KQItemMaterials.CLEAVER, -1, -3.2f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));

    private static RegistryObject<Item> register(String id, Supplier<Item> item) {
        return KnightQuestItems.ITEMS.register(id, item);
    }

}
