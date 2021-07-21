package ru.skhool21.rubik.model.Graphics.entity.builder;

import ru.skhool21.rubik.model.Graphics.System.MyPolygon;
import ru.skhool21.rubik.model.Graphics.System.Point3D;
import ru.skhool21.rubik.model.Graphics.model.Tetrahedron;
import ru.skhool21.rubik.model.Graphics.entity.Entity;
import ru.skhool21.rubik.model.Graphics.entity.impl.IEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ComplexEntityBuilder {
    public static IEntity createRubikCube(double size, double centerX, double centerY, double centerZ) {

        List<Tetrahedron> tetras = new ArrayList<>();

        double cubeSpacing = 5;

        for (int i = -1; i < 2; i++) {
            double cubeCenterX = i * (size + cubeSpacing) + centerX;
            for (int j = -1; j < 2; j++) {
                double cubeCenterY = j * (size + cubeSpacing) + centerY;
                for (int k = -1; k < 2; k++) {
                    if (i == 0 && j == 0 && k == 0) {
                        continue;
                    }
                    double cubeCenterZ = k * (size + cubeSpacing) + centerZ;
                    Point3D p1 = new Point3D(cubeCenterX + size / 2, cubeCenterY - size / 2, cubeCenterZ - size / 2);
                    Point3D p2 = new Point3D(cubeCenterX + size / 2, cubeCenterY + size / 2, cubeCenterZ - size / 2);
                    Point3D p3 = new Point3D(cubeCenterX + size / 2, cubeCenterY + size / 2, cubeCenterZ + size / 2);
                    Point3D p4 = new Point3D(cubeCenterX + size / 2, cubeCenterY - size / 2, cubeCenterZ + size / 2);
                    Point3D p5 = new Point3D(cubeCenterX - size / 2, cubeCenterY - size / 2, cubeCenterZ - size / 2);
                    Point3D p6 = new Point3D(cubeCenterX - size / 2, cubeCenterY + size / 2, cubeCenterZ - size / 2);
                    Point3D p7 = new Point3D(cubeCenterX - size / 2, cubeCenterY + size / 2, cubeCenterZ + size / 2);
                    Point3D p8 = new Point3D(cubeCenterX - size / 2, cubeCenterY - size / 2, cubeCenterZ + size / 2);

                    MyPolygon polygonRed = new MyPolygon(Color.RED, p2, p6, p7, p3);
                    MyPolygon polygonWhite = new MyPolygon(Color.WHITE, p1, p2, p3, p4);
                    MyPolygon polygonBlue = new MyPolygon(Color.BLUE, p4, p3, p7, p8);
                    MyPolygon polygonGreen = new MyPolygon(Color.GREEN, p1, p2, p6, p5);
                    MyPolygon polygonOrange = new MyPolygon(new Color(255, 109, 26), p1, p5, p8, p4);
                    MyPolygon polygonYellow = new MyPolygon(Color.YELLOW, p5, p6, p7, p8);

                    Tetrahedron tetra = new Tetrahedron(polygonRed, polygonWhite, polygonBlue, polygonGreen, polygonOrange, polygonYellow);
                    tetras.add(tetra);
                }
            }
        }
        return new Entity(tetras);
    }
}
