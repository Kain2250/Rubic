package ru.skhool21.rubik.controller;

import lombok.SneakyThrows;
import ru.skhool21.rubik.model.Action;
import ru.skhool21.rubik.model.Cub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolverController {
	private Cub cub;
	private List<Action> confuse;
	private List<Action> solve;

	public SolverController() {
		this.cub = new Cub();
		this.confuse = new ArrayList<>();
		this.solve = new ArrayList<>();
	}

	public void pars(String[] arg) {
		//TODO: Заебошить парсинг
		confuse.add(Action.B);
		confuse.add(Action.D);
		confuse.add(Action.F);

	}

	@SneakyThrows
	public void confuse() {
		final int GOD_NUMBER_CONFUSE = 21;
		Random random = new Random();

		if (!confuse.isEmpty()) {
			for (Action elem : confuse) {
				Cub.class.getMethod(Action.fromId(elem.ordinal()).getName()).invoke(this.cub);
			}
		} else {
			for (int i = 0; i < GOD_NUMBER_CONFUSE; i++) {
				int nextInt = random.nextInt(Action.values().length);
				Cub.class.getMethod(Action.fromId(nextInt).getName()).invoke(this.cub);
				confuse.add(Action.fromId(nextInt));
			}
		}
	}

	public void solve() {
		//TODO: Заебошить сольвер
	}

	public void printSolve() {
		System.out.println(this.solve);
	}

	public void printCub() {
		System.out.println(this.cub);
	}

	public void printConfuse() {
		System.out.println(this.confuse);
	}

}
