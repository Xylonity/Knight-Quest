package net.xylonity.registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.boss.NethermanCloneEntity;
import net.xylonity.common.entity.boss.NethermanEntity;
import net.xylonity.common.entity.boss.NethermanTeleportChargeEntity;
import net.xylonity.common.entity.entities.*;

public class KnightQuestEntities {

    public static final EntityType<GremlinEntity> GREMLIN;
    public static final EntityType<EldBombEntity> ELDBOMB;
    public static final EntityType<SamhainEntity> SAMHAIN;
    public static final EntityType<RatmanEntity> RATMAN;
    public static final EntityType<SwampmanEntity> SWAMPMAN;
    public static final EntityType<EldKnightEntity> ELDKNIGHT;
    public static final EntityType<LizzyEntity> LIZZY;
    public static final EntityType<BadPatchEntity> BADPATCH;
    public static final EntityType<GhostyEntity> GHOSTY;
    public static final EntityType<ShieldEntity> SHIELD;
    public static final EntityType<NethermanEntity> NETHERMAN;
    public static final EntityType<NethermanCloneEntity> NETHERMAN_CLONE;
    public static final EntityType<NethermanTeleportChargeEntity> NETHERMAN_TELEPORT_CHARGE;

    public static void registerModEntities() {
        KnightQuest.LOGGER.info("Registering entities for " + KnightQuest.MOD_ID);
    }

    static {
        GREMLIN = register("gremlin", GremlinEntity::new, SpawnGroup.MONSTER , 1f, 1f);
        ELDBOMB = register("eldbomb", EldBombEntity::new, SpawnGroup.MONSTER , 1f, 1f);
        ELDKNIGHT = register("eldknight", EldKnightEntity::new, SpawnGroup.MONSTER , 1f, 2.6f);
        SAMHAIN = register("samhain", SamhainEntity::new, SpawnGroup.MONSTER , 1f, 1.5f);
        SWAMPMAN = register("swampman", SwampmanEntity::new, SpawnGroup.MONSTER , 1f, 2f);
        RATMAN = register("ratman", RatmanEntity::new, SpawnGroup.MONSTER , 1f, 1f);
        LIZZY = register("lizzy", LizzyEntity::new, SpawnGroup.AMBIENT , 1f, 0.3f);
        BADPATCH = register("bad_patch", BadPatchEntity::new, SpawnGroup.MONSTER , 1f, 1f);
        SHIELD = register("ghastling", ShieldEntity::new, SpawnGroup.MONSTER , 0.65f, 0.65f);
        GHOSTY = register("ghosty", GhostyEntity::new, SpawnGroup.MONSTER, 1f, 1f);
        NETHERMAN = register("netherman", NethermanEntity::new, SpawnGroup.MONSTER, 0.8f, 2.8f);
        NETHERMAN_CLONE = register("netherman_clone", NethermanCloneEntity::new, SpawnGroup.MONSTER, 0.8f, 2.8f);
        NETHERMAN_TELEPORT_CHARGE = register("netherman_teleport_charge", NethermanTeleportChargeEntity::new, SpawnGroup.MONSTER, 0.5f, 0.5f);
    }

    private static <X extends Entity> EntityType<X> register(String name, EntityType.EntityFactory<X> entity,SpawnGroup spawnGroup, float width, float height) {
        return Registry.register(Registries.ENTITY_TYPE, Identifier.of(KnightQuest.MOD_ID, name), EntityType.Builder.create(entity, spawnGroup).dimensions(width, height).build(name));
    }

}
