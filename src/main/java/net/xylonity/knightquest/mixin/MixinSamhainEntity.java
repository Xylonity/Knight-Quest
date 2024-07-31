package net.xylonity.knightquest.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.xylonity.knightquest.common.entity.boss.NethermanTeleportChargeEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;

@Mixin(BossHealthOverlay.class)
@OnlyIn(Dist.CLIENT)
public abstract class MixinSamhainEntity {

//
    //@Shadow @Final private static ResourceLocation GUI_BARS_LOCATION;
    //@Shadow private final Minecraft minecraft;
    //@Shadow final Map<UUID, LerpingBossEvent> events;
//
    //protected MixinSamhainEntity(Minecraft minecraft, Map<UUID, LerpingBossEvent> events) {
    //    this.minecraft = minecraft;
    //    this.events = events;
    //}
//
    //private boolean isWitherBossEvent(BossEvent event) {
    //    return event.getName().getString().equals("Wither");
    //}
//
    ///**
    // * @author
    // * @reason
    // */
    //@Overwrite
    //private void drawBar(GuiGraphics pGuiGraphics, int pX, int pY, BossEvent pBossEvent) {
    //    ResourceLocation customTexture = GUI_BARS_LOCATION;
    //    if (isWitherBossEvent(pBossEvent)) {
    //        customTexture = new ResourceLocation("knightquest:textures/armor/apple_helmet.png");
    //    }
//
    //    //this.drawBar(pGuiGraphics, pX, pY, pBossEvent, 182, 0);
    //    //int i = (int)(pBossEvent.getProgress() * 183.0F);
    //    //if (i > 0) {
    //    //    pGuiGraphics.blit(customTexture, pX, pY, 0, 5, i, 5);
    //    //}
//
    //    //if (pBossEvent.getOverlay() != BossEvent.BossBarOverlay.PROGRESS) {
    //    //    RenderSystem.enableBlend();
    //    //    pGuiGraphics.blit(customTexture, pX, pY, 0, 10, 182, 5);
    //    //    RenderSystem.disableBlend();
    //    //}
//
    //    if (isWitherBossEvent(pBossEvent)) {
    //        this.drawBar(pGuiGraphics, pX, pY, pBossEvent, 182, 0);
    //        int i = (int)(pBossEvent.getProgress() * 183.0F);
    //        if (i > 0) {
    //            pGuiGraphics.blit(customTexture, pX, pY, 0, 5, i, 5);
    //        }
    //    } else {
    //        this.drawBar(pGuiGraphics, pX, pY, pBossEvent, 182, 0);
    //        int i = (int) (pBossEvent.getProgress() * 183.0F);
    //        if (i > 0) {
    //            this.drawBar(pGuiGraphics, pX, pY, pBossEvent, i, 5);
    //        }
    //    }
    //}
//
    ///**
    // * @author
    // * @reason
    // */
    //@Overwrite
    //private void drawBar(GuiGraphics pGuiGraphics, int pX, int pY, BossEvent pBossEvent, int pWidth, int p_281636_) {
//
    //    ResourceLocation customTexture = GUI_BARS_LOCATION;
    //    if (isWitherBossEvent(pBossEvent)) {
    //        customTexture = new ResourceLocation("knightquest:textures/armor/apple_helmet.png");
    //    }
//
    //    if (isWitherBossEvent(pBossEvent)) {
    //        if (pBossEvent.getOverlay() != BossEvent.BossBarOverlay.PROGRESS) {
    //            RenderSystem.enableBlend();
    //            pGuiGraphics.blit(customTexture, pX, pY, 0, 10, 182, 5);
    //            RenderSystem.disableBlend();
    //        }
    //    } else {
    //        pGuiGraphics.blit(GUI_BARS_LOCATION, pX, pY, 0, pBossEvent.getColor().ordinal() * 5 * 2 + p_281636_, pWidth, 5);
    //        if (pBossEvent.getOverlay() != BossEvent.BossBarOverlay.PROGRESS) {
    //            RenderSystem.enableBlend();
    //            pGuiGraphics.blit(GUI_BARS_LOCATION, pX, pY, 0, 80 + (pBossEvent.getOverlay().ordinal() - 1) * 5 * 2 + p_281636_, pWidth, 5);
    //            RenderSystem.disableBlend();
    //        }
    //    }
//
    //}

    //@Shadow @Final private Level level;
    //@Shadow @Final private double x;
    //@Shadow @Final private double y;
    //@Shadow @Final private double z;
    //@Shadow @Final private float radius;
    //@Shadow @Final private Explosion.BlockInteraction blockInteraction;
    //@Shadow @Final private Entity source;
//
    //@Shadow @Nullable public abstract Entity getExploder();
//
    //@Inject(method = "finalizeExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"), cancellable = true)
    //private void finalizeExplosion(boolean pSpawnParticles, CallbackInfo ci) {
    //    System.out.println(source);
    //    for (Player player : level.players()) {
    //        player.sendSystemMessage(Component.literal(getExploder() + " <-~"));
    //    }
    //    if (source instanceof NethermanTeleportChargeEntity) {
    //        ci.cancel();
    //    }
    //}
//
    ////private void createCustomExplosionParticles() {
    ////    for (int i = 0; i < 100; i++) {
    ////        double offsetX = (this.level.random.nextDouble() - 0.5) * 2.0;
    ////        double offsetY = (this.level.random.nextDouble() - 0.5) * 2.0;
    ////        double offsetZ = (this.level.random.nextDouble() - 0.5) * 2.0;
    ////        this.level.addParticle(ParticleTypes.DRAGON_BREATH, this.level.random.nextDouble(), this.level.random.nextDouble(), this.level.random.nextDouble(), offsetX, offsetY, offsetZ);
    ////    }
    ////}

}