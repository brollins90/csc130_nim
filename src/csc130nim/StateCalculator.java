package csc130nim;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateCalculator {


	public static void calculateStates(Map<Board, StateContainer> gameKnowledge, List<Board> gameTurns) {
		boolean firstPlayer = true;
		boolean firstWin = (gameTurns.size() % 2 == 0);
		int secondTurn = gameTurns.size() / 2;
		int firstTurn = (gameTurns.size() % 2 == 0 ? secondTurn : (gameTurns.size() / 2) + 1);

		for (int i = 1; i < gameTurns.size(); i++) {
			Board previousBoard = gameTurns.get(i - 1);
			Board currentBoard = gameTurns.get(i);
			firstPlayer = !firstPlayer;
			double value = ((i / 2 + 1) / ((double) (firstPlayer ? firstTurn : secondTurn))) * (firstPlayer == firstWin ? 1 : -1);
			// Here is where you would store the states into the machine learning.
			if (gameKnowledge.get(previousBoard) == null) {
				gameKnowledge.put(previousBoard, addNewState(currentBoard, value));
			} else {				
				StateContainer possible = gameKnowledge.get(previousBoard);
				if (possible.contains(currentBoard)) {
					possible.get(possible.indexOf(currentBoard)).addValue(value);
				} else {
					possible.add(new MeanState(currentBoard, value));
				}
			}
		}
	}

	private static StateContainer addNewState(Board currentBoard, double value){
		StateContainer contain = new StateContainer();
		contain.add(new MeanState(currentBoard, value));
		return contain;
	}

	public static Map<Board, StateContainer> load() {
		Map<Board, StateContainer> gameKnowledge;
		try {
			ObjectInputStream ois;
			ois = new ObjectInputStream(new FileInputStream("learning.data"));
			gameKnowledge = (Map<Board, StateContainer>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			gameKnowledge = new HashMap<>();
		}
		return gameKnowledge;
	}

	public static void save(Map<Board, StateContainer> gameKnowledge) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("learning.data"));
			oos.writeObject(gameKnowledge);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
