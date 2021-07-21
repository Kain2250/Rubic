package ru.skhool21.rubik.model.Graphics;

import ru.skhool21.rubik.model.Graphics.System.MyPolygon;

import java.awt.Color;

public class Block {
    private MyPolygon polygon;
    private Color color;
    private int Id;
    private boolean isClicked;

    public Block(MyPolygon polygon, Color color, int id) {
        this.polygon = polygon;
        this.color = color;
        Id = id;
        isClicked = false;
    }

    public MyPolygon getPolygon() {
        return polygon;
    }

    public void setPolygon(MyPolygon polygon) {
        this.polygon = polygon;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return Id;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isClicked() {
        return isClicked;
    }
}
