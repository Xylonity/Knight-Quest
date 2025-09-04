package dev.xylonity.knightquest.common.item;

import dev.xylonity.knightlib.api.IGreatChaliceInteractable;
import dev.xylonity.knightlib.api.impl.GreatChaliceState;
import dev.xylonity.knightlib.common.blockentity.GreatChaliceBlockEntity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ChaoticEssenceItem extends KnightQuestItem implements IGreatChaliceInteractable {

    public ChaoticEssenceItem(Properties properties, String tooltipInfoName) {
        super(properties, tooltipInfoName);
    }

    @Override
    public int getChargesToApply() {
        return 0;
    }

    @Override
    public boolean canInteract(GreatChaliceBlockEntity greatChaliceBlockEntity, Level level, Player player) {
        return greatChaliceBlockEntity.isFull() && greatChaliceBlockEntity.getState() == GreatChaliceState.NORMAL;
    }

    @Override
    public void onPostInteraction(GreatChaliceBlockEntity chalice, Player player, Level level, BlockHitResult hit) {
        IGreatChaliceInteractable.super.onPostInteraction(chalice, player, level, hit);
        chalice.setState(GreatChaliceState.CHAOTIC);
    }

    @Override
    public @NotNull Set<SoundEvent> getInteractionSounds() {
        return Set.of(SoundEvents.EVOKER_PREPARE_SUMMON);
    }

}