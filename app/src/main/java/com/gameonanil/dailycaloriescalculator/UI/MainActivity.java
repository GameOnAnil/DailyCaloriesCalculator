package com.gameonanil.dailycaloriescalculator.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gameonanil.dailycaloriescalculator.Adapters.BreakfastAdapter;
import com.gameonanil.dailycaloriescalculator.Adapters.DinnerAdapter;
import com.gameonanil.dailycaloriescalculator.Adapters.LunchAdapter;
import com.gameonanil.dailycaloriescalculator.Model.BreakfastFood;
import com.gameonanil.dailycaloriescalculator.Model.DinnerFood;
import com.gameonanil.dailycaloriescalculator.Model.Food;
import com.gameonanil.dailycaloriescalculator.Model.LunchFood;
import com.gameonanil.dailycaloriescalculator.R;
import com.gameonanil.dailycaloriescalculator.ViewModel.FoodViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button mAddBreakfast;
    private Button mAddLunch;
    private Button mAddDinner;

    private FrameLayout mAddBreakfastFrame;
    private FrameLayout mAddLunchFrame;
    private FrameLayout mAddDinnerFrame;

    private Button mCalculate;
    private RecyclerView mBfRecyclerView;
    private RecyclerView mLuRecyclerView;
    private RecyclerView mDiRecyclerView;

    private BreakfastAdapter mBreakfastAdapter;
    private LunchAdapter mLunchAdapter;
    private DinnerAdapter mDinnerAdapter;
    private ArrayList<BreakfastFood> breakfastFoods;
    private ArrayList<LunchFood> lunchFoods;
    private ArrayList<DinnerFood> dinnerFoods;

    private static String BREAKFAST_TYPE = "breakfast";
    private static String LUNCH_TYPE = "lunch";
    private static String DINNER_TYPE = "dinner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        mAddBreakfastFrame = findViewById(R.id.btn_add_breakfast_frame);
        mAddLunchFrame = findViewById(R.id.btn_add_lunch_frame);
        mAddDinnerFrame = findViewById(R.id.btn_add_dinner_frame);

        mAddBreakfast = findViewById(R.id.btn_add_breakfast);
        mAddLunch = findViewById(R.id.btn_add_lunch);
        mAddDinner = findViewById(R.id.btn_add_dinner);
        mCalculate = findViewById(R.id.btn_calculate);

        //SETTING UP BUTTONS LISTENERS
        setupButtons();

        //setting up frame click listener
        setupframeButtonListener();





        breakfastFoods = new ArrayList<>();
        lunchFoods = new ArrayList<>();
        dinnerFoods = new ArrayList<>();

        mBfRecyclerView = findViewById(R.id.recyclerView_breakfast);
        mLuRecyclerView = findViewById(R.id.recyclerView_lunch);
        mDiRecyclerView = findViewById(R.id.recyclerView_dinner);

       setupBreakfastRecyclerView();
       setupLunchRecyclerView();
       setupDinnerRecyclerView();

        //initializing view model


        mCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalBreakFast = 0;
                int totalLunch = 0;
                int totalDinner = 0;
                for (int i = 0; i < breakfastFoods.size(); i++) {
                    totalBreakFast += breakfastFoods.get(i).getFoodCalorie();

                }
                for (int i = 0; i < lunchFoods.size(); i++) {
                    totalLunch += lunchFoods.get(i).getFoodCalorie();

                }
                for (int i = 0; i < dinnerFoods.size(); i++) {
                    totalDinner += dinnerFoods.get(i).getFoodCalorie();

                }
                int finalTotal = totalBreakFast+totalLunch+totalDinner;
                Toast.makeText(MainActivity.this, "total calories is : " + finalTotal, Toast.LENGTH_SHORT).show();
                clearData();
                breakfastFoods = new ArrayList<>();
                lunchFoods = new ArrayList<>();
                dinnerFoods = new ArrayList<>();
               setupBreakfastRecyclerView();
               setupLunchRecyclerView();
               setupDinnerRecyclerView();

            }
        });

    }

    private void setupframeButtonListener() {
        mAddBreakfastFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(MainActivity.this, AddFood.class);
                intent.putExtra("Food Type",BREAKFAST_TYPE);
                startActivity(intent);
            }
        });

        mAddLunchFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(MainActivity.this,AddFood.class);
                intent.putExtra("Food Type",LUNCH_TYPE);
                startActivity(intent);

            }
        });

        mAddDinnerFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(MainActivity.this,AddFood.class);
                intent.putExtra("Food Type",DINNER_TYPE);
                startActivity(intent);

            }
        });
    }

    private void setupButtons() {
        mAddBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(MainActivity.this, AddFood.class);
                intent.putExtra("Food Type",BREAKFAST_TYPE);
                startActivity(intent);
            }
        });

        mAddLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(MainActivity.this,AddFood.class);
                intent.putExtra("Food Type",LUNCH_TYPE);
                startActivity(intent);

            }
        });

        mAddDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(MainActivity.this,AddFood.class);
                intent.putExtra("Food Type",DINNER_TYPE);
                startActivity(intent);

            }
        });
    }


    public void setupBreakfastRecyclerView(){
        mBreakfastAdapter = new BreakfastAdapter(breakfastFoods);
        mBfRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBfRecyclerView.setAdapter(mBreakfastAdapter);
    }

    public void setupLunchRecyclerView(){
        mLunchAdapter = new LunchAdapter(lunchFoods);
        mLuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLuRecyclerView.setAdapter(mLunchAdapter);
    }

    public void setupDinnerRecyclerView(){
        mDinnerAdapter = new DinnerAdapter(dinnerFoods);
        mDiRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDiRecyclerView.setAdapter(mDinnerAdapter);
    }

    private void clearData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(breakfastFoods);
        String json2 = gson.toJson(lunchFoods);
        String json3 = gson.toJson(dinnerFoods);
        editor.putString("task list", json);
        editor.putString("task list 2", json2);
        editor.putString("task list 3", json3);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        String json2 = sharedPreferences.getString("task list 2", null);
        String json3 = sharedPreferences.getString("task list 3", null);
        Type type = new TypeToken<ArrayList<BreakfastFood>>() {
        }.getType();
        breakfastFoods = gson.fromJson(json, type);

        Type type2 = new TypeToken<ArrayList<LunchFood>>() {
        }.getType();
        lunchFoods = gson.fromJson(json2, type2);

        Type type3 = new TypeToken<ArrayList<DinnerFood>>() {
        }.getType();
        dinnerFoods = gson.fromJson(json3, type3);

        if (breakfastFoods == null) {
            breakfastFoods = new ArrayList<>();
        }
        if(lunchFoods == null){
            lunchFoods = new ArrayList<>();
        }
        if(dinnerFoods == null){
            dinnerFoods = new ArrayList<>();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        clearData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        if (intent.hasExtra("foodName")) {
            String foodName = intent.getStringExtra("foodName");
            int foodCalorie = intent.getIntExtra("foodCalorie", 0);
            String foodType = intent.getStringExtra("foodType");

                loadData();
                if (foodCalorie != 0 && !foodName.isEmpty() && !foodType.isEmpty()){

                    Log.d(TAG, "onStart: foodtype is: "+foodType);
                    if(foodType.equals(BREAKFAST_TYPE)){
                        Log.d(TAG, "onStart: breakfast if is true is called");
                        breakfastFoods.add(new BreakfastFood(foodName, foodCalorie));
                    }else if(foodType.equals(LUNCH_TYPE)){
                        lunchFoods.add(new LunchFood(foodName, foodCalorie));
                    }else if(foodType.equals(DINNER_TYPE)){
                        dinnerFoods.add(new DinnerFood(foodName, foodCalorie));
                    }
                }


            setupBreakfastRecyclerView();
            setupLunchRecyclerView();
            setupDinnerRecyclerView();
        }


    }


}