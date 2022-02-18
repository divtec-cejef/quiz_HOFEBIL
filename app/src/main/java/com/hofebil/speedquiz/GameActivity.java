package com.hofebil.speedquiz;

import android.app.Activity;
import android.os.Bundle;
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

    private QuestionManager myQuestion = new QuestionManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myQuestion.addQuestion();

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

            }
        });

        // commence le jeu
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DefilementQuestion monDefilement = new DefilementQuestion(question1, question2, secondeParQuestion, myQuestion);
                monDefilement.start();
                btStart.setVisibility(View.GONE);
            }
        });

        // bt retour au menu
        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void switchQuestion() {
        if (!myQuestion.allQuestionPassed()) {
            question1.setText(myQuestion.getMyQuestion().getQuestion());
        } else {
            finiLayout.setVisibility(View.VISIBLE);
        }
    }
}
