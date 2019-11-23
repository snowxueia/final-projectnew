package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPageActivity extends AppCompatActivity {

    private Button bDailyTask;
    private Button bReview;
    private Button bGame;
    private Button bCheckin;
    private Button bSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        bDailyTask = findViewById(R.id.dailyTask);
        bReview = findViewById(R.id.review);
        bGame = findViewById(R.id.game);
        bCheckin = findViewById(R.id.checkIn);
        bSetting = findViewById(R.id.setting);

        bCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalenderActivity();
            }
        });

        bGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();
            }
        });

        bDailyTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDailyTaskActivity();
            }
        });

        bReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReviewActivity();
            }
        });


    }

    private void openCalenderActivity() {
        Intent intent = new Intent(this, CalenderActivity.class);
        startActivity(intent);
    }

    private void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void openReviewActivity() {
        Intent intent = new Intent(this, ReviewActivity.class);
        startActivity(intent);
    }

    private void openDailyTaskActivity() {
        Intent intent = new Intent(this, DailyTaskActivity.class);
        startActivity(intent);
    }
}
