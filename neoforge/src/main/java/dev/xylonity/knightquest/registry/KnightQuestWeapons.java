package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.item.weapons.*;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class KnightQuestWeapons {

    public static final Supplier<Item> PALADIN_SWORD = register("paladin_sword", () -> new PaladinWeapon(KQItemMaterials.PALADIN, new Item.Properties()));
    public static final Supplier<Item> NAIL = register("nail_glaive", () -> new NailWeapon(KQItemMaterials.NAIL, new Item.Properties()));
    public static final Supplier<Item> UCHIGATANA = register("uchigatana_katana", () -> new UchigatanaWeapon(KQItemMaterials.UCHIGATANA, new Item.Properties()));
    public static final Supplier<Item> KUKRI = register("kukri_dagger", () -> new KukriWeapon(KQItemMaterials.KUKRI, new Item.Properties()));
    public static final Supplier<Item> KHOPESH = register("khopesh_claymore", () -> new KhopeshWeapon(KQItemMaterials.KHOPESH, new Item.Properties()));
    public static final Supplier<Item> CLEAVER = register("cleaver_heavy_axe", () -> new CleaverWeapon(KQItemMaterials.CLEAVER, new Item.Properties()));

    private static Supplier<Item> register(String id, Supplier<Item> item) {
        return KnightQuest.ITEMS.register(id, item);
    }

}
