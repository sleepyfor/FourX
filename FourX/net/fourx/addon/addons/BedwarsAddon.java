package net.fourx.addon.addons;

import lombok.val;
import net.fourx.Client;
import net.fourx.addon.Addon;
import net.fourx.utils.render.RenderingUtils;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import java.util.Collection;

public class BedwarsAddon extends Addon {

    public static int iron, gold, diamond, emerald;
    public Item item;

    public BedwarsAddon() {
        super("Bedwars Overlay");
    }

    public static void drawBedwarsOverlay(ScaledResolution sr) {
        Client.INSTANCE.getFontManager().getJbm14().drawStringWithShadow(String.valueOf(iron), 19, 209, -1);
        Client.INSTANCE.getFontManager().getJbm14().drawStringWithShadow(String.valueOf(gold), 19, 224, -1);
        Client.INSTANCE.getFontManager().getJbm14().drawStringWithShadow(String.valueOf(diamond), 19, 241, -1);
        Client.INSTANCE.getFontManager().getJbm14().drawStringWithShadow(String.valueOf(emerald), 19, 258, -1);
        RenderingUtils.drawImg(new ResourceLocation("images/iron_ingot.png"), 2, 205, 16, 16);
        RenderingUtils.drawImg(new ResourceLocation("images/gold_ingot.png"), 2, 220, 16, 16);
        RenderingUtils.drawImg(new ResourceLocation("images/diamond.png"), 2, 235, 16, 16);
        RenderingUtils.drawImg(new ResourceLocation("images/emerald.png"), 2, 254, 16, 16);
    }
}
