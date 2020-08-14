package com.gameonanil.dailycaloriescalculator.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gameonanil.dailycaloriescalculator.Adapters.AddFoodAdapter;
import com.gameonanil.dailycaloriescalculator.Model.Food;
import com.gameonanil.dailycaloriescalculator.R;
import com.gameonanil.dailycaloriescalculator.ViewModel.FoodViewModel;

import java.util.List;

public class SearchFragment extends Fragment {
    View mainView;


    RecyclerView mRecyclerView;
    AddFoodAdapter mAddFoodAdapter;
    FoodViewModel foodViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment





        mRecyclerView = mainView.findViewById(R.id.recyclerView_add_food);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAddFoodAdapter = new AddFoodAdapter();
        mRecyclerView.setAdapter(mAddFoodAdapter);

        foodViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(FoodViewModel.class);
        foodViewModel.getAllFood().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                mAddFoodAdapter.setNotes(foods);
            }
        });

        return mainView;
    }
}