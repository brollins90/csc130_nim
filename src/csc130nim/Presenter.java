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
        MenuOptions[] enumValues = MenuOptions.values();
        for (int i = 0; i < enumValues.length; i++) {
            System.out.println((i+1) + ". " + enumValues[i]);
        }
    }

    enum MenuOptions {
        PlayerVsPlayer,
        PlayerVsAI,
        AIVsAI,
        Exit;

//        public abstract void execute();
    }
}
