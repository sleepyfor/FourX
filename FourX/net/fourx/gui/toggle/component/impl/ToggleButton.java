package net.fourx.gui.toggle.component.impl;

import lombok.var;
import net.fourx.Client;
import net.fourx.addon.Addon;
import net.fourx.gui.toggle.ToggleGUI;
import net.fourx.gui.toggle.component.Component;
import net.fourx.utils.render.RenderingUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class ToggleButton extends Component {

    private double slide;
    public Addon addon;

    public ToggleButton(double x, double y, double width, double height, ToggleGUI parent, Addon addon, int color) {
        super(x, y, width, height, parent, color);
        this.addon = addon;
        slide = 0;
    }

    @Override
    public void onPressed(int key) {
        super.onPressed(key);
        addon.setState(!addon.isState());
    }

    @Override
    public void drawComponent(int mouseX, int mouseY, boolean hovered) {
        var font = Client.INSTANCE.getFontManager().getArial17();
        Gui.drawRect(x, y, x + width, y + height, new Color(25, 25, 25, 255).getRGB());
        if (hovered)
            slide = RenderingUtils.progressiveAnimation(slide, width, 0.6);
        else
            slide = RenderingUtils.progressiveAnimation(slide, 0, 0.6);
        Gui.drawRect(x, y + height - 1, x + slide, y + height, -1);
        font.drawCenteredStringWithShadow(addon.getName(), (int) (x + width / 2), (int) (y + height / 2) - 4, color);
    }
}
