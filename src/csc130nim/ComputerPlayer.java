package csc130nim;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class ComputerPlayer extends Player {
	
	private static Brain brain = new Brain();
	private Stack<GameState> previousMoves;
	private HashMap<int[], StateContainer> gameKnowledge = Manager.gameKnowledge;
	private int[] board = Manager.gameBoard;
	private Random rand = new Random();

	private int row = -1, count = -1;
	
	public ComputerPlayer() {
		previousMoves = new Stack<GameState>();
	}

	@Override
	public int getRow() {
		if(row == -1)
			decide();
		int decision = row;
		row = -1;
		return decision;
	}

	@Override
	public int getNumberToRemove() {
		if(count == -1)
			decide();
		int decision = count;
		count = -1;
		return decision;
	}
	
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
	
	public int[] goal()
	{
		StateContainer container = gameKnowledge.get(board);
		int[] goodGoal = null;
		double val = -2;
		
		if(container != null)
		{
			for(State s : container)
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
