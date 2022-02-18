package com.hofebil.speedquiz;

import android.widget.TextView;

import com.hofebil.speedquiz.Controllers.QuestionManager;

public class DefilementQuestion extends Thread {
    private TextView question1;
    private TextView question2;
    private int secondeParQuestion;
    private QuestionManager myQuestion;

    public DefilementQuestion(TextView question1, TextView question2, int secondeParQuestion, QuestionManager myQuestion) {
        this.question1 = question1;
        this.question2 = question2;
        this.secondeParQuestion = secondeParQuestion;
        this.myQuestion = myQuestion;
    }

    @Override
    public void run() {
        while(true) {
            question1.setText(myQuestion.getMyQuestion().getQuestion());
            question2.setText(myQuestion.getMyQuestion().getQuestion());
            try {
                Thread.sleep(1000L * secondeParQuestion);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
