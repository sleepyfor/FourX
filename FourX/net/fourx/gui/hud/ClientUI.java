package net.fourx.gui.hud;

import lombok.var;
import net.fourx.Client;
import net.fourx.addon.addons.ArmorDisplay;
import net.fourx.addon.addons.PotionDisplay;
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
    private final InfoHud info = new InfoHud();

    public ClientUI(Minecraft minecraft) {
        super(minecraft);
    }

    @Override
    public void renderGameOverlay(float partialTicks) {
        super.renderGameOverlay(partialTicks);
        var manager = Client.INSTANCE.getAddonManager();
        var color = Client.INSTANCE.getClientColor();
        Gui.drawRect(0, 0, 0, 0, 0);
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        var font = Client.INSTANCE.getFontManager().getJbm14();
        if (manager.getAddon(ToggleSprint.class).isState())
            font.drawStringWithShadow("Sprinting (Toggled)", scaledresolution.getScaledWidth() - font.getWidth("Sprinting (Toggled)"), 2, -1);
        if (manager.getAddon(net.fourx.addon.addons.Keystrokes.class).isState())
            Keystrokes.drawKeystrokes(scaledresolution.getScaledWidth() - 66, scaledresolution.scaledHeight - 44);
        info.drawInfoHud(2, 3);
        if (manager.getAddon(PotionDisplay.class).isState())
            PotionDisplay.drawPotionStatus(scaledresolution);
        if (manager.getAddon(ArmorDisplay.class).isState())
            ArmorDisplay.drawItemsStatus(scaledresolution);
    }
}
