package com.gameonanil.dailycaloriescalculator.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.gameonanil.dailycaloriescalculator.R;

public class AddFood extends AppCompatActivity {
    EditText mFoodName;
    EditText mFoodCalorie;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        Toolbar toolbar = findViewById(R.id.toolbar_add_food);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mFoodName = findViewById(R.id.enter_name);
        mFoodCalorie = findViewById(R.id.enter_calorie);
        saveBtn = findViewById(R.id.save_food);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFoodName.getText().toString().equals("") || mFoodCalorie.getText().toString().equals("")) {
                    Toast.makeText(AddFood.this, "Please enter food name and calories", Toast.LENGTH_SHORT).show();
                } else {
                    String foodName = mFoodName.getText().toString();
                    int foodCalorie = 0;
                    foodCalorie = Integer.parseInt(mFoodCalorie.getText().toString());
                    Intent intent = new Intent(AddFood.this, MainActivity.class);
                    intent.putExtra("foodName", foodName);
                    intent.putExtra("foodCalorie", foodCalorie);
                    startActivity(intent);
                }


            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
}