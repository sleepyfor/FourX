package net.fourx.manager;

import net.fourx.addon.Addon;
import net.fourx.addon.addons.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class AddonManager {
    private final Map<Class, Addon> addons = new LinkedHashMap<>();

    public AddonManager() {
        registerAddon(ToggleSprint.class, new ToggleSprint());
        registerAddon(BiomeHud.class, new BiomeHud());
        registerAddon(CordsHud.class, new CordsHud());
        registerAddon(DirectionHud.class, new DirectionHud());
        registerAddon(FPSHud.class, new FPSHud());
        registerAddon(Keystrokes.class, new Keystrokes());
        registerAddon(WatermarkHud.class, new WatermarkHud());
        registerAddon(CPSHud.class, new CPSHud());
        registerAddon(PotionDisplay.class, new PotionDisplay());
        registerAddon(ArmorDisplay.class, new ArmorDisplay());
    }

    public void registerAddon(Class clazz, Addon addon) {
        addons.put(clazz, addon);
    }

    public Addon getAddon(Class addon) {
        return addons.values().stream().filter(add -> add.getClass().getName().equalsIgnoreCase(addon.getName())).findFirst().orElse(null);
    }
}
