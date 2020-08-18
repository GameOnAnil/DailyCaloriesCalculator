package com.gameonanil.dailycaloriescalculator.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.gameonanil.dailycaloriescalculator.Model.Food;
import com.gameonanil.dailycaloriescalculator.Repository.FoodRepository;
import com.gameonanil.dailycaloriescalculator.RoomDB.FoodDao;
import com.gameonanil.dailycaloriescalculator.RoomDB.FoodDatabase;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    FoodRepository foodRepository;
    public LiveData<PagedList<Food>> foodList;



    public FoodViewModel(@NonNull Application application) {
        super(application);
        foodRepository = new FoodRepository(application);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder()).setEnablePlaceholders(true)
                        .setPrefetchDistance(50)
                        .setPageSize(50).build();

        foodList = (new LivePagedListBuilder(foodRepository.getPagedListLiveData()
                , pagedListConfig))
                .build();

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

//    public LiveData<PagedList<Food>> getPagedFood(){
//        return foodRepository.getPagedListLiveData();
//    }

}
