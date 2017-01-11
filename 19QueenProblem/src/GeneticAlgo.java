/**
 *
 * @author \//\T$@|_
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;



public class GeneticAlgo {
	
		private static ChessBoard presentState;
	    private static int Searchcost = 0;
	    private static Random rndm = new Random();
	    private static int p = 11;
	    private static int r = 10;
	    
	    
	    public static boolean Solve(int[] state,boolean isRandom)
	    {
	        try {
				presentState = new ChessBoard(state);
      
				while (!presentState.isSolution())
				{
				    ArrayList<ChessBoard> population = presentState.generateCandidate();
				    ArrayList<ChessBoard> stateCandidates = new ArrayList<ChessBoard>();
				    ArrayList<ChessBoard> childCandidates = new ArrayList<ChessBoard>();
				    ArrayList<ChessBoard> mutatedCandidates = new ArrayList<ChessBoard>();
				    
				    for (int i = 0; i < population.size(); i++)
				    {                
				        if (population.get(i).heuristicValue() > averageFitness(population))
				            stateCandidates.add(population.get(i));
				    }
				    
				    try
				    {
				        sort(stateCandidates);
				    }
				    catch(IllegalArgumentException e)
				    {
				        
				    }
				    for (int i = 0; i < p; i++)
				    {
				        int number = 0;
				        do
				        {
				            number = rndm.nextInt(stateCandidates.size() - 1);
				        } while(number == i);
				        ArrayList<ChessBoard> tempCandidates = crossover(stateCandidates.get(i),stateCandidates.get(number));
				        childCandidates.add(tempCandidates.get(0));
				        childCandidates.add(tempCandidates.get(1));
				    }
				    
				    for (int i = 0; i < childCandidates.size(); i++)
				    {
				    	mutatedCandidates.add(mutation(childCandidates.get(i)));
				    }
				    ChessBoard highestState = mutatedCandidates.get(0);
				    for (int i = 0; i < mutatedCandidates.size(); i++)
				    {               
				        if (mutatedCandidates.get(i).heuristicValue() > highestState.heuristicValue())
				            highestState = mutatedCandidates.get(i);
				    }
				          
				    presentState = highestState;
				    Searchcost++;
				}
				
				if (isRandom) {
					System.out.println("Problem : ");
					presentState.print();
					if (presentState.isSolution()) {
						System.out.println("Final State : ");
						presentState.print();
						System.out.println("The puzzle has been solved");
						System.out.println("Cost : " + Searchcost);
						return true;
					} else {
						System.out.println("Unsolvable State : ");
						presentState.print();
						System.out.println(presentState.heuristicValue());
						System.out.println("The puzzle cannot be solved");
						System.out.println("Cost : " + Searchcost);
						return false;
					} 
				}
				else		
				{
					return presentState.isSolution();
				}
			} 
	        catch (Exception e) {
			return false;
				
			}
	        	
	        
	    }
	    
	    public static int averageFitness(ArrayList<ChessBoard> a)
	    {
	        try {
				int average = 0;
				int sum = 0;
				for (int i = 0; i < a.size(); i++) {
					sum += a.get(i).heuristicValue();
				}
				average = sum / (a.size());
				return average;
			} catch (Exception e) {
				return -1;
			}
	    }
	    
	    public static ArrayList<ChessBoard> crossover(ChessBoard b1, ChessBoard b2)
	    {
	        try {
				ArrayList<ChessBoard> children = new ArrayList<ChessBoard>();
				int[] parent1 = b1.getPresentState();
				int[] parent2 = b2.getPresentState();
				int[] crossover1 = new int[parent1.length - r];
				int[] crossover2 = new int[parent2.length - r];
				for (int i = r; i < parent1.length; i++) {
					crossover1[i - r] = parent1[i];
				}
				for (int i = r; i < parent2.length; i++) {
					crossover2[i - r] = parent2[i];
				}
				for (int i = r; i < parent1.length; i++) {
					parent1[i] = crossover2[i - r];
				}
				for (int i = r; i < parent2.length; i++) {
					parent2[i] = crossover1[i - r];
				}
				children.add(new ChessBoard(parent1));
				children.add(new ChessBoard(parent2));
				return children;
			} 
	        catch (Exception e) {
			return null;
			}
	    }
	    
	    public static ChessBoard mutation(ChessBoard b)
	    {
	    	try {
				ChessBoard mutated;
				int[] temp = b.getPresentState();
				int no1 = rndm.nextInt(temp.length - 1);
				int no2 = 1 + rndm.nextInt(temp.length);
				temp[no1] = no2;
				return mutated = new ChessBoard(temp);
			} 
	    	catch (Exception e) {
			return null;
			}
	    }

	    
	    public static void sort(ArrayList<ChessBoard> a)
	    {
	        Collections.sort(a, new SortArray());
	    }
	   
	    static class SortArray implements Comparator<ChessBoard>
		{
		 
		    @Override
		    public int compare(ChessBoard b1, ChessBoard b2)
		    {
		        if(b1.heuristicValue() < b2.heuristicValue())
		        {
		            return 1;
		        } 
		        else 
		        {
		            return -1;
		        }
		    }
		}
	    
}
