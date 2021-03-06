package ru.skhool21.rubik.graphics.model;

import ru.skhool21.rubik.graphics.System.MyPolygon;

import java.awt.*;

public class Tetrahedron {

    private MyPolygon[] polygons;
    private Color color;

    public Tetrahedron(Color color, boolean decayColor, MyPolygon... polygons) {
        this.color = color;
        this.polygons = polygons;
        if (decayColor) {
            this.setDecayingPolygonColor();
        } else {
            this.setPolygonColor();
        }
        this.sortPolygons();
    }

    public Tetrahedron(MyPolygon... polygons) {
        this.color = Color.WHITE;
        this.polygons = polygons;
        this.sortPolygons();
    }

    public void render(Graphics2D g) {
        for (MyPolygon poly : this.polygons) {
            poly.render(g);
        }
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for (MyPolygon p : this.polygons) {
            p.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();
    }

    public void sortPolygons() {
        MyPolygon.sortPolygons(this.polygons);
    }

    private void setPolygonColor() {
        for (MyPolygon poly : this.polygons) {
            poly.setColor(this.color);
        }
    }

    public void setDecayingPolygonColor() {
        double decayFactor = .97;
        for (MyPolygon poly : this.polygons) {
            poly.setColor(this.color);
            int r = (int) (this.color.getRed() * decayFactor);
            int g = (int) (this.color.getGreen() * decayFactor);
            int b = (int) (this.color.getBlue() * decayFactor);
            this.color = new Color(r, g, b);
        }
    }

    public MyPolygon[] getPolygons() {
        return polygons;
    }
}
