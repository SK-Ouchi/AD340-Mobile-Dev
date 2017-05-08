package com.example.sko.wip;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by SKO on 5/4/2017.
 */

public class Assign4Dialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick an Animal");
        String[] animals = {"horse", "cow", "camel", "sheep", "goat"};
                builder.setItems(animals, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // horse
                            case 1: // cow
                            case 2: // camel
                            case 3: // sheep
                            case 4: // goat
                        }
                    }
                });
        return builder.create();
    }
}
