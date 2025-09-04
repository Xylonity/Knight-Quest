package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.item.weapons.*;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

import java.util.function.Supplier;

public class KnightQuestWeapons {

    public static final Supplier<Item> PALADIN_SWORD = register("paladin_sword", () -> new PaladinWeapon(KQItemMaterials.PALADIN, new Item.Properties().fireResistant().attributes(SwordItem.createAttributes(KQItemMaterials.PALADIN, -1f, -2.6f))));
    public static final Supplier<Item> NAIL = register("nail_glaive", () -> new NailWeapon(KQItemMaterials.NAIL, new Item.Properties().fireResistant().attributes(SwordItem.createAttributes(KQItemMaterials.NAIL, -1, -2.4f))));
    public static final Supplier<Item> UCHIGATANA = register("uchigatana_katana", () -> new UchigatanaWeapon(KQItemMaterials.UCHIGATANA, new Item.Properties().fireResistant().attributes(SwordItem.createAttributes(KQItemMaterials.UCHIGATANA, -1, -2.2f))));
    public static final Supplier<Item> KUKRI = register("kukri_dagger", () -> new KukriWeapon(KQItemMaterials.KUKRI, new Item.Properties().attributes(SwordItem.createAttributes(KQItemMaterials.KUKRI, -1, -1f))));
    public static final Supplier<Item> KHOPESH = register("khopesh_claymore", () -> new KhopeshWeapon(KQItemMaterials.KHOPESH, new Item.Properties().fireResistant().attributes(SwordItem.createAttributes(KQItemMaterials.KHOPESH, -1, -2.6f))));
    public static final Supplier<Item> CLEAVER = register("cleaver", () -> new CleaverWeapon(KQItemMaterials.CLEAVER, new Item.Properties().fireResistant().attributes(SwordItem.createAttributes(KQItemMaterials.CLEAVER, -1, -3.2f))));

    private static Supplier<Item> register(String id, Supplier<Item> item) {
        return KnightQuest.ITEMS.register(id, item);
    }

}
