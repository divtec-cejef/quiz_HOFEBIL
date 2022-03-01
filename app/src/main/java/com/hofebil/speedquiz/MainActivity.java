package com.hofebil.speedquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.hofebil.speedquiz.Controllers.QuestionManager;
import com.hofebil.speedquiz.Models.SpeedQuizSQLiteOpenHelper;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private QuestionManager myQuestion = new QuestionManager(getApplicationContext());

    private View layout;
    private EditText player1;
    private EditText player2;
    private TextInputLayout layoutPlayer1;
    private TextInputLayout layoutPlayer2;

    private RadioButton rbVrai;
    private EditText question_text;
    private View question_layout;
    private Button question_cancel;
    private Button question_apply;
    private View param_layout;
    private SwitchCompat sw_dayNight;
    private Button bt_paramApply;

    private Button bt_launch_play;
    private Button bt_add_player;
    private Button bt_apply_player;

    private Button bt_paramCancel;
    private Slider nbSecond;

    private String name1;
    private String name2;

    private int nombreSecondeQuestion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        nbSecond = findViewById(R.id.param_s_slide);
        sw_dayNight = findViewById(R.id.param_dayNight_switch);
        bt_paramCancel = findViewById(R.id.param_cancel_button);
        bt_paramApply = findViewById(R.id.param_apply_button);
        question_cancel = findViewById(R.id.question_cancel_button);
        question_apply = findViewById(R.id.question_apply_button);
        question_text = findViewById(R.id.question_text_et);
        rbVrai = findViewById(R.id.rb_vrai);

        bt_launch_play = findViewById(R.id.main_play_button);
        bt_add_player = findViewById(R.id.main_player_button);
        bt_apply_player = findViewById(R.id.main_apply_button);
        player1 = findViewById(R.id.nameOfPlayer1);
        player2 = findViewById(R.id.nameOfPlayer2);
        layoutPlayer1 = findViewById(R.id.layoutPlayer1);
        layoutPlayer2 = findViewById(R.id.layoutPlayer2);
        param_layout = findViewById(R.id.main_param_layout);
        question_layout = findViewById(R.id.main_question_layout);
        layout = findViewById(R.id.main_layout);



        // applique les parametre de l'appareil a l'application
        sw_dayNight.setChecked(AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO);
        changeMode();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // bouton qui applique les parametre
        bt_paramApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreSecondeQuestion = (int) nbSecond.getValue();
                changeMode();

            }
        });

        // bouton qui applique les question
        question_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!question_text.getText().toString().equals("")) {
                    //TODO ajouter question
                    // myQuestion.setQuestion(question_text.getText().toString(), rbVrai.isChecked() ? 1:0);
                    afficheSnakBar(R.string.QValidate);
                } else {
                    afficheSnakBar(R.string.QNValidate);
                }
            }
        });

        // ferme la page parametre
        bt_paramCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param_layout.setVisibility(View.GONE);
            }
        });

        // ferme la page question
        question_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question_layout.setVisibility(View.GONE);
            }
        });

        // sauvegarde des nom des joueur
        bt_apply_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (player2.getText().toString().equals("") && player1.getText().toString().equals("")) {
                    afficheSnakBar(R.string.errorNoPlayer);
               } else {
                   name1 = player1.getText().toString();
                   name2 = player2.getText().toString();
                   afficheSnakBar(R.string.applyName);
               }

            }
        });

        // lancer la nouvelle activiy
        bt_launch_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name1 == null && name2 == null) {
                    afficheSnakBar(R.string.errorNoPlayer);
                } else {
                    Intent ActivityIntent = new Intent(getApplicationContext(), GameActivity.class);
                    ActivityIntent.putExtra("player1", name1);
                    ActivityIntent.putExtra("player2", name2);
                    ActivityIntent.putExtra("nombreSecondeQuestion", nombreSecondeQuestion);
                    ActivityIntent.putExtra("darkMode", sw_dayNight.isChecked());
                    startActivity(ActivityIntent);
                }
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
                // ouverture des parametre
                param_layout.setVisibility(View.VISIBLE);
                break;
            case R.id.action_question:
                // ouverture des question
                question_layout.setVisibility(View.VISIBLE);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * change le mode d'affichage / jour nuit
     */
    private void changeMode() {
        if (sw_dayNight.isChecked()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * affiche une snackbar
     * @param message le message a mettre dans la snackBar
     */
    private void afficheSnakBar(int message) {
        Snackbar snack = Snackbar.make(layout,message, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        snack.show();
    }
}