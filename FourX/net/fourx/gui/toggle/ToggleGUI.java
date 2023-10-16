package net.fourx.gui.toggle;

import lombok.var;
import net.fourx.Client;
import net.fourx.addon.Addon;
import net.fourx.addon.addons.*;
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

public class ToggleGUI extends GuiScreen {
    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    public List<ToggleButton> addons = new ArrayList<>();
    public float x, y, width = 246, height = 250,anchorX, anchorY;
    public float scale;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        scale = (float) RenderingUtils.progressiveAnimation(scale, 1, 0.01);
        RenderingUtils.scale(anchorX + (this.width / 2), (float) anchorY + (this.height / 2), scale);
        Gui.drawRect(x, y, x + width, y + height, new Color(10, 10, 10, 250).getRGB());
        for (ToggleButton addon : addons) {
            var hovered = isHovered(mouseX, mouseY, (float) addon.x, (float) addon.y, (float) addon.width, (float) addon.height);
            addon.color = hovered ? new Color(152, 218, 226, 255).getRGB() : -1;
            addon.drawComponent(mouseX, mouseY, hovered);
        }
        GlStateManager.popMatrix();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        scale = 0;
        x = ((float) sr.getScaledWidth() / 2) - 150;
        y = ((float) sr.getScaledHeight() / 2) - 125;
        anchorX = x;
        anchorY = y;
        addons.add(new ToggleButton(x + 2, y + 2, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(ToggleSprint.class), -1));
        addons.add(new ToggleButton(x + 124, y + 2, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(BiomeHud.class), -1));
        addons.add(new ToggleButton(x + 2, y + 24, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(DirectionHud.class), -1));
        addons.add(new ToggleButton(x + 124, y + 24, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(FPSHud.class), -1));
        addons.add(new ToggleButton(x + 2, y + 46, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(Keystrokes.class), -1));
        addons.add(new ToggleButton(x + 124, y + 46, 120, 20, this, Client.INSTANCE.getAddonManager().getAddon(WatermarkHud.class), -1));
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
