package cmpt276.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import cmpt276.assignment3.model1.Game;
import cmpt276.assignment3.model1.GameManager;
import cmpt276.assignment3.model1.Mine;
import cmpt276.assignment3.model1.Options;

public class GameScreen extends AppCompatActivity {
    Options options = Options.getInstance(this);
    GameManager gameManager = GameManager.getInstance();
    Game game = new Game(options);
    int numRow = options.getGameHeight();
    int numCol = options.getGameWidth();
    Button buttons[][] = new Button[numRow][numCol];
    int totalSize = numRow * numCol;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(GameScreen.this, MenuScreen.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        updateUI();
        populateButtons();
    }

    private void updateUI() {
        TextView minesFound = findViewById(R.id.tvMinesFound);
        TextView numScans = findViewById(R.id.tvScanUsed);
        minesFound.setText(getString(R.string.mines, game.getNumOfMinesFound(), options.getTotalMines()));
        numScans.setText(getString(R.string.scans, game.getNumOfScans()));
    }

    private void populateButtons() {
        TableLayout table = findViewById(R.id.tableForButtons);
        int count = 0;
        for(int row = 0; row < numRow; row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            table.addView(tableRow);
            for(int col = 0; col < numCol; col++){
                Mine mine = new Mine(row, col);
                Game.mineList.add(mine);
                final int FINAL_ROW = row;
                final int FINAL_COL = col;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonClicked(FINAL_ROW, FINAL_COL);
                    }
                });
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
        for(int i = 0; i < totalSize; i++){
            if(count < options.getTotalMines()){
                if(i == game.minePosition[count]){
                    Game.mineList.get(i).setMine(true);
                    count++;
                }
            }
        }
        for(int i = 0; i < totalSize; i++){
            Game.mineList.get(i).setHintNum(Game.mineScanner(Game.mineList.get(i)));
        }
    }

    private void buttonClicked(int row, int col) {
        int pos = row * numCol + col;
        Mine mine = Game.mineList.get(pos);
        Button button = buttons[row][col];
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        int numClicked = mine.getClicked();
        lockButtonSizes();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.airplane);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resources = getResources();
        if(mine.isMine() && numClicked == 0){
            button.setBackground(new BitmapDrawable(resources, scaledBitmap));
            game.setNumOfMinesFound(game.getNumOfMinesFound()+1);
            updateHintNum(mine);
        }
        else{
            button.setText(String.valueOf(mine.getHintNum()));
            game.setNumOfScans(game.getNumOfScans()+1);
        }
        mine.setClicked(numClicked + 1);
        updateUI();
        if(game.getNumOfMinesFound() == options.getTotalMines()){
            showAlertDialog();
        }
    }

    private void updateHintNum(Mine mine) {
        int totalSize = numRow * numCol;
        boolean isMine = false;
        mine.setMine(false);
        for(int i = 0; i < totalSize; i++){
            Mine m = Game.mineList.get(i);
            m.setHintNum(Game.mineScanner(m));
        }
        for(int row = 0; row < numRow; row++){
            for(int col = 0; col < numCol; col++){
                int pos = row *numCol + col;
                for(int i = 0; i < game.minePosition.length; i++){
                    isMine = false;
                    if(pos == game.minePosition[i]){
                        isMine = true;
                        break;
                    }
                }
                Mine m = Game.mineList.get(pos);
                Button btn = buttons[row][col];
                if((m.getClicked() > 0 && !isMine) || (m.getClicked() >= 2 && isMine)) {
                    btn.setText(String.valueOf(m.getHintNum()));
                }
            }
        }
    }


    private void lockButtonSizes() {
        for(int row = 0; row < numRow; row++){
            for(int col = 0; col < numCol; col++){
                Button button = buttons[row][col];
                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);
                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void showAlertDialog() {
        game.saveGame();
        for(int i = 0; i < game.minePosition.length; i++){
            Mine m = Game.mineList.get(game.minePosition[i]);
            m.setMine(true);
        }
        FragmentManager manager = getSupportFragmentManager();
        MessageFragment dialog = new MessageFragment();
        dialog.show(manager, "MessageDialog");
    }
}
