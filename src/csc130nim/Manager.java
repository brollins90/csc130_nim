package csc130nim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Manager implements Serializable{
	
	public static int[] gameBoard = new int[3];
	public static HashMap<int[], StateContainer> gameKnowledge = new HashMap<>();
	
	public Manager() {
		NewGame();
	}
	
	private ArrayList<int[]> gameTurns = new ArrayList<>();
	
	public void setBoard(int one, int two, int three)
	{
		gameBoard[0] = one;
		gameBoard[1] = two;
		gameBoard[2] = three;
		
		gameTurns.add(gameBoard.clone());
	}
	

	public void NewGame() {
		gameBoard[0] = 3;
		gameBoard[1] = 5;
		gameBoard[2] = 7;
		
		gameTurns = new ArrayList<>();
		gameTurns.add(gameBoard.clone());
	}
	
	public void StartGame(Player p1, Player p2) {
		
		NewGame();
		Boolean playing = true;
		Boolean playerOneCurrent = true;
		
		while(playing) {
			Player current = (playerOneCurrent) ? p1 : p2;
			
			boolean completedMove = false;
			
			while(!completedMove) {
				try {
					removePieces(current.getRow(), current.getNumberToRemove());
					completedMove = true;
				} catch (Exception e) {
					System.out.println("Invalid selection, try again.");
				}
			}
			
			if(GameEnded())
			{
				calculateStates();
				playing = false;
			}
		}
	}

    public void printOpeningMenu() {
        MenuOption[] enumValues = MenuOption.values();
        
        load();
        
        while(true)
        {
	        for (int i = 0; i < enumValues.length; i++) {
	            MenuOption current = enumValues[i];
	            System.out.println(current.getRetValue() + ". " + current.getReadableName());
	        }
	        try {
				int choice = handleInput(Player.reader.readLine());
				if(choice >= 0 && choice < 4) {
	                List<MenuOption> collectedOptions = Arrays.asList(enumValues)
	                        .stream()
	                        .filter(x -> x.getRetValue() == choice)
	                        .collect(Collectors.toList());
	
	                collectedOptions.addAll(Arrays.asList(enumValues));
//	                collectedOptions.forEach(System.out::println);
	                collectedOptions.get(0).execute(this);
	            }
	            else
				{
					System.err.println("Bad input");
				}
			} catch (IOException e) {
				System.err.println("The starter input was not parseable.");
			}
        }
    }

    private int handleInput(String readLine) {
		// TODO Auto-generated method stub
		return Integer.parseInt(readLine);
	}

	public void removePieces(int row, int toRemove)
	{
		if(row > 3 || row < 0)
			throw new IllegalArgumentException("Tried to pick a nonexistant row.");
		else
		{
			int zeroRow = row - 1; //Zero based row
			if(toRemove <= gameBoard[zeroRow] && toRemove > 0)
			{
				gameBoard[zeroRow] -= toRemove;
				gameTurns.add(gameBoard.clone());
//				PlayerOne = !PlayerOne;
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
		boolean player = true;
		for(int[] turn : gameTurns)
		{			
			System.out.println(((player) ? 1 : 2) + ": " + turn[0] + ", " + turn[1] + ", " + turn[2]);
			player = !player;
		}
	}
	
	public void calculateStates()
	{
		boolean firstPlayer = true;
		boolean firstWin = (gameTurns.size() % 2 == 0 ? true : false);
		int secondTurn = gameTurns.size() / 2;
		int firstTurn = (gameTurns.size() % 2 == 0 ? secondTurn : (gameTurns.size() / 2) + 1);
				
		for(int i = 1; i < gameTurns.size(); i++)
		{			
			int[] previousBoard = gameTurns.get(i-1);
			int[] currentBoard = gameTurns.get(i);
			firstPlayer = !firstPlayer;
			double value = ((i/2 + 1) / ((double)(firstPlayer ? firstTurn : secondTurn))) * (firstPlayer == firstWin ? 1 : -1);
			//Here is where you would store the states into the machine learning.
			if(gameKnowledge.get(previousBoard) != null)
			{
				ArrayList<State> possible = gameKnowledge.get(previousBoard);
				if(possible.contains(currentBoard))
				{
					possible.get(possible.indexOf(currentBoard)).addValue(value);
				}
				else
				{
					possible.add(new State(currentBoard, value));
				}
			}
			else
			{
				StateContainer contain = new StateContainer();
				contain.add(new State(currentBoard, value));
				gameKnowledge.put(previousBoard, contain);
			}
		}
	}
	
	public void load()
	{
		try {
			ObjectInputStream ois;
			ois = new ObjectInputStream(new FileInputStream("data/learning.data"));
			gameKnowledge = (HashMap<int[], StateContainer>)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			gameKnowledge = new HashMap<>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save()
	{
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/learning.data"));
			oos.writeObject(gameKnowledge);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
