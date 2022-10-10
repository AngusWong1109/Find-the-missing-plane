package cmpt276.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Arrays;
import java.util.Random;

import cmpt276.assignment3.model1.Game;
import cmpt276.assignment3.model1.GameManager;
import cmpt276.assignment3.model1.Mine;
import cmpt276.assignment3.model1.Options;

public class GameScreen extends AppCompatActivity {
    Options options = Options.getInstance();
    GameManager gameManager = GameManager.getInstance();
    Game game = new Game();
    int numRow = options.getGameHeight();
    int numCol = options.getGameWidth();
    Button buttons[][] = new Button[numRow][numCol];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        populateButtons();
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
                if((row * numCol + col) == game.minePosition[count]){
                    mine.setMine(true);
                    count++;
                }
                game.mineList.add(mine);
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
    }

    private void buttonClicked(int row, int col) {
        Button button = buttons[row][col];
        lockButtonSizes();
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.airplane);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resources = getResources();
        button.setBackground(new BitmapDrawable(resources, scaledBitmap));
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

}
