package com.hofebil.speedquiz.Models;

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

    public String getQuestion() {
        return question;
    }

    public int getReponse() {
        return reponse;
    }
}
