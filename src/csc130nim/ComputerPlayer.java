package csc130nim;

import java.util.HashMap;
import java.util.Random;

public class ComputerPlayer extends Player {

	private HashMap<int[], StateContainer> gameKnowledge = Manager.gameKnowledge;
	private int[] board = Manager.gameBoard;
	private Random rand = new Random();

	private int row = -1, count = -1;
	
	/**
	 * Returns the row the the AI chooses
	 */
	@Override
	public int getRow() {
		if(row == -1)
			decide();
		int decision = row;
		row = -1;
		return decision;
	}

	/**
	 * Returns the number to remove from the previously selected row
	 */
	@Override
	public int getNumberToRemove() {
		if(count == -1)
			decide();
		int decision = count;
		count = -1;
		return decision;
	}
	
	/**
	 * Makes a choice for the computer to return later
	 */
	public void decide()
	{
		board = Manager.gameBoard;
		
		int[] goal = goal();
		if(goal != null)
		{
			if(goal[0] < board[0])
			{
				row = 1;
				count = board[0] - goal[0];
			}
			else if(goal[1] < board[1])
			{
				row = 2;
				count = board[1] - goal[1];
			}
			else if(goal[2] < board[2])
			{
				row = 3;
				count = board[2] - goal[2];
			}
		}
		else
		{
			row = rand.nextInt(3) + 1;
			count = rand.nextInt(5);
		}
	}
	
	/**
	 * View all the boards that it knows about and save the one that we want as our goal
	 * @return The board state with the best value
	 */
	public int[] goal()
	{
		StateContainer container = gameKnowledge.get(board);
		int[] goodGoal = null;
		double val = -2;
		
		if(container != null)
		{
			for(MeanState s : container)
			{
				if(s.getValue() > val)
				{
					val = s.getValue();
					goodGoal = s.getBoard();
				}
			}
		}
				
		return goodGoal;
	}
}
