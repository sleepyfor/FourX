package net.fourx.gui.joingames;

import lombok.var;
import net.fourx.Client;
import net.fourx.addon.Addon;
import net.fourx.gui.toggle.ToggleGUI;
import net.fourx.utils.render.RenderingUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class JoinButton extends Component {

    public String command, text;
    public double slide;

    public JoinButton(double x, double y, double width, double height, JoinGamesGui parent, String command, String text, int color) {
        super(x, y, width, height, parent, color);
        this.command = command;
        this.text = text;
        slide = 0;
    }

    @Override
    public void onPressed(int key) {



        super.onPressed(key);

        Minecraft.getMinecraft().thePlayer.sendChatMessage(command);
    }

    @Override
    public void drawComponent(int mouseX, int mouseY, boolean hovered) {
        var font = Client.INSTANCE.getFontManager().getArial17();
        Gui.drawRect(x, y, x + width, y + height, new Color(25, 25, 25, 255).getRGB());
        if (hovered)
            slide = RenderingUtils.progressiveAnimation(slide, width, 0.6);
        else
            slide = RenderingUtils.progressiveAnimation(slide, 0, 0.6);
        Gui.drawRect(x, y + height - 2, x + slide, y + height, color);
        RenderingUtils.drawBlurredRect(RenderingUtils.BlurType.NORMAL, x, y, x + width, y + height, -1);
        font.drawCenteredStringWithShadow(text, (int) (x + width / 2), (int) (y + height / 2) - 4, color);
    }
}
