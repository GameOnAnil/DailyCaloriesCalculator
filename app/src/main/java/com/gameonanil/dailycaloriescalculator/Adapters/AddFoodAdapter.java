package com.gameonanil.dailycaloriescalculator.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gameonanil.dailycaloriescalculator.Model.Food;
import com.gameonanil.dailycaloriescalculator.R;

import java.util.ArrayList;
import java.util.List;

public class AddFoodAdapter extends RecyclerView.Adapter<AddFoodAdapter.AddFoodViewHolder> {
    List<Food> allFood = new ArrayList<>();


    public void setNotes(List<Food> notes){
        allFood = notes;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AddFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.addfood_list, parent, false);
        return new AddFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddFoodViewHolder holder, int position) {
        Food currentFood = allFood.get(position);

            holder.foodName.setText(currentFood.getFoodName());
            holder.foodCalorie.setText(String.valueOf(currentFood.getFoodCalories()));




    }

    @Override
    public int getItemCount() {
        return allFood.size();
    }

    public class AddFoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        TextView foodCalorie;

        public AddFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.add_food_food_name);
            foodCalorie = itemView.findViewById(R.id.add_food_calorie);
        }
    }
}
