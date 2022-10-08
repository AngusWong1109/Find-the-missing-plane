package cmpt276.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import cmpt276.assignment3.model1.GameManager;
import cmpt276.assignment3.model1.Options;

public class OptionScreen extends AppCompatActivity {
    Options options = Options.getInstance();
    GameManager gameManager = GameManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);
        updateUI();
        createRadioButtons();
        setUpSaveButton();
        setUpDeleteButton();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI();
    }

    private void updateUI() {
        TextView noOfGame = findViewById(R.id.tvNoOfGame);
        String message;
        if(gameManager.gameList == null){
            message = "Number of game played: 0";
        }
        else {
            message = "Number of game played: " + gameManager.gameList.size();
        }
        noOfGame.setText(message);
    }


    private void createRadioButtons() {
        RadioGroup radioGroup1 = findViewById(R.id.rGroupBoardSize);
        String[] boardSize = getResources().getStringArray(R.array.board_size);
        for (int i = 0; i < boardSize.length; i++) {
            RadioButton button = new RadioButton(this);
            String size = boardSize[i].toString();
            button.setText(size);
            radioGroup1.addView(button);
        }
        RadioGroup radioGroup2 = findViewById(R.id.rGroupNumOfMines);
        int[] mines = getResources().getIntArray(R.array.num_mines);
        for (int i = 0; i < mines.length; i++) {
            RadioButton button = new RadioButton(this);
            int numMines = mines[i];
            button.setText(numMines + "mines");
            radioGroup2.addView(button);
        }
    }

    private void setUpSaveButton() {
        Button save = findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup radioGroup1 = findViewById(R.id.rGroupBoardSize);
                RadioGroup radioGroup2 = findViewById(R.id.rGroupNumOfMines);
                int idOfSelected1 = radioGroup1.getCheckedRadioButtonId();
                int idOfSelected2 = radioGroup2.getCheckedRadioButtonId();
                RadioButton boardSize = findViewById(idOfSelected1);
                String size = boardSize.getText().toString();
                if(size.equals(getString(R.string._4Times6))){
                    options.setGameHeight(4);
                    options.setGameWidth(6);
                }
                else if(size.equals(getString(R.string._5Times10))){
                    options.setGameHeight(5);
                    options.setGameHeight(10);
                }
                else{
                    options.setGameHeight(6);
                    options.setGameHeight(15);
                }
                RadioButton numMines = findViewById(idOfSelected2);
                int mines = Integer.parseInt(numMines.getText().toString());
                options.setTotalMines(mines);
            }
        });
    }

    private void setUpDeleteButton() {
        Button delete = findViewById(R.id.btnErase);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.gameList.clear();
            }
        });
    }
}
