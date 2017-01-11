/**
 *
 * @author \//\T$@|_
 */
import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
	 
	//Data members
		private final int BoardSIZE = 19;
	    private char[][] chessboard = new char[BoardSIZE][BoardSIZE];
	    private int[] PresentState;
	    private int heuristicValue;
	    private static int maxheuristicValue=171;
	    
	    public int heuristicValue()
	    {
	        return heuristicValue;
	    }
	    public ChessBoard(int state[])
	    {
	        PresentState = state;
	        createChessBoard();
	        heuristicValue = maxheuristicValue - calculateAttack();
	        
	    }
	  
	    //public Methods
	    public void createChessBoard()
	    {
	        for (int i = 0; i < BoardSIZE; i++)
	        {
	        	chessboard[BoardSIZE - PresentState[i]][i] = 'Q';
	        }
	    }

	    public ArrayList<ChessBoard> generateCandidate()
	    {
	        ArrayList<ChessBoard> candidate = new ArrayList<ChessBoard>();
	        
	        for (int i = 0; i < BoardSIZE; i ++)
	        {
	            int[] copyBoard = new int[BoardSIZE];
	            int present = PresentState[i];
	            for (int j = 1; j <= BoardSIZE; j++)
	            {
	                if (j != PresentState[i])
	                	copyBoard[j-1] = j;
	            }
	            
	            for (int k = 0; k < copyBoard.length; k++)
	            {
	                if (copyBoard[k] != 0)
	                {
	                    PresentState[i] = copyBoard[k];
	                    int[] tempState = copyState(PresentState);
	                    candidate.add(new ChessBoard(tempState));                             
	                }
	            }                     
	            PresentState[i] = present;
	        }        
	        return candidate;
	    }
	    
	    public int calculateAttack()
	    {
	        int total = 0;
	        for (int i = 0; i < BoardSIZE; i++)
	        {
	        	total += checkAttack(BoardSIZE - PresentState[i],i);
	        }
	        return total;
	    }
	     
	    public int checkAttack(int row, int column)
	    {
	        int count = 0;
	        for (int i = column + 1; i < BoardSIZE; i++)
	        {
	            if (chessboard[row][i] == 'Q')
	                count++;
	        }
	        int j = 1;
	        int k = 1;
	        for (int i = column + 1; i < BoardSIZE; i++)
	        {
	            if (((row - j >= 0) && (chessboard[row - j][i] == 'Q')) || ((row + k < BoardSIZE) && (chessboard[row + k][i] == 'Q')))
	                count++;
	            j++;
	            k++;
	        }
	        
	        return count;
	    }
	    
	    
	    public void print()
	    {
	    	
	        for (int i = 0; i < BoardSIZE; i++)
	        {
	            for (int j = 0; j < BoardSIZE; j++)
	            {
	                if (chessboard[i][j] == 'Q')
	                    System.out.printf(" %s |",chessboard[i][j]);
	                else
	                    System.out.printf(" %s |",' ');
	            }
	            System.out.println();
	            System.out.println("----------------------------------------------------------------------------");
	        }
	    }
	   
	   
	    public boolean isSolution()
	    {
	        return heuristicValue() == maxheuristicValue;
	    }
	    
	    public int[] getPresentState()
	    {
	        return PresentState;
	    }
	    
	    //private methods
	    private int[] copyState(int[] state)
		{
			int[] puzzleboard = new int[BoardSIZE];
			for (int i = 0; i < BoardSIZE; i++)
			{
				puzzleboard[i] = state[i];
			}
			return puzzleboard;
		}
	    
}
