package csc130nim;

import java.util.Random;

public class RandomPlayer implements Player{

	private Random rand = new Random();
	
	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return rand.nextInt(4);
	}

	@Override
	public int getNumberToRemove() {
		return rand.nextInt(8);
	}

	@Override
	public void notifyGameEnded() {
		// TODO Auto-generated method stub
		
	}

	
	
}
