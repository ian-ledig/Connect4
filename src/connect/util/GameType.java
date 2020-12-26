package connect.util;

import java.util.Arrays;

public enum GameType {

    CONNECT4("ORIGINAL CONNECT 4"), POPOUT("PopOut"), POP10("Pop 10"), POWERUP("Power Up"), INAROW5("5-in-a-Row"), POPOUTINAROW5("PopOut & 5-in-a-row");

    private String name;

    GameType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GameType getGameType(String name){
        return Arrays.stream(GameType.values()).filter(gameType -> gameType.getName().equals(name)).findAny().orElse(null);
    }
}
