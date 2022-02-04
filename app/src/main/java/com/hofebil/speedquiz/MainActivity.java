package com.hofebil.speedquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button bt_add_player;
    private EditText player1;
    private EditText player2;
    private TextInputLayout layoutPlayer1;
    private TextInputLayout layoutPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        bt_add_player = findViewById(R.id.main_add_button);
        player1 = findViewById(R.id.nameOfPlayer1);
        player2 = findViewById(R.id.nameOfPlayer2);
        layoutPlayer1 = findViewById(R.id.layoutPlayer1);
        layoutPlayer2 = findViewById(R.id.layoutPlayer2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        bt_add_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutPlayer1.getVisibility() == View.VISIBLE) {
                    layoutPlayer1.setVisibility(View.INVISIBLE);
                    layoutPlayer2.setVisibility(View.INVISIBLE);
                } else {
                    layoutPlayer1.setVisibility(View.VISIBLE);
                    layoutPlayer2.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_parameter:
                //TODO parametre / 1 option pour changer la vitesse des question et une autre pour changer theme nuit jour
                break;
            case R.id.action_question:
                //TODO visualitation des question et possibilité d'en crée
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}