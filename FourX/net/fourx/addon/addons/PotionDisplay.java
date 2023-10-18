package net.fourx.addon.addons;

import net.fourx.Client;
import net.fourx.addon.Addon;
import net.fourx.utils.render.CustomFontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/*
Credit to felix client
*/

public class PotionDisplay extends Addon {
    public PotionDisplay() {
        super("Potion Display");
    }

    public static void drawPotionStatus(ScaledResolution sr) {
        int y = 0;
        CustomFontRenderer font = Client.INSTANCE.getFontManager().getJbm14();
        for (final PotionEffect effect : mc.thePlayer.getActivePotionEffects()) {
            Potion potion = Potion.potionTypes[effect.getPotionID()];
            String PType = I18n.format(potion.getName());
            switch (effect.getAmplifier()) {
                case 1:
                    PType = PType + " II";
                    break;
                case 2:
                    PType = PType + " III";
                    break;
                case 3:
                    PType = PType + " IV";
                    break;
                default:
                    break;
            }
            PType = PType + ":\2477 " + Potion.getDurationString(effect);
            font.drawStringWithShadow(PType, 2, 230 + y, -1);
            y += 10;
        }
    }
}
