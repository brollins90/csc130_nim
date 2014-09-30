package csc130nim;

import java.io.IOException;

public class Manager {
	
	public static int[] gameBoard = new int[3];
	
	public Manager() {
		NewGame();
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
			removePieces(current.getRow(), current.getNumberToRemove());
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
			enumValues[enumValues.length-1-choice].execute(this);
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
			}
			else
			{
				throw new IllegalArgumentException("Tried to remove too many pieces");
			}
		}
	}
	
	
	
}
