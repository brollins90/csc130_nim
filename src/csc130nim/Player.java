package csc130nim;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface Player {
	public abstract void notifyGameEnded();
	public abstract int getRow();
	public abstract int getNumberToRemove();
}
