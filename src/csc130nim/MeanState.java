package csc130nim;

import java.io.Serializable;
import java.util.Arrays;

public class MeanState implements Serializable, Comparable<MeanState> {

	private Board board;
	private int timesSeen;
	private double value;
	
	public MeanState(){}
	public MeanState(Board board)
	{
		this.board = board;
		this.timesSeen = 1;
		this.value = 0;
	}
	public MeanState(Board board, double value)
	{
		this.board = board;
		this.timesSeen = 1;
		this.value = value;
	}
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
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
		
	@Override
	public String toString()
	{
		return "[" + board.get(0) + ", " + board.get(1) + ", " + board.get(2) + "]; " + value;
	}
	
	//I honestly don't remember this override or if it is even used, but I'll leave it here for now. David
	@Override
	public int compareTo(MeanState o) {
		int compareVal = 1;
		if (this.board.get(0) == o.board.get(0) && this.board.get(1) == o.board.get(1) && this.board.get(2) == o.board.get(2)) {
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
