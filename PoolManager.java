package gens;

public class PoolManager {
	private final int CHROMOSOME_SIZE;
	private final int GENES_MIN;
	private final int GENES_MAX;
	private final int POOL_SIZE;
	private final boolean DUPLICATE;
	private final int ITERATION;
	private Pool p;
	private long startTime;
	
	public PoolManager(int iteration, int chromosomeSize, int poolSize, boolean duplicate, int min, int max){
		this.CHROMOSOME_SIZE = chromosomeSize;
		this.POOL_SIZE = poolSize;
		this.DUPLICATE = duplicate;
		this.GENES_MIN = min;
		this.GENES_MAX = max;
		this.ITERATION = iteration;
	}
	
	public void initPool(){
		this.p = new Pool(this.POOL_SIZE, this.CHROMOSOME_SIZE);
		p.init(this.DUPLICATE, this.CHROMOSOME_SIZE, this.GENES_MIN, this.GENES_MAX);
		System.out.println("Pool initiated");
	}
	
	public void simulation(){
		this.startTime = System.currentTimeMillis();
		System.out.println("Simulation started, start time recorded.");
		for (int i = 0; i < this.ITERATION && this.p.getBest().getFitness() != 0; i++) {
			p.crunch(this.DUPLICATE, this.CHROMOSOME_SIZE, this.GENES_MIN, this.GENES_MAX);
		}
		System.out.println("Simulation ended, it took : " + (System.currentTimeMillis() - this.startTime));
		System.out.println("The best solution is : ");
		this.p.getBest().printResult();
		System.out.print(" and here is its fitness :" + this.p.getBest().getFitness());
	}
}
