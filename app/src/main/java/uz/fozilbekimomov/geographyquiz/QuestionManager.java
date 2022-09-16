package uz.fozilbekimomov.geographyquiz;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionManager {
    private int currentPosition = 0;
    private ArrayList<QuestionData> data;
    private int totalQuestions = 0;
    private int totalTrues;
    private int totalFalse;

    public QuestionManager() {
    }

    public QuestionManager(ArrayList<QuestionData> data) {
        this.data = data;
        Collections.shuffle(this.data);
        totalQuestions = data.size();
    }

    public void setData(ArrayList<QuestionData> data) {
        this.data = data;
        Collections.shuffle(this.data);
        totalQuestions = data.size();
    }

    private QuestionData getCurrentQuestion() {
        return this.data.get(currentPosition);
    }

    public String getQuestion() {
        return getCurrentQuestion().getQuestion();
    }


    public String getVariantA() {
        return getCurrentQuestion().getAnswerA();
    }


    public String getVariantB() {
        return getCurrentQuestion().getAnswerB();
    }


    public String getVariantC() {
        return getCurrentQuestion().getAnswerC();
    }

    public boolean checkAnswer(String answer) {

        boolean isTrue = false;

        if (answer.equalsIgnoreCase(getCurrentQuestion().getAnswer())) {
            isTrue = true;
            totalTrues++;
        } else {
            isTrue = false;
            totalFalse++;
        }

        if (hasNextQuestion()) {
            currentPosition++;
        }

        return isTrue;

    }

    public int getTotalFalse() {
        return totalFalse;
    }

    public int getTotalTrues() {
        return totalTrues;
    }

    boolean isFinish() {
        return currentPosition  == totalQuestions;
    }

    public boolean hasNextQuestion() {
        return currentPosition  < totalQuestions;
    }

    public int getCurrentLevel() {
        return currentPosition + 1;
    }

    public int getTotal() {
        return data.size();
    }


}
