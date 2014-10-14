package csc130nim;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable{

	private int[] rowsWithPieces;
	
	public Board(){}
	
	public Board(int...a)
	{
		rowsWithPieces = a;
	}
	
	public int get(int index)
	{
		return rowsWithPieces[index];
	}

	public void set(int index, int value)
	{
		rowsWithPieces[index] = value;
	}
		
	@Override
	public Board clone()
	{
		int[] newBoard = new int[rowsWithPieces.length];
		for(int i = 0; i < newBoard.length; i++){
			newBoard[i] = rowsWithPieces[i];
		}
		
		return new Board(newBoard);
	}
	
	@Override
	public String toString()
	{
        StringBuilder toReturn = new StringBuilder("");
        for (int i : rowsWithPieces)
        {
            toReturn.append(i).append(", ");
        }
        return toReturn.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(rowsWithPieces);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.equals(rowsWithPieces, other.rowsWithPieces))
			return false;
		return true;
	}
	
}
