package ru.skhool21.rubik.graphics;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class RubicKeyListener extends KeyAdapter {
    private final MyPanel panel;
    private final float rotation = 1.f;

    public RubicKeyListener(MyPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void rotatePoint(Point pt, double rotationAngle) {

        AffineTransform.getRotateInstance
                (Math.toRadians(rotationAngle), MyPanel.PANEL_WIDTH * 0.5, MyPanel.PANEL_HEIGHT * 0.5)
                .transform(pt, pt);
    }

    public Block getClickBlock(Block[][] blocks) {
        for (Block[] blocksY : blocks) {
            for (Block blocksX : blocksY) {
                if (blocksX.isClicked()) {
                    return blocksX;
                }
            }
        }
        return null;
    }

}
