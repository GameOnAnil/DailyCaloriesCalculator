package com.gameonanil.dailycaloriescalculator.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gameonanil.dailycaloriescalculator.Adapters.BreakfastAdapter;
import com.gameonanil.dailycaloriescalculator.Model.BreakfastFood;
import com.gameonanil.dailycaloriescalculator.R;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Button mAddBreakfast;
    private Button mCalculate;
    private RecyclerView mRecyclerView;
    private BreakfastAdapter mBreakfastAdapter;
    ArrayList<BreakfastFood> breakfastFoods;

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
                Intent intent = new Intent(MainActivity.this,AddFood.class);
                startActivity(intent);
            }
        });

        breakfastFoods = new ArrayList<>();
        breakfastFoods.add(new BreakfastFood("Apple",100));
        breakfastFoods.add(new BreakfastFood("Banana",100));
        breakfastFoods.add(new BreakfastFood("Mango",200));


        mRecyclerView = findViewById(R.id.recyclerView);
        mBreakfastAdapter = new BreakfastAdapter(breakfastFoods);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mBreakfastAdapter);

        mCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = 0;
                for (int i=0; i<breakfastFoods.size(); i++){
                  total += breakfastFoods.get(i).getFoodCalorie();

                }
                int finalTotal = total;
                Toast.makeText(MainActivity.this, "total calories is : "+finalTotal, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String foodName =  intent.getStringExtra("foodName");
        int foodCalorie  = intent.getIntExtra("foodCalorie",0);

        if(foodCalorie!=0 && !foodName.isEmpty()){
            breakfastFoods.add(new BreakfastFood(foodName,foodCalorie));
        }
    }
}