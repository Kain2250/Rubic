package ru.skhool21.rubik;

import ru.skhool21.rubik.controller.SolverController;
import ru.skhool21.rubik.exception.ParsingException;

public class App {
    public static void main(String[] args) {
        SolverController solverController = new SolverController();

        try {
            solverController.pars(args);
        } catch (ParsingException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        solverController.confuse();
        solverController.solve();
        solverController.printConfuse();
        solverController.printSolve();
        solverController.printCub();
    }
}
