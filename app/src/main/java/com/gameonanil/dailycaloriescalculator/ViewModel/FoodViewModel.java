package com.gameonanil.dailycaloriescalculator.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gameonanil.dailycaloriescalculator.Model.Food;
import com.gameonanil.dailycaloriescalculator.Repository.FoodRepository;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    FoodRepository foodRepository;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        foodRepository = new FoodRepository(application);
    }

    public void insert(Food food){
        foodRepository.insert(food);
    }

    public void delete(Food food){
        foodRepository.delete(food);
    }

    public LiveData<List<Food>> getAllFood(){
        return  foodRepository.getAllFood();
    }

    public LiveData<List<Food>> getFilteredFood(String query){
        return  foodRepository.getFilteredFood(query);
    }
}
