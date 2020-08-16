package com.gameonanil.dailycaloriescalculator.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gameonanil.dailycaloriescalculator.Adapters.BreakfastAdapter;
import com.gameonanil.dailycaloriescalculator.Model.BreakfastFood;
import com.gameonanil.dailycaloriescalculator.Model.Food;
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
    private Button mCalculate;
    private RecyclerView mRecyclerView;
    private BreakfastAdapter mBreakfastAdapter;
    private ArrayList<BreakfastFood> breakfastFoods;
    FoodViewModel foodViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        mAddBreakfast = findViewById(R.id.btn_add_breakfast);
        mCalculate = findViewById(R.id.btn_calculate);
        mAddBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();

                Intent intent = new Intent(MainActivity.this, AddFood.class);
                startActivity(intent);
            }
        });

//        breakfastFoods = new ArrayList<>();
        loadData();
       // breakfastFoods.add(new BreakfastFood("cherry", 200));

        mRecyclerView = findViewById(R.id.recyclerView);
        mBreakfastAdapter = new BreakfastAdapter(breakfastFoods);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mBreakfastAdapter);

        //initializing view model


        mCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = 0;
                for (int i = 0; i < breakfastFoods.size(); i++) {
                    total += breakfastFoods.get(i).getFoodCalorie();

                }
                int finalTotal = total;
                Toast.makeText(MainActivity.this, "total calories is : " + finalTotal, Toast.LENGTH_SHORT).show();
                clearData();
                breakfastFoods = new ArrayList<>();
               setupRecyclerView();

            }
        });

    }
    public void setupRecyclerView(){
        mBreakfastAdapter = new BreakfastAdapter(breakfastFoods);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mBreakfastAdapter);
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
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<BreakfastFood>>() {
        }.getType();
        breakfastFoods = gson.fromJson(json, type);

        if (breakfastFoods == null) {
            breakfastFoods = new ArrayList<>();

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

            if (foodCalorie != 0 && !foodName.isEmpty()) {
                loadData();
                breakfastFoods.add(new BreakfastFood(foodName, foodCalorie));
                setupRecyclerView();

            }
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: called!");
    }

}