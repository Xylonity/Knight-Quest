package net.xylonity.knightquest.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.*;

public class KnightQuestEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, KnightQuest.MOD_ID);

    public static final RegistryObject<EntityType<GremlinEntity>> GREMLIN;
    public static final RegistryObject<EntityType<EldBombEntity>> ELDBOMB;
    public static final RegistryObject<EntityType<EldKnightEntity>> ELDKINGHT;
    public static final RegistryObject<EntityType<SwampmanEntity>> SWAMPMAN;
    public static final RegistryObject<EntityType<SamhainEntity>> SAMHAIN;
    public static final RegistryObject<EntityType<RatmanEntity>> RATMAN;
    public static final RegistryObject<EntityType<LizzyEntity>> LIZZY;
    public static final RegistryObject<EntityType<BadPatchEntity>> BADPATCH;
    public static final RegistryObject<EntityType<ShieldEntity>> SHIELD;
    public static final RegistryObject<EntityType<MommaLizzyEntity>> MOMMA_LIZZY;
    public static final RegistryObject<EntityType<GhostyEntity>> GHOSTY;

    static {
        GREMLIN = ENTITY.register("gremlin", () -> EntityType.Builder.of(GremlinEntity::new, MobCategory.MONSTER).sized(1f, 1f).build(new ResourceLocation(KnightQuest.MOD_ID, "gremlin").toString()));
        ELDBOMB = ENTITY.register("eldbomb", () -> EntityType.Builder.of(EldBombEntity::new, MobCategory.MONSTER).sized(1f, 1f).build(new ResourceLocation(KnightQuest.MOD_ID, "eldbomb").toString()));
        ELDKINGHT = ENTITY.register("eldknight", () -> EntityType.Builder.of(EldKnightEntity::new, MobCategory.MONSTER).sized(1f, 2.6f).build(new ResourceLocation(KnightQuest.MOD_ID, "eldknight").toString()));
        SAMHAIN = ENTITY.register("samhain", () -> EntityType.Builder.of(SamhainEntity::new, MobCategory.MONSTER).sized(1f, 1.5f).build(new ResourceLocation(KnightQuest.MOD_ID, "samhain").toString()));
        SWAMPMAN = ENTITY.register("swampman", () -> EntityType.Builder.of(SwampmanEntity::new, MobCategory.MONSTER).sized(1f, 2f).build(new ResourceLocation(KnightQuest.MOD_ID, "swampman").toString()));
        RATMAN = ENTITY.register("ratman", () -> EntityType.Builder.of(RatmanEntity::new, MobCategory.MONSTER).sized(1f, 1f).build(new ResourceLocation(KnightQuest.MOD_ID, "ratman").toString()));
        LIZZY = ENTITY.register("lizzy", () -> EntityType.Builder.of(LizzyEntity::new, MobCategory.MONSTER).sized(1f, 0.3f).build(new ResourceLocation(KnightQuest.MOD_ID, "lizzy").toString()));
        BADPATCH = ENTITY.register("bad_patch", () -> EntityType.Builder.of(BadPatchEntity::new, MobCategory.MONSTER).sized(1f, 1f).build(new ResourceLocation(KnightQuest.MOD_ID, "bad_patch").toString()));
        SHIELD = ENTITY.register("ghastling", () -> EntityType.Builder.of(ShieldEntity::new, MobCategory.MONSTER).sized(0.65f, 0.65f).build(new ResourceLocation(KnightQuest.MOD_ID, "ghastling").toString()));
        MOMMA_LIZZY = ENTITY.register("momma_lizzy", () -> EntityType.Builder.of(MommaLizzyEntity::new, MobCategory.MONSTER).sized(1f, 0.3f).build(new ResourceLocation(KnightQuest.MOD_ID, "momma_lizzy").toString()));
        GHOSTY = ENTITY.register("ghosty", () -> EntityType.Builder.of(GhostyEntity::new, MobCategory.MONSTER).sized(1f, 1f).build(new ResourceLocation(KnightQuest.MOD_ID, "ghosty").toString()));
    }

}
