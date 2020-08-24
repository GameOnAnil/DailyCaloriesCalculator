package com.gameonanil.dailycaloriescalculator.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.gameonanil.dailycaloriescalculator.R;

public class ScorePage extends AppCompatActivity {
    private static final String TAG = "ScorePage";
    Button btnCalculateAgain;
    TextView totalCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        Toolbar toolbar = findViewById(R.id.toolbar_score_page);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        totalCalories = findViewById(R.id.display_calorie);
        btnCalculateAgain = findViewById(R.id.btn_calculate_again);

        Intent intent = getIntent();
        String caloriesScore = String.valueOf(intent.getIntExtra("Total Calories",0)) ;
        Log.d(TAG, "onCreate: "+caloriesScore);
        if(!caloriesScore.isEmpty()){
            totalCalories.setText(caloriesScore+" Calories");
        }

        btnCalculateAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ScorePage.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }

    }
}