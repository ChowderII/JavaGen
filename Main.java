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
		int threadNumber = 2;

		PoolManager pm = new PoolManager(geneSize, geneNumber, poolSize, iteration, threadNumber);

		pm.initPool();
		pm.simulation();
		/*
		int[] test1 = {0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		
		Chromosome test = new Chromosome(test1, 8);
		
		int[] result = getReadableGenome(test1);
		
		for (int i = 0; i < 8; i++){
			System.out.print(" " + result[i]);*/
		}
	}

	
	/*public static int[] getReadableGenome(int[] Genome){
		int[] Genes = new int[8];
		int indI = 0;
		int expo = this.GeneSize-1;
		for (int i = 0; i < this.Size; i++){
			Genes[indI] += (int) Genome[i] * (Math.pow(2,expo));
			expo--;
			if(expo == -1){
				expo = this.GeneSize-1;
				indI++;
			}
		}
		return Genes;
	}*/

