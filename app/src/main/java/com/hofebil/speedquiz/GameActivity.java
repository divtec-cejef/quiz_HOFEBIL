package com.hofebil.speedquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

public class GameActivity extends Activity {

    private String name1;
    private String name2;
    private int nombreSecondeQuestion;

    private TextView player1;
    private TextView player2;

    private boolean darkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();


        player1 = findViewById(R.id.game_name_player1);
        player2 = findViewById(R.id.game_name_player2);

        // on récupère les valeur
        name1 = intent.getStringExtra("player1");
        name2 = intent.getStringExtra("player2");
        nombreSecondeQuestion = intent.getExtras().getInt("nombreSecondeQuestion");
        darkMode = intent.getExtras().getBoolean("darkMode");

        player2.setText(name2);
        player1.setText(name1);

        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
