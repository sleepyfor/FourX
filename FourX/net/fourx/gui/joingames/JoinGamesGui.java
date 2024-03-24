package net.fourx.gui.joingames;

import lombok.val;
import lombok.var;
import net.fourx.Client;
import net.fourx.addon.addons.JoinGames;
import net.fourx.gui.toggle.ToggleGUI;
import net.fourx.gui.toggle.component.impl.ToggleButton;
import net.fourx.utils.render.RenderingUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JoinGamesGui extends GuiScreen {

    public List<JoinButton> buttons = new ArrayList<>();
    public ToggleGUI toggleGUI = new ToggleGUI();

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);
        float x = ((float) sr.getScaledWidth() / 2) - 152;
        float y = ((float) sr.getScaledHeight() / 2) - 127;
        float width = 246, height = 250;
        if (mc.currentScreen == this)
            RenderingUtils.drawBlurredRect(RenderingUtils.BlurType.NORMAL, 0, 0, sr.scaledWidth, sr.scaledHeight, -1);
        RenderingUtils.drawBorderCorneredRectangle(x, y, x + width + 4, y + height + 4,
                1, new Color(10, 10, 10, 250).getRGB(), Client.INSTANCE.getClientColor());
        RenderingUtils.drawBlurredRect(RenderingUtils.BlurType.NORMAL,x, y, x + width + 4, y + height + 4, -1);
        Client.INSTANCE.getFontManager().getArial17().drawCenteredStringWithShadow("Join Bedwars", x + (width / 2), y + 9, -1);
        Client.INSTANCE.getFontManager().getArial17().drawCenteredStringWithShadow("Join Skywars", x + (width / 2), y + 70, -1);
        Client.INSTANCE.getFontManager().getArial17().drawCenteredStringWithShadow("Join Murder Mystery", x + (width / 2), y + 132, -1);
        Client.INSTANCE.getFontManager().getArial17().drawCenteredStringWithShadow("Join Other Games", x + (width / 2), y + 172, -1);
        for (val button : buttons){
            var hovered = isHovered(mouseX, mouseY, (float) button.x, (float) button.y, (float) button.width, (float) button.height);
            button.color = hovered ? Client.INSTANCE.getClientColor() : -1;
            button.drawComponent(mouseX, mouseY, hovered);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (val button : buttons)
            if(isHovered(mouseX, mouseY, (float) button.x, (float) button.y, (float) button.width, (float) button.height))
                button.onPressed(mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void initGui() {
        ScaledResolution sr = new ScaledResolution(mc);
        float x = ((float) sr.getScaledWidth() / 2) - 150;
        float y = ((float) sr.getScaledHeight() / 2) - 125;
        buttons.add(new JoinButton((int) (x + 2), (int) (y + 20), 120, 20, this, "/play bedwars_eight_one", "Solo Bedwars", -1));
        buttons.add(new JoinButton((int) (x + 124), (int) (y + 20), 120, 20, this, "/play bedwars_eight_two", "Doubles Bedwars", -1));
        buttons.add(new JoinButton((int) (x + 2), (int) (y + 42), 120, 20, this, "/play bedwars_four_three", "Triples Bedwars", -1));
        buttons.add(new JoinButton((int) (x + 124), (int) (y + 42), 120, 20, this, "/play bedwars_four_four", "Fours Bedwars", -1));
        buttons.add(new JoinButton((int) (x + 2), (int) (y + 82), 120, 20, this, "/play solo_normal", "Solo Skywars Normal", -1));
        buttons.add(new JoinButton((int) (x + 124), (int) (y + 82), 120, 20, this, "/play solo_insane", "Solo Skywars Insane", -1));
        buttons.add(new JoinButton((int) (x + 2), (int) (y + 104), 120, 20, this, "/play teams_normal", "Doubles Skywars Normal", -1));
        buttons.add(new JoinButton((int) (x + 124), (int) (y + 104), 120, 20, this, "/play teams_insane", "Doubles Skywars Insane", -1));
        buttons.add(new JoinButton((int) (x + 2), (int) (y + 144), 120, 20, this, "/play murder_classic", "Murder Mystery Solo", -1));
        buttons.add(new JoinButton((int) (x + 124), (int) (y + 144), 120, 20, this, "/play murder_double_up", "Murder Mystery Double Up", -1));
        buttons.add(new JoinButton((int) (x + 2), (int) (y + 184), 120, 20, this, "/play pit", "The Pit", -1));
        buttons.add(new JoinButton((int) (x + 124), (int) (y + 184), 120, 20, this, "/play sb", "Skyblock", -1));
        super.initGui();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            Client.INSTANCE.getAddonManager().getAddon(JoinGames.class).setState(false);
            mc.displayGuiScreen(new ToggleGUI());
        }
    }

    public boolean isHovered(int mouseX, int mouseY, float x, float y, float width, float height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }
}
