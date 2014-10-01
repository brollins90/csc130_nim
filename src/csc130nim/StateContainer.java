package csc130nim;

import java.io.Serializable;
import java.util.ArrayList;

public class StateContainer extends ArrayList<MeanState> implements Serializable{

	@Override
	public boolean contains(Object state)
	{
		int[] board = new int[3];
		for(MeanState s : this)
		{
			if(state.getClass() == s.getClass())
			{
				if(s.sameBoard(((MeanState)state).getBoard()))
				{
					return true;
				}
			}
			
			else if(state.getClass() == board.getClass())
			{
				if(s.sameBoard((int[])state))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int indexOf(int[] board)
	{
		int index = -1;
		
		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i).sameBoard(board))
			{
				return i;
			}
		}
		
		return index;
	}
	
	@Override
	public String toString()
	{
		String toReturn = "";
		
		for(MeanState s : this)
		{
			toReturn += s.toString() + ", ";
		}
		
		return toReturn;
	}
}
