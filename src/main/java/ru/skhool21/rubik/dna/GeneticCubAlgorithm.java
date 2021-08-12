package ru.skhool21.rubik.dna;

import ru.skhool21.rubik.controller.SolverController;
import ru.skhool21.rubik.model.Action;
import ru.skhool21.rubik.model.Cub;

import java.util.Random;

public class GeneticCubAlgorithm {
	public static final int NUMBER_OF_ELITE_CHROMOSOMES = 1;
	public static final int TOURNAMENT_SELECTION_SIZE = 4;
	public static final double MUTATION_RATE = 0.25d;
	public Random random = new Random();

	public Population evolve(Population population) {
		return mutatePopulation(crossoverPopulation(population));
	}

	private Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(Population.getLength()).initPopulation();
		for (int i = 0; i < NUMBER_OF_ELITE_CHROMOSOMES; i++) {
			crossoverPopulation.getCubs().set(i, population.getCubs().get(0));
		}
		for (int i = NUMBER_OF_ELITE_CHROMOSOMES; i < Population.getLength(); i++) {
			Cub chromosome1 = selectTournamentPopulation(population).getCubs().get(0);
			Cub chromosome2 = selectTournamentPopulation(population).getCubs().get(0);
			crossoverPopulation.getCubs().set(i, crossoverChromosome(chromosome1, chromosome2));
		}
		return crossoverPopulation;
	}

	private Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(population.getCubs().size()).initPopulation();
		for (int i = 0; i < NUMBER_OF_ELITE_CHROMOSOMES; i++) {
			mutatePopulation.getCubs().add(i, population.getCubs().get(0));
		}
		for (int i = NUMBER_OF_ELITE_CHROMOSOMES; i < population.getCubs().size(); i++) {
			mutatePopulation.getCubs().add(i, mutateCubChromosome(population.getCubs().get(i)));
		}
		return mutatePopulation;
	}

	private Cub crossoverChromosome(Cub chromosome1, Cub chromosome2) {
		Cub crossoverChromosome = new Cub().initCubChromosome();
		for (int i = 0; i < crossoverChromosome.getGenes().size(); i++) {
			if (Math.random() < 0.5) {
				crossoverChromosome.getGenes().set(i, chromosome1.getGenes().get(i));
			} else {
				crossoverChromosome.getGenes().set(i, chromosome2.getGenes().get(i));
			}
		}
		return crossoverChromosome;
	}

	private Cub mutateCubChromosome(Cub chromosome) {
		Cub mutateChromosome = new Cub();
		mutateChromosome.runSequence(SolverController.getConfuse());
		mutateChromosome = mutateChromosome.initCubChromosome();

		if (!chromosome.isLevel1Solve()) {

		} else if (!chromosome.isLevel2Solve()) {
			mutateChromosome = solveLevel2();
		} else if (!chromosome.isLevel3Solve()) {

		}
		return mutateChromosome;
	}

	private Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(Population.getLength());
		tournamentPopulation.confusePopulation();
		tournamentPopulation.initPopulation();
		for (int i = 0; i < TOURNAMENT_SELECTION_SIZE; i++) {
			tournamentPopulation.getCubs().add(population.getCubs().get((int) (Math.random() * population.getCubs().size())));
		}
		tournamentPopulation.sortChromosomesByFitness();
		return tournamentPopulation;
	}
}
