package csc130nim;

/**
 * Created by Stephen on 9/29/2014.
 * In project: csc130_nim
 */
public enum MenuOption {
    PvP("Player vs. Player", 1),
    PvE("Player vs. AI", 2), // Player versus Engine
    EvE("AI vs. AI", 3),
    Exit("Exit", 0);

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

    //        public abstract void execute();
}