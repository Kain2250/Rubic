package ru.skhool21.rubik.dna;

import ru.skhool21.rubik.model.Cub;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Population {
	private static int length;
	private List<Cub> cubs;

	public Population(int length) {
		Population.length = length;
		cubs = new ArrayList<>();
	}

	public Population initPopulation() {
		for (int i = 0; i < length; i++) {
			cubs.add(new Cub().initCubChromosome());
		}
		sortChromosomesByFitness();
		return this;
	}

	public List<Cub> getCubs() {
		return cubs;
	}

	public void sortChromosomesByFitness() {
		cubs = cubs.stream()
				.sorted(Comparator.comparing(Cub::getFitness).reversed())
				.collect(Collectors.toList());
	}

	public void printCubChromosomes() {
		cubs.forEach(System.out::println);
	}

	public static int getLength() {
		return length;
	}
}
