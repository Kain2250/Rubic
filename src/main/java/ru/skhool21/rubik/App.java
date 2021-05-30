package ru.skhool21.rubik;

import ru.skhool21.rubik.controller.SolverController;

public class App {
	public static void main(String[] args) {
		SolverController solverController = new SolverController();
		solverController.pars(args);
		solverController.confuse();
		solverController.solve();
		solverController.printConfuse();
		solverController.printCub();
	}
}
