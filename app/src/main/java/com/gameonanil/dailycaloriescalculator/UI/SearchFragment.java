package com.gameonanil.dailycaloriescalculator.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
    private EditText foodQuantityET;
    private Button saveBtn;
    private ArrayList<BreakfastFood> breakfastFoods;
    String mFoodAmount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);

        searchET = mainView.findViewById(R.id.search_food);
        foodNameScore = mainView.findViewById(R.id.food_finalName);
        foodCalorieScore = mainView.findViewById(R.id.food_finalCalorie);
        foodQuantityET = mainView.findViewById(R.id.enter_food_quantity);


        foodNameScore.setText("");
        foodCalorieScore.setText("");


        mRecyclerView = mainView.findViewById(R.id.recyclerView_add_food);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(false);
        mAddFoodAdapter = new AddFoodAdapter(this);

        foodViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(FoodViewModel.class);


        foodViewModel.foodList.observe(this, pagedList -> {
            mAddFoodAdapter.setFoods(pagedList);
        });

        mRecyclerView.setAdapter(mAddFoodAdapter);


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




        return mainView;
    }

    public void saveValue() {

        if (foodCalorieScore.getText().toString().isEmpty() || foodNameScore.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please enter food name and calorie first", Toast.LENGTH_SHORT).show();
        } else {
            String foodNameValue = foodNameScore.getText().toString();
            int foodCalorieValue = 0;
            int foodQuantity = 0;
            foodCalorieValue = Integer.parseInt(foodCalorieScore.getText().toString());
            String foodAmount = mFoodAmount;

            Intent extraIntent = getActivity().getIntent();
            String foodType = extraIntent.getStringExtra("Food Type");
            foodQuantity = Integer.parseInt(foodQuantityET.getText().toString() );


            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("foodName", foodNameValue);
            intent.putExtra("foodCalorie", foodCalorieValue);
            intent.putExtra("foodType", foodType);
            intent.putExtra("foodAmount",foodAmount);
            intent.putExtra("foodQuantity",foodQuantity);
            startActivity(intent);


        }
    }


    @Override
    public void onItemClick(int position) {
        String foodName = mAddFoodAdapter.getCurrentFood(position).getFoodName();
        int foodCalorie = mAddFoodAdapter.getCurrentFood(position).getFoodCalories();

        foodNameScore.setText(foodName);
        foodCalorieScore.setText(String.valueOf(foodCalorie));
        mFoodAmount = mAddFoodAdapter.getCurrentFood(position).getFoodAmount();


    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.add_food_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_item:
                saveValue();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}