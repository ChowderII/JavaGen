package gens;

import java.util.Random;
import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		boolean benchmark = false;
		
		Scanner sv = new Scanner(System.in);
		System.out.println("Would like to perform a benchmark test ? (True or False)");
		benchmark = sv.nextBoolean();
		
		System.out.println("Very well.");
		
		System.out.println("Give a positive integer for the size of the genes : ");
		int geneSize = sv.nextInt();
		
		System.out.println("Give a positive integer for the number of the genes : ");
		int geneNumber = sv.nextInt();
		
		System.out.println("Give a positive integer for the size of the population (increments of 100 are generally a good idea) : ");
		int poolSize = sv.nextInt();
		
		System.out.println("Give a positive integer for the number of generations the simulation should perform : ");
		int iteration = sv.nextInt();
		
		if (benchmark == false) {
			System.out.println("Give a positive integer for the amounts of threads that will work together on this simulation : ");
			int threadNumber = sv.nextInt();
			
			boolean tryAgain = true;
			while(tryAgain){
				startSim(geneSize,geneNumber,poolSize,iteration,threadNumber);
				System.out.println("Would you like to try again ? (True or False)");
				tryAgain = sv.nextBoolean();
			}
		}
		else{
			Random r = new Random(1996);
			
		}
	}
	
	public static void startSim(int geneSize, int geneNumber, int poolSize, int iteration, int threadNumber){
		PoolManager pm = new PoolManager(geneSize, geneNumber, poolSize, iteration, threadNumber);
		
		pm.initPool();
		pm.simulation();
	}
}
