package cz.vsb.jakhol.caloriccounter.models;

import java.util.Date;

public class WeightStatistics {

    private Date date;
    private double weight;

    public WeightStatistics(Date date, double weight) {

        this.date = date;
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }



}
