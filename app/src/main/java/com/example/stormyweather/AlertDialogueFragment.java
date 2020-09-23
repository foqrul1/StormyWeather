package com.example.stormyweather;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AlertDialogueFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Oops! Sorry")
                .setMessage("There is an error! Please try Again")
                .setPositiveButton("OK", null);
         return builder.create();
        //return super.onCreateDialog(savedInstanceState);
    }
}
