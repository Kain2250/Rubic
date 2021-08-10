package ru.skhool21.rubik.model;

import java.util.Arrays;

public class Side {
	public ColorSide[][] colorSide = new ColorSide[3][3];

	public Side(ColorSide colorSide) {
		for (ColorSide[] ints : this.colorSide) {
			Arrays.fill(ints, colorSide);
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(colorSide[0]) + "\n" +
				Arrays.toString(colorSide[1]) + "\n" +
				Arrays.toString(colorSide[2]) + "\n";
	}

}
