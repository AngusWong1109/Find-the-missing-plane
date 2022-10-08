package cmpt276.assignment3.model1;

import java.util.List;

//This class is to store every information about each single game
public class Game {
    private int numOfScans;
    public static List<Mine> mineList;

    public Game() {
        numOfScans = 0;
    }

    public int getNumOfScans() {
        return numOfScans;
    }

    public void setNumOfScans(int numOfScans) {
        this.numOfScans = numOfScans;
    }

    public static int mineScanner(Mine mine) {
        int x = mine.getCoordinateX(), y = mine.getCoordinateY();
        int row = Options.getInstance().getGameHeight();
        int col = Options.getInstance().getGameWidth();
        int count = 0;
        if(mineList == null){
            return count;
        }
        for(int i=0; i<row; i++){
            if(i == y){
                continue;
            }
            if(mineList.get(col*i+y).isMine()){
                count++;
            }
        }
        for(int i=0; i<col; i++){
            if(i == x){
                continue;
            }
            if(mineList.get(y*col+i).isMine()){
                count++;
            }
        }
        return count;
    }
}
