package ru.skhool21.rubik.graphics;

import ru.skhool21.rubik.graphics.System.ClickType;

import java.awt.event.*;

public class RubicMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {
    private int mouseX = -1;
    private int mouseY = -1;
    private int mouseB = -1;
    private int scroll = 0;

    public int getX() {
        return this.mouseX;
    }

    public int getY() {
        return this.mouseY;
    }

    public boolean isScrollingUp() {
        return this.scroll == -1;
    }

    public boolean isScrollingDown() {
        return this.scroll == 1;
    }

    public void resetScroll() {
        this.scroll = 0;
    }

    public ClickType getButton() {
        switch (this.mouseB) {
            case 1:
                return ClickType.LeftClick;
            case 2:
                return ClickType.ScrollClick;
            case 3:
                return ClickType.RightClick;
            case 4:
                return ClickType.BackPage;
            case 5:
                return ClickType.ForwardPage;
            default:
                return ClickType.Unknown;
        }
    }

    public void resetButton() {
        this.mouseB = -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouseB = e.getButton();
//        Block[][] blocks = panel.getBlocks();
//        for (Block[] block : blocks) {
//            for (int i = 0, blockLength = block.length, x = 0, y = 0; i < blockLength; i++) {
//                Block elem = block[i];
//                if (elem.getPolygon().contains(e.getPoint())) {
//                    elem.setClicked(true);
//                    MyPanel.setPoint(y, x);
//                } else {
//                    elem.setClicked(false);
//                }
//                x++;
//                if (i == 2 || i == 5 || i == 8) {
//                    y++;
//                    x = 0;
//                }
//            }
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resetButton();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }
}
