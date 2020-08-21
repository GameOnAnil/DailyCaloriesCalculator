package com.gameonanil.dailycaloriescalculator.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "foods_table")
public class Food {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String foodName;
    private String foodAmount;
    private int foodCalories;

    public Food(String foodName,String foodAmount, int  foodCalories) {
        this.foodName = foodName;
        this.foodCalories = foodCalories;
        this.foodAmount = foodAmount;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodAmount() {
        return foodAmount;
    }

    public int getFoodCalories() {
        return foodCalories;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Food food = (Food) obj;
        return food.id == this.id ;
    }
}
