package com.gameonanil.dailycaloriescalculator.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gameonanil.dailycaloriescalculator.R;

public class CustomFoodFragment extends Fragment {
    View mainView;
    EditText mFoodName;
    EditText mFoodCalorie;
    Button saveBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_custom_food, container, false);
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);

        mFoodName = mainView.findViewById(R.id.enter_name);
        mFoodCalorie = mainView.findViewById(R.id.enter_calorie);
        saveBtn = mainView.findViewById(R.id.save_food_custom);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               saveFood();
            }
        });

        return mainView;
    }

    private void saveFood(){
        if (mFoodName.getText().toString().equals("") || mFoodCalorie.getText().toString().equals("")) {
            Toast.makeText(getContext(), "Please enter food name and calories", Toast.LENGTH_SHORT).show();
        } else {
            String foodName = mFoodName.getText().toString();
            int foodCalorie = 0;
            foodCalorie = Integer.parseInt(mFoodCalorie.getText().toString());

            Intent extraIntent = getActivity().getIntent();
            String foodType = extraIntent.getStringExtra("Food Type");

            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("foodName", foodName);
            intent.putExtra("foodCalorie", foodCalorie);
            intent.putExtra("foodType", foodType);
            startActivity(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_food_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.save_item){
            saveFood();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}