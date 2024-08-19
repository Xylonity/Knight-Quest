package net.xylonity.knightquest.common.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.xylonity.knightquest.common.material.KQArmorMaterials;

public class KQFullSetChecker {

    protected static boolean hasFullSuitOfArmorOn(Player player, KQArmorMaterials material) {

        for (ItemStack armorStack : player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem helmet = (ArmorItem) player.getInventory().getArmor(3).getItem();
        ArmorItem chestplate = (ArmorItem) player.getInventory().getArmor(2).getItem();
        ArmorItem leggings = (ArmorItem) player.getInventory().getArmor(1).getItem();
        ArmorItem boots = (ArmorItem) player.getInventory().getArmor(0).getItem();

        return helmet.getMaterial() == material && chestplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    protected static boolean hasFullSetOn(Player player, KQArmorMaterials material) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        if (helmet.isEmpty() || chestplate.isEmpty() || leggings.isEmpty() || boots.isEmpty()) {
            return false;
        }

        if (!(helmet.getItem() instanceof ArmorItem helmetArmor) ||
                !(chestplate.getItem() instanceof ArmorItem chestplateArmor) ||
                !(leggings.getItem() instanceof ArmorItem leggingsArmor) ||
                !(boots.getItem() instanceof ArmorItem bootsArmor)) {
            return false;
        }

        return helmetArmor.getMaterial() == material &&
                chestplateArmor.getMaterial() == material &&
                leggingsArmor.getMaterial() == material &&
                bootsArmor.getMaterial() == material;
    }

}