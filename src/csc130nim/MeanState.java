package csc130nim;

import java.io.Serializable;
import java.util.Arrays;

public class MeanState implements Serializable, Comparable<MeanState> {

	private int[] board = new int[3];
	private int timesSeen;
	private double value;
	
	public MeanState(){}
	public MeanState(int[] board)
	{
		this.board = board;
		this.timesSeen = 1;
		this.value = 0;
	}
	public MeanState(int[] board, double value)
	{
		this.board = board;
		this.timesSeen = 1;
		this.value = value;
	}
	
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
	
	@Override
	public String toString()
	{
		return "[" + board[0] + ", " + board[1] + ", " + board[2] + "]; " + value;
	}
	
	@Override
	public int compareTo(MeanState o) {
		int compareVal = 1;
		if (this.board[0] == o.board[0] && this.board[1] == o.board[1] && this.board[2] == o.board[2]) {
			compareVal = 0;
		} 
		return compareVal;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof MeanState)
            return this == obj || (this.compareTo((MeanState)obj) == 0);
		return false;
	}
	
}
