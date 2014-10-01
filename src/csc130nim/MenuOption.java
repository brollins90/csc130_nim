package csc130nim;

import java.util.HashMap;

/**
 * Created by Stephen on 9/29/2014.
 * In project: csc130_nim
 */
public enum MenuOption {
    PvP("Player vs. Player", 1) {
		@Override
		public void execute(Manager manager) {
			System.out.println("Playing a Two-Player game...");
			manager.StartGame(new HumanPlayer(), new HumanPlayer());
		}
	},
    PvE("Player vs. AI", 2) {
		@Override
		public void execute(Manager manager) {
			System.out.println("Playing Player vs. AI game...");
			manager.StartGame(new HumanPlayer(), new ComputerPlayer());
		}
	}, // Player versus Engine
    EvE("AI vs. AI", 3) {
		@Override
		public void execute(Manager manager) {
			for(int i = 0; i < 10; i++)
			{
				System.out.println("Playing AI vs AI game...");
				manager.StartGame(new ComputerPlayer(), new ComputerPlayer());
			}
		}
	}, // Random v Random
    RvR("Rand vs. Rand", 4) {
		@Override
		public void execute(Manager manager) {
			for(int i = 0; i < 10; i++)
			{
				System.out.println("Playing Rand vs Rand game...");
				manager.StartGame(new RandomPlayer(), new RandomPlayer());
			}
		}
	}, // Player v Random
    PvR("Player vs. Rand", 5) {
		@Override
		public void execute(Manager manager) {
			System.out.println("Playing Player vs Rand game...");
			manager.StartGame(new HumanPlayer(), new RandomPlayer());
		}
	},
    Exit("Exit", 0) {
		@Override
		public void execute(Manager manager) {
			System.out.println("Thanks for playing");
			System.exit(0);
		}
	};

    private final String readableName;
    private final int retValue;

    MenuOption(String legibleName, int value) {
        this.readableName = legibleName;
        this.retValue = value;
    }

    public String getReadableName() {
        return readableName;
    }

    public int getRetValue() {
        return retValue;
    }

	public abstract void execute(Manager manager);

    //        public abstract void execute();
}