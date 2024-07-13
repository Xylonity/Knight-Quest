package net.xylonity.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
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

    public static void registerModEntities() {
        KnightQuest.LOGGER.info("Registering entities for " + KnightQuest.MOD_ID);
    }

    static {
        GREMLIN = Registry.register(Registries.ENTITY_TYPE, Identifier.of(KnightQuest.MOD_ID, "gremlin"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GremlinEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build());
        ELDBOMB = Registry.register(Registries.ENTITY_TYPE, Identifier.of(KnightQuest.MOD_ID, "eldbomb"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EldBombEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build());
        SAMHAIN = Registry.register(Registries.ENTITY_TYPE, Identifier.of(KnightQuest.MOD_ID, "samhain"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SamhainEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build());
        RATMAN = Registry.register(Registries.ENTITY_TYPE, Identifier.of(KnightQuest.MOD_ID, "ratman"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, RatmanEntity::new).dimensions(EntityDimensions.fixed(1.2f, 1.2f)).build());
        SWAMPMAN = Registry.register(Registries.ENTITY_TYPE, Identifier.of(KnightQuest.MOD_ID, "swampman"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SwampmanEntity::new).dimensions(EntityDimensions.fixed(1f, 2f)).build());
        ELDKNIGHT = Registry.register(Registries.ENTITY_TYPE, Identifier.of(KnightQuest.MOD_ID, "eldknight"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EldKnightEntity::new).dimensions(EntityDimensions.fixed(1f, 2f)).build());
        LIZZY = Registry.register(Registries.ENTITY_TYPE, Identifier.of(KnightQuest.MOD_ID, "lizzy"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, LizzyEntity::new).dimensions(EntityDimensions.fixed(1f, 0.3f)).build());
        BADPATCH = Registry.register(Registries.ENTITY_TYPE, Identifier.of(KnightQuest.MOD_ID, "bad_patch"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BadPatchEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build());
        GHOSTY = Registry.register(Registries.ENTITY_TYPE, Identifier.of(KnightQuest.MOD_ID, "ghosty"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GhostyEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build());
        SHIELD = Registry.register(Registries.ENTITY_TYPE, Identifier.of(KnightQuest.MOD_ID, "ghastling"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ShieldEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build());
    }

}
