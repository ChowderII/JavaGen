package gens;

import java.util.ArrayList;
import java.util.Random;

public class Breeder implements Runnable{
	
	private Pool p;
	private Random r;
	private int index;
	ArrayList<Chromosome> childPopulation;
	
	public Breeder(Pool p, ArrayList<Chromosome> childPopulation, int ind){
		this.p = p;
		this.r = new Random();
		this.index = ind;
		this.childPopulation = childPopulation;

	}
	
	public void run(){
		Chromosome parent0 = tournament();
		Chromosome parent1 = tournament();
		
		Chromosome child = crossOver(parent0, parent1);
		
		child = mutate(child);
		try{
		childPopulation.add(this.index, child);
		}
		catch(Exception e) {
			System.out.println(this.index);
		}
	}
	
	private Chromosome mutate(Chromosome c) {
		int[] gene = c.getGenome();
		int selector = r.nextInt(this.p.getChromosomeSize());
		
		if (gene[selector] == 1) // flip the bit at position selector
			gene[selector] = 0;
		else
			gene[selector] = 1;
		
		return (new Chromosome(gene, this.p.getNumberGenes()));
	}

	private Chromosome tournament() {
		Chromosome best = null;
		
		for (int i = 0; i <2; i++) {
			Chromosome tempInd = this.p.getPopulation()[r.nextInt(this.p.getPopSize())];
			
			if (best == null || tempInd.getFitness() > best.getFitness()) {
				best = tempInd;
			}
		}
		return best;
	}
	
	private Chromosome crossOver(Chromosome p0, Chromosome p1){
		int[] gene = new int[this.p.getChromosomeSize()];
		int selector = r.nextInt(2);
		int counter = this.p.getSizeGenes();
		
		for (int i = 0; i < this.p.getChromosomeSize(); i++) { // simple way to get different genes from two different parents
			if(counter == 0) {
				counter = this.p.getSizeGenes();
				selector = r.nextInt(2);
			}
			
			if (selector == 0){
				gene[i] = p0.getGenome()[i];
				counter--;
			}
			else {
				gene[i] = p1.getGenome()[i];
				counter--;
			}
		}
		
		return (new Chromosome(gene, this.p.getNumberGenes()));
	}
}
