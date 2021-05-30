package ru.skhool21.rubik.model;

import lombok.Getter;

@Getter
public class Cub {
    private Side front;
    private Side back;
    private Side left;
    private Side right;
    private Side up;
    private Side down;

    public Cub() {
        front = new Side(Color.WHITE);
        back = new Side(Color.YELLOW);
        left = new Side(Color.GREEN);
        right = new Side(Color.BLUE);
        up = new Side(Color.ORANGE);
        down = new Side(Color.RED);
    }

}
