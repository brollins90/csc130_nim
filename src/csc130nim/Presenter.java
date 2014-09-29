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
     *
     * @param append the type of value being asked for.
     * @return the integer input from the user.
     */
    public int promptBasicInteger(String append) {

        System.out.println("Please input " + append);

        int i = 0;

        try {
            i = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return i == 0 ? promptBasicInteger(append + " properly") : i;
    }

}
