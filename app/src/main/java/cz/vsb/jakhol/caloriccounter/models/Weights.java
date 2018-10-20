package cz.vsb.jakhol.caloriccounter.models;

public class Weights {

    private String name;
    private Integer grams;

    public Weights(String name, Integer grams) {
        this.name = name;
        this.grams = grams;
    }

    public Weights(Integer grams) {
        this.grams = grams;
        this.name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrams() {
        return grams;
    }

    public void setGrams(Integer grams) {
        this.grams = grams;
    }
}
