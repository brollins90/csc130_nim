package csc130nim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by Stephen on 9/29/2014.
 * In project: csc130_nim
 */
public class Presenter {

    private static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    /**
     *
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

    public void printOpeningMenu() {
        new PrintWriter(System.out)
                .printf("1. Player vs. Player")
                .printf("2. Player vs. AI")
                .printf("3. AI vs. AI")
                .println("0. Exit");
    }
}
