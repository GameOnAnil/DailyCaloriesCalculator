package com.gameonanil.dailycaloriescalculator.Model;

public class LunchFood {
    private String foodName;
    private int foodCalorie;
    private String foodAmount;
    private int foodQuantity;

    public LunchFood(String foodName, int foodCalorie, String foodAmount, int foodQuantity) {
        this.foodName = foodName;
        this.foodCalorie = foodCalorie;
        this.foodAmount = foodAmount;
        this.foodQuantity = foodQuantity;
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

    public String getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(String foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }
}
