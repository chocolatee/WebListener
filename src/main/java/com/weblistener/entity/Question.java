package com.weblistener.entity;

/**
 * Created by windylee on 2016/5/19.
 */
public class Question {

    private String queTopic;

    private String ansA;

    private String ansB;

    private String ansC;

    private String ansD;

    private String ansRight;

    private int testId;

    public String getQueTopic() {
        return queTopic;
    }

    public void setQueTopic(String queTopic) {
        this.queTopic = queTopic;
    }

    public String getAnsA() {
        return ansA;
    }

    public void setAnsA(String ansA) {
        this.ansA = ansA;
    }

    public String getAnsB() {
        return ansB;
    }

    public void setAnsB(String ansB) {
        this.ansB = ansB;
    }

    public String getAnsC() {
        return ansC;
    }

    public void setAnsC(String ansC) {
        this.ansC = ansC;
    }

    public String getAnsD() {
        return ansD;
    }

    public void setAnsD(String ansD) {
        this.ansD = ansD;
    }

    public String getAnsRight() {
        return ansRight;
    }

    public void setAnsRight(String ansRight) {
        this.ansRight = ansRight;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "queTopic='" + queTopic + '\'' +
                ", ansA='" + ansA + '\'' +
                ", ansB='" + ansB + '\'' +
                ", ansC='" + ansC + '\'' +
                ", ansD='" + ansD + '\'' +
                ", ansRight='" + ansRight + '\'' +
                ", testId=" + testId +
                '}';
    }
}
