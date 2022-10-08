package cmpt276.assignment3.model1;

import java.util.List;

//This class is to manage all games played
public class GameManager {
    public List<Game> gameList;
    public List<Game> bestScoreList;

    private static GameManager instance;
    private GameManager(){

    }
    public static GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return instance;
    }
}
