package dev.xylonity.knightquest.mixin;

import dev.xylonity.knightquest.common.item.KQFullSetChecker;
import dev.xylonity.knightquest.common.item.weapons.KhopeshWeapon;
import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
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

            if (KQConfigValues.DEEPSLATESET.get() && KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.DEEPSLATESET) && source.is(DamageTypes.FALL))
                return damageOriginal * KQConfigValues.DEEPSLATE_FALL_DAMAGE_MULTIPLIER.get().floatValue();

            if (KQConfigValues.CREEPERSET.get() && KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.CREEPERSET) && (source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.PLAYER_EXPLOSION)))
                return damageOriginal * KQConfigValues.CREEPER_EXPLOSION_DAMAGE_MULTIPLIER.get().floatValue();

            if (KQConfigValues.SQUIRESET.get() && KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SQUIRESET))
                return damageOriginal * KQConfigValues.SQUIRE_DAMAGE_RECEIVED_MULTIPLIER.get().floatValue();

            // Khopesh
            ItemStack stack = player.getMainHandItem();
            if (stack.getItem() instanceof KhopeshWeapon && source.getEntity() != null && KQConfigValues.KHOPESH.get()) {

                CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
                CompoundTag dataTag = customData.copyTag();

                if (player.level().getGameTime() - dataTag.getLong("KhopeshActive") < KQConfigValues.REFLECTION_TIME_KHOPESH.get().floatValue()) {
                    source.getEntity().hurt(source, damageOriginal * 0.5F);
                }
            }

        }

        return damageOriginal;

    }

}