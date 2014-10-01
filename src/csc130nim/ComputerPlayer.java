package csc130nim;

import java.util.ArrayList;

public class ComputerPlayer extends Player {

	private int rowToChoose = 0;
	private int numToChoose = 0;

	@Override
	public int getRow() {
		rowToChoose = 0;
		numToChoose = 0;

		ArrayList<State> statesToTest = new ArrayList<State>();
		for (int i = 0; i < Manager.gameBoard[0]; i++) {
			statesToTest.add(new State(new int[] { i, Manager.gameBoard[1], Manager.gameBoard[2] }));
		}
		for (int i = 0; i < Manager.gameBoard[1]; i++) {
			statesToTest.add(new State(new int[] { Manager.gameBoard[0], i, Manager.gameBoard[2] }));
		}
		for (int i = 0; i < Manager.gameBoard[2]; i++) {
			statesToTest.add(new State(new int[] { Manager.gameBoard[0], Manager.gameBoard[1], i }));
		}

		System.out.println("num states to test: " + statesToTest.size());
		System.out.println(statesToTest);
		State bestState = null;
		for (State s : statesToTest) {
			if (bestState == null) {
				bestState = s;
				
				// this next line doesnt actually check against the computers knowledge because all the values are 0
			} else if (bestState.getValue() <  s.getValue()) {
				bestState = s;
			}
		}
		
		if (Manager.gameBoard[0] != bestState.getBoard()[0]) {
			rowToChoose = 1;
			numToChoose = Manager.gameBoard[0] - bestState.getBoard()[0];
		} else if (Manager.gameBoard[1] != bestState.getBoard()[1]) {
			rowToChoose = 2;
			numToChoose = Manager.gameBoard[1] - bestState.getBoard()[1];
		} else if (Manager.gameBoard[2] != bestState.getBoard()[2]) {
			rowToChoose = 3;
			numToChoose = Manager.gameBoard[2] - bestState.getBoard()[2];
		}
		return rowToChoose;
	}

	@Override
	public int getNumberToRemove() {
		return numToChoose;
	}

}
