package csc130nim;

import java.io.IOException;

public class HumanPlayer extends Player {

	@Override
	public int getRow() {
        int rowChoice = promptBasicInteger("the row of choice");
        return rowChoice;
    }

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

        System.out.println("Please input " + append);

        int i = 0;
        try {
            i = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return i;
    }
}
