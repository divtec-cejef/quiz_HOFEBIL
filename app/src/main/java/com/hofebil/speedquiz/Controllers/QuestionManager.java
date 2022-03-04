package com.hofebil.speedquiz.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hofebil.speedquiz.Models.Question;
import com.hofebil.speedquiz.Models.SpeedQuizSQLiteOpenHelper;

import java.util.ArrayList;

public class QuestionManager {
    ArrayList<Question> questionList = new ArrayList<>();
    ArrayList<Question> questionListCopy = new ArrayList<>();
    private int indexQuestion = 0;
    private Context c;

    public QuestionManager(Context context) {
        questionList = initQuestionList(context);
        questionListCopy = questionList;
    }

    private ArrayList<Question> initQuestionList(Context context) {
        c = context;
        ArrayList<Question> listQuestion = new ArrayList<>();
        SpeedQuizSQLiteOpenHelper helper = new SpeedQuizSQLiteOpenHelper(c);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true, "quiz", new String[]{"idQuiz", "question", "reponse"}, null, null, null, null, "idquiz", null);

        while (cursor.moveToNext()) {
            listQuestion.add(new Question(cursor));
        }
        cursor.close();
        db.close();

        return listQuestion;
    }

    public void setQuestion(String question, int reponse) {
        SpeedQuizSQLiteOpenHelper helper = new SpeedQuizSQLiteOpenHelper(c);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO quiz(question, reponse) VALUES(question, reponse)");
        questionList.add(new Question(question, reponse));
    }

    public void rollQuestion() {
        indexQuestion = (int) (Math.random() * questionListCopy.size());
    }

    public Question getQuestion() {
        Question question = questionListCopy.get(indexQuestion);
  //      Question question = questionList.get(indexQuestion);
        return question;
    }

    public void removeQuestion() {
        questionListCopy.remove(indexQuestion);
    }

    public boolean allQuestionPassed() {
        return questionListCopy.size() == 0;
    }

    public void restart() {
        questionListCopy = questionList;
    }
}
