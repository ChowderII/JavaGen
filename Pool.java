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
	private long StartTime;
	private long EndTime;
	
	//information for the chromosomes inside the pool
	private int NumberGenes;
	private int SizeGenes;
	private int ChromosomeSize;
	
	public Pool(int Generations, int PopSize, int ThreadNumber, int ChromosomeSize, int NumberGenes, int SizeGenes) {
		this.PopSize = PopSize;  // how many chromosome are in a generations
		this.Generations = Generations; // how many generations (cycle of a for loop) will the simulation run for
		this.ThreadNumber = ThreadNumber; // how many threads will work together
		this.ChromosomeSize = ChromosomeSize; // the bit length of the whole chromosome
		this.NumberGenes = NumberGenes; // the number of different genes
		this.SizeGenes = SizeGenes; // how big are each genes
		this.Population = new Chromosome[PopSize];
		this.ThreadPool = new Thread[ThreadNumber];
		
		for (int i = 0; i < PopSize; i++) {
			this.Population[i] = new Chromosome(NumberGenes, SizeGenes);
		}
	}
	
	public void crunch (){
		this.StartTime = System.currentTimeMillis();
		
		for (int i = 0; i < this.Generations; i++) {
			ArrayList<Chromosome> childPopulation = new ArrayList<Chromosome>(this.PopSize);
			
			for(int j = 0; j < this.ThreadNumber; j++){
				ThreadPool[j] = new Thread(new Breeder(this, childPopulation, j)); // create the threads
			}
			for(int j = 0; j < this.ThreadNumber; j++){
				ThreadPool[j].start(); // start all the threads
			}
			for(int j = 0; j < this.ThreadNumber; j++){
				try {
					ThreadPool[j].join(); // join all the threads
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(int j = 0; j < this.PopSize; j++){
				this.Population[j] = childPopulation.get(j); // reset the parent pool.
			}
		}
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