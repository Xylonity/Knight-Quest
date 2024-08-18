package net.xylonity.knightquest.datagen;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.xylonity.knightquest.config.values.KQConfigValues;
import net.xylonity.knightquest.registry.KnightQuestItems;

import java.util.List;
import java.util.Objects;

public class KQAddItemModifier extends LootModifier {
    private final Item addition;

    protected KQAddItemModifier(LootItemCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @Override
    protected List<ItemStack> doApply(List<ItemStack> list, LootContext lootContext) {

        if (addition == KnightQuestItems.SMALL_ESSENCE.get() && lootContext.getRandom().nextFloat() <= KQConfigValues.DROP_CHANCE_SMALL_ESSENCE)
            list.add(new ItemStack(this.addition));

        if (addition == KnightQuestItems.RATMAN_EYE.get() && lootContext.getRandom().nextFloat() <= KQConfigValues.DROP_CHANCE_RATMAN_EYE)
            list.add(new ItemStack(this.addition));

        if (addition == KnightQuestItems.LIZZY_SCALE.get() && lootContext.getRandom().nextFloat() <= KQConfigValues.DROP_CHANCE_LIZZY_SCALE)
            list.add(new ItemStack(this.addition));

        return list;
    }

    public static class Serializer extends GlobalLootModifierSerializer<KQAddItemModifier> {

        @Override
        public KQAddItemModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "addition")));

            return new KQAddItemModifier(conditionsIn, addition);
        }

        @Override
        public JsonObject write(KQAddItemModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(instance.addition)).toString());
            return json;
        }
    }

}