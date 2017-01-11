/**
 *
 * @author \//\T$@|_
 */
import java.util.Random;
import java.util.Scanner;

public class Main {
	//Data Members
	private static int[] state = new int[19];
    private static Random random = new Random();
    private static int mode;
    private static int algo;
    private static long startTime, endTime, time = 0;
    private static int TotalSolved=0;
    private static float avgSolved=0;
    private static int TotalCases=100;
    private static float TotalTime=0;
    private static float avgTime=0;
    private static boolean solved=false;
    private static boolean Exit=false;
    //Main Method
    public static void main(String[] args) {
        
    	 while (!Exit) {
    		 mode=0;algo=0;startTime=0;endTime=0;time=0;TotalSolved=0;avgSolved=0;TotalTime=0;avgTime=0;solved=false;
			
    		 System.out.print("Choose from one of the options \n" + "1. Random Puzzle\n" + "2. States of 100+\n" + "3. Exit\n");
			
    		 Scanner input = new Scanner(System.in);
			mode = input.nextInt();
			
			if(mode==3)
			{break;}
			
			System.out.print("Which algorithm do you want to implement ?\n" + "1. Steepest-ascent hill climbing\n"
					+ "2. The Genetic Algorithm\n");
			algo = input.nextInt();
			switch (mode) {
			case 1: {
				for (int i = 0; i < state.length; i++) {
					int randomNo = 1 + random.nextInt(19);
					state[i] = randomNo;
				}
				solveRandom(state, true, algo);

			}
				break;
			case 2: {
				System.out.print("Loading....");
				for (int j = 0; j < TotalCases; j++) {
					System.out.print("....");
					for (int i = 0; i < state.length; i++) {
						int randomNumber = 1 + random.nextInt(19);
						state[i] = randomNumber;
					}
					solveBulk(state, true, algo);

				}
				avgSolved = (TotalSolved * 100) / TotalCases;
				avgTime = TotalTime / TotalCases;

				System.out.println("\nAvgTime:" + avgTime);
				System.out.println("AvgSolved:" + avgSolved);
				
			}
				break;
			default:Exit=true;
				System.out.println("Exit");
				break;

			}
		}
        
             
       
    }

	private static void solveRandom(int[] state2, boolean b,int mode) {
		try {
			startTime = System.currentTimeMillis();
			if(mode==1)
			{SteepestHillClimbAlgorithm.Solve(state,true);}
			else
			{GeneticAlgo.Solve(state,true);}
			endTime = System.currentTimeMillis();
			time = endTime - startTime;                
			System.out.println("Time : " + time);
		} catch (Exception e) {
			
		}
		
	}
	
	private static void solveBulk(int[] state2, boolean b,int mode) {
		try {
			startTime = System.currentTimeMillis();
			solved=mode==1?SteepestHillClimbAlgorithm.Solve(state,false):GeneticAlgo.Solve(state,false);
			endTime = System.currentTimeMillis();
			time = endTime - startTime;                
			TotalTime+=time;
			TotalSolved+=solved?1:0;
		} catch (Exception e) {
			
		}
		
	}
    
}
