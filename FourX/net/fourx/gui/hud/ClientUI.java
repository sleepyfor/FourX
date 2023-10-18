package net.fourx.gui.hud;

import lombok.var;
import net.fourx.Client;
import net.fourx.addon.addons.ToggleSprint;
import net.fourx.gui.hud.keystrokes.Keystrokes;
import net.fourx.utils.render.CustomFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.Sys;

public class ClientUI extends GuiIngame {
    private final Minecraft mc = Minecraft.getMinecraft();

    public ClientUI(Minecraft minecraft) {
        super(minecraft);
    }

    @Override
    public void renderGameOverlay(float partialTicks) {
        super.renderGameOverlay(partialTicks);
        var color = Client.INSTANCE.getClientColor();
        Gui.drawRect(0,0,0,0,0);
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        var font = Client.INSTANCE.getFontManager().getArial17();
        if(Client.INSTANCE.getAddonManager().getAddon(ToggleSprint.class).isState())
            font.drawStringWithShadow("Sprinting (Toggled)", scaledresolution.getScaledWidth() - font.getWidth("Sprinting (Toggled)"), 2, color);
        Keystrokes.drawKeystrokes(2, 2);
    }
}
