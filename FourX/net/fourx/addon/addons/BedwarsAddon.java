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

    public BedwarsAddon() {
        super("Bedwars Overlay");
    }

    public static void drawBedwarsOverlay(ScaledResolution sr) {
        GL11.glPushMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.clear(256);
        int iron = 0;
        int gold = 0;
        int diamond = 0;
        int emerald = 0;
        for (int i = 9; i < 45; i++) {
            if (mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                Item item = mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem();
                if(item == Items.iron_ingot){
                    iron = mc.thePlayer.inventory.getStackInSlot(i).stackSize;
                }
                if(item == Items.gold_ingot && item != null){
                    gold = mc.thePlayer.inventory.getStackInSlot(i).stackSize;
                }
            }
        }
        Client.INSTANCE.getFontManager().getJbm14().drawStringWithShadow(String.valueOf(iron), 2, 140, -1);
        Client.INSTANCE.getFontManager().getJbm14().drawStringWithShadow(String.valueOf(gold), 2, 150, -1);
        RenderingUtils.drawImg(new ResourceLocation("images/iron_ingot.png"), 50, 125, 24, 24);
//        for(int i = 0; i < mc.thePlayer.inventory.getSizeInventory(); i++)
//            System.out.println(mc.thePlayer.inventory.getStackInSlot(i).getItem().getUnlocalizedName());
//        for(int i = 0; i < mc.thePlayer.inventory.mainInventory.length; i++) {
////            if(mc.thePlayer.inventory.getStackInSlot(i).getItem() == Items.iron_ingot)
//            if(mc.thePlayer.inventory.getStackInSlot(i).getItem() == null)
//                System.out.println(mc.thePlayer.inventory.getStackInSlot(i).getItem().getUnlocalizedName());
//            if (item == Items.iron_ingot) {
//                iron++;
////                mc.getRenderItem().renderItemAndEffectIntoGUI(ItemStack.copyItemStack(item), 2, 125);
////                mc.getRenderItem().renderItemOverlays(Client.INSTANCE.getFontManager().getJbm14(), item, 2, 125);
//                RenderingUtils.drawImg(new ResourceLocation("minecraft/images/iron_ingot.png"), 2, 125, 32, 32);
//            }
//            if (item.getItem() == Items.gold_ingot)
//                gold++;
//            if(item.getItem() == Items.diamond)
//                diamond++;
//            if(item.getItem() == Items.emerald)
//                emerald++;
//                if (item == null)
//                    System.out.println(item);

//        }
        GL11.glPopMatrix();
        GlStateManager.disableBlend();
        GlStateManager.scale(0.5D, 0.5D, 0.5D);
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0F, 2.0F, 2.0F);
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
    }
}
