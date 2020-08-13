package com.gameonanil.dailycaloriescalculator.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gameonanil.dailycaloriescalculator.Model.BreakfastFood;
import com.gameonanil.dailycaloriescalculator.R;

import java.net.ProtocolFamily;
import java.util.ArrayList;


public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.BreakfastViewHolder> {
    private static final String TAG = "BreakfastAdapter";

    ArrayList<BreakfastFood> breakfastFoods;

    public BreakfastAdapter(ArrayList<BreakfastFood> breakfastFoods) {
        this.breakfastFoods = breakfastFoods;
    }

    @NonNull
    @Override
    public BreakfastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.breakfast_list,parent,false);
        return new BreakfastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastViewHolder holder, int position) {
//        Log.d(TAG, "onBindViewHolder: breakfastFoods.get(position).getFoodName() "+breakfastFoods.get(position).getFoodName());
//        Log.d(TAG, "onBindViewHolder: breakfastcalorie.get(position).getFoodName() "+breakfastFoods.get(position).getFoodCalorie());

        holder.foodname.setText(breakfastFoods.get(position).getFoodName());
        holder.foodCalorie.setText(String.valueOf(breakfastFoods.get(position).getFoodCalorie())+" cal");
    }

    @Override
    public int getItemCount() {
        return breakfastFoods.size();
    }

    public class BreakfastViewHolder extends RecyclerView.ViewHolder{
        TextView foodname;
        TextView foodCalorie;
        public BreakfastViewHolder(@NonNull View itemView) {
            super(itemView);
            foodname = itemView.findViewById(R.id.breakfast_food_name);
            foodCalorie = itemView.findViewById(R.id.breakfast_calorie);
        }
    }
}
