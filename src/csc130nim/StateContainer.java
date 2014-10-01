package csc130nim;

import java.io.Serializable;
import java.util.ArrayList;

public class StateContainer extends ArrayList<MeanState> implements Serializable{

	@Override
	public boolean contains(Object state)
	{
		for(MeanState s : this)
		{
			if(state.getClass() == MeanState.class)
			{
                return (s.getBoard() == (((MeanState)state).getBoard()));
            }

			else if(state.getClass() == Board.class)
			{
                return (s.getBoard() == ((Board)state));
			}
		}
		
		return false;
	}
	
	public int indexOf(Board board)
	{
		int index = -1;
		
		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i).getBoard() == (board))
			{
				return i;
			}
		}
		
		return index;
	}
	
	@Override
	public String toString()

    {
        StringBuilder toReturn = new StringBuilder("");

        for (MeanState s : this)
        {
            toReturn.append(s.toString()).append(", ");
        }

        return toReturn.toString();
    }
}
