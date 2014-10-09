package csc130nim;

public interface Player {
	public abstract void notifyGameEnded();
	public abstract int getRow(Board board);
	public abstract int getNumberToRemove(Board board);
}
