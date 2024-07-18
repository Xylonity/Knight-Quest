package net.xylonity.knightquest.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.custom.*;

public class KnightQuestEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, KnightQuest.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<GremlinEntity>> GREMLIN;
    public static final DeferredHolder<EntityType<?>, EntityType<EldBombEntity>> ELDBOMB;
    public static final DeferredHolder<EntityType<?>, EntityType<EldKnightEntity>> ELDKINGHT;
    public static final DeferredHolder<EntityType<?>, EntityType<SwampmanEntity>> SWAMPMAN;
    public static final DeferredHolder<EntityType<?>, EntityType<SamhainEntity>> SAMHAIN;
    public static final DeferredHolder<EntityType<?>, EntityType<RatmanEntity>> RATMAN;
    public static final DeferredHolder<EntityType<?>, EntityType<LizzyEntity>> LIZZY;
    public static final DeferredHolder<EntityType<?>, EntityType<BadPatchEntity>> BADPATCH;
    public static final DeferredHolder<EntityType<?>, EntityType<ShieldEntity>> SHIELD;
    public static final DeferredHolder<EntityType<?>, EntityType<MommaLizzyEntity>> MOMMA_LIZZY;
    public static final DeferredHolder<EntityType<?>, EntityType<GhostyEntity>> GHOSTY;

    static {
        GREMLIN = ENTITIES.register("gremlin", () -> EntityType.Builder.of(GremlinEntity::new, MobCategory.MONSTER).sized(1f, 1f).build(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "gremlin").toString()));
        ELDBOMB = ENTITIES.register("eldbomb", () -> EntityType.Builder.of(EldBombEntity::new, MobCategory.MONSTER).sized(1f, 1f).build(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "eldbomb").toString()));
        ELDKINGHT = ENTITIES.register("eldknight", () -> EntityType.Builder.of(EldKnightEntity::new, MobCategory.MONSTER).sized(1f, 2.6f).build(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "eldknight").toString()));
        SAMHAIN = ENTITIES.register("samhain", () -> EntityType.Builder.of(SamhainEntity::new, MobCategory.MONSTER).sized(1f, 1.5f).build(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "samhain").toString()));
        SWAMPMAN = ENTITIES.register("swampman", () -> EntityType.Builder.of(SwampmanEntity::new, MobCategory.MONSTER).sized(1f, 2f).build(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "swampman").toString()));
        RATMAN = ENTITIES.register("ratman", () -> EntityType.Builder.of(RatmanEntity::new, MobCategory.MONSTER).sized(1f, 1f).build(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "ratman").toString()));
        LIZZY = ENTITIES.register("lizzy", () -> EntityType.Builder.of(LizzyEntity::new, MobCategory.MONSTER).sized(1f, 0.3f).build(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "lizzy").toString()));
        BADPATCH = ENTITIES.register("bad_patch", () -> EntityType.Builder.of(BadPatchEntity::new, MobCategory.MONSTER).sized(1f, 1f).build(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "bad_patch").toString()));
        SHIELD = ENTITIES.register("ghastling", () -> EntityType.Builder.of(ShieldEntity::new, MobCategory.MONSTER).sized(0.65f, 0.65f).build(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "ghastling").toString()));
        MOMMA_LIZZY = ENTITIES.register("momma_lizzy", () -> EntityType.Builder.of(MommaLizzyEntity::new, MobCategory.MONSTER).sized(1f, 0.3f).build(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "momma_lizzy").toString()));
        GHOSTY = ENTITIES.register("ghosty", () -> EntityType.Builder.of(GhostyEntity::new, MobCategory.MONSTER).sized(1f, 1f).build(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "ghosty").toString()));
    }

}
