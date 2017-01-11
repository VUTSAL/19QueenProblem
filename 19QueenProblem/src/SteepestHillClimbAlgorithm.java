/**
 *
 * @author \//\T$@|_
 */
import java.util.ArrayList;
public class SteepestHillClimbAlgorithm {
	 	
	
	    private static ChessBoard presentState;
	    public static int searchCost = 0;
	    
	    
	    public static boolean Solve(int[] state,boolean isRandom)
	    {
	        presentState = new ChessBoard(state);
	        
	        while (!presentState.isSolution())
	        {
	        	ArrayList<ChessBoard> stateCandidates = new ArrayList<ChessBoard>();
	            ArrayList<ChessBoard> tempCandidates = presentState.generateCandidate();
	            

	            for (int i = 0; i < tempCandidates.size(); i++)
	            {                
	                if (tempCandidates.get(i).heuristicValue() > presentState.heuristicValue())
	                {   
	                	stateCandidates.add(tempCandidates.get(i));
	                }
	            }
	            
	            if (stateCandidates.size() == 0)
	            { 
	            	break;
	            }
	            
	            ChessBoard highestState = stateCandidates.get(0);
	            for (int i = 0; i < stateCandidates.size(); i++)
	            {               
	                if (stateCandidates.get(i).heuristicValue() > highestState.heuristicValue())
	                {  
	                	highestState = stateCandidates.get(i);
	                }
	                
	            }
	                    
	            presentState = highestState;
	            searchCost++;
	        }
	        
	        if (isRandom) {
	        	System.out.println("Problem: ");
	            presentState.print();      
	       
				if (presentState.isSolution()) {
					System.out.println("Solution: ");
					presentState.print();
					System.out.println("The puzzle has been solved");
					System.out.println("Cost : " + searchCost);
					return true;
				} else {
					System.out.println("Unsolvable Problem : ");
					presentState.print();
					System.out.println("The Problem cannot be solved");
					System.out.println("Cost: " + searchCost);
					return false;
				} 
			}
	        else
	        {
	        return presentState.isSolution();	
	        }
	        
	    }
	    
}
