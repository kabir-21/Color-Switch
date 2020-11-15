package sample;

public class Player {
    private String name;
    private int score;
    Player(String name){
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }
}
