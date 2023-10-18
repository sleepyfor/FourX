package net.fourx.manager.font;

import lombok.Getter;
import lombok.var;
import net.fourx.utils.render.CustomFontRenderer;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

@Getter
public class FontManager {

    private final CustomFontRenderer arial17, arial15, jbm14;

    public FontManager() throws IOException, FontFormatException {
        var fileLocation = "fonts/arial.ttf";
        var jbmLocation = "fonts/JetBrainsMono.ttf";
        arial17 = new CustomFontRenderer(Font.createFont(0, Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(fileLocation)))
                .deriveFont(Font.PLAIN, 17f));
        arial15 = new CustomFontRenderer(Font.createFont(0, Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(fileLocation)))
                .deriveFont(Font.PLAIN, 15f));
        jbm14 = new CustomFontRenderer(Font.createFont(0, Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(jbmLocation)))
                .deriveFont(Font.PLAIN, 14f));
    }
}
