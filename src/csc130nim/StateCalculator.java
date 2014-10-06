package csc130nim;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StateCalculator {

//	public void testReturnTurns() {
//		boolean player = true;
//		for (Board turn : gameTurns) {
//			System.out.println(((player) ? 1 : 2) + ": " + turn.get(0) + ", " + turn.get(1) + ", " + turn.get(2));
//			player = !player;
//		}
//	}

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
			if (gameKnowledge.get(previousBoard) != null) {
				StateContainer possible = gameKnowledge.get(previousBoard);
				if (possible.contains(currentBoard)) {
//					boolean test = possible.contains(currentBoard);
//					int a = possible.indexOf(currentBoard);
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

//	public void checkforduplicate() {
//		Set<Board> holder = gameKnowledge.keySet();
//		int duplicates = 0;
//		for (Board board : holder) {
//			int count = 0;
//			for (Board second : holder) {
//				if (board.get(0) == second.get(0) && board.get(1) == second.get(1) && board.get(2) == second.get(2)) {
//					count++;
//				}
//			}
//			if (count > 1) {
//				duplicates++;
//			}
//		}
//
//		System.out.println("Number of duplicate keys: " + duplicates);
//	}

//	public void checkInternalDuplicates() {
//		Set<Board> holder = gameKnowledge.keySet();
//		int duplicates = 0;
//		for (Board board : holder) {
//
//			for (MeanState s : gameKnowledge.get(board)) {
//				int count = 0;
//				for (MeanState second : gameKnowledge.get(board)) {
//					if (s.getBoard() == second.getBoard()) {
//						count++;
//					}
//				}
//
//				if (count > 1) {
//					duplicates++;
//				}
//			}
//		}
//
//		System.out.println("Number of duplicate keys: " + duplicates);
//	}

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
