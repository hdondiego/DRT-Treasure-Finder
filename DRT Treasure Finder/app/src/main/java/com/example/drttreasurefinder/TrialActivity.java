package com.example.drttreasurefinder;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class TrialActivity extends AppCompatActivity {
    private final static String TAG = TrialActivity.class.getSimpleName();

    private int difficulty;
    private int attempts = 0;
    private int easyAttempt = 0;
    private int medAttempt = 0;
    private int hardAttempt = 0;
    private Random rnd;

    private AlertDialog nextAD, completeAD;

    private LinearLayout dotLinearLayout;
    private ImageView dotImageView;
    private TextView redX, redY, blueX, blueY;
    private TextView txtEasy, txtMed, txtHard, txtOption;

    private String redXText, redYText, blueXText, blueYText, attemptText, attemptLog;
    private String easyText, medText, hardText;

    private int x,y;

    private boolean isTouched, diffGen, dotGen, complete;

    private int startX, startY;
    private int targetX, targetY, minRangeX, maxRangeX, minRangeY, maxRangeY;
    private float screenX, screenY;
    private int minX, maxX;
    private int minY, maxY;

    private float diffX, diffY;
    private int sumDifferential;
    private float easyX, easyY, mediumX, mediumY, hardX, hardY, distance;
    private float easyHyp, medHyp, hardHyp;
    private int xRange, yRange;

    private int minimumVal, maximumVal;
    private int attempt;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        intent = getIntent();
        screenX = intent.getFloatExtra("screenX", 0);
        screenY = intent.getFloatExtra("screenY", 0);

        // X boundary from fifth percent to the ninety-fifth percent of the screen
        minX = ((int)screenX / 20);
        maxX = ((int)(screenX * 17) / 20);

        minY = ((int)screenY / 20);
        maxY = ((int)(screenY * 17) / 20);

        easyX = maxX / 3;
        easyY = maxY / 3;
        easyHyp = (float) Math.hypot(easyX, easyY);

        mediumX = (maxX * 2) / 3;
        mediumY = (maxY * 2) / 3;
        medHyp = (float) Math.hypot(mediumX, mediumY);

        hardX = maxX;
        hardY = maxY;
        hardHyp = (float) Math.hypot(hardX, hardY);

        // The Alert message once the user stops touching the screen
        AlertDialog.Builder nextADBuilder = new AlertDialog.Builder(this);
        nextADBuilder.setPositiveButton(R.string.next, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AttemptCount();
                DotGenerate();
            }
        });
        nextADBuilder.setTitle(R.string.nextstage);
        nextADBuilder.setIcon(R.drawable.baseline_done_black_18dp);
        nextADBuilder.setCancelable(false);
        nextAD = nextADBuilder.create();

        // The Alert message once the user stops touching the screen
        AlertDialog.Builder completeADBuilder = new AlertDialog.Builder(this);
        completeADBuilder.setPositiveButton(R.string.finish, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        completeADBuilder.setTitle(R.string.stagescomplete);
        completeADBuilder.setIcon(R.drawable.baseline_star_rate_black_18dp);
        completeADBuilder.setCancelable(false);
        completeAD = completeADBuilder.create();

        dotLinearLayout = findViewById(R.id.trialLinearLayout);
        dotImageView = findViewById(R.id.dotImageView);
        redX = findViewById(R.id.redX);
        redY = findViewById(R.id.redY);
        blueX = findViewById(R.id.blueX);
        blueY = findViewById(R.id.blueY);
        txtEasy = findViewById(R.id.txtEasy);
        txtMed = findViewById(R.id.txtMed);
        txtHard = findViewById(R.id.txtHard);
        txtOption = findViewById(R.id.txtOption);

        rnd = new Random();

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.coin05);

        dotImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isTouched = true;
                //mediaPlayer.start();
                return false;
            }
        });

        dotLinearLayout.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        if (isTouched == true) {
                            x = (int)event.getX();
                            y = (int)event.getY();

                            Log.d(TAG, "Dot is moving");

                            redXText = Integer.toString(x);
                            redYText = Integer.toString(y);

                            redX.setText(redXText);
                            redY.setText(redYText);

                            dotImageView.setX(x);
                            dotImageView.setY(y);

                            diffX = Math.abs(targetX - x);
                            diffY = Math.abs(targetY - y);
                            //distance = Math.sqrt((int)diffX^2 + (int)diffY^2);
                            sumDifferential = Math.abs(targetX - x) + Math.abs(targetY - y);

                            /*
                            Relative to Samsung Galaxy Tab A
                            Pressing and holding will become an issue.
                            Although each device is different,
                            A person's touch to the screen will cause
                            The pixel detection value to fluctuate very quickly.

                            Instead, have a "coin collected" prompt
                            On the screen (if possible)
                             */

                            // Create a ratio, instead of the number 30
                            if(sumDifferential <= 30){
                                mediaPlayer.start();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (isTouched == true) {
                            if (attempts < 12) {
                                nextAD.show();
                            } else {
                                completeAD.show();
                            }
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        AttemptCount();
        DotGenerate();
    }


    public void AttemptCount() {
        diffGen = false;
        while (!diffGen) {
            difficulty = rnd.nextInt(3);

            switch (difficulty) {
                case 0:
                    if (easyAttempt < 4) {
                        Log.d(TAG, "Easy");
                        easyAttempt += 1;
                        xRange = (int) easyX;
                        yRange = (int) easyY;
                        diffGen = true;
                    } else {
                        continue;
                    }
                    break;
                case 1:
                    if (medAttempt < 4) {
                        Log.d(TAG, "Medium");
                        medAttempt += 1;
                        xRange = (int) mediumX;
                        yRange = (int) mediumY;
                        diffGen = true;
                    } else {
                        continue;
                    }
                    break;
                case 2:
                    if (hardAttempt < 4) {
                        Log.d(TAG, "Hard");
                        hardAttempt += 1;
                        xRange = (int) hardX;
                        yRange = (int) hardY;
                        diffGen = true;
                    } else {
                        continue;
                    }
                    break;
                default:
                    break;
            }
        }

        easyText = Integer.toString(easyAttempt);
        medText = Integer.toString(medAttempt);
        hardText = Integer.toString(hardAttempt);

        txtEasy.setText(easyText);
        txtMed.setText(medText);
        txtHard.setText(hardText);

        attempts = easyAttempt + medAttempt + hardAttempt;
    }

    /*Create checker to see if the point surpasses the allowed boundary*/
    public void DotGenerate() {
        attempt = 0;
        isTouched = false;
        dotGen = false;
        complete = false;

        while (!dotGen) {
            startX = rnd.nextInt(maxX) + minX;
            startY = rnd.nextInt(maxY) + minY;

            // Already adjusted to work with any difficulty
            // MaximumVal is already set from AttemptCount()
            minRangeX = startX - xRange;
            maxRangeX = startX + xRange;

            minRangeY = startY - yRange;
            maxRangeY = startY + yRange;

            targetX = rnd.nextInt(maxRangeX - minRangeX) + minRangeX;
            targetY = rnd.nextInt(maxRangeY - minRangeY) + minRangeY;

            // This will determine if the generated points
            // satisfy the requirement for the appropriate difficulty
            diffX = targetX - startX;
            diffY = targetY - startY;

            if ((diffX < 0) || (diffY < 0)) {
                attempt++;
                continue;
            }

            if ((targetX > screenX) || (targetY > screenY)) {
                attempt++;
                continue;
            }

            distance = (float) Math.hypot(diffX, diffY);

            // 1. Checks to see if the target point is within the usable parts of the screen
            // 2. Checks to see if it matches to the appropriate distance for difficulty
            switch (difficulty) {
                case 0:
                    // Easy
                    if (distance <= easyHyp) {
                        dotGen = true;
                    } else {
                        attempt++;
                        continue;
                    }
                    break;
                case 1:
                    // Medium
                    if ((distance > easyHyp) && (distance <= medHyp)) {
                        dotGen = true;
                    } else {
                        attempt++;
                        continue;
                    }
                    break;
                case 2:
                    // Hard
                    if ((distance > medHyp) && (distance <= hardHyp)) {
                        dotGen = true;
                    } else {
                        attempt++;
                        continue;
                    }
                    break;
                default:
                    break;
            }
            attemptText = Integer.toString(attempt);
            Log.d(TAG, "Attempt: " + attemptText);
        }

        redXText = Integer.toString(startX);
        redYText = Integer.toString(startY);
        redX.setText(redXText);
        redY.setText(redYText);

        blueXText = Integer.toString(targetX);
        blueYText = Integer.toString(targetY);
        blueX.setText(blueXText);
        blueY.setText(blueYText);

        dotImageView.setX((float)startX);
        dotImageView.setY((float)startY);
        dotImageView.setVisibility(View.VISIBLE);
    }
}