package com.hofebil.speedquiz.Models;

import android.annotation.SuppressLint;
import android.database.Cursor;

/**
 * représente une question
 */
public class Question {
    private String question;
    private int reponse;

    /**
     * constructeur d'une question
     * @param question la question
     * @param reponse sa reponse
     */
    public Question(String question, int reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    /**
     * constructeur d'une question
     * @param cursor resultat d'une query
     */
    public Question(Cursor cursor) {
        question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        reponse = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }

    public String getQuestion() {
        return question;
    }

    public int getReponse() {
        return reponse;
    }
}
