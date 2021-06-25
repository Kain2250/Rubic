package ru.skhool21.rubik.model.Graphics;

import lombok.Getter;
import ru.skhool21.rubik.model.Cub;
import ru.skhool21.rubik.model.Side;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Getter
public class MyPanel extends JPanel implements ActionListener {
    @Deprecated
    private final int[] xPoints = {10, 60, 60, 10};
    @Deprecated
    private final int[] yPoints = {10, 10, 60, 60};

    private static final int PANEL_WIDTH = 1024;
    private static final int PANEL_HEIGHT = 1024;

    private Timer timer;
    private int xVelocity = 1;
    private int yVelocity = 2;
    private Block[][] blocks = new Block[6][9];


    private Cub cub = Cub.getInstance();

    public MyPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.darkGray);
        this.addMouseListener(new RubicMouseListener(this));
        this.addMouseMotionListener(new RubicMouseMotions(this));

        Point startPoint = new Point(50, 50);

        blockInit(blocks[0], cub.getFront(), startPoint);
        startPoint.translate(175, 0);

        blockInit(blocks[1], cub.getBack(), startPoint);
        startPoint.translate(175, 0);

        blockInit(blocks[2], cub.getUp(), startPoint);
        startPoint.translate(-(175 * 2), 175);

        blockInit(blocks[3], cub.getDown(), startPoint);
        startPoint.translate(175, 0);

        blockInit(blocks[4], cub.getLeft(), startPoint);
        startPoint.translate(175, 0);

        blockInit(blocks[5], cub.getRight(), startPoint);

        timer = new Timer(10, this);
        timer.start();

    }

    private void blockInit(Block[] block, Side side, Point start) {
        for (int i = 0, xOffset = 1, yOffset = 1, startXOffset = 0, startYOffset = 0; i < block.length; i++) {
            if (xOffset > 3) {
                xOffset = 1;
                yOffset++;
                startYOffset += 55;
                startXOffset = 0;
            }
            block[i] = new Block(polygonize(
                    getOriginalPoints(start.x + startXOffset, start.y + startYOffset)),
                    side.color[yOffset - 1][xOffset - 1].getColor(),
                    i);
            xOffset++;
            startXOffset += 55;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        try {
            for (Block[] block : blocks) {
                drawSide(g2D, block);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Font font = new Font("Courier", Font.ITALIC, 50);
//        g2D.setFont(font);
//        Rectangle2D f = font.getStringBounds("Как уебу сука!", g2D.getFontRenderContext());
//        g2D.setColor(Color.PINK);
//        g2D.drawString(
//                "Как уебу сука!",
//                (int) (PANEL_WIDTH / 2 - f.getCenterX()),
//                (int) (f.getHeight() + PANEL_HEIGHT / 10)
//        );
//
//        g2D.setFont(new Font("Arial", Font.ITALIC, 50));
//        g2D.drawString("Съебался в ужасе!", xPoints[0], yPoints[0]);
    }

    private void moveBlock(int[] points, int velocity) {
        for (int iter = 0; iter < points.length; iter++) {
            points[iter] += velocity;
        }
    }

    private Point[] getOriginalPoints(int x, int y) {
        Point[] point = new Point[4];

        point[0] = new Point(x, y);
        point[1] = new Point(x + 50, y);
        point[2] = new Point(x + 50, y + 50);
        point[3] = new Point(x, y + 50);
        return point;
    }

    public Polygon polygonize(Point[] polyPoints) {
        Polygon tempPoly = new Polygon();

        for (Point polyPoint : polyPoints) {
            tempPoly.addPoint(polyPoint.x, polyPoint.y);
        }

        return tempPoly;

    }

    private void drawSide(Graphics2D g2D, Block[] blocks) {
        for (Block block : blocks) {
            g2D.setColor(block.getColor());
            g2D.fillPolygon(block.getPolygon());
        }
    }

    @Deprecated
    private void drawSide(Graphics2D g2D, Side side) {
        float countBlock = 1;

        int xReturn = 0;
        for (int y = 0; y < side.color.length; y++) {
            for (int x = 0; x < side.color[y].length; x++) {
                g2D.setColor(side.color[y][x].getColor());
                g2D.fillPolygon(xPoints, yPoints, 4);
                moveBlock(xPoints, 55);
                xReturn += 55;
                if (countBlock == 3 || countBlock == 6 || countBlock == 9) {
                    moveBlock(xPoints, -xReturn);
                    moveBlock(yPoints, 55);
                    xReturn = 0;
                }
                countBlock++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if ((xPoints[0] >= PANEL_WIDTH || xPoints[0] < 0) ||
//                (xPoints[1] >= PANEL_WIDTH || xPoints[1] < 0) ||
//                (xPoints[2] >= PANEL_WIDTH || xPoints[2] < 0) ||
//                (xPoints[3] >= PANEL_WIDTH || xPoints[3] < 0)
////                || (xPoints[0] + image.getWidth() >= PANEL_WIDTH)
//        ) {
//            xVelocity *= -1;
//        }
//        if ((yPoints[0] >= PANEL_HEIGHT || yPoints[0] < 0) ||
//                (yPoints[1] >= PANEL_HEIGHT || yPoints[1] < 0) ||
//                (yPoints[2] >= PANEL_HEIGHT || yPoints[2] < 0) ||
//                (yPoints[3] >= PANEL_HEIGHT || yPoints[3] < 0)
////                || (yPoints[0] + image.getWidth() >= PANEL_HEIGHT)
//        ) {
//            yVelocity *= -1;
//        }
//
//        xPoints[0] += xVelocity;
//        xPoints[1] += xVelocity;
//        xPoints[2] += xVelocity;
//        xPoints[3] += xVelocity;
//
//        yPoints[0] += yVelocity;
//        yPoints[1] += yVelocity;
//        yPoints[2] += yVelocity;
//        yPoints[3] += yVelocity;
//        repaint();
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    protected static BufferedImage createImage(String path) {
        try {
            File image = new File("src/main/resources/" + path);
            return ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
