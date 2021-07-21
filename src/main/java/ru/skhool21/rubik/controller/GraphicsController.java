package ru.skhool21.rubik.controller;

import ru.skhool21.rubik.model.Graphics.MyPanel;

import javax.swing.*;
import java.awt.*;

public class GraphicsController extends JFrame {
    private MyPanel panel;

    public GraphicsController() throws HeadlessException {
        super("Rubik");
        panel = new MyPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
