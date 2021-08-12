package ru.skhool21.rubik.model;

import lombok.Getter;
import lombok.Setter;
import ru.skhool21.rubik.dna.Population;
import ru.skhool21.rubik.exception.ParsingException;
import ru.skhool21.rubik.service.RotatorService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Cub implements RotatorService {
	public static final Cub idealCub = new Cub();
	private Side front;
	private Side back;
	private Side left;
	private Side right;
	private Side up;
	private Side down;
	private List<Action> genes = new ArrayList<>();
	private int fitness = 0;
	private boolean isLevel1Solve = false;
	private boolean isLevel2Solve = false;
	private boolean isLevel3Solve = false;
	private boolean isFitnessChanged = true;

	public Cub() {
		front = new Side(ColorSide.WHITE);
		back = new Side(ColorSide.YELLOW);
		left = new Side(ColorSide.GREEN);
		right = new Side(ColorSide.BLUE);
		up = new Side(ColorSide.ORANGE);
		down = new Side(ColorSide.RED);
	}

	public void runSequence(String sequence) {
		String normalizeStr = sequence.replace('\'', '_');
		boolean itsValidAction;
		List<Action> actionsList = new ArrayList<>();

		String[] actions = normalizeStr.split("\\s+");
		for (String actionStr : actions) {
			itsValidAction = false;
			for (Action action : Action.values()) {
				if (actionStr.equals(action.name())) {
					itsValidAction = true;
					actionsList.add(action);
				}
			}
			if (!itsValidAction) {
				try {
					throw new ParsingException("Неизвестный ключ поворота - " + actionStr);
				} catch (ParsingException e) {
					e.printStackTrace();
				}
			}
		}

		if (!actionsList.isEmpty()) {
			for (Action elem : actionsList) {
				try {
					Cub.class.getMethod(Action.fromId(elem.ordinal()).getMethodName()).invoke(this);
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void runSequence(List<Action> sequence) {
		if (!sequence.isEmpty()) {
			for (Action elem : sequence) {
				try {
					Cub.class.getMethod(Action.fromId(elem.ordinal()).getMethodName()).invoke(this);
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void runSequence(Action action) {
		try {
			Cub.class.getMethod(Action.fromId(action.ordinal()).getMethodName()).invoke(this);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public Cub initCubChromosome() {
		Random random = new Random();
		for (int i = 0; i < Population.getLength(); i++) {
			genes.add(Action.fromId(random.nextInt(Action.values().length - 1)));
		}
		runSequence(genes);
		return this;
	}

	public Cub initCubChromosome(int length) {
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			genes.add(Action.fromId(random.nextInt(Action.values().length - 1)));
		}
		runSequence(genes);
		return this;
	}

	private int recalculateFitness() {
		return layer1() * (isLevel1Solve ? 10 : 0) +
                layer2() * (isLevel2Solve ? 50 : 0) +
                layer3() * (isLevel3Solve ? 100 : 0);
	}

	public int getFitness() {
		if (isFitnessChanged) {
			fitness = recalculateFitness();
			isFitnessChanged = false;
		}
		return fitness;
	}

	public List<Action> getGenes() {
		isFitnessChanged = true;
		return genes;
	}

	private int layer1() {
		int fitness = 0;
		if (this.front.colorSide[0][1] == idealCub.front.colorSide[0][1] &&
				this.up.colorSide[2][1] == idealCub.up.colorSide[2][1]) {
			fitness += 1;
		}

		if (this.front.colorSide[1][2] == idealCub.front.colorSide[1][2] &&
				this.right.colorSide[1][0] == idealCub.right.colorSide[1][0]) {
			fitness += 1;
		}

		if (this.front.colorSide[1][0] == idealCub.front.colorSide[1][0] &&
				this.left.colorSide[1][2] == idealCub.left.colorSide[1][2]) {
			fitness += 1;
		}

		if (this.front.colorSide[2][1] == idealCub.front.colorSide[2][1] &&
				this.down.colorSide[0][1] == idealCub.down.colorSide[0][1]) {
			fitness += 1;
		}
        if (this.front.colorSide[0][0] == idealCub.front.colorSide[0][0] &&
                this.up.colorSide[2][0] == idealCub.up.colorSide[2][0] &&
                this.left.colorSide[0][2] == idealCub.left.colorSide[0][2]) {
            fitness += 1;
        }

        if (this.front.colorSide[0][2] == idealCub.front.colorSide[0][2] &&
                this.up.colorSide[2][2] == idealCub.up.colorSide[2][2] &&
                this.right.colorSide[0][0] == idealCub.right.colorSide[0][0]) {
            fitness += 1;
        }

        if (this.front.colorSide[2][0] == idealCub.front.colorSide[2][0] &&
                this.down.colorSide[0][0] == idealCub.down.colorSide[0][0] &&
                this.left.colorSide[2][2] == idealCub.left.colorSide[2][2]) {
            fitness += 1;
        }

        if (this.front.colorSide[2][2] == idealCub.front.colorSide[2][2] &&
                this.right.colorSide[2][0] == idealCub.right.colorSide[2][0] &&
                this.down.colorSide[0][2] == idealCub.down.colorSide[0][2]) {
            fitness += 1;
        }
        if (fitness == 8) {
            isLevel1Solve = true;
        }
		return fitness;
	}

	private int layer2() {
		int fitness = 0;
		if (this.up.colorSide[1][0] == idealCub.up.colorSide[1][0] &&
				this.left.colorSide[0][1] == idealCub.left.colorSide[0][1]) {
			fitness += 1;
		}

		if (this.up.colorSide[1][2] == idealCub.up.colorSide[1][2] &&
				this.right.colorSide[0][1] == idealCub.right.colorSide[0][1]) {
			fitness += 1;
		}

		if (this.right.colorSide[2][1] == idealCub.right.colorSide[2][1] &&
				this.down.colorSide[1][2] == idealCub.down.colorSide[1][2]) {
			fitness += 1;
		}

		if (this.left.colorSide[2][1] == idealCub.left.colorSide[2][1] &&
				this.down.colorSide[1][0] == idealCub.down.colorSide[1][0]) {
			fitness += 1;
		}
		if (fitness == 4) {
		    isLevel2Solve = true;
        }
		return fitness;
	}

    private int layer3() {
        int fitness = 0;
        if (this.back.colorSide[0][1] == idealCub.back.colorSide[0][1] &&
                this.up.colorSide[0][1] == idealCub.up.colorSide[0][1]) {
            fitness += 1;
        }

        if (this.back.colorSide[1][0] == idealCub.back.colorSide[1][0] &&
                this.right.colorSide[1][2] == idealCub.right.colorSide[1][2]) {
            fitness += 1;
        }

        if (this.back.colorSide[1][2] == idealCub.back.colorSide[1][2] &&
                this.left.colorSide[1][0] == idealCub.left.colorSide[1][0]) {
            fitness += 1;
        }

        if (this.back.colorSide[2][1] == idealCub.back.colorSide[2][1] &&
                this.down.colorSide[2][1] == idealCub.down.colorSide[2][1]) {
            fitness += 1;
        }
        if (this.back.colorSide[0][0] == idealCub.back.colorSide[0][0] &&
                this.up.colorSide[0][2] == idealCub.up.colorSide[0][2] &&
                this.right.colorSide[0][2] == idealCub.right.colorSide[0][2]) {
            fitness += 1;
        }

        if (this.back.colorSide[0][2] == idealCub.back.colorSide[0][2] &&
                this.up.colorSide[0][0] == idealCub.up.colorSide[0][0] &&
                this.left.colorSide[0][0] == idealCub.left.colorSide[0][0]) {
            fitness += 1;
        }
        if (this.back.colorSide[2][0] == idealCub.back.colorSide[2][0] &&
                this.down.colorSide[2][2] == idealCub.down.colorSide[2][2] &&
                this.right.colorSide[2][2] == idealCub.right.colorSide[2][2]) {
            fitness += 1;
        }

        if (this.back.colorSide[2][2] == idealCub.back.colorSide[2][2] &&
                this.down.colorSide[2][0] == idealCub.down.colorSide[2][0] &&
                this.left.colorSide[2][0] == idealCub.left.colorSide[2][0]) {
            fitness += 1;
        }
        if (fitness == 8) {
            isLevel3Solve = true;
        }
		return fitness;
	}

    public boolean isLevel1Solve() {
        return isLevel1Solve;
    }

    public boolean isLevel2Solve() {
        return isLevel2Solve;
    }

    public boolean isLevel3Solve() {
        return isLevel3Solve;
    }

    @Override
	public String toString() {
		return "Front\n" + this.front +
				"Back\n" + this.back +
				"Left\n" + this.left +
				"Right\n" + this.right +
				"Up\n" + this.up +
				"Down\n" + this.down +
				"Fitness: " + this.getFitness();
	}

	@Override
	public void frontClockwise() {
		rotateClockwiseFrontInSide(front);
		ColorSide buffer = left.colorSide[0][2];
		left.colorSide[0][2] = down.colorSide[0][0];
		down.colorSide[0][0] = right.colorSide[2][0];
		right.colorSide[2][0] = up.colorSide[2][2];
		up.colorSide[2][2] = buffer;

		buffer = left.colorSide[1][2];
		left.colorSide[1][2] = down.colorSide[0][1];
		down.colorSide[0][1] = right.colorSide[1][0];
		right.colorSide[1][0] = up.colorSide[2][1];
		up.colorSide[2][1] = buffer;

		buffer = left.colorSide[2][2];
		left.colorSide[2][2] = down.colorSide[0][2];
		down.colorSide[0][2] = right.colorSide[0][0];
		right.colorSide[0][0] = up.colorSide[2][0];
		up.colorSide[2][0] = buffer;
	}

	@Override
	public void frontCounterClockwise() {
		rotateCounterClockwiseFrontInSide(front);
		ColorSide buffer = left.colorSide[0][2];
		left.colorSide[0][2] = up.colorSide[2][2];
		up.colorSide[2][2] = right.colorSide[2][0];
		right.colorSide[2][0] = down.colorSide[0][0];
		down.colorSide[0][0] = buffer;

		buffer = left.colorSide[1][2];
		left.colorSide[1][2] = up.colorSide[2][1];
		up.colorSide[2][1] = right.colorSide[1][0];
		right.colorSide[1][0] = down.colorSide[0][1];
		down.colorSide[0][1] = buffer;

		buffer = left.colorSide[2][2];
		left.colorSide[2][2] = up.colorSide[2][0];
		up.colorSide[2][0] = right.colorSide[0][0];
		right.colorSide[0][0] = down.colorSide[0][2];
		down.colorSide[0][2] = buffer;
	}

	@Override
	public void backClockwise() {
		rotateClockwiseFrontInSide(back);
		ColorSide buffer = up.colorSide[0][0];
		up.colorSide[0][0] = right.colorSide[0][2];
		right.colorSide[0][2] = down.colorSide[2][2];
		down.colorSide[2][2] = left.colorSide[2][0];
		left.colorSide[2][0] = buffer;

		buffer = up.colorSide[0][1];
		up.colorSide[0][1] = right.colorSide[1][2];
		right.colorSide[1][2] = down.colorSide[2][1];
		down.colorSide[2][1] = left.colorSide[1][0];
		left.colorSide[1][0] = buffer;

		buffer = up.colorSide[0][2];
		up.colorSide[0][2] = right.colorSide[2][2];
		right.colorSide[2][2] = down.colorSide[2][0];
		down.colorSide[2][0] = left.colorSide[0][0];
		left.colorSide[0][0] = buffer;
	}

	@Override
	public void backCounterClockwise() {
		rotateCounterClockwiseFrontInSide(back);
		ColorSide buffer = up.colorSide[0][0];
		up.colorSide[0][0] = left.colorSide[2][0];
		left.colorSide[2][0] = down.colorSide[2][2];
		down.colorSide[2][2] = right.colorSide[0][2];
		right.colorSide[0][2] = buffer;

		buffer = up.colorSide[0][1];
		up.colorSide[0][1] = left.colorSide[1][0];
		left.colorSide[1][0] = down.colorSide[2][1];
		down.colorSide[2][1] = right.colorSide[1][2];
		right.colorSide[1][2] = buffer;

		buffer = up.colorSide[0][2];
		up.colorSide[0][2] = left.colorSide[0][0];
		left.colorSide[0][0] = down.colorSide[2][0];
		down.colorSide[2][0] = right.colorSide[2][2];
		right.colorSide[2][2] = buffer;
	}

	@Override
	public void leftClockwise() {
		rotateClockwiseFrontInSide(left);
		ColorSide buffer = up.colorSide[0][0];
		up.colorSide[0][0] = back.colorSide[2][2];
		back.colorSide[2][2] = down.colorSide[0][0];
		down.colorSide[0][0] = front.colorSide[0][0];
		front.colorSide[0][0] = buffer;

		buffer = up.colorSide[1][0];
		up.colorSide[1][0] = back.colorSide[1][2];
		back.colorSide[1][2] = down.colorSide[1][0];
		down.colorSide[1][0] = front.colorSide[1][0];
		front.colorSide[1][0] = buffer;

		buffer = up.colorSide[2][0];
		up.colorSide[2][0] = back.colorSide[0][2];
		back.colorSide[0][2] = down.colorSide[2][0];
		down.colorSide[2][0] = front.colorSide[2][0];
		front.colorSide[2][0] = buffer;
	}

	@Override
	public void leftCounterClockwise() {
		rotateCounterClockwiseFrontInSide(left);
		ColorSide buffer = up.colorSide[0][0];
		up.colorSide[0][0] = front.colorSide[0][0];
		front.colorSide[0][0] = down.colorSide[0][0];
		down.colorSide[0][0] = back.colorSide[2][2];
		back.colorSide[2][2] = buffer;

		buffer = up.colorSide[1][0];
		up.colorSide[1][0] = front.colorSide[1][0];
		front.colorSide[1][0] = down.colorSide[1][0];
		down.colorSide[1][0] = back.colorSide[1][2];
		back.colorSide[1][2] = buffer;

		buffer = up.colorSide[2][0];
		up.colorSide[2][0] = front.colorSide[2][0];
		front.colorSide[2][0] = down.colorSide[2][0];
		down.colorSide[2][0] = back.colorSide[0][2];
		back.colorSide[0][2] = buffer;
	}

	@Override
	public void rightClockwise() {
		rotateClockwiseFrontInSide(right);
		ColorSide buffer = up.colorSide[2][2];
		up.colorSide[2][2] = front.colorSide[2][2];
		front.colorSide[2][2] = down.colorSide[2][2];
		down.colorSide[2][2] = back.colorSide[0][0];
		back.colorSide[0][0] = buffer;

		buffer = up.colorSide[1][2];
		up.colorSide[1][2] = front.colorSide[1][2];
		front.colorSide[1][2] = down.colorSide[1][2];
		down.colorSide[1][2] = back.colorSide[1][0];
		back.colorSide[1][0] = buffer;

		buffer = up.colorSide[0][2];
		up.colorSide[0][2] = front.colorSide[0][2];
		front.colorSide[0][2] = down.colorSide[0][2];
		down.colorSide[0][2] = back.colorSide[2][0];
		back.colorSide[2][0] = buffer;
	}

	@Override
	public void rightCounterClockwise() {
		rotateCounterClockwiseFrontInSide(right);
		ColorSide buffer = up.colorSide[2][2];
		up.colorSide[2][2] = back.colorSide[0][0];
		back.colorSide[0][0] = down.colorSide[2][2];
		down.colorSide[2][2] = front.colorSide[2][2];
		front.colorSide[2][2] = buffer;

		buffer = up.colorSide[1][2];
		up.colorSide[1][2] = back.colorSide[1][0];
		back.colorSide[1][0] = down.colorSide[1][2];
		down.colorSide[1][2] = front.colorSide[1][2];
		front.colorSide[1][2] = buffer;

		buffer = up.colorSide[0][2];
		up.colorSide[0][2] = back.colorSide[2][0];
		back.colorSide[2][0] = down.colorSide[0][2];
		down.colorSide[0][2] = front.colorSide[0][2];
		front.colorSide[0][2] = buffer;
	}

	@Override
	public void upClockwise() {
		rotateClockwiseFrontInSide(up);
		ColorSide buffer = back.colorSide[0][2];
		back.colorSide[0][2] = left.colorSide[0][2];
		left.colorSide[0][2] = front.colorSide[0][2];
		front.colorSide[0][2] = right.colorSide[0][2];
		right.colorSide[0][2] = buffer;

		buffer = back.colorSide[0][1];
		back.colorSide[0][1] = left.colorSide[0][1];
		left.colorSide[0][1] = front.colorSide[0][1];
		front.colorSide[0][1] = right.colorSide[0][1];
		right.colorSide[0][1] = buffer;

		buffer = back.colorSide[0][0];
		back.colorSide[0][0] = left.colorSide[0][0];
		left.colorSide[0][0] = front.colorSide[0][0];
		front.colorSide[0][0] = right.colorSide[0][0];
		right.colorSide[0][0] = buffer;
	}

	@Override
	public void upCounterClockwise() {
		rotateCounterClockwiseFrontInSide(up);
		ColorSide buffer = back.colorSide[0][2];
		back.colorSide[0][2] = right.colorSide[0][2];
		right.colorSide[0][2] = front.colorSide[0][2];
		front.colorSide[0][2] = left.colorSide[0][2];
		left.colorSide[0][2] = buffer;

		buffer = back.colorSide[0][1];
		back.colorSide[0][1] = right.colorSide[0][1];
		right.colorSide[0][1] = front.colorSide[0][1];
		front.colorSide[0][1] = left.colorSide[0][1];
		left.colorSide[0][1] = buffer;

		buffer = back.colorSide[0][0];
		back.colorSide[0][0] = right.colorSide[0][0];
		right.colorSide[0][0] = front.colorSide[0][0];
		front.colorSide[0][0] = left.colorSide[0][0];
		left.colorSide[0][0] = buffer;
	}

	@Override
	public void downClockwise() {
		rotateClockwiseFrontInSide(down);
		ColorSide buffer = front.colorSide[2][0];
		front.colorSide[2][0] = left.colorSide[2][0];
		left.colorSide[2][0] = back.colorSide[2][0];
		back.colorSide[2][0] = right.colorSide[2][0];
		right.colorSide[2][0] = buffer;

		buffer = front.colorSide[2][1];
		front.colorSide[2][1] = left.colorSide[2][1];
		left.colorSide[2][1] = back.colorSide[2][1];
		back.colorSide[2][1] = right.colorSide[2][1];
		right.colorSide[2][1] = buffer;

		buffer = front.colorSide[2][2];
		front.colorSide[2][2] = left.colorSide[2][2];
		left.colorSide[2][2] = back.colorSide[2][2];
		back.colorSide[2][2] = right.colorSide[2][2];
		right.colorSide[2][2] = buffer;
	}

	@Override
	public void downCounterClockwise() {
		rotateCounterClockwiseFrontInSide(down);
		ColorSide buffer = front.colorSide[2][0];
		front.colorSide[2][0] = right.colorSide[2][0];
		right.colorSide[2][0] = back.colorSide[2][0];
		back.colorSide[2][0] = left.colorSide[2][0];
		left.colorSide[2][0] = buffer;

		buffer = front.colorSide[2][1];
		front.colorSide[2][1] = right.colorSide[2][1];
		right.colorSide[2][1] = back.colorSide[2][1];
		back.colorSide[2][1] = left.colorSide[2][1];
		left.colorSide[2][1] = buffer;

		buffer = front.colorSide[2][2];
		front.colorSide[2][2] = right.colorSide[2][2];
		right.colorSide[2][2] = back.colorSide[2][2];
		back.colorSide[2][2] = left.colorSide[2][2];
		left.colorSide[2][2] = buffer;
	}

	@Override
	public void frontDoubleClockwise() {
		frontClockwise();
		frontClockwise();
	}

	@Override
	public void frontDoubleCounterClockwise() {
		frontCounterClockwise();
		frontCounterClockwise();
	}

	@Override
	public void backDoubleClockwise() {
		backClockwise();
		backClockwise();
	}

	@Override
	public void backDoubleCounterClockwise() {
		backCounterClockwise();
		backCounterClockwise();
	}

	@Override
	public void leftDoubleClockwise() {
		leftClockwise();
		leftClockwise();
	}

	@Override
	public void leftDoubleCounterClockwise() {
		leftCounterClockwise();
		leftCounterClockwise();
	}

	@Override
	public void rightDoubleClockwise() {
		rightClockwise();
		rightClockwise();
	}

	@Override
	public void rightDoubleCounterClockwise() {
		rightCounterClockwise();
		rightCounterClockwise();
	}

	@Override
	public void upDoubleClockwise() {
		upClockwise();
		upClockwise();
	}

	@Override
	public void upDoubleCounterClockwise() {
		upCounterClockwise();
		upCounterClockwise();
	}

	@Override
	public void downDoubleClockwise() {
		downClockwise();
		downClockwise();
	}

	@Override
	public void downDoubleCounterClockwise() {
		downCounterClockwise();
		downCounterClockwise();
	}

	private void rotateClockwiseFrontInSide(Side side) {
		ColorSide buffer = side.colorSide[0][0];
		side.colorSide[0][0] = side.colorSide[2][0];
		side.colorSide[2][0] = side.colorSide[2][2];
		side.colorSide[2][2] = side.colorSide[0][2];
		side.colorSide[0][2] = buffer;

		buffer = side.colorSide[0][1];
		side.colorSide[0][1] = side.colorSide[1][0];
		side.colorSide[1][0] = side.colorSide[2][1];
		side.colorSide[2][1] = side.colorSide[1][2];
		side.colorSide[1][2] = buffer;
	}

	private void rotateCounterClockwiseFrontInSide(Side side) {
		ColorSide buffer = side.colorSide[0][0];
		side.colorSide[0][0] = side.colorSide[0][2];
		side.colorSide[0][2] = side.colorSide[2][2];
		side.colorSide[2][2] = side.colorSide[2][0];
		side.colorSide[2][0] = buffer;

		buffer = side.colorSide[0][1];
		side.colorSide[0][1] = side.colorSide[1][2];
		side.colorSide[1][2] = side.colorSide[2][1];
		side.colorSide[2][1] = side.colorSide[1][0];
		side.colorSide[1][0] = buffer;
	}
}
