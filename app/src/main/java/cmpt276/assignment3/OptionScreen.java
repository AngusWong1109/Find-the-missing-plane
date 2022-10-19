package cmpt276.assignment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import cmpt276.assignment3.model1.Game;
import cmpt276.assignment3.model1.GameManager;
import cmpt276.assignment3.model1.Options;

public class OptionScreen extends AppCompatActivity {
    private static final String BOARD_SIZE_PREF = "Size pref";
    private static final String BOARD_SIZE = "Board Size";
    private static final String NUMBER_OF_MINE_PREF = "Number pref";
    private static final String NUMBER_OF_MINES = "Number of mines";
    Options options = Options.getInstance(this);
    GameManager gameManager = GameManager.getInstance();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OptionScreen.this, MenuScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);
        updateUI();
        createRadioButtons();
        savePrefs();
        createDropDownList();
        setUpDeleteButton();
    }

    private void savePrefs() {
        String savedBoardSizeValue = getBoardSize(this);
        if(savedBoardSizeValue.equals(getString(R.string._4Times6))){
            options.setGameHeight(4);
            options.setGameWidth(6);
        }
        else if(savedBoardSizeValue.equals(getString(R.string._5Times10))){
            options.setGameHeight(5);
            options.setGameWidth(10);
        }
        else{
            options.setGameHeight(6);
            options.setGameWidth(15);
        }
        int savedNumMinesValue = getNumberOfMines(this);
        options.setTotalMines(savedNumMinesValue);
    }


    @Override
    protected void onStart() {
        super.onStart();
        updateUI();
    }

    private void updateUI() {
        TextView noOfGame = findViewById(R.id.tvNoOfGame);
        noOfGame.setText(getString(R.string.number_of_games,gameManager.getNumOfGame()));
    }


    private void createRadioButtons() {
        RadioGroup radioGroup1 = findViewById(R.id.rGroupBoardSize);
        String[] boardSize = getResources().getStringArray(R.array.board_size);
        for (int i = 0; i < boardSize.length; i++) {
            RadioButton button = new RadioButton(this);
            String size = boardSize[i];
            button.setText(size);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveBoardSize(size);
                }
            });
            radioGroup1.addView(button);
            if(size.equals(getBoardSize(this))){
                button.setChecked(true);
            }
        }
        RadioGroup radioGroup2 = findViewById(R.id.rGroupNumOfMines);
        int[] mines = getResources().getIntArray(R.array.num_mines);
        for (int i = 0; i < mines.length; i++) {
            RadioButton button = new RadioButton(this);
            int numMines = mines[i];
            button.setText(getString(R.string.display_mine, numMines));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveNumberOfMines(numMines);
                }
            });
            radioGroup2.addView(button);
            if(numMines == getNumberOfMines(this)){
                button.setChecked(true);
            }
        }
    }

    private void saveBoardSize(String size) {
        SharedPreferences prefs = this.getSharedPreferences(BOARD_SIZE_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(BOARD_SIZE, size);
        editor.apply();
    }

    static public String getBoardSize(Context context){
        SharedPreferences prefs = context.getSharedPreferences(BOARD_SIZE_PREF, MODE_PRIVATE);
        String defaultValue = context.getResources().getString(R.string.default_board_size);
        return prefs.getString(BOARD_SIZE, defaultValue);
    }

    private void saveNumberOfMines(int numMines) {
        SharedPreferences prefs = this.getSharedPreferences(NUMBER_OF_MINE_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(NUMBER_OF_MINES, numMines);
        editor.apply();
    }

    static public int getNumberOfMines(Context context){
        SharedPreferences prefs = context.getSharedPreferences(NUMBER_OF_MINE_PREF, MODE_PRIVATE);
        int defaultValue = context.getResources().getInteger(R.integer.default_num_mines);
        return prefs.getInt(NUMBER_OF_MINES, defaultValue);
    }

    private void createDropDownList() {
        TextView numGame = findViewById(R.id.tvShowNumGame);
        Spinner spin = findViewById(R.id.gameConfigDropDown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.game_config, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 1:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r4c6m6)));
                        break;
                    case 2:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r4c6m10)));
                        break;
                    case 3:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r4c6m15)));
                        break;
                    case 4:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r4c6m20)));
                        break;
                    case 5:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r5c10m6)));
                        break;
                    case 6:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r5c10m10)));
                        break;
                    case 7:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r5c10m15)));
                        break;
                    case 8:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r5c10m20)));
                        break;
                    case 9:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r6c15m6)));
                        break;
                    case 10:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r6c15m10)));
                        break;
                    case 11:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r6c15m15)));
                        break;
                    case 12:
                        numGame.setText(getString(R.string.used_scan, gameManager.findBestGame(gameManager.r6c15m20)));
                        break;
                    default:
                        numGame.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpDeleteButton() {
        Button delete = findViewById(R.id.btnErase);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
                TextView display = findViewById(R.id.tvShowNumGame);
                display.setText(getString(R.string.used_scan, 0));
            }
        });
    }

    private void showAlert() {
        AlertDialog.Builder build = new AlertDialog.Builder(OptionScreen.this);
        build.setMessage(R.string.confirm_user_options);
        build.setTitle(R.string.delete);
        build.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Game.deleteGame();
                updateUI();
            }
        });
        build.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = build.create();
        alert.show();
    }
}
