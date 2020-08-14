package com.gameonanil.dailycaloriescalculator.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.gameonanil.dailycaloriescalculator.Model.Food;
import com.gameonanil.dailycaloriescalculator.RoomDB.FoodDao;
import com.gameonanil.dailycaloriescalculator.RoomDB.FoodDatabase;

import java.util.List;

public class FoodRepository {
    FoodDao foodDao;

   public FoodRepository(Application application){
      FoodDatabase db = FoodDatabase.getInstance(application);
      foodDao = db.foodDao();
   }

   public void insert(Food food){
       new InsertAsyncTask(foodDao).execute(food);
   }

    public void delete(Food food){
        new DeleteAsyncTask(foodDao).execute(food);
    }

   public LiveData<List<Food>> getAllFood(){
     return   foodDao.getAllFood();
   }

   public class InsertAsyncTask extends AsyncTask<Food,Void,Void>{
    private FoodDao foodDao;

       public InsertAsyncTask(FoodDao foodDao) {
           this.foodDao = foodDao;
       }

       @Override
       protected Void doInBackground(Food... foods) {
           foodDao.insert(foods[0]);
           return null;
       }
   }

    public class DeleteAsyncTask extends AsyncTask<Food,Void,Void>{
        private FoodDao foodDao;

        public DeleteAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Food... foods) {
            foodDao.delete(foods[0]);
            return null;
        }
    }

}
