package net.xylonity.knightquest.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.*;
import net.xylonity.knightquest.common.entity.entities.*;

public class KnightQuestEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, KnightQuest.MOD_ID);

    public static final RegistryObject<EntityType<GremlinEntity>> GREMLIN;
    public static final RegistryObject<EntityType<EldBombEntity>> ELDBOMB;
    public static final RegistryObject<EntityType<EldKnightEntity>> ELDKINGHT;
    public static final RegistryObject<EntityType<SwampmanEntity>> SWAMPMAN;
    public static final RegistryObject<EntityType<SamhainEntity>> SAMHAIN;
    public static final RegistryObject<EntityType<RatmanEntity>> RATMAN;
    public static final RegistryObject<EntityType<LizzyEntity>> LIZZY;
    public static final RegistryObject<EntityType<BadPatchEntity>> BADPATCH;
    public static final RegistryObject<EntityType<GhastlingEntity>> SHIELD;
    public static final RegistryObject<EntityType<MommaLizzyEntity>> MOMMA_LIZZY;
    public static final RegistryObject<EntityType<GhostyEntity>> GHOSTY;
    public static final RegistryObject<EntityType<NethermanEntity>> NETHERMAN;
    public static final RegistryObject<EntityType<NethermanCloneEntity>> NETHERMAN_CLONE;
    public static final RegistryObject<EntityType<NethermanTeleportChargeEntity>> NETHERMAN_TELEPORT_CHARGE;
    public static final RegistryObject<EntityType<SwampmanAxeEntity>> SWAMPMAN_AXE;

    static {
        GREMLIN = register("gremlin", GremlinEntity::new, MobCategory.MONSTER , 1f, 1f);
        ELDBOMB = register("eldbomb", EldBombEntity::new, MobCategory.MONSTER , 1f, 1f);
        ELDKINGHT = register("eldknight", EldKnightEntity::new, MobCategory.MONSTER , 1f, 2.6f);
        SAMHAIN = register("samhain", SamhainEntity::new, MobCategory.MONSTER , 1f, 1.5f);
        SWAMPMAN = register("swampman", SwampmanEntity::new, MobCategory.MONSTER , 1f, 2f);
        RATMAN = register("ratman", RatmanEntity::new, MobCategory.MONSTER , 1f, 1f);
        LIZZY = register("lizzy", LizzyEntity::new, MobCategory.AMBIENT , 1f, 0.3f);
        BADPATCH = register("bad_patch", BadPatchEntity::new, MobCategory.MONSTER , 1f, 1f);
        SHIELD = register("ghastling", GhastlingEntity::new, MobCategory.MONSTER , 0.65f, 0.65f);
        MOMMA_LIZZY = register("momma_lizzy", MommaLizzyEntity::new, MobCategory.MONSTER, 1f, 0.3f);
        GHOSTY = register("ghosty", GhostyEntity::new, MobCategory.MONSTER, 1f, 1f);
        NETHERMAN = register("netherman", NethermanEntity::new, MobCategory.MONSTER, 0.8f, 2.8f);
        NETHERMAN_CLONE = register("netherman_clone", NethermanCloneEntity::new, MobCategory.MONSTER, 0.8f, 2.8f);
        NETHERMAN_TELEPORT_CHARGE = register("netherman_teleport_charge", NethermanTeleportChargeEntity::new, MobCategory.MONSTER, 0.5f, 0.5f);
        SWAMPMAN_AXE = register("swampman_axe", SwampmanAxeEntity::new, MobCategory.MONSTER, 0.3f, 1f);
    }

    private static <X extends Entity> RegistryObject<EntityType<X>> register(String name, EntityType.EntityFactory<X> entity, MobCategory category, float width, float height) {
        return ENTITY.register(name, () -> EntityType.Builder.of(entity, category).sized(width, height).build(String.valueOf(new ResourceLocation(KnightQuest.MOD_ID, name))));
    }

}
