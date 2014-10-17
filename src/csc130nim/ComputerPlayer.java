package csc130nim;

import java.util.*;

public class ComputerPlayer implements Player {

	public static Map<Board, StateContainer> gameKnowledge = StateCalculator.load();
	private List<Board> gameTurns;
	
	private Random rand = new Random();

	private int row = -1, count = -1;
	
	public ComputerPlayer() {
        gameTurns = new ArrayList<>();
	}

	/**
	 * Returns the row the the AI chooses
	 */
	@Override
	public int getRow(Board board) {
		if (row == -1)
			decide(board);
		int decision = row;
		row = -1;
		return decision;
	}

	/**
	 * Returns the number to remove from the previously selected row
	 */
	@Override
	public int getNumberToRemove(Board board) {
		if (count == -1)
			decide(board);
		int decision = count;
		count = -1;
		return decision;
	}

	/**
	 * Makes a choice for the computer to return later
	 */
	public void decide(Board gameBoard) {
		gameTurns.add(gameBoard.clone());

		Board goal = goal(gameBoard);
        // If the potential "goal" is null, go random
        if (goal == null) {
            row = rand.nextInt(3) + 1;
            count = rand.nextInt(5);
        }
        else {
            // Otherwise, get smart
            if (goal.get(0) < gameBoard.get(0)) {
                row = 1;
                count = gameBoard.get(0) - goal.get(0);
            }
            else if (goal.get(1) < gameBoard.get(1)) {
                row = 2;
                count = gameBoard.get(1) - goal.get(1);
            }
            else if (goal.get(2) < gameBoard.get(2)) {
                row = 3;
                count = gameBoard.get(2) - goal.get(2);
            }
        }
    }

    /**
     * /**
     * View all the boards that it knows about and save the one that we want as our goal
     *
     * @param board The current state of the Game Board.
     * @return The board state with the best value
     */
	public Board goal(Board board) {
		Iterator<MeanState> container = gameKnowledge.get(board).iterator();
		Board goodGoal = null;
		double val = -2;

		if (container != null) {
			while(container.hasNext())
			{
				MeanState s = container.next();
				if (s.getValue() > val) {
					val = s.getValue();
					goodGoal = s.getBoard();
				}
			}
		}
		return goodGoal;
	}

	@Override
	public void notifyGameEnded() {
		StateCalculator.calculateStates(gameKnowledge, gameTurns);
		StateCalculator.save(gameKnowledge);
	}
}
