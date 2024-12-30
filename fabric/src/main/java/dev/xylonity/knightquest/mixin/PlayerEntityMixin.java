package dev.xylonity.knightquest.mixin;

import dev.xylonity.knightquest.common.item.KQFullSetChecker;
import dev.xylonity.knightquest.common.item.weapons.CleaverWeapon;
import dev.xylonity.knightquest.common.item.weapons.KhopeshWeapon;
import dev.xylonity.knightquest.common.item.weapons.UchigatanaWeapon;
import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
@SuppressWarnings("UnreachableCode")
public abstract class PlayerEntityMixin {

    @ModifyVariable(method = "actuallyHurt", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
    private float modifyDamage(float damageOriginal, DamageSource source) {

        // Victim: Player
        if ((Object) this instanceof Player player) {

            // BLAZESET
            if (KQConfigValues.BLAZESET)
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.BLAZESET)) {
                    RandomSource random = player.level().getRandom();
                    if (source.getEntity() != null && random.nextFloat() < KQConfigValues.BLAZE_FIRE_CHANCE)
                        source.getEntity().setSecondsOnFire(random.nextIntBetweenInclusive(KQConfigValues.BLAZE_FIRE_DURATION_MIN, KQConfigValues.BLAZE_FIRE_DURATION_MAX));
                }

            if (KQConfigValues.DEEPSLATESET && KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.DEEPSLATESET) && source.is(DamageTypes.FALL))
                return (float) (damageOriginal * KQConfigValues.DEEPSLATE_FALL_DAMAGE_MULTIPLIER);

            if (KQConfigValues.CREEPERSET && KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.CREEPERSET) && (source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.PLAYER_EXPLOSION)))
                return (float) (damageOriginal * KQConfigValues.CREEPER_EXPLOSION_DAMAGE_MULTIPLIER);

            if (KQConfigValues.SQUIRESET && KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SQUIRESET))
                return (float) (damageOriginal * KQConfigValues.SQUIRE_DAMAGE_RECEIVED_MULTIPLIER);

            // Khopesh
            ItemStack stack = player.getMainHandItem();
            if (stack.getItem() instanceof KhopeshWeapon && source.getEntity() != null && KQConfigValues.KHOPESH) {
                if (player.level().getGameTime() - stack.getOrCreateTag().getLong("KhopeshActive") < KQConfigValues.REFLECTION_TIME_KHOPESH) {
                    source.getEntity().hurt(source, damageOriginal * 0.5F);
                }
            }

        }

        return damageOriginal;

    }

}