package com.example.capstone.API;
import com.google.gson.annotations.SerializedName;
public class Predict {
    @SerializedName("fruit")
    private String fruit;

    @SerializedName("predict")
    private String predict;

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getPredict() {
        return predict;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }
}
