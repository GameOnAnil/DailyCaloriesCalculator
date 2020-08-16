package com.gameonanil.dailycaloriescalculator.RoomDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.gameonanil.dailycaloriescalculator.Model.Food;

import java.util.List;

@Dao
public interface FoodDao {
    @Insert
    void insert(Food food);
    @Delete
    void delete(Food food);

    @Query("SELECT * FROM foods_table ORDER BY foodName ASC")
    LiveData<List<Food>> getAllFood();

    @Query("SELECT * FROM foods_table WHERE foodName LIKE :foodText Order by foodName ASC")
    LiveData<List<Food>> getFilteredFood(String foodText);
}
