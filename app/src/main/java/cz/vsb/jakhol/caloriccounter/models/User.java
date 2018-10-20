package cz.vsb.jakhol.caloriccounter.models;

import cz.vsb.jakhol.caloriccounter.models.enums.GoalState;

import java.util.Date;
import java.util.List;

public class User {

    private String nickname;

    private double weight;
    private double goalWeight;
    private GoalState goalState;

    private List<WeightStatistics> history;


    public User(String nickname, double weight, double goalWeight, List<WeightStatistics> history, GoalState goalState) {
        this.nickname = nickname;
        this.weight = weight;
        this.goalWeight = goalWeight;
        this.history = history;
        this.goalState = goalState;

    }

    public GoalState getGoalState() {
        return goalState;
    }

    public void setGoalState(GoalState goalState) {
        this.goalState = goalState;
    }

    public List<WeightStatistics> getHistory() {
        return history;
    }

    public void setHistory(List<WeightStatistics> history) {
        this.history = history;
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

    public void changeWeight(double weight) {
        List<WeightStatistics> historyList = getHistory();
        WeightStatistics newInfo = new WeightStatistics(new Date(), weight);
        historyList.add(newInfo);
        setWeight(weight);
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }
}
