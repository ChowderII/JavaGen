package gens;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int mutation100 = 10; 
		int iteration = 10000;
		int chromosomeSize = 16;
		int poolSize = 32;
		boolean duplicate = false;
		int min = 0;
		int max = 15;
		
		PoolManager pm = new PoolManager(iteration, mutation100, chromosomeSize, poolSize, duplicate, min, max);
		
		pm.initPool();
		pm.simulation();
	}

}
