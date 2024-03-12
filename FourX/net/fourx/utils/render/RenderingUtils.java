package net.fourx.utils.render;

import lombok.var;
import net.fourx.gui.blur.impl.BlurShader;
import net.fourx.gui.blur.impl.KawaseBlur;
import net.fourx.utils.blur.StencilUtility;
import net.fourx.utils.math.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RenderingUtils {

    public static double rainbowState;

    public static void drawRectangle(float startX, float startY, float endX, float endY, int color) {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        glColor(color);
        GL11.glBegin(7);
        GL11.glVertex2f(startX, endY);
        GL11.glVertex2f(endX, endY);
        GL11.glVertex2f(endX, startY);
        GL11.glVertex2f(startX, startY);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void drawBorderedRectangle(float startX, float startY, float endX, float endY, float width, int color, int borderColor) {
       drawRectangle(startX, startY, endX, endY, color);
       drawRectangle(startX + 0.5f - width, startY, startX + 0.5f, endY, borderColor);
       drawRectangle(startX + 0.5f - width, startY + 0.5f - width, endX - 0.5f + width, startY + 0.5f, borderColor);
        drawRectangle(endX - 0.5f, startY, endX - 0.5f + width, endY, borderColor);
        drawRectangle(startX + 0.5f - width, endY - 0.5f, endX - 0.5f + width, endY - 0.5f + width, borderColor);
    }

    public static void drawImg(ResourceLocation loc, double posX, double posY, double width, double height) {
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        Minecraft.getMinecraft().getTextureManager().bindTexture(loc);
        float f = 1.0F / (float)width;
        float f1 = 1.0F / (float)height;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(posX, posY + height, 0.0D).tex(0.0F * f, (0.0F + (float)height) * f1).endVertex();
        worldrenderer.pos(posX + width, posY + height, 0.0D).tex((0.0F + (float)width) * f, (0.0F + (float)height) * f1).endVertex();
        worldrenderer.pos(posX + width, posY, 0.0D).tex((0.0F + (float)width) * f, 0.0F * f1).endVertex();
        worldrenderer.pos(posX, posY, 0.0D).tex(0.0F * f, 0.0F * f1).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
    }

    public static Color getRainbow(final int delay) {
        rainbowState = Math.ceil((double) ((System.currentTimeMillis() + delay) / 8L));
        rainbowState %= 270.0;
        return Color.getHSBColor((float) (rainbowState / 270.0), 0.4f, 1f);
    }

    public static double progressiveAnimation( double now,  double desired,  double speed) {
        double dif = Math.abs(now - desired);
        int fps = Minecraft.getDebugFPS();
        if (dif > 0.0) {
            double animationSpeed = MathUtils.roundToDecimalPlace(Math.min(10.0, Math.max(0.05, 144.0 / fps * (dif / 10.0) * speed)), 0.05);
            if (dif < animationSpeed) {
                animationSpeed = dif;
            }
            if (now < desired) {
                return now + animationSpeed;
            }
            if (now > desired) {
                return now - animationSpeed;
            }
        }
        return now;
    }

    public static void scissorBox(int x, int y, int width, int height) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int factor = sr.getScaleFactor();
        glEnable(GL_SCISSOR_TEST);
        glScissor(x * factor, (sr.getScaledHeight() - (y + height)) * factor, ((x + width) - x) * factor, ((y + height) - y) * factor);
    }

    public static void cropBox(float x, float y, float width, float height) {
        ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft());
        int factor = scale.getScaleFactor();
        glScissor((int) (x * factor), (int) ((scale.getScaledHeight() - height) * factor), (int) ((width - x) * factor), (int) ((height - y) * factor));
    }

    public static void makeCropBox(float left, float top, float right, float bottom) {
        glPushMatrix();
        glEnable(GL_SCISSOR_TEST);
        cropBox(left, top, right, bottom);
    }

    public static void destroyCropBox() {
        glDisable(GL_SCISSOR_TEST);
        glPopMatrix();
    }

    public static void scale(float x, float y, float scale) {
        Gui.drawRect(0,0,0,0,0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.scale(scale, scale, 1);
        GlStateManager.translate(-x, -y, 0);
    }

    public static void resetColor() {
        GlStateManager.color(1, 1, 1, 1);
    }

    public static void bindTexture(int texture) {
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public static Framebuffer createFramebuffer(Framebuffer framebuffer, boolean depth) {
        if (framebuffer == null || framebuffer.framebufferWidth != Minecraft.getMinecraft().displayWidth || framebuffer.framebufferHeight != Minecraft.getMinecraft().displayHeight) {
            if (framebuffer != null) {
                framebuffer.deleteFramebuffer();
            }
            return new Framebuffer(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, depth);
        }
        return framebuffer;
    }

    public static void glColor(int color) {
        var red = (color >> 16 & 0xFF) / 255.0f;
        var green = (color >> 8 & 0xFF) / 255.0f;
        var blue = (color & 0xFF) / 255.0f;
        var alpha = (color >> 24 & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static void drawBlurredRect(BlurType type, double x, double y, double x1, double y1, int color) {
        switch (type) {
            case KAWASE:
                StencilUtility.initStencilToWrite();
                enableGL2D();
                glColor(color);
                Gui.drawRect(x, y, x1, y1, -1);
                disableGL2D();
                StencilUtility.readStencilBuffer(1);
                KawaseBlur.renderBlur(1, 8);
                StencilUtility.uninitStencilBuffer();
                break;
            case NORMAL:
                StencilUtility.initStencilToWrite();
                enableGL2D();
                glColor(color);
                Gui.drawRect(x, y, x1, y1, new Color(0,0,0,30).getRGB());
                disableGL2D();
                StencilUtility.readStencilBuffer(1);
                BlurShader.renderBlur(6);
                StencilUtility.uninitStencilBuffer();
                break;
        }
    }

    public static void enableGL2D() {
        glDisable(2929);
        glEnable(3042);
        glDisable(3553);
        glBlendFunc(770, 771);
        glDepthMask(true);
        glEnable(2848);
        glHint(3154, 4354);
        glHint(3155, 4354);
    }

    public static void disableGL2D() {
        glEnable(3553);
        glDisable(3042);
        glEnable(2929);
        glDisable(2848);
        glHint(3154, 4352);
        glHint(3155, 4352);
    }

    public enum BlurType {
        KAWASE, NORMAL
    }
}
