package gens;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int geneSize = 4;
		int geneNumber = 8;
		int iteration = 100;
		int poolSize = 32;
		int threadNumber = 4;
		
		PoolManager pm = new PoolManager(geneSize, geneNumber, poolSize, iteration, threadNumber);
		
		pm.initPool();
		pm.simulation();
	}

}
