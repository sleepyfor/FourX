package net.fourx.gui.hud;

import net.fourx.Client;
import net.fourx.addon.addons.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.Chunk;
import org.lwjgl.input.Mouse;

import java.util.*;

public class InfoHud {
    public String fps, biome, facing, CPS, xPos, yPos,zPos;
    public static Queue<Long> clicks = new LinkedList<>();
    public final Minecraft mc = Minecraft.getMinecraft();
    public List<String> info = new ArrayList<>();
    public boolean wasPressed;
    public long lastPress;

    public void drawInfoHud(float x, float y) {
        fps = "FPS: " + Minecraft.getDebugFPS();
        biome();
        direction();
        cps();
        position();
        add();
        float i = y;
        for (String inf : info) {
            i += Objects.equals(inf, info.get(0)) ? 0 : ((Objects.equals(inf, "_______________") ? 2 : 9));
            Client.INSTANCE.getFontManager().getJbm14().drawStringWithShadow(inf, x, i, -1);
        }
    }

    public void add() {
        if(Client.INSTANCE.getAddonManager().getAddon(FPSHud.class).isState())
            info.clear();
        if (Client.INSTANCE.getAddonManager().getAddon(WatermarkHud.class).isState() && !info.contains(Client.INSTANCE.getName() + " Mod " + Client.INSTANCE.getVersion()))
            info.add(Client.INSTANCE.getName() + " Mod " + Client.INSTANCE.getVersion());
        if (info.contains(Client.INSTANCE.getName() + " Mod " + Client.INSTANCE.getVersion()) && !Client.INSTANCE.getAddonManager().getAddon(WatermarkHud.class).isState())
            info.remove(Client.INSTANCE.getName() + " Mod " + Client.INSTANCE.getVersion());
        if (Client.INSTANCE.getAddonManager().getAddon(WatermarkHud.class).isState() && !info.contains("_______________"))
            info.add("_______________");
        if (info.contains("_______________") && !Client.INSTANCE.getAddonManager().getAddon(WatermarkHud.class).isState())
            info.remove("_______________");
        if (Client.INSTANCE.getAddonManager().getAddon(FPSHud.class).isState() && !info.contains(fps))
            info.add(fps);
        if (!Client.INSTANCE.getAddonManager().getAddon(FPSHud.class).isState())
            info.remove(fps);
        if (Client.INSTANCE.getAddonManager().getAddon(BiomeHud.class).isState() && !info.contains(biome))
            info.add(biome);
        if (info.contains(biome) && !Client.INSTANCE.getAddonManager().getAddon(BiomeHud.class).isState())
            info.remove(biome);
        if (Client.INSTANCE.getAddonManager().getAddon(DirectionHud.class).isState() && !info.contains(facing))
            info.add(facing);
        if (info.contains(facing) && !Client.INSTANCE.getAddonManager().getAddon(DirectionHud.class).isState())
            info.remove(facing);
        if (Client.INSTANCE.getAddonManager().getAddon(CPSHud.class).isState() && !info.contains(CPS))
            info.add(CPS);
        if (info.contains(CPS) && !Client.INSTANCE.getAddonManager().getAddon(CPSHud.class).isState())
            info.remove(CPS);
        if (Client.INSTANCE.getAddonManager().getAddon(CordsHud.class).isState() && (!info.contains(xPos) && !info.contains(yPos) && !info.contains(zPos))) {
            info.add(xPos);
            info.add(yPos);
            info.add(zPos);
        }
        if ((info.contains(xPos) && info.contains(yPos) && info.contains(zPos)) && !Client.INSTANCE.getAddonManager().getAddon(CordsHud.class).isState()) {
            info.remove(xPos);
            info.remove(yPos);
            info.remove(zPos);
        }
    }

    public void cps(){
        int cps = getClicks();
        boolean pressed = Mouse.isButtonDown(0);
        if (pressed != wasPressed) {
            wasPressed = pressed;
            lastPress = System.currentTimeMillis();
            if (pressed) {
                clicks.add(lastPress);
            }
        } else if (cps > 0)
            cps--;
        CPS = "CPS: " + cps;
    }

    public int getClicks() {
        long time = System.currentTimeMillis();
        clicks.removeIf(aLong -> aLong + 1000L < time);
        return clicks.size();
    }

    public void direction(){
        EnumFacing enumfacing = mc.thePlayer.getHorizontalFacing();
        String dir = String.format("%S", enumfacing, null, MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationYaw), MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationPitch));
        facing = "Facing: " + dir.charAt(0) + dir.substring(1).toLowerCase();
    }

    public void biome(){
        BlockPos blockpos = new BlockPos(mc.getRenderViewEntity().posX, mc.getRenderViewEntity().getEntityBoundingBox().minY, mc.getRenderViewEntity().posZ);
        Chunk chunk = mc.theWorld.getChunkFromBlockCoords(blockpos);
        biome = "Biome: " + chunk.getBiome(blockpos, mc.theWorld.getWorldChunkManager()).biomeName;
    }

    public void position(){
        xPos = "X: " + mc.thePlayer.getPosition().getX();
        yPos = "Y: " + mc.thePlayer.getPosition().getY();
        zPos = "Z: " + mc.thePlayer.getPosition().getZ();
    }
}
