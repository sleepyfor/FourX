package net.fourx.gui.toggle;

import lombok.val;
import lombok.var;
import net.fourx.Client;
import net.fourx.addon.Addon;
import net.fourx.addon.addons.*;
import net.fourx.gui.joingames.JoinButton;
import net.fourx.gui.toggle.component.impl.ToggleButton;
import net.fourx.utils.render.RenderingUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class ToggleGUI extends GuiScreen {

    public float x, y, width = 246, height = 250,anchorX, anchorY, subX, subY, subWidth, subHeight;
    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    public List<ToggleButton> addons = new ArrayList<>();

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (mc.currentScreen == this)
            RenderingUtils.drawBlurredRect(RenderingUtils.BlurType.NORMAL, 0, 0, sr.scaledWidth, sr.scaledHeight, -1);
        subX = (float) RenderingUtils.progressiveAnimation(subX, 0, 0.8);
        subY = (float) RenderingUtils.progressiveAnimation(subY, 0, 0.8);
        subWidth = (float) RenderingUtils.progressiveAnimation(subWidth, width + 1, 0.8);
        subHeight = (float) RenderingUtils.progressiveAnimation(subHeight, height + 1, 0.8);
        RenderingUtils.makeCropBox((int) (x + subX) - 3, (int) (y + subY) -2 , (int) (x+ width + subWidth) + 2, (int) (y + height + subHeight) + 2);
        RenderingUtils.drawBorderCorneredRectangle(x + subX - 2, y + subY - 2, x + width + width +2,
                y + height + height +2, 1, new Color(10, 10, 10, 250).getRGB(), Client.INSTANCE.getClientColor());
        RenderingUtils.drawBlurredRect(RenderingUtils.BlurType.NORMAL, x + subX - 2, y + subY - 2, x + width + width +2, y + height + height +2, -1);
        for (ToggleButton addon : addons) {
            var hovered = isHovered(mouseX, mouseY, (float) addon.x, (float) addon.y, (float) addon.width, (float) addon.height);
            addon.color = addon.addon.isState() ? Client.INSTANCE.getClientColor() : (hovered ? Client.INSTANCE.getClientColor() : -1);
            addon.drawComponent(mouseX, mouseY, hovered);
        }
       RenderingUtils.destroyCropBox();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        x = ((float) sr.getScaledWidth() / 2) - 150;
        y = ((float) sr.getScaledHeight() / 2) - 125;
        subX = width / 2;
        subY = height / 2;
        subWidth = 0;
        subHeight = 0;
        width = width / 2;
        height = height / 2;
        anchorX = x;
        anchorY = y;
        addons.add(new ToggleButton(x + 2, y + 2, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(ToggleSprint.class), -1));
        addons.add(new ToggleButton(x + 124, y + 2, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(BiomeHud.class), -1));
        addons.add(new ToggleButton(x + 2, y + 24, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(DirectionHud.class), -1));
        addons.add(new ToggleButton(x + 124, y + 24, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(FPSHud.class), -1));
        addons.add(new ToggleButton(x + 2, y + 46, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(Keystrokes.class), -1));
        addons.add(new ToggleButton(x + 124, y + 46, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(WatermarkHud.class), -1));
        addons.add(new ToggleButton(x + 2, y + 68, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(CordsHud.class), -1));
        addons.add(new ToggleButton(x + 124, y + 68, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(CPSHud.class), -1));
        addons.add(new ToggleButton(x + 2, y + 90, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(PotionDisplay.class), -1));
        addons.add(new ToggleButton(x + 124, y + 90, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(ArmorDisplay.class), -1));
        addons.add(new ToggleButton(x + 2, y + 112, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(BedwarsAddon.class), -1));
        addons.add(new ToggleButton(x + 124, y + 112, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(JoinGames.class), -1));
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (ToggleButton addon : addons)
            if(isHovered(mouseX, mouseY, (float) addon.x, (float) addon.y, (float) addon.width, (float) addon.height))
                addon.onPressed(mouseButton);
    }

    public boolean isHovered(int mouseX, int mouseY, float x, float y, float width, float height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }
}
