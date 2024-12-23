package dev.xylonity.knightquest.common.item.weapons;

import dev.xylonity.knightquest.common.item.KQWeaponItem;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class UchigatanaWeapon extends KQWeaponItem {



    public UchigatanaWeapon(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void interaction(Level level, Player player, InteractionHand hand) {
        player.getItemInHand(hand).getOrCreateTag().putBoolean("ShouldDoActiveAttack", true);
    }

    @Override
    public int getCooldownTicks() {
        return KQConfigValues.COOLDOWN_UCHIGATANA;
    }

    @Override
    public String getName() {
        return "uchigatana";
    }

    @Override
    protected boolean isEnabled() {
        return KQConfigValues.UCHIGATANA;
    }
}
