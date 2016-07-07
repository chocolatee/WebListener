package com.weblistener.entity;

/**
 * Created by windylee on 6/23/2016.
 */
public class Student {

    private String username;

    private String password;

    private int grade;

    private int mClass;

    private String realName;

    private String stuNum;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getmClass() {
        return mClass;
    }

    public void setmClass(int mClass) {
        this.mClass = mClass;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", grade=" + grade +
                ", mClass=" + mClass +
                ", realName='" + realName + '\'' +
                ", stuNum='" + stuNum + '\'' +
                '}';
    }
}
