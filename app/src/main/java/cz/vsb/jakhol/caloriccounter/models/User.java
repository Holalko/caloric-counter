package cz.vsb.jakhol.caloriccounter.models;

import cz.vsb.jakhol.caloriccounter.models.enums.GoalState;


public final class User {

    private String nickname;

    private double weight;
    private double heightInCm;
    private int age;

    private double goalWeight;
    private GoalState goalState;


    public User(String nickname, double weight, double goalWeight, double heightInCm, int age, GoalState goalState) {
        this.nickname = nickname;
        this.weight = weight;
        this.goalWeight = goalWeight;
        this.goalState = goalState;
        this.age = age;
        this.heightInCm = heightInCm;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(double heightInCm) {
        this.heightInCm = heightInCm;
    }

    public GoalState getGoalState() {
        return goalState;
    }

    public void setGoalState(GoalState goalState) {
        this.goalState = goalState;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }
}
