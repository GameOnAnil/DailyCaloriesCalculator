package com.gameonanil.dailycaloriescalculator.RoomDB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.gameonanil.dailycaloriescalculator.Model.Food;

@Database(entities = Food.class,version = 1)
public abstract class FoodDatabase extends RoomDatabase {
   public abstract FoodDao foodDao();

   public static FoodDatabase instance;

   public static synchronized FoodDatabase getInstance(Context context){
       if(instance == null){
        instance = Room.databaseBuilder(context.getApplicationContext(),FoodDatabase.class,"food_db")
                   .fallbackToDestructiveMigration()
                .addCallback(callback)
                   .build();

       }
       return instance;
   }


   // To populate the db with initial data

   private static  RoomDatabase.Callback callback = new RoomDatabase.Callback(){
       @Override
       public void onCreate(@NonNull SupportSQLiteDatabase db) {
           super.onCreate(db);
           new PopulateDBAysncTask(instance).execute();
       }
   };

    public static class PopulateDBAysncTask extends AsyncTask<Void,Void,Void>{
        private FoodDao foodDao;

        public PopulateDBAysncTask(FoodDatabase db) {
            foodDao = db.foodDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            foodDao.insert(new Food("Rice",400));
            foodDao.insert(new Food("Orange Juice",120));
            foodDao.insert(new Food("Cereal",150));
            foodDao.insert(new Food("Apple",80));
            foodDao.insert(new Food("Egg Scrambled",91));
            foodDao.insert(new Food("Egg Boiled",78));
            foodDao.insert(new Food("Rice",400));
            foodDao.insert(new Food("Orange Juice",120));
            foodDao.insert(new Food("Cereal",150));
            foodDao.insert(new Food("Apple",80));
            foodDao.insert(new Food("Egg Scrambled",91));
            foodDao.insert(new Food("Egg Boiled",78));
            foodDao.insert(new Food("Rice",400));
            foodDao.insert(new Food("Orange Juice",120));
            foodDao.insert(new Food("Cereal",150));
            foodDao.insert(new Food("Apple",80));
            foodDao.insert(new Food("Egg Scrambled",91));
            foodDao.insert(new Food("Egg Boiled",78));
            foodDao.insert(new Food("Rice",400));
            foodDao.insert(new Food("Orange Juice",120));
            foodDao.insert(new Food("Cereal",150));
            foodDao.insert(new Food("Apple",80));
            foodDao.insert(new Food("Egg Scrambled",91));
            foodDao.insert(new Food("Egg Boiled",78));
            foodDao.insert(new Food("Rice",400));
            foodDao.insert(new Food("Orange Juice",120));
            foodDao.insert(new Food("Cereal",150));
            foodDao.insert(new Food("Apple",80));
            foodDao.insert(new Food("Egg Scrambled",91));
            foodDao.insert(new Food("Egg Boiled",78));
            foodDao.insert(new Food("Rice",400));
            foodDao.insert(new Food("Orange Juice",120));
            foodDao.insert(new Food("Cereal",150));
            foodDao.insert(new Food("Apple",80));
            foodDao.insert(new Food("Egg Scrambled",91));
            foodDao.insert(new Food("Egg Boiled",78));
            return null;
        }
    }

}
