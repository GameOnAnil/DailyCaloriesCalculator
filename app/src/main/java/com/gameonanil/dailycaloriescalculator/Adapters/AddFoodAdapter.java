package com.gameonanil.dailycaloriescalculator.Adapters;

import android.annotation.SuppressLint;
import android.graphics.pdf.PdfRenderer;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.gameonanil.dailycaloriescalculator.Model.Food;
import com.gameonanil.dailycaloriescalculator.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddFoodAdapter extends PagedListAdapter<Food, AddFoodAdapter.AddFoodViewHolder> {
    private static final String TAG = "AddFoodAdapter";
    List<Food> allFood = new ArrayList<>();

    AddFoodListener addFoodListener;

    public AddFoodAdapter(AddFoodListener addFoodListener) {
        super(DIFF_CALLBACK);
        this.addFoodListener = addFoodListener;
    }

    public static DiffUtil.ItemCallback<Food> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Food>() {
                @Override
                public boolean areItemsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
                    return oldItem.getId() == newItem.getId();
                }


                @Override
                public boolean areContentsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
                    return oldItem.equals(newItem);
                }
            };


    public void setFoods(List<Food> foods){
        allFood = foods;
        notifyDataSetChanged();
    }


    public Food getCurrentFood(int position){
        Food currentFood = allFood.get(position);

        return currentFood;

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
           //  Food currentFood = allFood.get(position);
             Food currentFood = allFood.get(position);
            Log.d(TAG, "onBindViewHolder: current food "+currentFood.getFoodName());
             if(currentFood != null){
                 holder.foodName.setText(currentFood.getFoodName());
                 holder.foodCalorie.setText(String.valueOf(currentFood.getFoodCalories()));


             }

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addFoodListener.onItemClick(getAdapterPosition());
                }
            });
        }

    }

    public interface AddFoodListener{
        public void onItemClick(int position);
    }
}
