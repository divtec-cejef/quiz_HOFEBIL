package com.hofebil.speedquiz.Models;

import android.annotation.SuppressLint;
import android.database.Cursor;

/**
 * représente une question
 */
public class Question {
    private String question;
    private int reponse;

    public Question(String question, int reponse) {
        this.question = question;
        this.reponse = reponse;
    }

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
