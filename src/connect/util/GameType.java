package connect.util;

import connect.gamerule.*;

import java.util.Arrays;

public enum GameType {

    CONNECT4("ORIGINAL CONNECT 4", RuleConnect4.class), POPOUT("PopOut", RulePopOut.class), POP10("Pop 10", RulePop10.class), POWERUP("Power Up", RulePopOut.class), INAROW5("5-in-a-Row", Rule5InARow.class), POPOUTINAROW5("PopOut & 5-in-a-row", RulePopOut5InARow.class);

    private String name;
    private Class<? extends GameRule> gameRule;

    GameType(String name, Class<? extends GameRule> gameRule){
        this.name = name;
        this.gameRule = gameRule;
    }

    public String getName() {
        return name;
    }

    public Class<? extends GameRule> getGameRule() {
        return gameRule;
    }

    public static GameType getGameType(String name){
        return Arrays.stream(GameType.values()).filter(gameType -> gameType.getName().equals(name)).findAny().orElse(null);
    }
}
