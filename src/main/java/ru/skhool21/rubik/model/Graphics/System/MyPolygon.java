package ru.skhool21.rubik.model.Graphics.System;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MyPolygon {

    private Color color;
    private Point3D[] points;
    private Polygon polygon;
    public int npoints;

    public MyPolygon(Color color, Point3D... points) {
        this.color = color;
        this.npoints = points.length;
        this.polygon = new Polygon();
        this.points = new Point3D[npoints];
        for (int i = 0; i < points.length; i++) {
            Point3D p = points[i];
            this.points[i] = new Point3D(p.x, p.y, p.z);
        }
    }

    public MyPolygon(Point3D... points) {
        this.color = Color.WHITE;
        this.npoints = points.length;
        this.polygon = new Polygon();
        this.points = new Point3D[npoints];
        for (int i = 0; i < points.length; i++) {
            Point3D p = points[i];
            this.points[i] = new Point3D(p.x, p.y, p.z);
        }
    }

    public void render(Graphics2D g) {
        Polygon poly = new Polygon();
        for (Point3D point : points) {
            Point p = Point3D.convertPoint(point);
            poly.addPoint(p.x, p.y);
        }
        this.polygon = poly;
        g.setColor(color);
        g.fillPolygon(poly);
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for (Point3D p : points) {
            Point3D.rotateAxisX(p, CW, xDegrees);
            Point3D.rotateAxisY(p, CW, yDegrees);
            Point3D.rotateAxisZ(p, CW, zDegrees);
        }
    }

    public double getAverageX() {
        double sum = 0;
        for (Point3D p : this.points) {
            sum += p.x;
        }
        return sum/ points.length;
    }

    public static MyPolygon[] sortPolygons(MyPolygon[] polygons) {
        List<MyPolygon> polygonList = new ArrayList<>();

        Collections.addAll(polygonList, polygons);

        Collections.sort(polygonList, new Comparator<MyPolygon>() {
            @Override
            public int compare(MyPolygon p1, MyPolygon p2) {
                double p1AverageX = p1.getAverageX();
                double p2AverageX = p2.getAverageX();
                double diff = p2AverageX - p1AverageX;
                if (diff == 0) {
                    return 0;
                }
                return diff < 0 ? 1 : -1;
            }
        });
        for (int i = 0; i < polygons.length; i++) {
            polygons[i] = polygonList.get(i);
        }
        return polygons;
    }

    @Override
    public String toString() {
        return "MyPolygon{" +
                "points=" + Arrays.toString(points) +
                '}';
    }

    public boolean contains(Point point) {
        return polygon.contains(point);
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
