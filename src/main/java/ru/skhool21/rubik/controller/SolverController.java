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
	private List<Integer> list;

	public SolverController() {
		this.cub = new Cub();
	}

	public void pars(String[] arg) {
		//TODO: Заебошить парсинг
		this.list = new ArrayList<>();

		/*
		list.add(1);
		list.add(4);
		list.add(2);
		list.add(0);
		 */
	}

	@SneakyThrows
	public void confuse() {
		Object cubeClass = this.cub;
		Random random = new Random();

		confuse = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Integer elem : list) {
				Cub.class.getMethod(Action.fromId(elem).getName()).invoke(cubeClass);
				confuse.add(Action.fromId(elem));
			}
		} else {
			//TODO: Придумать как в ручную задавать колличество итераций
			for (int i = 0; i < 50; i++) {
				int nextInt = random.nextInt(24);
				Cub.class.getMethod(Action.fromId(nextInt).getName()).invoke(cubeClass);
				confuse.add(Action.fromId(nextInt));
			}
		}
	}

	public void solve() {
		//TODO: Заебошить сольвер
		this.solve = new ArrayList<>();
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
