package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuestCommon;
import dev.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import dev.xylonity.knightquest.common.entity.boss.NethermanTeleportChargeEntity;
import dev.xylonity.knightquest.common.entity.entities.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class KnightQuestEntities {

    public static void init() { ;; }

    public static final Supplier<EntityType<GremlinEntity>> GREMLIN;
    public static final Supplier<EntityType<EldBombEntity>> ELDBOMB;
    public static final Supplier<EntityType<EldKnightEntity>> ELDKNIGHT;
    public static final Supplier<EntityType<SwampmanEntity>> SWAMPMAN;
    public static final Supplier<EntityType<SamhainEntity>> SAMHAIN;
    public static final Supplier<EntityType<RatmanEntity>> RATMAN;
    public static final Supplier<EntityType<LizzyEntity>> LIZZY;
    public static final Supplier<EntityType<BadPatchEntity>> BADPATCH;
    public static final Supplier<EntityType<GhastlingEntity>> SHIELD;
    public static final Supplier<EntityType<MommaLizzyEntity>> MOMMA_LIZZY;
    public static final Supplier<EntityType<GhostyEntity>> GHOSTY;
    public static final Supplier<EntityType<NethermanEntity>> NETHERMAN;
    public static final Supplier<EntityType<NethermanCloneEntity>> NETHERMAN_CLONE;
    public static final Supplier<EntityType<NethermanTeleportChargeEntity>> NETHERMAN_TELEPORT_CHARGE;
    public static final Supplier<EntityType<SwampmanAxeEntity>> SWAMPMAN_AXE;

    static {
        GREMLIN = register("gremlin", GremlinEntity::new, MobCategory.MONSTER , 1f, 1f);
        ELDBOMB = register("eldbomb", EldBombEntity::new, MobCategory.MONSTER , 1f, 1f);
        ELDKNIGHT = register("eldknight", EldKnightEntity::new, MobCategory.MONSTER , 1f, 2.6f);
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
        NETHERMAN_TELEPORT_CHARGE = register("netherman_teleport_charge", NethermanTeleportChargeEntity::new, MobCategory.MISC, 0.5f, 0.5f);
        SWAMPMAN_AXE = register("swampman_axe", SwampmanAxeEntity::new, MobCategory.MISC, 0.3f, 1f);
    }

    private static <X extends Entity> Supplier<EntityType<X>> register(String name, EntityType.EntityFactory<X> entity, MobCategory spawnGroup, float width, float height) {
        return KnightQuestCommon.COMMON_PLATFORM.registerEntity(name, () -> EntityType.Builder.of(entity, spawnGroup).sized(width, height).build(name));
    }

}
