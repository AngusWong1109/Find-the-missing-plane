package cmpt276.assignment3.model1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//This class is to store every information about each single game
public class Game {
    int numMines = Options.getInstance().getTotalMines();
    private int numOfScans;
    private int numOfMinesFound;
    public static List<Mine> mineList = new ArrayList<>();
    public int[] minePosition;
    int totalSize = Options.getInstance().getGameHeight() * Options.getInstance().getGameWidth();
    public Game() {
        numOfScans = 0;
        numOfMinesFound = 0;
        minePosition = new int[numMines];
        randomMine();
    }

    public int getNumOfScans() {
        return numOfScans;
    }

    public void setNumOfScans(int numOfScans) {
        this.numOfScans = numOfScans;
    }

    public int getNumOfMinesFound(){
        return numOfMinesFound;
    }

    public void setNumOfMinesFound(int numOfMinesFound){
        this.numOfMinesFound = numOfMinesFound;
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

    private void randomMine(){
        int totalMine = Options.getInstance().getTotalMines();
        int count = 0;
        boolean flag = false;
        do {
            Random random = new Random();
            int pos = random.nextInt(totalSize);
            do {
                for (int j = 0; j < count; j++) {
                    if (minePosition[j] == pos) {
                        pos = random.nextInt(totalMine);
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }
            } while (flag);
            minePosition[count] = pos;
            count++;
        }while(count < totalMine);
        Arrays.sort(minePosition);
    }
}
