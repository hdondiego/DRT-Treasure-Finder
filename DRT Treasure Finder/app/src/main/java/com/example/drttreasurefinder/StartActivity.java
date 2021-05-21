package com.example.drttreasurefinder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class StartActivity extends AppCompatActivity {
    private Button addNewProfile;
    private EditText profileNameEditText;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;

    private float screenX, screenY;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        intent = getIntent();
        screenX = intent.getFloatExtra("screenX", 0);
        screenY = intent.getFloatExtra("screenY", 0);

        addNewProfile = findViewById(R.id.addNewProfile);
        addNewProfile.setOnClickListener(new NewProfileClickListener());

        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent beginIntent = new Intent(StartActivity.this, BeginActivity.class);
                beginIntent.putExtra("screenX", screenX);
                beginIntent.putExtra("screenY", screenY);
                startActivity(beginIntent);
            }
        });
        alertDialogBuilder.setTitle(R.string.createprofile);
        profileNameEditText = new EditText(this);
        alertDialogBuilder.setView(profileNameEditText);
        alertDialog = alertDialogBuilder.create();
    }

    public class NewProfileClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            alertDialog.show();
        }
    }
}
