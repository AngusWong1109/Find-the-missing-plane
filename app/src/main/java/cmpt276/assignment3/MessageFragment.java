package cmpt276.assignment3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class MessageFragment extends AppCompatDialogFragment{

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Create the view to show
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.message_layout, null);

        //Create a button listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getActivity(), MenuScreen.class);
                startActivity(intent);
            }
        };

        //Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Game Over")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }
}
