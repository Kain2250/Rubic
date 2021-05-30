package ru.skhool21.rubik.model;

import lombok.Getter;
import lombok.Setter;
import ru.skhool21.rubik.service.RotatorService;

@Getter
@Setter
public class Cub implements RotatorService {
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

	@Override
	public String toString() {
		return "Front\n" + this.front +
				"Back\n" + this.back +
				"Left\n" + this.left +
				"Right\n" + this.right +
				"Up\n" + this.up +
				"Down\n" + this.down;
	}

	@Override
	public void frontClockwise() {
		rotateClockwiseFrontInSide(front);
		Color buffer = left.color[0][2];
		left.color[0][2] = down.color[0][0];
		down.color[0][0] = right.color[2][0];
		right.color[2][0] = up.color[2][2];
		up.color[2][2] = buffer;

		buffer = left.color[1][2];
		left.color[1][2] = down.color[0][1];
		down.color[0][1] = right.color[1][0];
		right.color[1][0] = up.color[2][1];
		up.color[2][1] = buffer;

		buffer = left.color[2][2];
		left.color[2][2] = down.color[0][2];
		down.color[0][2] = right.color[0][0];
		right.color[0][0] = up.color[2][0];
		up.color[2][0] = buffer;
	}

	@Override
	public void frontCounterClockwise() {
		rotateCounterClockwiseFrontInSide(front);
		Color buffer = left.color[0][2];
		left.color[0][2] = up.color[2][2];
		up.color[2][2] = right.color[2][0];
		right.color[2][0] = down.color[0][0];
		down.color[0][0] = buffer;

		buffer = left.color[1][2];
		left.color[1][2] = up.color[2][1];
		up.color[2][1] = right.color[1][0];
		right.color[1][0] = down.color[0][1];
		down.color[0][1] = buffer;

		buffer = left.color[2][2];
		left.color[2][2] = up.color[2][0];
		up.color[2][0] = right.color[0][0];
		right.color[0][0] = down.color[0][2];
		down.color[0][2] = buffer;
	}

	@Override
	public void backClockwise() {
		rotateClockwiseFrontInSide(back);
		Color buffer = up.color[0][0];
		up.color[0][0] = right.color[0][2];
		right.color[0][2] = down.color[2][2];
		down.color[2][2] = left.color[2][0];
		left.color[2][0] = buffer;

		buffer = up.color[0][1];
		up.color[0][1] = right.color[1][2];
		right.color[1][2] = down.color[2][1];
		down.color[2][1] = left.color[1][0];
		left.color[1][0] = buffer;

		buffer = up.color[0][2];
		up.color[0][2] = right.color[2][2];
		right.color[2][2] = down.color[2][0];
		down.color[2][0] = left.color[0][0];
		left.color[0][0] = buffer;
	}

	@Override
	public void backCounterClockwise() {
		rotateCounterClockwiseFrontInSide(back);
		Color buffer = up.color[0][0];
		up.color[0][0] = left.color[2][0];
		left.color[2][0] = down.color[2][2];
		down.color[2][2] = right.color[0][2];
		right.color[0][2] = buffer;

		buffer = up.color[0][1];
		up.color[0][1] = left.color[1][0];
		left.color[1][0] = down.color[2][1];
		down.color[2][1] = right.color[1][2];
		right.color[1][2] = buffer;

		buffer = up.color[0][2];
		up.color[0][2] = left.color[0][0];
		left.color[0][0] = down.color[2][0];
		down.color[2][0] = right.color[2][2];
		right.color[2][2] = buffer;
	}

	@Override
	public void leftClockwise() {
		rotateClockwiseFrontInSide(left);
		Color buffer = up.color[0][0];
		up.color[0][0] = back.color[2][2];
		back.color[2][2] = down.color[0][0];
		down.color[0][0] = front.color[0][0];
		front.color[0][0] = buffer;

		buffer = up.color[1][0];
		up.color[1][0] = back.color[1][2];
		back.color[1][2] = down.color[1][0];
		down.color[1][0] = front.color[1][0];
		front.color[1][0] = buffer;

		buffer = up.color[2][0];
		up.color[2][0] = back.color[0][2];
		back.color[0][2] = down.color[2][0];
		down.color[2][0] = front.color[2][0];
		front.color[2][0] = buffer;
	}

	@Override
	public void leftCounterClockwise() {
		rotateCounterClockwiseFrontInSide(left);
		Color buffer = up.color[0][0];
		up.color[0][0] = front.color[0][0];
		front.color[0][0] = down.color[0][0];
		down.color[0][0] = back.color[2][2];
		back.color[2][2] = buffer;

		buffer = up.color[1][0];
		up.color[1][0] = front.color[1][0];
		front.color[1][0] = down.color[1][0];
		down.color[1][0] = back.color[1][2];
		back.color[1][2] = buffer;

		buffer = up.color[2][0];
		up.color[2][0] = front.color[2][0];
		front.color[2][0] = down.color[2][0];
		down.color[2][0] = back.color[0][2];
		back.color[0][2] = buffer;
	}

	@Override
	public void rightClockwise() {
		rotateClockwiseFrontInSide(right);
		Color buffer = up.color[2][2];
		up.color[2][2] = front.color[2][2];
		front.color[2][2] = down.color[2][2];
		down.color[2][2] = back.color[0][0];
		back.color[0][0] = buffer;

		buffer = up.color[1][2];
		up.color[1][2] = front.color[1][2];
		front.color[1][2] = down.color[1][2];
		down.color[1][2] = back.color[1][0];
		back.color[1][0] = buffer;

		buffer = up.color[0][2];
		up.color[0][2] = front.color[0][2];
		front.color[0][2] = down.color[0][2];
		down.color[0][2] = back.color[2][0];
		back.color[2][0] = buffer;
	}

	@Override
	public void rightCounterClockwise() {
		rotateCounterClockwiseFrontInSide(right);
		Color buffer = up.color[2][2];
		up.color[2][2] = back.color[0][0];
		back.color[0][0] = down.color[2][2];
		down.color[2][2] = front.color[2][2];
		front.color[2][2] = buffer;

		buffer = up.color[1][2];
		up.color[1][2] = back.color[1][0];
		back.color[1][0] = down.color[1][2];
		down.color[1][2] = front.color[1][2];
		front.color[1][2] = buffer;

		buffer = up.color[0][2];
		up.color[0][2] = back.color[2][0];
		back.color[2][0] = down.color[0][2];
		down.color[0][2] = front.color[0][2];
		front.color[0][2] = buffer;
	}

	@Override
	public void upClockwise() {
		rotateClockwiseFrontInSide(up);
		Color buffer = back.color[0][2];
		back.color[0][2] = left.color[0][2];
		left.color[0][2] = front.color[0][2];
		front.color[0][2] = right.color[0][2];
		right.color[0][2] = buffer;

		buffer = back.color[0][1];
		back.color[0][1] = left.color[0][1];
		left.color[0][1] = front.color[0][1];
		front.color[0][1] = right.color[0][1];
		right.color[0][1] = buffer;

		buffer = back.color[0][0];
		back.color[0][0] = left.color[0][0];
		left.color[0][0] = front.color[0][0];
		front.color[0][0] = right.color[0][0];
		right.color[0][0] = buffer;
	}

	@Override
	public void upCounterClockwise() {
		rotateCounterClockwiseFrontInSide(up);
		Color buffer = back.color[0][2];
		back.color[0][2] = right.color[0][2];
		right.color[0][2] = front.color[0][2];
		front.color[0][2] = left.color[0][2];
		left.color[0][2] = buffer;

		buffer = back.color[0][1];
		back.color[0][1] = right.color[0][1];
		right.color[0][1] = front.color[0][1];
		front.color[0][1] = left.color[0][1];
		left.color[0][1] = buffer;

		buffer = back.color[0][0];
		back.color[0][0] = right.color[0][0];
		right.color[0][0] = front.color[0][0];
		front.color[0][0] = left.color[0][0];
		left.color[0][0] = buffer;
	}

	@Override
	public void downClockwise() {
		rotateClockwiseFrontInSide(down);
		Color buffer = front.color[2][0];
		front.color[2][0] = left.color[2][0];
		left.color[2][0] = back.color[2][0];
		back.color[2][0] = right.color[2][0];
		right.color[2][0] = buffer;

		buffer = front.color[2][1];
		front.color[2][1] = left.color[2][1];
		left.color[2][1] = back.color[2][1];
		back.color[2][1] = right.color[2][1];
		right.color[2][1] = buffer;

		buffer = front.color[2][2];
		front.color[2][2] = left.color[2][2];
		left.color[2][2] = back.color[2][2];
		back.color[2][2] = right.color[2][2];
		right.color[2][2] = buffer;
	}

	@Override
	public void downCounterClockwise() {
		rotateCounterClockwiseFrontInSide(down);
		Color buffer = front.color[2][0];
		front.color[2][0] = right.color[2][0];
		right.color[2][0] = back.color[2][0];
		back.color[2][0] = left.color[2][0];
		left.color[2][0] = buffer;

		buffer = front.color[2][1];
		front.color[2][1] = right.color[2][1];
		right.color[2][1] = back.color[2][1];
		back.color[2][1] = left.color[2][1];
		left.color[2][1] = buffer;

		buffer = front.color[2][2];
		front.color[2][2] = right.color[2][2];
		right.color[2][2] = back.color[2][2];
		back.color[2][2] = left.color[2][2];
		left.color[2][2] = buffer;
	}

	private void rotateClockwiseFrontInSide(Side side) {
		Color buffer = side.color[0][0];
		side.color[0][0] = side.color[2][0];
		side.color[2][0] = side.color[2][2];
		side.color[2][2] = side.color[0][2];
		side.color[0][2] = buffer;

		buffer = side.color[0][1];
		side.color[0][1] = side.color[1][0];
		side.color[1][0] = side.color[2][1];
		side.color[2][1] = side.color[1][2];
		side.color[1][2] = buffer;
	}

	private void rotateCounterClockwiseFrontInSide(Side side) {
		Color buffer = side.color[0][0];
		side.color[0][0] = side.color[0][2];
		side.color[0][2] = side.color[2][2];
		side.color[2][2] = side.color[2][0];
		side.color[2][0] = buffer;

		buffer = side.color[0][1];
		side.color[0][1] = side.color[1][2];
		side.color[1][2] = side.color[2][1];
		side.color[2][1] = side.color[1][0];
		side.color[1][0] = buffer;
	}
}
