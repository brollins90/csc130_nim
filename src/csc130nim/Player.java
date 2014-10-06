package csc130nim;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class Player {

    protected static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    // Hold a state passed in with the constructor
	public Player() {
		
	}
	public abstract void notifyGameEnded();
	public abstract int getRow();
	public abstract int getNumberToRemove();
}
