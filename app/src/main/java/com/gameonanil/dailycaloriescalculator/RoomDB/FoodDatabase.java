package com.gameonanil.dailycaloriescalculator.RoomDB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.gameonanil.dailycaloriescalculator.Model.Food;

@Database(entities = Food.class,version = 2)
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
            foodDao.insert(new Food("Apple, medium","1",72));
            foodDao.insert(new Food("Apple juice","1 cup",111));
            foodDao.insert(new Food("Bagel: 289","1",105));
            foodDao.insert(new Food("Banana, medium","Single",105));
            foodDao.insert(new Food("Beer (regular)","12 ounces",153));
            foodDao.insert(new Food("Bread (wheat or white","1 slice",66));
            foodDao.insert(new Food("Butter (salted,)","1 tablespoon",102));
            foodDao.insert(new Food("Carrots (raw)","1 cup",52));
            foodDao.insert(new Food("Cheddar cheese ","1 slice",113));
            foodDao.insert(new Food("Chicken breast (boneless, skinless, roasted)","3 ounces",142));
            foodDao.insert(new Food("Chili with beans (canned, 1 cup)","1 cup",287));
            foodDao.insert(new Food("Chocolate chip cookie (from packaged dough)","1 pack",59));
            foodDao.insert(new Food("Coffee (Black)","8 ounch/ 240ml",2));
            foodDao.insert(new Food("Cola ","12 ounces",136));
            foodDao.insert(new Food("Corn (sweet yellow whole kernel, drained)","1 cup",180));
            foodDao.insert(new Food("Egg (large, scrambled)","1 Large",91));
            foodDao.insert(new Food("Egg (Boiled)","1",48));
            foodDao.insert(new Food("Egg(Whole, fried)","1",90));
            foodDao.insert(new Food("Graham cracker (plain, honey, or cinnamon)","1 packet",59));
            foodDao.insert(new Food("Granola bar (chewy, with raisins)","1.5 ounces bar",193));
            foodDao.insert(new Food("Grapes ","1 cup",114));
            foodDao.insert(new Food("Grape Juice ","1 cup",96));
            foodDao.insert(new Food("Green beans (canned, drained)","1 cup",40));
            foodDao.insert(new Food("Ground beef patty (15 percent fat, pan-broiled)","4 ounces",193));
            foodDao.insert(new Food("Hot dog (beef and pork)","1",137));
            foodDao.insert(new Food("Ice cream (vanilla)","4 ounces",145));
            foodDao.insert(new Food("Jelly doughnut","1",289));
            foodDao.insert(new Food("Ketchup","1 tablespoon",15));
            foodDao.insert(new Food("Milk (1 % milk fat)","1cup(244g)",103));
            foodDao.insert(new Food("Mixed nuts (dry roasted, with peanuts, salted)","1 ounces",168));
            foodDao.insert(new Food("Mustard, yellow ","2 teaspoon",6));
            foodDao.insert(new Food("Oatmeal (plain, cooked in water without salt)","8 ounces",112));
            foodDao.insert(new Food("Orange","1",65));
            foodDao.insert(new Food("Orange juice (frozen concentrate, made with water)","8 ounces",112));
            foodDao.insert(new Food("Peanut butter (creamy)","2 tablespoon",180));
            foodDao.insert(new Food("Pizza (pepperoni, regular crust)","1 slice",298));
            foodDao.insert(new Food("Pear","1",98));
            foodDao.insert(new Food("Pineapple","1 cup",77));
            foodDao.insert(new Food("Pork chop (center rib, boneless, broiled)","3 ounces",215));
            foodDao.insert(new Food("Rice (white,cooked) ","1 cup",223));
            foodDao.insert(new Food("Rice (brown,cooked) ","1 cup",232));
            foodDao.insert(new Food("Rice (white, long grain, cooked)","1 cup",205));
            foodDao.insert(new Food("Salsa","4 ounces",35));
            foodDao.insert(new Food("Shrimp (cooked under moist heat)","3 ounces",84));
            foodDao.insert(new Food("Spaghetti (cooked, enriched, without added salt)","1 cup",221));
            foodDao.insert(new Food("Spaghetti sauce (marinara, ready to serve)","4 ounces",92));
            foodDao.insert(new Food("Tuna (light, canned in water, drained)","3 ounces",100));
            foodDao.insert(new Food("White wine ","750 gm",622));
            foodDao.insert(new Food("Yogurt (frozen, low fat)","1 cup",220));



            return null;
        }
    }

}
