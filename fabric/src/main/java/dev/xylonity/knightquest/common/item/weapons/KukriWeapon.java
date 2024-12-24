package dev.xylonity.knightquest.common.item.weapons;

import dev.xylonity.knightquest.common.item.KQWeaponItem;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class KukriWeapon extends KQWeaponItem {

    public KukriWeapon(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void interaction(Level level, Player player, InteractionHand hand) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, KQConfigValues.SPEED_TICKS_KUKRI, 0, false, false));
    }

    @Override
    public int getCooldownTicks() {
        return KQConfigValues.COOLDOWN_KUKRI;
    }

    @Override
    public String getName() {
        return "kukri";
    }

    @Override
    protected boolean isEnabled() {
        return KQConfigValues.KUKRI;
    }

}
