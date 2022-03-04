package com.hofebil.speedquiz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import com.hofebil.speedquiz.Controllers.QuestionManager;

import java.util.concurrent.locks.LockSupport;

public class GameActivity extends Activity {

    private String name1;
    private String name2;
    private int secondeParQuestion;
    private String question;

    private TextView player1;
    private TextView player2;
    private boolean enTrainDeJouer = false;

    private boolean darkMode;

    private TextView tvScorePlayer1;
    private TextView tvScorePlayer2;
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;

    public TextView question1;
    public TextView question2;
    private Button btVrai2;
    private Button btVrai1;

    private View finiLayout;

    private Button btStart;
    private Button btMenu;
    private Button btAgain;

    private QuestionManager myQuestion;

    Runnable questionRunnable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myQuestion = new QuestionManager(GameActivity.this);

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
        tvScorePlayer1 = findViewById(R.id.game_score_player1);
        tvScorePlayer2 = findViewById(R.id.game_score_player2);

        // on récupère les valeur
        name1 = getIntent().getStringExtra("player1");
        name2 = getIntent().getStringExtra("player2");
        secondeParQuestion = getIntent().getExtras().getInt("nombreSecondeQuestion");
        darkMode = getIntent().getExtras().getBoolean("darkMode");

        btVrai1.setEnabled(false);
        btVrai2.setEnabled(false);

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

        //implementation du score
        btVrai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enTrainDeJouer) {
                    if (myQuestion.getQuestion().getReponse() == 1) {
                        scorePlayer2++;
                        tvScorePlayer2.setText(String.valueOf(scorePlayer2));
                        btVrai2.setEnabled(false);
                    }
                }
            }
        });

        // implementation du score
        btVrai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enTrainDeJouer) {
                    if (myQuestion.getQuestion().getReponse() == 1) {
                        scorePlayer1++;
                        tvScorePlayer1.setText(String.valueOf(scorePlayer1));
                        btVrai1.setEnabled(false);
                    }
                }
            }
        });

        // commence le jeu
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defilQuestion();
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

    /**
     * relance la partie
     */
    private void restartGame() {
        myQuestion.restart();
        scorePlayer1 = 0;
        scorePlayer2 = 0;
    }

    /**
     * lance la parti
     */
    public void defilQuestion() {
        Handler handler = new Handler();
        questionRunnable = new Runnable() {

            @Override
            public void run() {
                myQuestion.rollQuestion();
                if (myQuestion.allQuestionPassed()) {
                    question1.setText(" la parti est terminé");
                    question2.setText(" la parti est terminé");
                    enTrainDeJouer = false;

                    handler.removeCallbacks(this);
                    finiLayout.setVisibility(View.VISIBLE);
                } else {
                    // code du thread
                    question = myQuestion.getQuestion().getQuestion();
                    question1.setText(question);
                    question2.setText(question);
                    btVrai1.setEnabled(true);
                    btVrai2.setEnabled(true);

                    handler.postDelayed(this, 1000L * secondeParQuestion);
                    myQuestion.removeQuestion();
                }
            }
        };
        // lance le thread
        handler.postDelayed(questionRunnable, 500);
        enTrainDeJouer = true;
    }
}
