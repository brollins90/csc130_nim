package csc130nim;

import java.io.Serializable;
import java.util.Arrays;

public class State implements Serializable, Comparable<State> {

	private int[] board = new int[3];
	private int timesSeen;
	private double value;
	
	public State(){}
	public State(int[] board)
	{
		this.board = board;
		this.timesSeen = 1;
		this.value = 0;
	}
	public State(int[] board, double value)
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
	public int compareTo(State o) {
		int compareVal = 1;
		if (this.board[0] == o.board[0] && this.board[1] == o.board[1] && this.board[2] == o.board[2]) {
			compareVal = 0;
		} 
		return compareVal;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof State)
            return this == obj || (this.compareTo((State)obj) == 0);
		return false;
	}
	
}
