package com.hofebil.speedquiz.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hofebil.speedquiz.Models.Question;
import com.hofebil.speedquiz.Models.SpeedQuizSQLiteOpenHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * une classe qui controle les question
 */
public class QuestionManager {
    ArrayList<Question> questionList = new ArrayList<>();
    ArrayList<Question> questionListCopy = new ArrayList<>();
    private int indexQuestion = 0;
    private SpeedQuizSQLiteOpenHelper helper;

    /**
     * constructeur du controleur
     *
     * @param context le context de l'application
     */
    public QuestionManager(Context context) {
        questionList = initQuestionList(context);
        questionListCopy = (ArrayList<Question>) questionList.clone();
    }

    /**
     * initialise les question
     *
     * @param context le context de l'application
     * @return une liste de question
     */
    private ArrayList<Question> initQuestionList(Context context) {
        ArrayList<Question> listQuestion = new ArrayList<>();
         helper = new SpeedQuizSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true, "quiz", new String[]{"idQuiz", "question", "reponse"}, null, null, null, null, "idquiz", null);

        while (cursor.moveToNext()) {
            listQuestion.add(new Question(cursor));
        }
        cursor.close();
        db.close();

        return listQuestion;
    }

    /**
     * ajoute une question dans la base de donnée
     *
     * @param question la question
     * @param reponse  sa reponse
     */
    public void setQuestion(String question, int reponse) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO quiz(question, reponse) VALUES(question, reponse)");
        questionList.add(new Question(question, reponse));
        restart();
        db.close();
    }

    /**
     * change l'index de la question
     */
    public void rollQuestion() {
        indexQuestion = (int) (Math.random() * questionListCopy.size());
    }

    /**
     * obtien une question
     *
     * @return une question
     */
    public Question getQuestion() {
        Question question = questionListCopy.get(indexQuestion);
        //      Question question = questionList.get(indexQuestion);
        return question;
    }

    /**
     * efface une question
     */
    public void removeQuestion() {
        questionListCopy.remove(indexQuestion);
    }

    /**
     * test si la liste de question est vide
     *
     * @return vrai si oui, sinon faux
     */
    public boolean allQuestionPassed() {
        return questionListCopy.size() == 0;
    }

    /**
     * re charge la liste
     */
    public void restart() {
        questionListCopy = (ArrayList<Question>) questionList.clone();
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}


