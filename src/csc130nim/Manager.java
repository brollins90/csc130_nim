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
import java.util.Set;
import java.util.stream.Collectors;

public class Manager implements Serializable{
	
	public static Board gameBoard = new Board();
	public static HashMap<Board, StateContainer> gameKnowledge = new HashMap<>();
	
	public Manager() {
		NewGame();
	}
	
	private ArrayList<Board> gameTurns = new ArrayList<>();
	
	public void setBoard(int one, int two, int three)
	{
		gameBoard.set(0, one);
		gameBoard.set(1, two);
		gameBoard.set(2, three);
	}
	
	public void NewGame() {
		setBoard(3, 5, 7);
		
		gameTurns = new ArrayList<>();
		gameTurns.add(gameBoard.clone());
	}
	
	public void StartGame(Player p1, Player p2) {
		
		NewGame();
		Boolean playing = true;
		Boolean playerOneCurrent = true;
		
		int row = -3;
		int count = -3;
		
		while(playing) {
			Player current = (playerOneCurrent) ? p1 : p2;
			System.out.println(playerOneCurrent ? "Player One: " : "Player Two: ");
			boolean completedMove = false;


			
			while(!completedMove) {
				
				try {
					row = current.getRow();
					count = current.getNumberToRemove();
					removePieces(row, count);
					completedMove = true;
					playerOneCurrent = !playerOneCurrent;
				} catch (Exception e) {
					System.out.println("Invalid selection, try again.");
					//e.printStackTrace();
				}
				if(GameEnded())
				{
					calculateStates();
					save();
					playing = false;
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
        checkforduplicate();
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
			if(toRemove <= gameBoard.get(zeroRow) && toRemove > 0)
			{
				gameBoard.set(zeroRow, (gameBoard.get(zeroRow) - toRemove));
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
		if(gameBoard.get(0) == 0 && gameBoard.get(1) == 0 && gameBoard.get(2) == 0)
		{
			return true;
		}
		return false;
	}
	
	public void testReturnTurns()
	{
		boolean player = true;
		for(Board turn : gameTurns)
		{			
			System.out.println(((player) ? 1 : 2) + ": " + turn.get(0) + ", " + turn.get(1) + ", " + turn.get(2));
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
			Board previousBoard = gameTurns.get(i-1);
			Board currentBoard = gameTurns.get(i);
			firstPlayer = !firstPlayer;
			double value = ((i/2 + 1) / ((double)(firstPlayer ? firstTurn : secondTurn))) * (firstPlayer == firstWin ? 1 : -1);
			//Here is where you would store the states into the machine learning.
			if(gameKnowledge.get(previousBoard) != null)
			{
				StateContainer possible = gameKnowledge.get(previousBoard);
				if(possible.contains(currentBoard))
				{
					boolean test = possible.contains(currentBoard);
					int a = possible.indexOf(currentBoard);
					possible.get(possible.indexOf(currentBoard)).addValue(value);
				}
				else
				{
					possible.add(new MeanState(currentBoard, value));
				}
			}
			else
			{
				StateContainer contain = new StateContainer();
				contain.add(new MeanState(currentBoard, value));
				gameKnowledge.put(previousBoard, contain);
			}
		}
	}
	
	public void checkforduplicate()
	{
		Set<Board> holder = gameKnowledge.keySet();
		int duplicates = 0;
		for(Board board : holder)
		{
			int count = 0;
			for(Board second : holder)
			{
				if(board.get(0) == second.get(0) && board.get(1) == second.get(1) && board.get(2) == second.get(2))
				{
					count++;
				}
			}
			if(count > 1)
			{
				duplicates++;
			}
		}
		
		System.out.println("Number of duplicate keys: " + duplicates);
	}
	
	public void load()
	{
		try {
			ObjectInputStream ois;
			ois = new ObjectInputStream(new FileInputStream("data/learning.data"));
			gameKnowledge = (HashMap<Board, StateContainer>)ois.readObject();
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
