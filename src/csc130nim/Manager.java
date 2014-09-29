package csc130nim;

public class Manager {
	
	private int[] gameBoard = new int[3];
	
	
	public boolean removePieces(int row, int toRemove)
	{
		if(row > 3 || row < 0)
		{
			throw new IllegalArgumentException("Tried to pick a nonexistant row.");
		}
		else
		{
			int zeroRow = row - 1; //Zero based row
			if(toRemove <= gameBoard[zeroRow] && toRemove > 0)
			{
				gameBoard[zeroRow] -= toRemove;
			}
			else
			{
				throw new IllegalArgumentException("Tried to remove too many pieces");
			}
		}
		
		return false;
	}
	
}
