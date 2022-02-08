package com.hofebil.speedquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
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

    private Button bt_launch_play;
    private Button bt_add_player;
    private Button bt_apply_player;
    private EditText player1;
    private EditText player2;
    private TextInputLayout layoutPlayer1;
    private TextInputLayout layoutPlayer2;
    private View param_layout;

    private Button bt_paramCancel;
    private Button bt_paramApply;

    private String name1;
    private String name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        bt_paramCancel = findViewById(R.id.param_cancel_button);
        bt_paramApply = findViewById(R.id.param_apply_button);
        bt_launch_play = findViewById(R.id.main_play_button);
        bt_add_player = findViewById(R.id.main_player_button);
        bt_apply_player = findViewById(R.id.main_apply_button);
        player1 = findViewById(R.id.nameOfPlayer1);
        player2 = findViewById(R.id.nameOfPlayer2);
        layoutPlayer1 = findViewById(R.id.layoutPlayer1);
        layoutPlayer2 = findViewById(R.id.layoutPlayer2);
        param_layout = findViewById(R.id.main_param_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // ferme la page parametre
        bt_paramCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param_layout.setVisibility(View.GONE);
            }
        });

        // sauvegarde les parametre
        bt_paramApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param_layout.setVisibility(View.GONE);
            }
        });

        // sauvegarde des nom des joueur
        bt_apply_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name1 = player1.getText().toString();
                name2 = player2.getText().toString();
            }
        });

        // lancer la nouvelle activiy
        bt_launch_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ActivityIntent = new Intent(getApplicationContext(), GameActivity.class);
                ActivityIntent.putExtra("player1", name1);
                ActivityIntent.putExtra("player2", name2);
                startActivity(ActivityIntent);
            }
        });

        // afficher/déafficher les paramêtre joueur
        bt_add_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutPlayer1.getVisibility() == View.VISIBLE) {
                    bt_apply_player.setVisibility(View.INVISIBLE);
                    layoutPlayer1.setVisibility(View.INVISIBLE);
                    layoutPlayer2.setVisibility(View.INVISIBLE);
                } else {
                    bt_apply_player.setVisibility(View.VISIBLE);
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
                //TODO parametre / 1 option pour changer la vitesse des question et une autre pour changer theme nuit/jour
                // ouverture des parametre
                param_layout.setVisibility(View.VISIBLE);
            case R.id.action_question:
                //TODO visualitation des question et possibilité d'en crée
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}