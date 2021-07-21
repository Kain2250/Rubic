package ru.skhool21.rubik.model.Graphics.entity;

import ru.skhool21.rubik.model.Graphics.RubicMouseListener;
import ru.skhool21.rubik.model.Graphics.System.ClickType;
import ru.skhool21.rubik.model.Graphics.System.Point3D;
import ru.skhool21.rubik.model.Graphics.entity.builder.ComplexEntityBuilder;
import ru.skhool21.rubik.model.Graphics.entity.impl.IEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private List<IEntity> entities;
    private int initialX;
    private int initialY;
    private final double mouseSensitivity = 2.5;


    public EntityManager() {
        this.entities = new ArrayList<>();
    }

    public void init() {
//        this.entities.add(BasicEntityBuilder.createCube(100, 0, 0, 0));
//        this.entities.add(BasicEntityBuilder.createDiamond(Color.BLUE, 200, 0, 0, 0));
        this.entities.add(ComplexEntityBuilder.createRubikCube(100, 0, 0, 0));
    }

    public void update(RubicMouseListener mouse) {
        int x = mouse.getX();
        int y = mouse.getY();
        if (mouse.getButton() == ClickType.LeftClick) {
            int xDif = x - initialX;
            int yDif = y - initialY;
            this.rotate(true, 0, -yDif / mouseSensitivity, -xDif / mouseSensitivity);
        }
        if (mouse.getButton() == ClickType.RightClick) {
            double xDif = x - initialX;
            this.rotate(true, -xDif / mouseSensitivity, 0, 0);
        }
        initialX = x;
        initialY = y;
        if (mouse.isScrollingUp()) {
            Point3D.zoomIn();
        } else if (mouse.isScrollingDown()) {
            Point3D.zoomOut();
        }
        mouse.resetScroll();


    }

    public void render(Graphics2D g) {
        for (IEntity entity : this.entities) {
            entity.render(g);
        }
    }

    private void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for (IEntity entity : this.entities) {
            entity.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
    }

}
