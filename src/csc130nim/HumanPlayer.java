package csc130nim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer implements Player {

    private static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    // Hold a state passed in with the constructor
	/**
	 * Asks the player for a row to use
	 */
	@Override
	public int getRow() {
		printGameBoard();
		
        int rowChoice = promptBasicInteger("the row of choice");
        return rowChoice;
    }

	/**
	 * Asks the player for the number of pieces to remove in the previously selected row
	 */
	@Override
	public int getNumberToRemove() {
        int numChoice = promptBasicInteger("the number to remove from that row");
        return numChoice;
    }

    /**
     * @param append the type of value being asked for.
     * @return the integer input from the user.
     */
    private int promptBasicInteger(String append) {

        assert append != null;
        System.out.println("Please input " + append);

        int i = 0;
        try {
            i = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return i;
    }
    
    /**
     * Prints the board for the player
     */
    private void printGameBoard() {
    	String line = "";
    	line += "1:";
    	for (int i = 0; i < Manager.gameBoard.get(0); i++) {
    		line += " X";
    	}
    	line += "\n";
    	
    	line += "2:";
    	for (int i = 0; i < Manager.gameBoard.get(1); i++) {
    		line += " X";
    	}
    	line += "\n";
    	
    	line += "3:";
    	for (int i = 0; i < Manager.gameBoard.get(2); i++) {
    		line += " X";
    	}
    	line += "\n";
    	
    	System.out.println(line);
    }

	@Override
	public void notifyGameEnded() {
		// TODO print an end screen
	}
}
