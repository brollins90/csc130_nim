package csc130nim;

import java.io.Serializable;
import java.util.ArrayList;

public class StateContainer extends ArrayList<MeanState> implements Serializable{

	public boolean contains(Board board)
	{
		boolean ret = false;
		for(MeanState s : this)
		{
			if(s.getBoard().equals(board))
			{
				ret = true;
			}
		}
		
		return ret;
	}
	
	public int indexOf(Board board)
	{
		int index = -1;
		
		for(int i = 0; i < this.size(); i++)
		{
			if(this.get(i).getBoard().equals(board))
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
