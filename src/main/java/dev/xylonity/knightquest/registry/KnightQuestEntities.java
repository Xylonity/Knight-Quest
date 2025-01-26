package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import dev.xylonity.knightquest.common.entity.boss.NethermanProjectileChargeEntity;
import dev.xylonity.knightquest.common.entity.entities.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

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
    //public static final RegistryObject<EntityType<MommaLizzyEntity>> MOMMA_LIZZY;
    public static final RegistryObject<EntityType<GhostyEntity>> GHOSTY;
    public static final RegistryObject<EntityType<NethermanEntity>> NETHERMAN;
    public static final RegistryObject<EntityType<NethermanCloneEntity>> NETHERMAN_CLONE;
    public static final RegistryObject<EntityType<NethermanProjectileChargeEntity>> NETHERMAN_PROJECTILE_CHARGE;
    public static final RegistryObject<EntityType<SwampmanAxeEntity>> SWAMPMAN_AXE;
    public static final Supplier<EntityType<FallenKnightEntity>> FALLEN_KNIGHT;

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
       //MOMMA_LIZZY = register("momma_lizzy", MommaLizzyEntity::new, MobCategory.MONSTER, 1f, 0.3f);
        GHOSTY = register("ghosty", GhostyEntity::new, MobCategory.MONSTER, 1f, 1f);
        NETHERMAN = register("netherman", NethermanEntity::new, MobCategory.MONSTER, 0.8f, 2.8f);
        NETHERMAN_CLONE = register("netherman_clone", NethermanCloneEntity::new, MobCategory.MONSTER, 0.8f, 2.8f);
        NETHERMAN_PROJECTILE_CHARGE = register("netherman_projectile_charge", NethermanProjectileChargeEntity::new, MobCategory.MISC, 0.5f, 0.5f);
        SWAMPMAN_AXE = register("swampman_axe", SwampmanAxeEntity::new, MobCategory.MISC, 0.3f, 1f);
        FALLEN_KNIGHT = register("fallen_knight", FallenKnightEntity::new, MobCategory.MONSTER , 1f, 2f);
    }

    private static <X extends Entity> RegistryObject<EntityType<X>> register(String name, EntityType.EntityFactory<X> entity, MobCategory category, float width, float height) {
        return ENTITY.register(name, () -> EntityType.Builder.of(entity, category).sized(width, height).build(String.valueOf(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, name))));
    }

}
