package net.fourx.gui.toggle.component;

import net.fourx.gui.toggle.ToggleGUI;

public class Component {

    public double x, y, width, height;
    public ToggleGUI parent;
    public int color = -1;

    public Component(double x, double y, double width, double height, ToggleGUI parent, int color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.parent = parent;
        this.color = color;
    }

    public void onPressed(int key){
    }

    public void drawComponent(int mouseX, int mouseY, boolean hovered){
    }
}
