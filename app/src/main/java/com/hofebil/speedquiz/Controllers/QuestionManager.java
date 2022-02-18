package com.hofebil.speedquiz.Controllers;

import com.hofebil.speedquiz.Models.Question;

import java.util.ArrayList;

public class QuestionManager {
    ArrayList<Question> myQuestion = new ArrayList<>();
    private int indexQuestion;
    private int nbQuestionPassed = 0;

    public void addQuestion() {
        myQuestion.add(new Question("1le chocolat peut posséder plusieurs couleurs", 1));
        myQuestion.add(new Question("2une couleur peut posséder plusieurs couleurs", 0));
        myQuestion.add(new Question("3les chats peuvent posséder plusieurs couleurs", 1));
        myQuestion.add(new Question("4l'air peut posséder plusieurs couleurs", 0));
        myQuestion.add(new Question("5une Toyota AE86 est disponible en plusieurs couleurs", 1));
    }

    public void setQuestion(String question, int reponse) {
        myQuestion.add(new Question(question, reponse));
    }

    public void rollQuestion() {
        nbQuestionPassed++;
        indexQuestion = (int) (Math.random()* myQuestion.size());
    }

    public Question getMyQuestion() {
        return myQuestion.get(indexQuestion);
    }

    public boolean allQuestionPassed() {
        return nbQuestionPassed >= myQuestion.size();
    }

    public void setNbQuestionPassed(int nbQuestionPassed) {
        this.nbQuestionPassed = nbQuestionPassed;
    }
}
