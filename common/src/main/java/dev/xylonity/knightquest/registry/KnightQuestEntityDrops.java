package dev.xylonity.knightquest.registry;

import dev.xylonity.knightlib.api.loot.KnightLibLoot;
import dev.xylonity.knightquest.KnightQuestCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class KnightQuestEntityDrops {

    public static final TagKey<EntityType<?>> RATMAN_EYE_DROP = tag("ratman_eye_drop");
    public static final TagKey<EntityType<?>> LIZZY_SCALE_DROP = tag("lizzy_scale_drop");

    public static void init(Supplier<Float> ratmanEyeChance, Supplier<Float> lizzyScaleChance) {
        KnightLibLoot.builder()
                .tag(RATMAN_EYE_DROP)
                .chance(ratmanEyeChance)
                .item(KnightQuestItems.RATMAN_EYE)
                .submit();

        KnightLibLoot.builder()
                .tag(LIZZY_SCALE_DROP)
                .chance(lizzyScaleChance)
                .item(KnightQuestItems.LIZZY_SCALE)
                .count(1, 2)
                .submit();
    }

    private static TagKey<EntityType<?>> tag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(KnightQuestCommon.MOD_ID, name));
    }

}
