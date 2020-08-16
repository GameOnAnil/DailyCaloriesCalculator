package com.gameonanil.dailycaloriescalculator.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gameonanil.dailycaloriescalculator.Adapters.AddFoodAdapter;
import com.gameonanil.dailycaloriescalculator.Model.BreakfastFood;
import com.gameonanil.dailycaloriescalculator.Model.Food;
import com.gameonanil.dailycaloriescalculator.R;
import com.gameonanil.dailycaloriescalculator.ViewModel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements AddFoodAdapter.AddFoodListener {
    private static final String TAG = "SearchFragment";
    private View mainView;
    private EditText searchET;
    private RecyclerView mRecyclerView;
    private AddFoodAdapter mAddFoodAdapter;
    private FoodViewModel foodViewModel;
    private TextView foodNameScore;
    private TextView foodCalorieScore;
    private Button saveBtn;
    private ArrayList<BreakfastFood> breakfastFoods;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment

        searchET = mainView.findViewById(R.id.search_food);
        foodNameScore = mainView.findViewById(R.id.food_finalName);
        foodCalorieScore = mainView.findViewById(R.id.food_finalCalorie);
        saveBtn = mainView.findViewById(R.id.save_food_search);

        foodNameScore.setText("");
        foodCalorieScore.setText("");


        mRecyclerView = mainView.findViewById(R.id.recyclerView_add_food);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAddFoodAdapter = new AddFoodAdapter(this);
        mRecyclerView.setAdapter(mAddFoodAdapter);

        foodViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(FoodViewModel.class);
        foodViewModel.getAllFood().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                mAddFoodAdapter.setFoods(foods);
            }
        });


        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //note sql LIKE query need %a% to actually search whole 'a'
                String searchText = "%" + editable.toString() + "%";
                foodViewModel.getFilteredFood(searchText).observe(getActivity(), new Observer<List<Food>>() {
                    @Override
                    public void onChanged(List<Food> foods) {
                        mAddFoodAdapter.setFoods(foods);
                    }
                });

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveValue();
            }
        });


        return mainView;
    }

    private void saveValue() {

        if (foodCalorieScore.getText().toString().isEmpty() || foodNameScore.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please enter food name and calorie first", Toast.LENGTH_SHORT).show();
        } else {
            String foodNameValue = foodNameScore.getText().toString();
            int foodCalorieValue = 0;
            foodCalorieValue = Integer.parseInt(foodCalorieScore.getText().toString());
            Intent intent = new Intent(getActivity(),MainActivity.class);
            intent.putExtra("foodNameFromSearch", foodNameValue);
            intent.putExtra("foodCalorieFromSearch", foodCalorieValue);
            startActivity(intent);

        }
    }


    @Override
    public void onItemClick(int position) {
        String foodName = mAddFoodAdapter.getCurrentFood(position).getFoodName();
        int foodCalorie = mAddFoodAdapter.getCurrentFood(position).getFoodCalories();

        foodNameScore.setText(foodName);
        foodCalorieScore.setText(String.valueOf(foodCalorie));


    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == android.R.id.home){
//            getActivity().finish();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}