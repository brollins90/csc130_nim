package csc130nim;

import java.util.HashMap;
import java.util.Random;

public class ComputerPlayer extends Player {

	private HashMap<Board, StateContainer> gameKnowledge = Manager.gameKnowledge;
	private Board board = Manager.gameBoard;
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
		
		Board goal = goal();
		if(goal != null)
		{
			if(goal.get(0) < board.get(0))
			{
				row = 1;
				count = board.get(0) - goal.get(0);
			}
			else if(goal.get(1) < board.get(1))
			{
				row = 2;
				count = board.get(1) - goal.get(1);
			}
			else if(goal.get(2) < board.get(2))
			{
				row = 3;
				count = board.get(2) - goal.get(2);
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
	public Board goal()
	{
		StateContainer container = gameKnowledge.get(board);
		Board goodGoal = null;
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
