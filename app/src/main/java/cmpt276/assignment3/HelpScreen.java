package cmpt276.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        TextView author = findViewById(R.id.tvAboutAuthor);
        author.setMovementMethod(LinkMovementMethod.getInstance());
        author.setLinkTextColor(Color.BLUE);
        TextView game = findViewById(R.id.tvGameDescribe);
        String describe = "This game is about the user has to find all the missing airplane behind those card./n\nWhen the user clicked once, if there is an airplane under that field, " +
                "the airplane will be displayed. However, if there has no airplane, there will display a hint number that tell you how many airplane are there in the same row and same column " +
                "in the field that the user have clicked.\n\nBesides, after the airplane has displayed, you can clicked once again to display the hint number.\n\nHowever, this game is to aim " +
                "for using the fewest scan to finish the game. Good Luck!";
        game.setText(describe);
    }
}