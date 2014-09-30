package csc130nim;

public class GameState implements Comparable<GameState> {

	public int low;
	public int middle;
	public int high;
	
	@Override
	public int compareTo(GameState o) {
		int compareVal = 1;
		if (this.low == o.low && this.middle == o.middle && this.high == o.high) {
			compareVal = 0;
		} 
		return compareVal;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof GameState)
            return this == obj || (this.compareTo((GameState)obj) == 0);
		return false;
	}
}
