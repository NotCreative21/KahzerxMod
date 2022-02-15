package com.kahzerx.kahzerxmod.extensions.shopExtension.gui.components.labels;

import com.kahzerx.kahzerxmod.extensions.shopExtension.gui.GuiPlayer;
import com.kahzerx.kahzerxmod.extensions.shopExtension.gui.Renderer;
import com.kahzerx.kahzerxmod.extensions.shopExtension.gui.components.Component;
import com.kahzerx.kahzerxmod.extensions.shopExtension.gui.components.helpers.TextMapper;

public class TextLabel extends Component {
    private TextMapper text;
    private byte textColor;
    private int x;
    private int y;
    private int width;
    private int height;
    public TextLabel(TextMapper text, byte textColor) {
        this.text = text;
        this.textColor = textColor;
    }

    public void setDimensions(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setBounds(x, y, x + width, y + height);
    }

    @Override
    public void render(GuiPlayer guiPlayer) {
        Renderer.drawText(guiPlayer, text, x + width / 2 - text.getWidth() / 2, y + height / 2 - text.getHeight() / 2, textColor);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
