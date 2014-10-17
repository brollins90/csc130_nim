package csc130nim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Manager implements Serializable {

	private static final long serialVersionUID = 1L;
	private Board gameBoard = new Board();
    private final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
    private final int FIRST_ROW_MAX = 3;
    private final int SECOND_ROW_MAX = 5;
    private final int THIRD_ROW_MAX = 7;
	/**
	 * Create a Manager and instantiate a new game
	 */
	public Manager() {
		NewGame();
	}
		
	public void setBoard(int one, int two, int three)
	{
		gameBoard.set(0, one);
		gameBoard.set(1, two);
		gameBoard.set(2, three);
	}
	
	public void NewGame() {
		setBoard(FIRST_ROW_MAX, SECOND_ROW_MAX, THIRD_ROW_MAX);
	}
	
	public void StartGame(Player p1, Player p2) {
		
		NewGame();
		Boolean playing = true;
		Boolean playerOneCurrent = true;
	
		while(playing) {
			Player current = (playerOneCurrent) ? p1 : p2;
			System.out.println(playerOneCurrent ? "Player One: " : "Player Two: ");
			boolean completedMove = false;

			while(!completedMove) {
				
				try {
					int row = current.getRow(gameBoard);
					int count = current.getNumberToRemove(gameBoard);
					removePieces(row, count);
					completedMove = true;
					playerOneCurrent = !playerOneCurrent;
				} catch (Exception e) {
					System.out.println("Invalid selection, try again.");
					//e.printStackTrace();
				}
				if(GameEnded())
				{
					p1.notifyGameEnded();
					p2.notifyGameEnded();
					playing = false;
				}
			}
		}
	}

    public void presentMenu() {
        MenuOption[] enumValues = MenuOption.values();

        while(true)
        {
            printMenuOptions(enumValues);
            try {
				int choice = handleInput(this.reader.readLine());
				if(choice >= 0 && choice < enumValues.length) {
	                List<MenuOption> collectedOptions = Arrays.asList(enumValues)
	                        .stream()
	                        .filter(x -> x.getRetValue() == choice)
	                        .collect(Collectors.toList());
	                collectedOptions.get(0).execute(this);
                }
	            else
                    System.err.println("Bad input");

			} catch (IOException e) {
				System.err.println("The starter input was not parseable.");
			}
        }
    }

    private void printMenuOptions(MenuOption[] enumValues) {
        for (int i = 0; i < enumValues.length; i++) {
            MenuOption current = enumValues[i];
            System.out.println(current.getRetValue() + ". " + current.getReadableName());
        }
    }

    private int handleInput(String readLine) {
		return Integer.parseInt(readLine);
	}

	public void removePieces(int row, int toRemove)
	{
		if(row > 3 || row < 0) // the game will never have more than 3 rows
			throw new IllegalArgumentException("Tried to pick a nonexistant row.");
		else
		{
			int zeroRow = row - 1; //Zero based row
			if(toRemove <= gameBoard.get(zeroRow) && toRemove > 0)
			{
				gameBoard.set(zeroRow, (gameBoard.get(zeroRow) - toRemove));
			}
			else
			{
				throw new IllegalArgumentException("Tried to remove too many pieces");
			}
		}
	}
	
	public boolean GameEnded()
	{
        return gameBoard.get(0) == 0 && gameBoard.get(1) == 0 && gameBoard.get(2) == 0;
    }

}
