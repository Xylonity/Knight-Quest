package dev.xylonity.knightquest.mixin;

import dev.xylonity.knightquest.common.item.KQFullSetChecker;
import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyVariable(method = "actuallyHurt", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
    private float modifyDamage(float damageOriginal, DamageSource source) {

        // Attacker: Player
        if (source.getEntity() != null && source.getEntity() instanceof Player player) {

            if (KQConfigValues.DRAGONSET && KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.DRAGONSET))
                return (float) (damageOriginal * KQConfigValues.DRAGONSET_DAMAGE_MULTIPLIER);

        }

        return damageOriginal;
    }

}