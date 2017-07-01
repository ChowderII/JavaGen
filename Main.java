package gens;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int geneSize = 3;
		int geneNumber = 8;
		int iteration = 1000;
		int poolSize = 	500;
		int threadNumber = 8;

		PoolManager pm = new PoolManager(geneSize, geneNumber, poolSize, iteration, threadNumber);

		pm.initPool();
		pm.simulation();
		}
	}
