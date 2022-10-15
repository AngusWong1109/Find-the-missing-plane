package cmpt276.assignment3.model1;

import java.util.ArrayList;
import java.util.List;

//This class is to manage all games played
public class GameManager {
    public List<Game> r4c6m6 = new ArrayList<>();
    public List<Game> r4c6m10 = new ArrayList<>();
    public List<Game> r4c6m15 = new ArrayList<>();
    public List<Game> r4c6m20 = new ArrayList<>();
    public List<Game> r5c10m6 = new ArrayList<>();
    public List<Game> r5c10m10 = new ArrayList<>();
    public List<Game> r5c10m15 = new ArrayList<>();
    public List<Game> r5c10m20 = new ArrayList<>();
    public List<Game> r6c15m6 = new ArrayList<>();
    public List<Game> r6c15m10 = new ArrayList<>();
    public List<Game> r6c15m15 = new ArrayList<>();
    public List<Game> r6c15m20 = new ArrayList<>();
    private static int numOfGame;

    private static GameManager instance;
    private GameManager(){
        numOfGame = 0;
    }
    public static GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return instance;
    }
    public int getNumOfGame(){
        int num = r4c6m6.size() + r4c6m10.size() + r4c6m15.size() + r4c6m20.size() + r5c10m6.size() + r5c10m10.size() + r5c10m15.size() + r5c10m20.size()
                + r6c15m6.size() + r6c15m10.size() + r6c15m15.size() + r6c15m20.size();
        return num;
    }

    public int findBestGame(List<Game> list) {
        if(list.isEmpty()){
            return 0;
        }
        int min = list.get(0).getNumOfScans();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getNumOfScans() < min){
                min = list.get(i).getNumOfScans();
            }
        }
        return min;
    }
}
