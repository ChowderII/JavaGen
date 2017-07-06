package gens;

public class PoolManager {
	private final int GENE_SIZE;
	private final int GENE_NUMBER;
	private final int POOL_SIZE;
	private final int THREAD_NUMBER;
	private final int ITERATION;
	private Pool p;
	private long startTime;
	
	public PoolManager(int geneSize, int geneNumber, int poolSize, int iteration, int threadNumber){
		this.GENE_SIZE = geneSize;
		this.GENE_NUMBER = geneNumber;
		this.POOL_SIZE = poolSize;
		this.ITERATION = iteration;
		this.THREAD_NUMBER = threadNumber;
		this.startTime = System.currentTimeMillis();
	}
	
	public void initPool(){
		this.p = new Pool(this.ITERATION, this.POOL_SIZE, this.THREAD_NUMBER, this.GENE_NUMBER, this.GENE_SIZE);
		System.out.println("Pool initiated.");
	}
	
	public void simulation(){
		this.startTime = System.currentTimeMillis();
		System.out.println("Simulation started, start time recorded.");
		
		int i =0;
		for (i = 0; i <= this.ITERATION && this.p.getBest().getFitness() != 0; i++) {
			this.p.crunch();
			if(i%100 == 0){
				System.out.println("Generation " + i + " has passed.");
			}
		}
		System.out.println("Best individual : ");
		this.p.getBest().printResult();
		System.out.print(" - Fitness : " + this.p.getBest().getFitness());
		System.out.println("\nSimulation time : " + (System.currentTimeMillis() - this.startTime));
	}
}
