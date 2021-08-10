package ru.skhool21.rubik.graphics;

import lombok.Getter;
import ru.skhool21.rubik.graphics.System.MyPolygon;
import ru.skhool21.rubik.graphics.System.Point3D;
import ru.skhool21.rubik.graphics.entity.EntityManager;
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
    public static final int PANEL_WIDTH = 800;
    public static final int PANEL_HEIGHT = 600;
    @Deprecated
    private final int[] xPoints = {10, 60, 60, 10};
    @Deprecated
    private final int[] yPoints = {10, 10, 60, 60};


    private Timer timer;
    private final int xVelocity = 1;
    private final int yVelocity = 2;
    private final Block[][] blocks = new Block[6][9];
    private static final Point point = new Point();
    private RubicMouseListener mouseListener;
    private EntityManager entityManager;

    private final Cub cub = new Cub();

    public MyPanel() {
        this.mouseListener = new RubicMouseListener();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.darkGray);
        this.addMouseListener(this.mouseListener);
        this.addMouseMotionListener(this.mouseListener);
        this.addMouseWheelListener(this.mouseListener);
        this.addKeyListener(new RubicKeyListener(this));
        this.setFocusable(true);
        this.entityManager = new EntityManager();

        Point[] startPoint = new Point[6];
        for (int i = 0, dx = 50, dy = 50; i < startPoint.length; i++, dx += 175) {
            startPoint[i] = new Point(dx, dy);
        }
        blockInit(blocks[0], cub.getFront(), startPoint[0], 50);
        blockInit(blocks[1], cub.getBack(), startPoint[1], 50);
        blockInit(blocks[2], cub.getUp(), startPoint[2], 50);
        blockInit(blocks[3], cub.getDown(), startPoint[3], 50);
        blockInit(blocks[4], cub.getLeft(), startPoint[4], 50);
        blockInit(blocks[5], cub.getRight(), startPoint[5], 50);

        this.entityManager.init();
        timer = new Timer(1, this);
        timer.start();
    }

    public static void setPoint(int x, int y) {
        MyPanel.point.setLocation(x, y);
    }

    public static Point getPoint() {
        return point;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.entityManager.update(this.mouseListener);
        this.repaint();
    }

    private void blockInit(Block[] block, Side side, Point start, int size) {
        for (int i = 0, xOffset = 1, yOffset = 1, startXOffset = 0, startYOffset = 0; i < block.length; i++) {
            if (xOffset > 3) {
                xOffset = 1;
                yOffset++;
                startYOffset += 55;
                startXOffset = 0;
            }
            int x = start.x + startXOffset;
            int y = start.y + startYOffset;
            block[i] = new Block(new MyPolygon(side.colorSide[yOffset - 1][xOffset - 1].getColor(),
                    new Point3D(x, y, 2),
                    new Point3D(x + size, y, 1),
                    new Point3D(x + size, y + size, 6),
                    new Point3D(x, y + size, 0)
            ),
//                    polygonize(getOriginalPoints(start.x + startXOffset, start.y + startYOffset)),
                    side.colorSide[yOffset - 1][xOffset - 1].getColor(),
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
        this.entityManager.render(g2D);
//        try {
//            for (Block[] block : blocks) {
//                drawSide(g2D, block);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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

    private Point[] getOriginalPoints(int x, int y) {
        Point[] point = new Point[4];

        point[0] = new Point(x, y);
        point[1] = new Point(x + 50, y);
        point[2] = new Point(x + 50, y + 50);
        point[3] = new Point(x, y + 50);
        return point;
    }

//    public MyPolygon polygonize(Point[] polyPoints) {
//        MyPolygon tempPoly = new MyPolygon();
//
//        for (Point polyPoint : polyPoints) {
//            tempPoly.addPoint(polyPoint.x, polyPoint.y);
//        }
//
//        return tempPoly;
//
//    }

    private void drawSide(Graphics2D g2D, Block[] blocks) {
        for (Block block : blocks) {
            block.getPolygon().render(g2D);
        }
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
