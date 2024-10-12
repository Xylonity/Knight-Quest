package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.KnightQuestCommon;
import dev.xylonity.knightquest.common.entity.boss.*;
import dev.xylonity.knightquest.common.entity.entities.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
        NETHERMAN_TELEPORT_CHARGE = register("netherman_teleport_charge", NethermanTeleportChargeEntity::new, MobCategory.MISC, 0.5f, 0.5f);
        SWAMPMAN_AXE = register("swampman_axe", SwampmanAxeEntity::new, MobCategory.MISC, 0.3f, 1f);
    }

    private static <X extends Entity> RegistryObject<EntityType<X>> register(String name, EntityType.EntityFactory<X> entity, MobCategory category, float width, float height) {
        return ENTITY.register(name, () -> EntityType.Builder.of(entity, category).sized(width, height).build(String.valueOf(new ResourceLocation(KnightQuest.MOD_ID, name))));
    }

    private static <X extends Mob> RegistryObject<Item> registerSpawnEggItem(String id, Supplier<EntityType<X>> entityType, int primaryEggColour, int secondaryEggColour) {
        return KnightQuest.ITEMS.register(id, () -> new ForgeSpawnEggItem(entityType, primaryEggColour, secondaryEggColour, new Item.Properties()));
    }

    public static final RegistryObject<Item> GREMLIN_EGG = registerSpawnEggItem("gremlin_spawn_egg", KnightQuestEntities.GREMLIN, 0xc22f26, 0x45545d);
    public static final RegistryObject<Item> ELD_BOMB_EGG = registerSpawnEggItem("eldbomb_spawn_egg", KnightQuestEntities.ELDBOMB, 0x43404e, 0x81da25);
    public static final RegistryObject<Item> ELD_KNIGHT_EGG = registerSpawnEggItem("eldknight_spawn_egg", KnightQuestEntities.ELDKINGHT, 0x7f8ab2, 0x8a392e);
    public static final RegistryObject<Item> RATMAN_EGG = registerSpawnEggItem("ratman_spawn_egg", KnightQuestEntities.RATMAN, 0x3a303d, 0xb75383);
    public static final RegistryObject<Item> SAMHAIN_EGG = registerSpawnEggItem("samhain_spawn_egg", KnightQuestEntities.SAMHAIN, 0xfdde03, 0x982938);
    public static final RegistryObject<Item> SWAMPMAN_EGG = registerSpawnEggItem("swampman_spawn_egg", KnightQuestEntities.SWAMPMAN, 0x108773, 0x9e304f);
    public static final RegistryObject<Item> LIZZY_EGG = registerSpawnEggItem("lizzy_spawn_egg", KnightQuestEntities.LIZZY, 0x0babf2, 0xd1802b);
    public static final RegistryObject<Item> BADPATCH_EGG = registerSpawnEggItem("bad_patch_spawn_egg", KnightQuestEntities.BADPATCH, 0xec160b, 0xeff1f8);
    public static final RegistryObject<Item> GHOSTY_EGG = registerSpawnEggItem("ghosty_spawn_egg", KnightQuestEntities.GHOSTY, 0x2cb87e, 0xfbe105);
    public static final RegistryObject<Item> NETHERMAN_EGG = registerSpawnEggItem("netherman_spawn_egg", KnightQuestEntities.NETHERMAN, 0xebedec, 0xc3c3c3);
    public static final RegistryObject<Item> MOMMA_LIZZY_EGG = registerSpawnEggItem("momma_lizzy_spawn_egg", KnightQuestEntities.MOMMA_LIZZY, 0x0babf2, 0x9f5b14);

}
