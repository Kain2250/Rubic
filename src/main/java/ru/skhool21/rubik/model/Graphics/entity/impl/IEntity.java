package ru.skhool21.rubik.model.Graphics.entity.impl;

import java.awt.*;

public interface IEntity {
    void render(Graphics2D g);

    void rotate (boolean CW, double xDegrees, double yDegrees, double zDegrees);
}
