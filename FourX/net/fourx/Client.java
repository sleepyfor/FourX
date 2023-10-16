package net.fourx;

import lombok.Getter;
import lombok.Setter;
import net.fourx.manager.AddonManager;
import net.fourx.manager.font.FontManager;

import java.awt.*;
import java.io.IOException;

@Getter
public enum Client {
    INSTANCE;
    private final String name = "FourX", version = "0.1.0";
    private AddonManager addonManager;
    private FontManager fontManager;

    public void initClient() {
        addonManager = new AddonManager();
        initFont();
    }

    private void initFont() {
        try {
            fontManager = new FontManager();
        } catch (IOException | FontFormatException ignored) {
        }
    }
}
