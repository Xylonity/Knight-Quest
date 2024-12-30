package net.xylonity.knightquest.common.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.xylonity.knightquest.config.values.KQConfigValues;

public class KQFullSetChecker {

    public static boolean hasFullSetOn(Player player, ArmorMaterial material) {
        int requiredPieces = KQConfigValues.REQUIRED_ARMOR_PIECES;
        int equippedPieces = 0;

        for (ItemStack armorStack : player.getInventory().armor) {
            if (!armorStack.isEmpty() && armorStack.getItem() instanceof ArmorItem armorItem) {
                if (armorItem.getMaterial() == material) {
                    equippedPieces++;
                }
            }
        }

        return equippedPieces >= requiredPieces;
    }

}
