package com.gameonanil.dailycaloriescalculator.Model;

public class DinnerFood {
    private String foodName;
    private int foodCalorie;

    public DinnerFood(String foodName, int foodCalorie) {
        this.foodName = foodName;
        this.foodCalorie = foodCalorie;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodCalorie() {
        return foodCalorie;
    }

    public void setFoodCalorie(int foodCalorie) {
        this.foodCalorie = foodCalorie;
    }
}