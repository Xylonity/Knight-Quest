package dev.xylonity.knightquest.common.item.weapons;

import dev.xylonity.knightquest.common.item.KQWeaponItem;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

public class UchigatanaWeapon extends KQWeaponItem {

    public UchigatanaWeapon(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public void interaction(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        data = data.update(tag -> tag.putBoolean("ShouldDoActiveAttack", true));

        stack.set(DataComponents.CUSTOM_DATA, data);
    }

    @Override
    public int getCooldownTicks() {
        return KQConfigValues.COOLDOWN_UCHIGATANA.get();
    }

    @Override
    public String getName() {
        return "uchigatana";
    }

    @Override
    protected boolean isEnabled() {
        return KQConfigValues.UCHIGATANA.get();
    }
}
