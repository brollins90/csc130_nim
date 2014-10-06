package csc130nim;

import java.util.Random;

public class RandomPlayer extends Player{

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
	public void gameEnded() {
		// TODO Auto-generated method stub
		
	}

	
	
}
