package gens;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int geneSize = 4;
		int geneNumber = 8;
		int iteration = 200;
		int poolSize = 100;
		int threadNumber = 16;

		PoolManager pm = new PoolManager(geneSize, geneNumber, poolSize, iteration, threadNumber);

		pm.initPool();
		pm.simulation();
		}
	}
