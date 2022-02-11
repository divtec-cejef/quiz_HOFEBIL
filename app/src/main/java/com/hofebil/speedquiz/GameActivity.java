package com.hofebil.speedquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

public class GameActivity extends Activity {

    private String name1;
    private String name2;
    private int nombreSecondeQuestion;

    private TextView player1;
    private TextView player2;

    private boolean darkMode;

    private Button btVrai2;
    private Button btVrai1;

    private View finiLayout;

    private Button btMenu;
    private Button btAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btMenu = findViewById(R.id.fini_cancel_bt);
        btAgain = findViewById(R.id.fini_again_bt);
        finiLayout = findViewById(R.id.fini_layout);
        btVrai2 = findViewById(R.id.game_bt_vrai_player2);
        btVrai1 = findViewById(R.id.game_bt_vrai_player1);
        player1 = findViewById(R.id.game_name_player1);
        player2 = findViewById(R.id.game_name_player2);

        // on récupère les valeur
        name1 = getIntent().getStringExtra("player1");
        name2 = getIntent().getStringExtra("player2");
        nombreSecondeQuestion = getIntent().getExtras().getInt("nombreSecondeQuestion");
        darkMode = getIntent().getExtras().getBoolean("darkMode");

        player2.setText(name2);
        player1.setText(name1);

        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // bt test menu fin de jeu
        btVrai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finiLayout.setVisibility(View.VISIBLE);
            }
        });

        // bt retour au menu
        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });

        // bt retour au jeu
        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finiLayout.setVisibility(View.GONE);
            }
        });
    }
}
