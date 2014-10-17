package csc130nim;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable{

    private static final int DEFAULT_SIZE = 3;
    private int[] rowsWithPieces;
	
	public Board() {
        rowsWithPieces = new int[DEFAULT_SIZE];
    }

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
        boolean equals = (this == obj);
        equals |= (obj == null);
        equals |= (getClass() != obj.getClass());

		Board other = (Board) obj;
        equals |= (!Arrays.equals(rowsWithPieces, other.rowsWithPieces));

        return equals;
	}
	
}
