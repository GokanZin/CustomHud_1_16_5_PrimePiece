package br.com.gokan.customhud.events;

import br.com.gokan.customhud.Main;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
//import handler.PlayerCapabilityHandler;
import handler.PlayerCapabilityHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HudEvents {


    private static final ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/hud/hud3.png");


    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            event.setCanceled(true);
        }
        
    }


    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {

            Minecraft minecraft = Minecraft.getInstance();
            PlayerEntity player = minecraft.player;
            if (player == null) return;
            FontRenderer fontRenderer = minecraft.font;
            MatrixStack matrixStack = event.getMatrixStack();
            render(minecraft, fontRenderer, player, event, matrixStack);
        }
    }


    public static void render(Minecraft minecraft, FontRenderer render, PlayerEntity player, RenderGameOverlayEvent event, MatrixStack matrixStack) {
        if (minecraft.player != null && !Minecraft.getInstance().options.hideGui && !Minecraft.getInstance().options.renderDebug) {
            hudBar(minecraft, render, player, event, matrixStack);
            vidaBar(minecraft, render, player, event, matrixStack);
            staminaBar(minecraft, render, player, event, matrixStack);
            redeWorldText(minecraft, render, player, event, matrixStack);

        }
    }


    private static void redeWorldText(Minecraft minecraft, FontRenderer render, PlayerEntity player, RenderGameOverlayEvent event, MatrixStack matrixStack) {
        minecraft.font.draw(matrixStack, "§lREDE WORLD", 70, 18, 0xFFFFFF);
    }

    private static void staminaBar(Minecraft minecraft, FontRenderer render, PlayerEntity player, RenderGameOverlayEvent event, MatrixStack matrixStack) {
        minecraft.getTextureManager().bind(texture);
        int staminaMax = PlayerCapabilityHandler.getMaxStamina(player).intValue();
        int stamina = PlayerCapabilityHandler.getStamina(player).intValue();
        int result = calcularTamanhoBarra((int) stamina, staminaMax, 149);
        RenderSystem.enableBlend();
        AbstractGui.blit(matrixStack, 10, 31+10, 0, 55, result, 6, 256, 256); // Coordenadas x, y e tamanhos da textura
        RenderSystem.disableBlend();
    }

    private static void vidaBar(Minecraft minecraft, FontRenderer render, PlayerEntity player, RenderGameOverlayEvent event, MatrixStack matrixStack) {
        minecraft.getTextureManager().bind(texture);
        int maxVida = PlayerCapabilityHandler.getMaxHealth(player).intValue();
        float vida = player.getHealth();
        int result = calcularTamanhoBarra((int) vida, maxVida, 147);
        RenderSystem.enableBlend();
        AbstractGui.blit(matrixStack, 10, 31, 0, 45, result, 10, 256, 256); // Coordenadas x, y e tamanhos da textura
        RenderSystem.disableBlend();
        matrixStack.pushPose();
        matrixStack.scale(0.7F, 0.7F, 1.0F); // Diminuir para 50% do tamanho original
        minecraft.font.draw(matrixStack, "Vida:  " + vida, 59, 18 + 27, 0xFFFFFF);
        matrixStack.popPose();
    }

    private static void hudBar(Minecraft minecraft, FontRenderer render, PlayerEntity player, RenderGameOverlayEvent event, MatrixStack matrixStack) {
        minecraft.getTextureManager().bind(texture);
        RenderSystem.enableBlend();
        AbstractGui.blit(matrixStack, 10, 10, 0, 0, 170, 45, 256, 256); // Coordenadas x, y e tamanhos da textura
        RenderSystem.disableBlend();
    }


    public static int calcularTamanhoBarra(int vidaAtual, int vidaMaxima, int tamanhoMaximo) {
        if (vidaAtual < 0) vidaAtual = 0;
        if (vidaAtual > vidaMaxima) vidaAtual = vidaMaxima;
        return (int) ((vidaAtual / (double) vidaMaxima) * tamanhoMaximo);
    }
}
