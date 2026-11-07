package dev.xylonity.knightquest.registry;

import dev.xylonity.knightlib.api.loot.KnightLibLoot;
import dev.xylonity.knightquest.KnightQuestCommon;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class KnightQuestEntityDrops {

    public static final TagKey<EntityType<?>> RATMAN_EYE_DROP = tag("ratman_eye_drop");
    public static final TagKey<EntityType<?>> LIZZY_SCALE_DROP = tag("lizzy_scale_drop");

    public static void init() {
        KnightLibLoot.builder()
                .tag(RATMAN_EYE_DROP)
                .chance(() -> KQConfigValues.DROP_CHANCE_RATMAN_EYE.get().floatValue())
                .item(KnightQuestItems.RATMAN_EYE)
                .submit();

        KnightLibLoot.builder()
                .tag(LIZZY_SCALE_DROP)
                .chance(() -> KQConfigValues.DROP_CHANCE_LIZZY_SCALE.get().floatValue())
                .item(KnightQuestItems.LIZZY_SCALE)
                .count(1, 2)
                .submit();
    }

    private static TagKey<EntityType<?>> tag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KnightQuestCommon.MOD_ID, name));
    }

}