package dev.xylonity.knightquest.mixin;

import dev.xylonity.knightquest.common.item.KQFullSetChecker;
import dev.xylonity.knightquest.common.item.weapons.CleaverWeapon;
import dev.xylonity.knightquest.common.item.weapons.UchigatanaWeapon;
import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
                damageOriginal = (float) (damageOriginal * KQConfigValues.DRAGONSET_DAMAGE_MULTIPLIER);

            ItemStack stack = player.getMainHandItem();

            // Uchigatana
            if (stack.getItem() instanceof UchigatanaWeapon && KQConfigValues.UCHIGATANA && source.getEntity() instanceof LivingEntity entity) {
                if (stack.getOrCreateTag().getBoolean("ShouldDoActiveAttack")) {
                    stack.getOrCreateTag().putBoolean("ShouldDoActiveAttack", false);
                    entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 1, false, false));
                    damageOriginal = (float) (damageOriginal + damageOriginal * KQConfigValues.EXTRA_DAMAGE_UCHIGATANA);
                }
                if (entity.getHealth() < KQConfigValues.ENEMY_HEALTH_PASSIVE_UCHIGATANA * entity.getMaxHealth()) {
                    damageOriginal = (float) (damageOriginal + damageOriginal * KQConfigValues.EXTRA_DAMAGE_PASSIVE_UCHIGATANA);
                }

            }

            // Cleaver
            if (stack.getItem() instanceof CleaverWeapon && KQConfigValues.CLEAVER && source.getEntity() instanceof LivingEntity entity) {
                if (entity.getHealth() > KQConfigValues.ENEMY_HEALTH_PASSIVE_CLEAVER * entity.getMaxHealth()) {
                    damageOriginal = (float) (damageOriginal + damageOriginal * KQConfigValues.EXTRA_DAMAGE_PASSIVE_CLEAVER);
                }
            }

        }

        return damageOriginal;
    }

}