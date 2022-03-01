package com.hofebil.speedquiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import com.hofebil.speedquiz.Controllers.QuestionManager;

public class GameActivity extends Activity {

    private String name1;
    private String name2;
    private int secondeParQuestion;

    private TextView player1;
    private TextView player2;

    private boolean darkMode;

    public TextView question1;
    public TextView question2;
    private Button btVrai2;
    private Button btVrai1;

    private View finiLayout;

    private Button btStart;
    private Button btMenu;
    private Button btAgain;

    private QuestionManager myQuestion;

    Runnable questionRunnable=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myQuestion  = new QuestionManager(GameActivity.this);

        btStart = findViewById(R.id.start_bt);
        btMenu = findViewById(R.id.fini_cancel_bt);
        btAgain = findViewById(R.id.fini_again_bt);
        finiLayout = findViewById(R.id.fini_layout);
        btVrai2 = findViewById(R.id.game_bt_vrai_player2);
        btVrai1 = findViewById(R.id.game_bt_vrai_player1);
        player1 = findViewById(R.id.game_name_player1);
        player2 = findViewById(R.id.game_name_player2);
        question1 = findViewById(R.id.game_question_player1);
        question2 = findViewById(R.id.game_question_player2);

        // on récupère les valeur
        name1 = getIntent().getStringExtra("player1");
        name2 = getIntent().getStringExtra("player2");
        secondeParQuestion = getIntent().getExtras().getInt("nombreSecondeQuestion");
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

        //TODO implementation score
        btVrai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //TODO implementation score
        btVrai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myQuestion.getQuestion().getReponse() == 1) {
                    // ++
                }
            }
        });

        // commence le jeu
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defilQusetion();
                btStart.setVisibility(View.GONE);
            }
        });

        // bt retour au menu
        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
                finish();
            }
        });

        // bt retour au jeu
        btAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finiLayout.setVisibility(View.GONE);
                restartGame();
                btStart.setVisibility(View.VISIBLE);
            }
        });
    }

    private void restartGame() {
        myQuestion.setNbQuestionPassed(0);
    }

    public void defilQusetion() {

    Handler handler = new Handler();

    questionRunnable = new Runnable() {

        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            myQuestion.rollQuestion();
            if(myQuestion.allQuestionPassed()){
                question1.setText(" fini");
                question2.setText(" fini");

                handler.removeCallbacks(this);
                finiLayout.setVisibility(View.VISIBLE);
            }else{
                // code du thread
                question1.setText(myQuestion.getQuestion().getQuestion());
                question2.setText(myQuestion.getQuestion().getQuestion());

                handler.postDelayed(this,1000L * secondeParQuestion);
            }
        }
    };

    // lance le thread
    handler.postDelayed(questionRunnable,500);

    }
}
