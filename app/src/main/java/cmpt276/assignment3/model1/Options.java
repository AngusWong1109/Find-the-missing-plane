package cmpt276.assignment3.model1;

public class Options {
    private int gameWidth;
    private int gameHeight;
    private int totalMines;
    private static Options instance;

    private Options(){
        this.gameHeight = 4;
        this.gameWidth = 6;
        this.totalMines = 6;
    }
    public static Options getInstance(){
        if(instance == null){
            instance = new Options();
        }
        return instance;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }

    public int getTotalMines() {
        return totalMines;
    }

    public void setTotalMines(int totalMines) {
        this.totalMines = totalMines;
    }
}
