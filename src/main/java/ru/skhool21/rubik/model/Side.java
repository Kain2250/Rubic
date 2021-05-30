package ru.skhool21.rubik.model;

import java.util.Arrays;

public class Side {
    Color[][] color = new Color[3][3];

    public Side(Color color) {
        for (Color[] ints : this.color) {
            Arrays.fill(ints, color);
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(color);
    }

}
