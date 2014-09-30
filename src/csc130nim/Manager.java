package csc130nim;

public class Manager {
	
	public static int[] gameBoard = new int[3];
	
	public Manager() {
		NewGame();
	}
	
	public void NewGame() {
		gameBoard[0] = 3;
		gameBoard[0] = 5;
		gameBoard[0] = 7;		
	}
	
	public void StartGame(Player p1, Player p2) {
		
		Boolean playing = true;
		Boolean playerOneCurrent = true;
		
		while(playing) {
			Player current = (playerOneCurrent) ? p1 : p2;
			removePieces(current.getRow(), current.getNumberToRemove());
		}
	}
	
	
	public void removePieces(int row, int toRemove)
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
	}
	
	
	
}
