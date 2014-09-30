package csc130nim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Manager {
	
	public static int[] gameBoard = new int[3];
	
	public Manager() {
		NewGame();
	}
	
	private ArrayList<int[]> gameTurns = new ArrayList<>();
	
//	public void playGame()
//	{
//		boolean running = true;
//		
//		Random rand = new Random();
//		PlayerOne = (rand.nextInt(2) == 1) ? true : false;
//		PlayerOneFirst = PlayerOne;
//		
//		while(running)
//		{
//			present.printBoard(gameBoard);
//			present.printTurn(PlayerOne);
//			int row = present.promptRowSelection();
//			int pieces = present.promptNumPieces();
//			
//			try
//			{
//				removePieces(row, pieces);
//				if(GameEnded())
//				{
//					testReturnTurns();
//					running = false;
//				}
//			}
//			catch(IllegalArgumentException ex)
//			{
//				System.err.println(ex);
//			}
//		}
//	}
	
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
	}
	
	public void StartGame(Player p1, Player p2) {
		
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
				testReturnTurns();
				playing = false;
			}
		}
	}

    public void printOpeningMenu() {
        MenuOption[] enumValues = MenuOption.values();
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
//                collectedOptions.forEach(System.out::println);
                collectedOptions.get(0).execute(this);
            } else
			{
				System.err.println("Bad input");
			}
		} catch (IOException e) {
			System.err.println("The starter input was not parseable.");
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
	
}
