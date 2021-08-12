package ru.skhool21.rubik;

import ru.skhool21.rubik.controller.GraphicsController;
import ru.skhool21.rubik.controller.SolverController;
import ru.skhool21.rubik.dna.GeneticCubAlgorithm;
import ru.skhool21.rubik.dna.Population;
import ru.skhool21.rubik.exception.ParsingException;
import ru.skhool21.rubik.model.Cub;

import java.lang.reflect.InvocationTargetException;

public class App {
	public static void main(String[] args) throws NoSuchMethodException, ParsingException, IllegalAccessException, InvocationTargetException {
		SolverController solverController = new SolverController();

		try {
			solverController.pars(args);
		} catch (ParsingException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		if (solverController.isGraphicMode()) {
			solverController.confuse();
			new GraphicsController();
		} else {
			solverController.confuse();
			Population population = new Population(10);
			GeneticCubAlgorithm cubAlgorithm = new GeneticCubAlgorithm();
            population.confusePopulation();
            population.initPopulation();
            Cub solveCub = null;
            int generationNumber = 0;
            boolean run = true;
            while (run) {
                generationNumber += 1;
                System.out.println("Fitness: " + population.getCubs().get(0).getFitness());
                System.out.println("Gens : " + population.getCubs().get(0).getGenes());
                for (Cub currentCub : population.getCubs()) {
                    if (currentCub.isLevel1Solve()) {
                        run = false;
                        solveCub = currentCub;
                    }
                }
                population = cubAlgorithm.evolve(population);
                population.sortChromosomesByFitness();
                System.out.println();
            }
            System.out.println("Generation number = " + generationNumber);
            System.out.println(solveCub.toString());
            solverController.printConfuse();
            System.out.println(solveCub.getGenes());
		}
	}


//        Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initPopulation();
//        GeneticAlgorithm algorithm = new GeneticAlgorithm();
//        int generationNumber = 0;
//        System.out.println("|-------------------------------------------|");
//        System.out.println("Generation # " + generationNumber + " Fitness chromosomes : " + population.getChromosomes()[0].getFitness());
//        printPopulation(population, "Target chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
//
//        while (population.getChromosomes()[0].getFitness() < GeneticAlgorithm.POPULATION_SIZE) {
//            generationNumber += 1;
//            System.out.println("|-------------------------------------------|");
//            population = algorithm.evolve(population);
//            population.sortChromosomesByFitness();
//            System.out.println("Generation # " + generationNumber + " Fitness chromosomes : " + population.getChromosomes()[0].getFitness());
//            printPopulation(population, "Target chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
//        }
//    }
//
//    public static void printPopulation(Population population, String head) {
//        System.out.println(head);
//        System.out.println("|-------------------------------------------|");
//        for (int i = 0; i < population.getChromosomes().length; i++) {
//            System.out.println("Chromosome # " + i +
//                    " : " + Arrays.toString(population.getChromosomes()[i].getGenes()) +
//                    " Fitness: " + population.getChromosomes()[i].getFitness());
//        }
//    }
}
