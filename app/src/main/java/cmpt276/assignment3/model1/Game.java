package cmpt276.assignment3.model1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//This class is to store every information about each single game
public class Game {
    private int numOfScans;
    private int numOfMinesFound;
    private final int numRow = Options.getInstance().getGameHeight();
    private final int numCol = Options.getInstance().getGameWidth();
    private final int numMines = Options.getInstance().getTotalMines();
    public static List<Mine> mineList = new ArrayList<>();
    public int[] minePosition;
    int totalSize = numRow * numCol;
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


    private void randomMine(){
        int count = 0;
        boolean flag = false;
        do {
            Random random = new Random();
            int pos = random.nextInt(totalSize);
            do {
                for (int j = 0; j < count; j++) {
                    if (minePosition[j] == pos) {
                        pos = random.nextInt(totalSize);
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }
            } while (flag);
            minePosition[count] = pos;
            count++;
        }while(count < numMines);
        Arrays.sort(minePosition);
    }

    public static int mineScanner(Mine mine) {
        int x = mine.getCoordinateX();
        int y = mine.getCoordinateY();
        int count = 0;
        int height = Options.getInstance().getGameHeight();
        int width = Options.getInstance().getGameWidth();
        int totalSize = height * width;
        for(int i = 0; i < totalSize; i++){
            Mine temp = mineList.get(i);
            if(temp.getCoordinateX() == x && temp.getCoordinateY() == y){
                continue;
            }
            if(temp.getCoordinateX() == x && temp.isMine()){
                count ++;
            }
            if(temp.getCoordinateY() == y && temp.isMine()){
                count ++;
            }
        }
        return count;
    }

    public void saveGame(){
        GameManager gm = GameManager.getInstance();
        switch(numRow){
            case 4:
                switch(numMines){
                    case 6:
                        gm.r4c6m6.add(this);
                        break;
                    case 10:
                        gm.r4c6m10.add(this);
                        break;
                    case 15:
                        gm.r4c6m15.add(this);
                        break;
                    case 20:
                        gm.r4c6m20.add(this);
                        break;
                }
                break;
            case 5:
                switch(numMines){
                    case 6:
                        gm.r5c10m6.add(this);
                        break;
                    case 10:
                        gm.r5c10m10.add(this);
                        break;
                    case 15:
                        gm.r5c10m15.add(this);
                        break;
                    case 20:
                        gm.r5c10m20.add(this);
                        break;
                }
                break;
            case 6:
                switch(numMines){
                    case 6:
                        gm.r6c15m6.add(this);
                        break;
                    case 10:
                        gm.r6c15m10.add(this);
                        break;
                    case 15:
                        gm.r6c15m15.add(this);
                        break;
                    case 20:
                        gm.r6c15m20.add(this);
                        break;
                }
                break;
        }
    }

    public static void deleteGame(){
        GameManager gm = GameManager.getInstance();
        gm.r4c6m6.clear();
        gm.r4c6m10.clear();
        gm.r4c6m15.clear();
        gm.r4c6m20.clear();
        gm.r5c10m6.clear();
        gm.r5c10m10.clear();
        gm.r5c10m15.clear();
        gm.r5c10m20.clear();
        gm.r6c15m6.clear();
        gm.r6c15m10.clear();
        gm.r6c15m15.clear();
        gm.r6c15m20.clear();
    }
}
