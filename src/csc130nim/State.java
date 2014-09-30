package csc130nim;

import java.util.Arrays;

public class State {

	private int[] board = new int[3];
	private int timesSeen;
	private double value;
	
	public int[] getBoard() {
		return board;
	}
	public void setBoard(int[] board) {
		this.board = board;
	}
	public int getTimesSeen() {
		return timesSeen;
	}
	public void setTimesSeen(int timesSeen) {
		this.timesSeen = timesSeen;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public void addValue(double val)
	{
		double totalAvg = timesSeen * value;
		totalAvg += val;
		timesSeen++;
		value = totalAvg / timesSeen;
	}
	
	public boolean sameBoard(int[] otherBoard)
	{
		return Arrays.equals(board, otherBoard);
	}
	
}
