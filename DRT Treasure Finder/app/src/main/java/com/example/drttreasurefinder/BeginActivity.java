package com.example.drttreasurefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BeginActivity extends AppCompatActivity {
    private float screenX, screenY;
    private Button beginButton;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        intent = getIntent();
        screenX = intent.getFloatExtra("screenX", 0);
        screenY = intent.getFloatExtra("screenY", 0);

        beginButton = findViewById(R.id.beginButton);
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trialIntent = new Intent(BeginActivity.this, TrialActivity.class);
                trialIntent.putExtra("screenX", screenX);
                trialIntent.putExtra("screenY", screenY);
                startActivity(trialIntent);
            }
        });
    }
}
