package connect.util;

public class Game {

    private int[] points = {0, 0};

    public Game(){
    }

    public int[] getPoints() {
        return points;
    }

    public void setPoints(int[] points) {
        this.points = points;
    }

    public void incrementRed(){
        points[0]++;
    }

    public void incrementYellow(){
        points[1]++;
    }
}
