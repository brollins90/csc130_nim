package csc130nim;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class StateContainer implements Serializable{
	
	private ArrayList<MeanState> array;
	
	
	
	public StateContainer() {
		array = new ArrayList<MeanState>();
	}

	public void add(MeanState toAdd) {
		array.add(toAdd);
	}
	
	public boolean contains(Board board) {
		boolean ret = false;
		for(MeanState s : array) {
			if(s.getBoard().equals(board)) {
				ret = true;
			}
		}
		return ret;
	}

	public MeanState get(int index) {
		return array.get(index);
	}

	public int indexOf(Board board) {
		int index = -1;
		
		for(int i = 0; i < array.size(); i++) {
			if(array.get(i).getBoard().equals(board)) {
				return i;
			}
		}
		return index;
	}
	
	public Iterator<MeanState> iterator() {
		return this.array.iterator();
	}
	
	@Override
	public String toString()

    {
        StringBuilder toReturn = new StringBuilder("");

        for (MeanState s : array)
        {
            toReturn.append(s.toString()).append(", ");
        }

        return toReturn.toString();
    }
}
