package connect.util;

public enum GameType {

    CONNECT4("ORIGINAL CONNECT 4"), POPOUT("PopOut"), POP10("Pop 10"), POWERUP("Power Up"), INAROW5("5-in-a-Row");

    private String name;

    GameType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
