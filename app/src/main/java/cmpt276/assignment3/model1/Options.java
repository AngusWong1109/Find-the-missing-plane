package cmpt276.assignment3.model1;

public class Options {
    private int gameWidth;
    private int gameHeight;
    private int totalMines;
    private static Options instance;
    private Options(){
        //Private to prevent anyone else from instantiating
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
