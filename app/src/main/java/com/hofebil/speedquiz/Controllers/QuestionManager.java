package com.hofebil.speedquiz.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hofebil.speedquiz.Models.Question;
import com.hofebil.speedquiz.Models.SpeedQuizSQLiteOpenHelper;

import java.util.ArrayList;

public class QuestionManager {
    ArrayList<Question> questionList = new ArrayList<>();
    private int indexQuestion;
    private int nbQuestionPassed = 0;

    public QuestionManager(Context context) {
        questionList = initQuestionList(context);
    }

    private ArrayList<Question> initQuestionList(Context context) {
        ArrayList<Question> listQuestion = new ArrayList<>();
        SpeedQuizSQLiteOpenHelper helper = new SpeedQuizSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true, "quiz", new String[]{"idQuiz", "question", "reponse"}, null, null, null, null, "idQuiz", null);

        while(cursor.moveToNext()) {
            listQuestion.add(new Question(cursor));
        }
        cursor.close();
        db.close();

        return listQuestion;
    }

    public void setQuestion(String question, int reponse) {
        questionList.add(new Question(question, reponse));
    }

    public void rollQuestion() {
        nbQuestionPassed++;
        indexQuestion = (int) (Math.random()* questionList.size());
    }

    public Question getQuestionList() {
        return questionList.get(indexQuestion);
    }

    public boolean allQuestionPassed() {
        return nbQuestionPassed > questionList.size();
    }

    public void setNbQuestionPassed(int nbQuestionPassed) {
        this.nbQuestionPassed = nbQuestionPassed;
    }
}
