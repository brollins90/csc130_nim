package csc130nim;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface Player {

    // Hold a state passed in with the constructor
	public abstract void notifyGameEnded();
	public abstract int getRow(Board board);
	public abstract int getNumberToRemove(Board board);
}
