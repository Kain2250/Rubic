package ru.skhool21.rubik.model.Graphics;

import java.awt.*;
import java.awt.Color;

public class Block {
    private Polygon polygon;
    private Color color;
    private int Id;

    public Block(Polygon polygon, Color color, int id) {
        this.polygon = polygon;
        this.color = color;
        Id = id;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return Id;
    }
}
