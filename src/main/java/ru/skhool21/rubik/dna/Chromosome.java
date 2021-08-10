package ru.skhool21.rubik.dna;

public class Chromosome {
	private boolean isFitnessChanged = true;
	private int fitness = 0;
	private int[] genes;

	public Chromosome(int length) {
		genes = new int[length];
	}

	public Chromosome initChromosome() {
		for (int i = 0; i < genes.length; i++) {
			if (Math.random() < 0.5) {
				genes[i] = 1;
			} else {
				genes[i] = 0;
			}
		}
		return this;
	}

	public int recalculateFitness() {
		int chromosomeFitness = 0;
		for (int i = 0; i < genes.length; i++) {
			if (genes[i] == GeneticAlgorithm.TARGET_CHROMOSOME[i]) {
				chromosomeFitness += 1;
			}
		}
		return chromosomeFitness;
	}

	public int getFitness() {
		if (isFitnessChanged) {
			fitness = recalculateFitness();
			isFitnessChanged = false;
		}
		return fitness;
	}

	public int[] getGenes() {
		isFitnessChanged = true;
		return genes;
	}
}
