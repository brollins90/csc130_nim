package csc130nim;

import java.io.Serializable;

public class MeanState implements Serializable, Comparable<MeanState> {

	private Board board;
	private int timesSeen;
	private double value;
	
	public MeanState(){}
	public MeanState(Board board, double value)
    {
        assert board != null;
        this.board = board;
        this.timesSeen = 1;
        this.value = value;
    }
    public MeanState(Board board)
    {
        this(board, 0);
    }

    public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
        assert board != null;
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
	
	@Override
	public int compareTo(MeanState o) {
        assert o != null;
        int compareVal = 1;
        if (thisEqualsOther(o)) {
            compareVal = 0;
        }
        return compareVal;
    }
	
	public boolean thisEqualsOther(MeanState o){
		return this.board.get(0) == o.board.get(0) && this.board.get(1) == o.board.get(1) && this.board.get(2) == o.board.get(2);
	}

    @Override
	public boolean equals(Object obj) {

		if (obj instanceof MeanState)
            return this == obj || (this.compareTo((MeanState)obj) == 0);
		return false;
	}
	
}
