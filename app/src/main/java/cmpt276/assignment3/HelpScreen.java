package cmpt276.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpScreen extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(HelpScreen.this, MenuScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        TextView author = findViewById(R.id.tvAboutAuthor);
        author.setMovementMethod(LinkMovementMethod.getInstance());
        author.setLinkTextColor(Color.BLACK);
        TextView reference = findViewById(R.id.tvCitation);
        reference.setMovementMethod(LinkMovementMethod.getInstance());
        reference.setLinkTextColor(Color.BLACK);
        reference.setText(getString(R.string.citation));
    }
}