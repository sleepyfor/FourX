package net.minecraft.client.gui;

import com.google.common.collect.Lists;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.microsoft.model.response.MinecraftProfile;
import net.fourx.Client;
import net.fourx.utils.render.RenderingUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Client.INSTANCE.updateRPC("Main Menu", "In The Menus");
        ScaledResolution sr = new ScaledResolution(mc);
        RenderingUtils.glColor(RenderingUtils.getRainbow(-16).getRGB());
        RenderingUtils.drawImg(new ResourceLocation("images/newbackground.jpg"), 0, 0, width, height);
        RenderingUtils.drawImg(new ResourceLocation("images/fourxlogo.png"), (double) sr.getScaledWidth() / 2 - 244,
                (double) sr.getScaledHeight() / 2 - 262, 420, 420);
        GlStateManager.color(1, 1, 1, 1);
        Client.INSTANCE.getFontManager().getArial17().drawStringWithShadow("FourX, the continuation of Destiny!", 4, height - 16, Client.INSTANCE.getClientColor());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void initGui() {
        addButtons();
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        int id = button.id;
        switch (id) {
            case 0:
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 1:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 3:
                try {
                    MicrosoftAuthenticator microsoftAuthenticator = new MicrosoftAuthenticator();
                    MicrosoftAuthResult result = microsoftAuthenticator.loginWithWebview();
                    MinecraftProfile profile = result.getProfile();
                    mc.session = new Session(profile.getName(), profile.getId(), result.getAccessToken(), "microsoft");
                } catch (MicrosoftAuthenticationException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                this.mc.shutdown();
                break;
        }
    }

    private void addButtons() {
        ScaledResolution sr = new ScaledResolution(mc);
        this.buttonList.add(new GuiButton(0, sr.scaledWidth / 2 - 102, sr.scaledHeight / 2 + 5, 100, 17,
                "Play Offline"));
        this.buttonList.add(new GuiButton(1, sr.scaledWidth / 2 + 2, sr.scaledHeight / 2 + 5, 100, 17,
                "Play Online"));
        this.buttonList.add(new GuiButton(2, sr.scaledWidth / 2 - 102, sr.scaledHeight / 2 + 26, 100, 18,
                "Settings"));
        this.buttonList.add(new GuiButton(3, sr.scaledWidth / 2 + 2, sr.scaledHeight / 2 + 26, 100, 18,
                "Account Login"));
        this.buttonList.add(new GuiButton(4, sr.scaledWidth / 2 - 102, sr.scaledHeight / 2 + 48, 204, 18,
                "Rage Quit to desktop..."));
    }
}
