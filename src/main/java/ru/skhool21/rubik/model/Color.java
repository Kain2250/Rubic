package ru.skhool21.rubik.model;

public enum Color {
    WHITE(java.awt.Color.WHITE),
    RED(java.awt.Color.RED),
    GREEN(java.awt.Color.GREEN),
    BLUE(java.awt.Color.BLUE),
    YELLOW(java.awt.Color.YELLOW),
    ORANGE(new java.awt.Color(255, 109, 26));

    private java.awt.Color color;

    Color(java.awt.Color color) {
        this.color = color;
    }

    public java.awt.Color getColor() {
        return this.color;
    }
}
