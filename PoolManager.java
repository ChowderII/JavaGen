package gens;

public class PoolManager {
	private final int CHROMOSOME_SIZE;
	private final int GENES_MIN;
	private final int GENES_MAX;
	private final int POOL_SIZE;
	private final boolean DUPLICATE;
	private ThreadGroup tg;
	
	public PoolManager(int chromosomeSize, int poolSize, boolean duplicate, int min, int max){
		this.CHROMOSOME_SIZE = chromosomeSize;
		this.POOL_SIZE = poolSize;
		this.DUPLICATE = duplicate;
		this.GENES_MIN = min;
		this.GENES_MAX = max;
	}
	
	public void initPool(){
		Pool p = new Pool(this.POOL_SIZE);
	}
}
