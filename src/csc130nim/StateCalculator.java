package csc130nim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class StateCalculator {

//	public void testReturnTurns() {
//		boolean player = true;
//		for (Board turn : gameTurns) {
//			System.out.println(((player) ? 1 : 2) + ": " + turn.get(0) + ", " + turn.get(1) + ", " + turn.get(2));
//			player = !player;
//		}
//	}

	public static void calculateStates(HashMap<Board, StateContainer> gameKnowledge, ArrayList<Board> gameTurns) {
		boolean firstPlayer = true;
		boolean firstWin = (gameTurns.size() % 2 == 0 ? true : false);
		int secondTurn = gameTurns.size() / 2;
		int firstTurn = (gameTurns.size() % 2 == 0 ? secondTurn : (gameTurns.size() / 2) + 1);

		for (int i = 1; i < gameTurns.size(); i++) {
			Board previousBoard = gameTurns.get(i - 1);
			Board currentBoard = gameTurns.get(i);
			firstPlayer = !firstPlayer;
			double value = ((i / 2 + 1) / ((double) (firstPlayer ? firstTurn : secondTurn))) * (firstPlayer == firstWin ? 1 : -1);
			if (gameKnowledge.get(previousBoard) != null) {
				StateContainer possible = gameKnowledge.get(previousBoard);
				if (possible.contains(currentBoard)) {
					possible.get(possible.indexOf(currentBoard)).addValue(value);
				} else {
					possible.add(new MeanState(currentBoard, value));
				}
			} else {
				StateContainer contain = new StateContainer();
				contain.add(new MeanState(currentBoard, value));
				gameKnowledge.put(previousBoard, contain);
			}
		}
	}

	public static HashMap<Board, StateContainer> load() {
		HashMap<Board, StateContainer> gameKnowledge;
		try {
			ObjectInputStream ois;
			ois = new ObjectInputStream(new FileInputStream("learning.data"));
			gameKnowledge = (HashMap<Board, StateContainer>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			gameKnowledge = new HashMap<>();
		}
		return gameKnowledge;
	}

	public static void save(HashMap<Board, StateContainer> gameKnowledge) {
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
