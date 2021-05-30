package ru.skhool21.rubik.model;

import java.util.Arrays;

public class Side {
	public Color[][] color = new Color[3][3];

	public Side(Color color) {
		for (Color[] ints : this.color) {
			Arrays.fill(ints, color);
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(color[0]) + "\n" +
				Arrays.toString(color[1]) + "\n" +
				Arrays.toString(color[2]) + "\n";
	}

}
