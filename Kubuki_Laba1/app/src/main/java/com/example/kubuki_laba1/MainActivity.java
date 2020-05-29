package com.example.kubuki_laba1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView playerTV;
    TextView phoneTV;
    TextView frstScrTV;
    TextView scndScrTV;
    Button btn;
    Random random = new Random();

    int yours = 0;
    int robots = 0;
    int first_rand;
    int second_rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerTV = findViewById(R.id.your_points);
        phoneTV = findViewById(R.id.robot_points);
        frstScrTV = findViewById(R.id.first_cube_points);
        scndScrTV = findViewById(R.id.second_cube_points);
        btn = findViewById(R.id.button);
    }

    public void onClick(View view) {
        if (!addUserPoints()) {
            btn.setText("Ход телефона");
            btn.setEnabled(false);
            btn.setBackgroundColor(Color.parseColor("#ff6e60"));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    boolean b = true;
                    while (b) {
                        b = addRobotPoints();
                    }
                    btn.setBackgroundColor(Color.parseColor("#ff6e60"));
                    btn.setText("Сделать ход");
                    btn.setEnabled(true);
                }
            }, 1000);
            if (robots >= 100) {
                Intent i2 = new Intent(this, LoseActivity.class);
                playerTV.setText("0");
                phoneTV.setText("0");
                frstScrTV.setText("0");
                scndScrTV.setText("0");
                yours = 0;
                robots = 0;
                first_rand = 0;
                second_rand = 0;
                startActivity(i2);
            }
        }
        if (yours >= 100) {
            Intent i = new Intent(this, WinActivity.class);
            playerTV.setText("0");
            phoneTV.setText("0");
            frstScrTV.setText("0");
            scndScrTV.setText("0");
            yours = 0;
            robots = 0;
            first_rand = 0;
            second_rand = 0;
            startActivity(i);
        }
    }

    public boolean addUserPoints() {
        first_rand = 1 + random.nextInt(6);
        frstScrTV.setText(Integer.toString(first_rand));
        yours += first_rand;
        second_rand = 1 + random.nextInt(6);
        scndScrTV.setText(Integer.toString(second_rand));
        yours += second_rand;
        playerTV.setText(Integer.toString(yours));
        return first_rand == second_rand;
    }

    public boolean addRobotPoints() {
        first_rand = 1 + random.nextInt(6);
        frstScrTV.setText(Integer.toString(first_rand));
        robots += first_rand;
        second_rand = 1 + random.nextInt(6);
        scndScrTV.setText(Integer.toString(second_rand));
        robots += second_rand;
        phoneTV.setText(Integer.toString(robots));
        return first_rand == second_rand;
    }
}
