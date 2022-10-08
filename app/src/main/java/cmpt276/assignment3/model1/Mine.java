package cmpt276.assignment3.model1;

//This class is to store all the information about each mine
public class Mine {
    private final int coordinateX;
    private final int coordinateY;
    private int hintNum;
    private int clicked;
    private boolean isMine;

    public Mine(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.clicked = 0;
        this.hintNum = Game.mineScanner(this);
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public int getHintNum() {
        return hintNum;
    }

    public void setHintNum(int hintNum) {
        this.hintNum = hintNum;
    }

    public int getClicked() {
        return clicked;
    }

    public void setClicked(int clicked) {
        this.clicked = clicked;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

}
