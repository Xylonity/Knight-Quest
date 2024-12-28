package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuestCommon;
import dev.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import dev.xylonity.knightquest.common.entity.boss.NethermanProjectileChargeEntity;
import dev.xylonity.knightquest.common.entity.entities.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.BiConsumer;
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
    public static final Supplier<EntityType<NethermanProjectileChargeEntity>> NETHERMAN_PROJECTILE_CHARGE;
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
        NETHERMAN_PROJECTILE_CHARGE = register("netherman_projectile_charge", NethermanProjectileChargeEntity::new, MobCategory.MISC, 0.5f, 0.5f);
        SWAMPMAN_AXE = register("swampman_axe", SwampmanAxeEntity::new, MobCategory.MISC, 0.3f, 1f);
    }

    private static <X extends Entity> Supplier<EntityType<X>> register(String name, EntityType.EntityFactory<X> entity, MobCategory spawnGroup, float width, float height) {
        return KnightQuestCommon.COMMON_PLATFORM.registerEntity(name, () -> EntityType.Builder.of(entity, spawnGroup).sized(width, height).build(name));
    }

    /**
     * Sets attributes to every entity defined in the scope.
     */

    public static void registerEntityAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> registrar) {
        registrar.accept(KnightQuestEntities.GREMLIN.get(), GremlinEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.ELDBOMB.get(), EldBombEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.ELDKNIGHT.get(), EldKnightEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.SWAMPMAN.get(), SwampmanEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.RATMAN.get(), RatmanEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.SAMHAIN.get(), SamhainEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.LIZZY.get(), LizzyEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.BADPATCH.get(), BadPatchEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.SHIELD.get(), GhastlingEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.MOMMA_LIZZY.get(), MommaLizzyEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.GHOSTY.get(), GhostyEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.NETHERMAN.get(), NethermanEntity.setAttributes().build());
        registrar.accept(KnightQuestEntities.NETHERMAN_CLONE.get(), NethermanCloneEntity.setAttributes().build());
    }

}
