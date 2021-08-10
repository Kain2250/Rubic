package ru.skhool21.rubik.model;

import java.awt.*;

public enum ColorSide {
    WHITE(Color.WHITE),
    RED(Color.RED),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    ORANGE(new Color(255, 109, 26));

    private Color color;

    ColorSide(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        switch (this) {
            case WHITE:
                return "\u001b[38;5;231m" + this.name() + "\u001b[0m";
            case RED:
                return "\u001b[38;5;196m" + this.name() + "\u001b[0m";
            case GREEN:
                return "\u001b[38;5;46m" + this.name() + "\u001b[0m";
            case BLUE:
                return "\u001b[38;5;21m" + this.name() + "\u001b[0m";
            case YELLOW:
                return "\u001b[38;5;226m" + this.name() + "\u001b[0m";
            case ORANGE:
                return "\u001b[38;5;208m" + this.name() + "\u001b[0m";
            default:
                return "";
        }
    }
}
