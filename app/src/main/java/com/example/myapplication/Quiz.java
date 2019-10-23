package com.example.myapplication;


public class Quiz {
    String[] questions;
    String[] ModelAns1;
    String[] ModelAns2;
    String[] ModelAns3;

    public Quiz(String[] questions, String[] ModelAns1, String[] ModelAns2, String[] ModelAns3) {
        this.questions = questions;
        this.ModelAns1 = ModelAns1;
        this.ModelAns2 = ModelAns2;
        this.ModelAns3 = ModelAns3;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public String[] getModelAns2() {
        return ModelAns2;
    }

    public String[] getModelAns3() {
        return ModelAns3;
    }

    public void setModelAns1(String[] modelAns1) {
        this.ModelAns1 = modelAns1;
    }

    public String[] getQuestions() {
        return questions;
    }

    public String[] getModelAns1() {
        return ModelAns1;
    }

    public boolean checkAnswer(String ans, String QNum) {


        int qn = Integer.parseInt(QNum);
        if (ModelAns1[qn].matches(ans))
            return true;
        return false;
    }
}
