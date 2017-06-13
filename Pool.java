package gens;

import java.util.ArrayList;

public class Pool{	
	//information for the pool itself and the threads
	private Chromosome[] Population;
	private Chromosome Best;
	private Thread[] ThreadPool;
	private int Generations;
	private int PopSize;
	private int ThreadNumber;

	//information for the chromosomes inside the pool
	private int NumberGenes;
	private int SizeGenes;
	private int ChromosomeSize;

	public Pool(int Generations, int PopSize, int ThreadNumber, int NumberGenes, int SizeGenes) {
		this.PopSize = PopSize;  // how many chromosome are in a generations
		this.Generations = Generations; // how many generations (cycle of a for loop) will the simulation run for
		this.ThreadNumber = ThreadNumber; // how many threads will work together
		this.ChromosomeSize = NumberGenes*SizeGenes; // the bit length of the whole chromosome
		this.NumberGenes = NumberGenes; // the number of different genes
		this.SizeGenes = SizeGenes; // how big are each genes
		this.Population = new Chromosome[PopSize];
		this.ThreadPool = new Thread[ThreadNumber];

		for (int i = 0; i < PopSize; i++) {
			this.Population[i] = new Chromosome(NumberGenes, SizeGenes);
		}

		updateBest();
	}

	public void crunch (){
		ArrayList<Chromosome> childs = new ArrayList<Chromosome>();

		while(childs.size() < this.PopSize){
			for(int i = 0; i< this.ThreadNumber; i++){
				ThreadPool[i] = new Thread(new Breeder(this, childs));
			}
			for(int i = 0; i< this.ThreadNumber; i++){
				ThreadPool[i].start();
			}
			for(int i = 0; i< this.ThreadNumber; i++){
				try {
					ThreadPool[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		this.Population = childs.subList(0, this.PopSize).toArray(this.Population);
		updateBest();
	}

	private void updateBest() {
		Chromosome temp = this.Population[0];

		for (int j = 1; j < this.PopSize; j++){ // updates the Best chromosome of the pool
			if (temp.getFitness() > this.Population[j].getFitness()) {
				temp = this.Population[j];
			}
		}
		this.Best = temp;
	}

	public int getPopSize(){
		return this.PopSize;
	}

	public Chromosome[] getPopulation(){
		return this.Population;
	}

	public Chromosome getBest(){
		return this.Best;
	}

	public int getGenerations(){
		return this.Generations;
	}
	public int getThreadNumber(){
		return this.ThreadNumber;
	}

	public int getNumberGenes(){
		return this.NumberGenes;
	}

	public int getSizeGenes(){
		return this.SizeGenes;
	}

	public int getChromosomeSize(){
		return this.ChromosomeSize;
	}
}