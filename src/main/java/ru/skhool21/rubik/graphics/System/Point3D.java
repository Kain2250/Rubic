package ru.skhool21.rubik.graphics.System;

import ru.skhool21.rubik.graphics.MyPanel;

import java.awt.*;

public class Point3D {

    public double x, y, z;
    private static double scale = 1;
    private static final double zoomFactor = 1.2;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Point convertPoint(Point3D point3D) {
        double x3d = point3D.y * scale;
        double y3d = point3D.z * scale;
        double depth = point3D.x * scale;
        double[] newVal = scale(x3d, y3d, depth);
        int x2d = (int) (MyPanel.PANEL_WIDTH * 0.5 - newVal[0]);
        int y2d = (int) (MyPanel.PANEL_HEIGHT * 0.5 + newVal[1]);

        return new Point(x2d, y2d);
    }

    public static Point convertPoint(Point3D point3D, Point start) {
        int x2d = (int) (start.x + point3D.y);
        int y2d = (int) (start.y - point3D.z);

        return new Point(x2d, y2d);
    }

    private static double[] scale(double x3d, double y3d, double depth) {
        double dist = Math.sqrt(x3d * x3d + y3d * y3d);
        double theta = Math.atan2(y3d, x3d);
        double depth2 = 15 - depth;
        double localScale = Math.abs(1400 / (depth2 + 1400));
        dist *= localScale;
        double[] newVal = new double[2];
        newVal[0] = dist * Math.cos(theta);
        newVal[1] = dist * Math.sin(theta);
        return newVal;
    }

    public  static void zoomIn() {
        scale *= zoomFactor;
    }

    public  static void zoomOut() {
        scale /= zoomFactor;
    }

    public static void rotateAxisX(Point3D p, boolean CW, double degrees) {
        double radius = Math.sqrt(p.y * p.y + p.z * p.z);
        double theta = Math.atan2(p.z, p.y);
        theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);
        p.y = radius * Math.cos(theta);
        p.z = radius * Math.sin(theta);
    }

    public static void rotateAxisY(Point3D p, boolean CW, double degrees) {
        double radius = Math.sqrt(p.x * p.x + p.z * p.z);
        double theta = Math.atan2(p.x, p.z);
        theta += 2 * Math.PI / 360 * degrees * (CW ? 1 : -1);
        p.x = radius * Math.sin(theta);
        p.z = radius * Math.cos(theta);

    }

    public static void rotateAxisZ(Point3D p, boolean CW, double degrees) {
        double radius = Math.sqrt(p.y * p.y + p.x * p.x);
        double theta = Math.atan2(p.y, p.x);
        theta += 2 * Math.PI / 360 * degrees * (CW ? 1 : -1);
        p.y = radius * Math.sin(theta);
        p.x = radius * Math.cos(theta);

    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
