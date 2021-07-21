package ru.skhool21.rubik.model.Graphics.entity;

import ru.skhool21.rubik.model.Graphics.System.MyPolygon;
import ru.skhool21.rubik.model.Graphics.model.Tetrahedron;
import ru.skhool21.rubik.model.Graphics.entity.impl.IEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entity implements IEntity {

    private List<Tetrahedron> tetrahedrons;
    private MyPolygon[] polygons;

    public Entity(List<Tetrahedron> tetrahedrons) {
        this.tetrahedrons = tetrahedrons;
        List<MyPolygon> tempList = new ArrayList<>();
        for (Tetrahedron tetra : this.tetrahedrons) {
            tempList.addAll(Arrays.asList(tetra.getPolygons()));
        }
        this.polygons = new MyPolygon[tempList.size()];
        this.polygons = tempList.toArray(this.polygons);
        this.sortPolygons();
    }

    @Override
    public void render(Graphics2D g) {
        for (Tetrahedron elem : this.tetrahedrons) {
            elem.render(g);
        }

    }

    @Override
    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for (Tetrahedron elem : this.tetrahedrons) {
            elem.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();
    }

    private void sortPolygons() {
        MyPolygon.sortPolygons(this.polygons);
    }
}
