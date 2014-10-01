package csc130nim;

import java.io.Serializable;
import java.util.ArrayList;

public class StateContainer extends ArrayList<State> implements Serializable{

	@Override
	public boolean contains(Object state)
	{
		int[] board = new int[3];
		for(State s : this)
		{
			if(state.getClass() == s.getClass())
			{
				if(s.sameBoard(((State)state).getBoard()))
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
	
	@Override
	public String toString()
	{
		String toReturn = "";
		
		for(State s : this)
		{
			toReturn += s.toString() + ", ";
		}
		
		return toReturn;
	}
	
}
