package csc130nim;

public class GameStarter {

	public static void main(String[] args) {

		Manager m = new Manager();
		m.StartGame(new HumanPlayer(), new HumanPlayer());
        // new Tester().testPrompt();

	}
}
