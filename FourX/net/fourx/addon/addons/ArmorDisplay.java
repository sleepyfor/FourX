package net.fourx.addon.addons;

import net.fourx.Client;
import net.fourx.addon.Addon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/*
Credit to felix client
*/

public class ArmorDisplay extends Addon {
    public ArmorDisplay() {
        super("Armor Display");
    }

    public static void drawItemsStatus(ScaledResolution sr) {
        GL11.glPushMatrix();
        List stuff = new ArrayList();
        int split = -3;

        ItemStack errything;
        for (int index = 3; index >= 0; --index) {
            errything = mc.thePlayer.inventory.armorInventory[index];
            if (errything != null) {
                stuff.add(errything);
            }
        }

        if (mc.thePlayer.getCurrentEquippedItem() != null) {
            stuff.add(mc.thePlayer.getCurrentEquippedItem());
        }

        Iterator var8 = stuff.iterator();

        while (var8.hasNext()) {
            errything = (ItemStack) var8.next();
            if (mc.theWorld != null) {
                RenderHelper.enableGUIStandardItemLighting();
                split += 16;
            }

            GlStateManager.pushMatrix();
            GlStateManager.disableAlpha();
            GlStateManager.clear(256);
            mc.getRenderItem().zLevel = -150.0F;
            int s = mc.thePlayer.capabilities.isCreativeMode ? 15 : 0;
            for (ItemStack offset : (Collection<ItemStack>) stuff) {
                mc.getRenderItem().renderItemAndEffectIntoGUI(errything, 2, 125 + split);
                mc.getRenderItem().renderItemOverlays(Client.INSTANCE.getFontManager().getJbm14(), errything, 2, 125 + split);
            }
            mc.getRenderItem().zLevel = 0.0F;
            GlStateManager.disableBlend();
            GlStateManager.scale(0.5D, 0.5D, 0.5D);
            GlStateManager.disableDepth();
            GlStateManager.disableLighting();
            GlStateManager.enableDepth();
            GlStateManager.scale(2.0F, 2.0F, 2.0F);
            GlStateManager.enableAlpha();
            GlStateManager.popMatrix();
            errything.getEnchantmentTagList();
        }

        GL11.glPopMatrix();
    }
}
