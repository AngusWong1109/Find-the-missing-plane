package cmpt276.assignment3.model1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

//This class is to store every information about each single game
public class Game {
    private int numOfScans;
    public static List<Mine> mineList;
    public static int[] minePosition;
    public Game() {
        numOfScans = 0;
        minePosition = new int[Options.getInstance().getTotalMines()];
        randomMine();
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

    private void randomMine(){
        int boardSize = Options.getInstance().getGameHeight() * Options.getInstance().getGameWidth();
        int totalMine = Options.getInstance().getTotalMines();
        int count = 0;
        boolean flag = false;
        Random random = new Random();
        do {
            for (int i = 0; i < totalMine; i++) {
                int pos = random.nextInt(totalMine);
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
            }
        }while(count < totalMine);
        Arrays.sort(minePosition);
    }
}
