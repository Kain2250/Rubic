package ru.skhool21.rubik.controller;

import ru.skhool21.rubik.model.Action;
import ru.skhool21.rubik.model.Cub;

import java.util.ArrayList;
import java.util.List;

public class SolverController {
	private Cub cub;
	private List<Action> confuse;
	private List<Action> solve;

	public SolverController() {
		this.cub = new Cub();
	}

	public void pars(String[] arg) {
		//TODO: Заебошить парсинг
	}

	public void confuse() {
		//TODO: Заебошить запутыватель
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

}
