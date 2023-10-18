package net.fourx.gui.hud.keystrokes;

import lombok.var;
import net.fourx.Client;
import net.fourx.utils.render.RenderingUtils;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Keystrokes {

    private static final Minecraft mc = Minecraft.getMinecraft();
    public static double opacityF, opacityB, opacityL, opacityR;

    public static void drawKeystrokes(float x, float y){
        opacityF = RenderingUtils.progressiveAnimation(opacityF, mc.gameSettings.keyBindForward.pressed ? 255 : 10, 0.2);
        opacityB = RenderingUtils.progressiveAnimation(opacityB, mc.gameSettings.keyBindBack.pressed ? 255 : 10, 0.2);
        opacityL = RenderingUtils.progressiveAnimation(opacityL, mc.gameSettings.keyBindLeft.pressed ? 255 : 10, 0.2);
        opacityR = RenderingUtils.progressiveAnimation(opacityR, mc.gameSettings.keyBindRight.pressed ? 255 : 10, 0.2);
        var font = Client.INSTANCE.getFontManager().getArial17();
        var color = Client.INSTANCE.getClientColor();
        RenderingUtils.drawBorderedRectangle(x + 22, y, x + 42, y + 20, 0.5f, new Color((int) opacityF, (int) opacityF, (int) opacityF, 90).getRGB(), color);
        RenderingUtils.drawBorderedRectangle(x, y + 22, x + 20, y + 42, 0.5f, new Color((int) opacityL, (int) opacityL, (int) opacityL, 90).getRGB(), color);
        RenderingUtils.drawBorderedRectangle(x + 22, y + 22, x + 42, y + 42, 0.5f, new Color((int) opacityB, (int) opacityB, (int) opacityB, 90).getRGB(), color);
        RenderingUtils.drawBorderedRectangle(x + 44, y + 22, x + 64, y + 42, 0.5f, new Color((int) opacityR, (int) opacityR, (int) opacityR, 90).getRGB(), color);
        font.drawCenteredStringWithShadow(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode()), x + 32.5f, y + 6.5f, color);
        font.drawCenteredStringWithShadow(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode()), x + 10.5f, y + 28.5f, color);
        font.drawCenteredStringWithShadow(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode()), x + 32.5f, y + 28.5f, color);
        font.drawCenteredStringWithShadow(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode()), x + 54.5f, y + 28.5f, color);
    }
}
