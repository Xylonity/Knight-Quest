package net.xylonity.knightquest.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import net.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.xylonity.knightquest.common.entity.boss.NethermanTeleportChargeEntity;
import net.xylonity.knightquest.common.entity.entities.*;

public class KnightQuestEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY =
            DeferredRegister.create(Registries.ENTITY_TYPE, KnightQuest.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<GremlinEntity>> GREMLIN;
    public static final DeferredHolder<EntityType<?>, EntityType<EldBombEntity>> ELDBOMB;
    public static final DeferredHolder<EntityType<?>, EntityType<EldKnightEntity>> ELDKINGHT;
    public static final DeferredHolder<EntityType<?>, EntityType<SwampmanEntity>> SWAMPMAN;
    public static final DeferredHolder<EntityType<?>, EntityType<SamhainEntity>> SAMHAIN;
    public static final DeferredHolder<EntityType<?>, EntityType<RatmanEntity>> RATMAN;
    public static final DeferredHolder<EntityType<?>, EntityType<LizzyEntity>> LIZZY;
    public static final DeferredHolder<EntityType<?>, EntityType<BadPatchEntity>> BADPATCH;
    public static final DeferredHolder<EntityType<?>, EntityType<GhastlingEntity>> SHIELD;
    public static final DeferredHolder<EntityType<?>, EntityType<MommaLizzyEntity>> MOMMA_LIZZY;
    public static final DeferredHolder<EntityType<?>, EntityType<GhostyEntity>> GHOSTY;
    public static final DeferredHolder<EntityType<?>, EntityType<NethermanEntity>> NETHERMAN;
    public static final DeferredHolder<EntityType<?>, EntityType<NethermanCloneEntity>> NETHERMAN_CLONE;
    public static final DeferredHolder<EntityType<?>, EntityType<NethermanTeleportChargeEntity>> NETHERMAN_TELEPORT_CHARGE;
    public static final DeferredHolder<EntityType<?>, EntityType<SwampmanAxeEntity>> SWAMPMAN_AXE;

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

    private static <X extends Entity> DeferredHolder<EntityType<?>, EntityType<X>> register(String name, EntityType.EntityFactory<X> entity, MobCategory category, float width, float height) {
        return ENTITY.register(name, () -> EntityType.Builder.of(entity, category).sized(width, height).build(String.valueOf(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, name))));
    }

}
