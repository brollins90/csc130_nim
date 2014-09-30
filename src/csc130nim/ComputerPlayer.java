package csc130nim;

import java.util.Stack;

public class ComputerPlayer extends Player {
	
	private static Brain brain = new Brain();
	private Stack<GameState> previousMoves;
	
	public ComputerPlayer() {
		previousMoves = new Stack<GameState>();
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberToRemove() {
		// TODO Auto-generated method stub
		return 0;
	}

}
