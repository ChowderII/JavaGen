package gens;

public class Pool{

	private Chromosome[] pool;
	private Chromosome Best;
	private Chromosome SecBest;
	private Thread[] threadPool;
	private int chromosomeSize;
	private long startTime;
	
	// config for the pool
	private final int POOL_SIZE; // requires at least of 2 !!!
	
	// constructor that allows different size of pool
	public Pool(int poolSize, int chromosomeSize) {
		POOL_SIZE = poolSize;
		this.chromosomeSize = chromosomeSize;
		this.pool = new Chromosome[this.POOL_SIZE];
		threadPool = new Thread[this.POOL_SIZE];
		Best = null;
		SecBest = null;
		this.startTime = System.currentTimeMillis();
	}
	
	public Chromosome getChromosome(int id){
		return this.pool[id];
	}
	
	public Chromosome[] getPool(){
		return this.pool;
	}
	
	/* this method should be called by the Pool Manager because the genes may change from case to case
	* therefore you should change the logic of the genes object in the main class then pass it to the pool manager which
	 will take care of the rest 
	 @param boolean duplicate : states whether the solutions can have duplicated numbers TRUE if yes FALSE otherwise
	 @param int size : states the size of the pool
	 @param min and max, min and max value that the genes can have
	 */
	public void init(boolean duplicate, int size, int min, int max) {
	 // create the random starting Chromosome. May the odds ever be in your favor.
		for (int i = 0; i < this.POOL_SIZE; i++) {
			this.pool[i] = new Chromosome(duplicate, size, min, max);
		}
		this.Best = this.pool[0];
		this.SecBest= this.pool[1];
		for (int i = 2; i < this.POOL_SIZE; i++) {
			if (this.pool[i].getFitness() > this.SecBest.getFitness()){
				if(this.pool[i].getFitness() > this.Best.getFitness()){
					this.Best = this.pool[i];
				}
				else {
					this.SecBest = this.pool[i];
				}
			}
		}
	}
	
	/*
	 * This method is the one that will in multiple threads, it tries to do everything at once,
	 * when this method gets called by all the threads, one generation has passed.
	 */	
	public void crunch(boolean duplicate, int mutation100, int size, int min, int max) {
		
		for (int i = 0; i < this.POOL_SIZE; i++){
			this.threadPool[i] = new Thread(new MyRunnable(i, this.getChromosome(i), this, duplicate, size, min, max));
		}
		for ( int i = 0; i < this.POOL_SIZE; i++) {
			this.threadPool[i].start();
		}
		for ( int i = 0; i < this.POOL_SIZE; i++) {
			try {
				this.threadPool[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 2; i < this.POOL_SIZE; i++) {
			if (this.pool[i].getFitness() > this.SecBest.getFitness()){
				if(this.pool[i].getFitness() > this.Best.getFitness()){
					this.Best = this.pool[i];
				}
				else {
					this.SecBest = this.pool[i];
				}
			}
		}
	}
		
	public void mutate(boolean duplicate, int min, int max) {
		for (int i = 0; i < this.POOL_SIZE; i++) {
			this.pool[i].mutate(duplicate, min, max);
		}
	}
	
	public void printSample(int i) {
		System.out.println("Simulation ended, here is what we found :");
		System.out.println("This is the " + i + "th iteration and the Simulation has been running for : " + (System.currentTimeMillis() - this.startTime) + "ms");
		System.out.println("Here is the best solution from this Simulation : ");
		this.Best.printResult();
		System.out.print(" and here is its fitness :" + this.Best.getFitness());
	}
	
	public Chromosome getBest() {
		return this.Best;
	}
}