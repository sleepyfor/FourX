package net.fourx.addon;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.minecraft.client.Minecraft;

@Getter@Setter
public class Addon {
    public static final Minecraft mc = Minecraft.getMinecraft();
    private boolean state;
    public String name;

    public Addon(String name){
        this.name = name;
    }
}
