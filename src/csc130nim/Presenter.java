package csc130nim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Stephen on 9/29/2014.
 * In project: csc130_nim
 */
public class Presenter {

    private static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    /**
     * @param append the type of value being asked for.
     * @return the integer input from the user.
     */
    private int promptBasicInteger(String append) {

        System.out.println("Please input " + append);

        int i = 0;
        try {
            i = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return i == 0 ? promptBasicInteger(append + " properly") : i;
    }

    public int promptRowSelection() {
        return promptBasicInteger("the number of rows.");
    }

    public int promptNumPieces() {
        return promptBasicInteger("the number of pieces.");
    }

    public int promptMenuSelection() {
        return promptBasicInteger("the menu selection.");
    }

    public void printOpeningMenu() {
        MenuOption[] enumValues = MenuOption.values();
        for (int i = 0; i < enumValues.length; i++) {
            MenuOption current = enumValues[i];
            System.out.println(current.getRetValue() + ". " + current.getReadableName());
        }
    }
    
    public void printBoard(int[] board)
    {
    	System.out.println(board[0] + ", " + board[1] + ", " + board[2]);
    }

    enum MenuOption {
        PvP("Player vs. Player", 1),
        PvE("Player vs. AI", 2), // Player versus Engine
        EvE("AI vs. AI", 3),
        Exit("Exit", 0);

        private final String readableName;
        private final int retValue;

        MenuOption(String legibleName, int value) {
            this.readableName = legibleName;
            this.retValue = value;
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public String getReadableName() {
            return readableName;
        }

        public int getRetValue() {
            return retValue;
        }

        //        public abstract void execute();
    }

}
