package csc130nim;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
	
	private int[] gameBoard = new int[3];
	
	private ArrayList<int[]> gameTurns;
	private Presenter present;
	
	public Manager()
	{
		gameTurns = new ArrayList<>();
		present = new Presenter();
	}
	
	public void playGame()
	{
		boolean running = true;
		
		setBoard(3, 5, 7);
		
		while(running)
		{
			present.printBoard(gameBoard);
			int row = present.promptRowSelection();
			int pieces = present.promptNumPieces();
			
			try
			{
				removePieces(row, pieces);
				if(GameEnded())
				{
					testReturnTurns();
					running = false;
				}
			}
			catch(IllegalArgumentException ex)
			{
				System.err.println(ex);
			}
		}
	}
	
	public void setBoard(int one, int two, int three)
	{
		gameBoard[0] = one;
		gameBoard[1] = two;
		gameBoard[2] = three;
		
		gameTurns.add(gameBoard.clone());
	}
	
	public void removePieces(int row, int toRemove) throws IllegalArgumentException
	{
		if(row > 3 || row < 0)
		{
			throw new IllegalArgumentException("Tried to pick a nonexistant row.");
		}
		else
		{
			int zeroRow = row - 1; //Zero based row
			if(toRemove <= gameBoard[zeroRow] && toRemove > 0)
			{
				gameBoard[zeroRow] -= toRemove;
				gameTurns.add(gameBoard.clone());
			}
			else
			{
				throw new IllegalArgumentException("Tried to remove too many pieces");
			}
		}
	}
	
	public boolean GameEnded()
	{
		if(gameBoard[0] == 0 && gameBoard[1] == 0 && gameBoard[2] == 0)
		{
			return true;
		}
		return false;
	}
	
	public void testReturnTurns()
	{
		int i = 1;
		for(int[] turn : gameTurns)
		{
			System.out.println(i + ": " + turn[0] + ", " + turn[1] + ", " + turn[2]);
			i++;
		}
	}
	
}
