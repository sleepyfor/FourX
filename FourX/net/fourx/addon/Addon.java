package net.fourx.addon;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter@Setter
public class Addon {

    private boolean state;
    public String name;

    public Addon(String name){
        this.name = name;
    }
}
