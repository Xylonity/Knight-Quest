package dev.xylonity.knightquest.common.item;

import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public class KQFullSetChecker {

    public static boolean hasFullSetOn(Player player, KQArmorMaterials material) {
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
