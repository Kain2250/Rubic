package ru.skhool21.rubik.graphics.entity.builder;

import ru.skhool21.rubik.graphics.System.MyPolygon;
import ru.skhool21.rubik.graphics.System.Point3D;
import ru.skhool21.rubik.graphics.entity.Entity;
import ru.skhool21.rubik.graphics.entity.impl.IEntity;
import ru.skhool21.rubik.graphics.model.Tetrahedron;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BasicEntityBuilder {

    public static IEntity createCube(double size, double centerX, double centerY, double centerZ) {

        double halfS = size * .5;
        Point3D p1 = new Point3D(centerX + halfS, centerY - halfS, centerZ - halfS);
        Point3D p2 = new Point3D(centerX + halfS, centerY + halfS, centerZ - halfS);
        Point3D p3 = new Point3D(centerX + halfS, centerY + halfS, centerZ + halfS);
        Point3D p4 = new Point3D(centerX + halfS, centerY - halfS, centerZ + halfS);
        Point3D p5 = new Point3D(centerX - halfS, centerY - halfS, centerZ - halfS);
        Point3D p6 = new Point3D(centerX - halfS, centerY + halfS, centerZ - halfS);
        Point3D p7 = new Point3D(centerX - halfS, centerY + halfS, centerZ + halfS);
        Point3D p8 = new Point3D(centerX - halfS, centerY - halfS, centerZ + halfS);

        Tetrahedron tetrahedron = new Tetrahedron(
                new MyPolygon(Color.YELLOW, p5, p6, p7, p8),
                new MyPolygon(new Color(255, 109, 26), p1, p5, p8, p4),
                new MyPolygon(Color.RED, p2, p6, p7, p3),
                new MyPolygon(Color.GREEN, p1, p2, p6, p5),
                new MyPolygon(Color.BLUE, p4, p3, p7, p8),
                new MyPolygon(Color.WHITE, p1, p2, p3, p4)
        );

        List<Tetrahedron> tetras = new ArrayList<>();
        tetras.add(tetrahedron);

        return new Entity(tetras);
    }

    public static IEntity createDiamond(Color color, double size, double centerX, double centerY, double centerZ) {
        List<Tetrahedron> tetras = new ArrayList<>();

        int edges = 20;
        double inFactor = 0.8;

        Point3D bottom = new Point3D(centerX, centerY, centerZ - size * .5);
        Point3D[] outerPoints = new Point3D[edges];
        Point3D[] innerPoints = new Point3D[edges];
        for (int i = 0; i < edges; i++) {
            double theta = 2 * Math.PI / edges * i;
            double xPos = -Math.sin(theta) * size * .5;
            double yPos = Math.cos(theta) * size * .5;
            double zPos = size * .5;
            outerPoints[i] = new Point3D(centerX +xPos , centerY + yPos, centerZ + zPos * inFactor);
            innerPoints[i] = new Point3D(centerX +xPos * inFactor, centerY + yPos * inFactor, centerZ + zPos);
        }
        MyPolygon[] polygons = new MyPolygon[2 * edges + 1];
        for (int i =0; i < edges; i++) {
            polygons[i] = new MyPolygon(outerPoints[i], bottom, outerPoints[(i + 1) % edges]);
        }
        for (int i = 0; i < edges; i++) {
            polygons[i + edges] = new MyPolygon(outerPoints[i], outerPoints[(i + 1) % edges], innerPoints[(i + 1) % edges], innerPoints[i]);
        }
        polygons[edges * 2] = new MyPolygon(innerPoints);
        Tetrahedron tetra = new Tetrahedron(color, true, polygons);
        tetras.add(tetra);

        return new Entity(tetras);

    }
}
