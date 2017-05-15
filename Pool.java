package gens;

public class Pool{

	private Chromosome[] pool;
	private Chromosome Best;
	private Chromosome SecBest;
	private Thread[] threadPool;
	
	// config for the pool
	private final int POOL_SIZE; // requires at least of 2 !!!
	
	// constructor that allows different size of pool
	public Pool(int size) {
		POOL_SIZE = size;
		this.pool = new Chromosome[this.POOL_SIZE];
		threadPool = new Thread[this.POOL_SIZE];
		Best = null;
		SecBest = null;
	}
	
	public Chromosome getChromosome(int id){
		return this.pool[id];
	}
	
	public Chromosome[] getPool(){
		return this.pool;
	}
	
	/* this method should be called the Pool Manager because the genes may change from case to case
	* therefore you should change the logic of the genes object in the main class then pass it to the pool manager which
	 will take care of the rest 
	 @param boolean duplicate : states whether the solutions can have duplicated numbers TRUE if yes FALSE otherwise
	 @param int size : states the size of the pool
	 */
	public void init(boolean duplicate, int size, int min, int max) {
	 // create the random starting Chromosome. May the odds ever be in your favor.
		for (int i = 0; i < this.POOL_SIZE; i++) {
			this.pool[i] = new Chromosome(duplicate, size, min, max);
		}
		Chromosome best = this.pool[0];
		Chromosome secondbest= this.pool[1];
		for (int i = 2; i < this.POOL_SIZE; i++) {
			if (this.pool[i].getFitness() > secondbest.getFitness()){
				if(this.pool[i].getFitness() > best.getFitness()){
					best = this.pool[i];
				}
				else {
					secondbest = this.pool[i];
				}
			}
		}
	}
	
	/*
	 * This method is the one that will in multiple threads, it tries to do everything at once,
	 * when this method gets called by all the threads, one generation has passed.
	 */	
	public void crunch(boolean duplicate, int size, int min, int max) {
		for (int i = 0; i < this.POOL_SIZE; i++){
			this.threadPool[i] = new Thread(new MyRunnable(i, this.getChromosome(i), this, duplicate, size, min, max));
		}
		
		
	}
}