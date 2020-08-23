package com.gameonanil.dailycaloriescalculator.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gameonanil.dailycaloriescalculator.Model.DinnerFood;
import com.gameonanil.dailycaloriescalculator.Model.LunchFood;
import com.gameonanil.dailycaloriescalculator.R;

import java.util.ArrayList;


public class DinnerAdapter extends RecyclerView.Adapter<DinnerAdapter.BreakfastViewHolder> {
    private static final String TAG = "DinnerAdapter";

    ArrayList<DinnerFood> dinnerFoods;
    DinnerListener dinnerListener;

    public DinnerAdapter(ArrayList<DinnerFood> dinnerFoods,DinnerListener dinnerListener) {
        this.dinnerFoods = dinnerFoods;
        this.dinnerListener = dinnerListener;
    }

    @NonNull
    @Override
    public BreakfastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.dinner_list,parent,false);
        return new BreakfastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastViewHolder holder, int position) {
//        Log.d(TAG, "onBindViewHolder: breakfastFoods.get(position).getFoodName() "+breakfastFoods.get(position).getFoodName());
//        Log.d(TAG, "onBindViewHolder: breakfastcalorie.get(position).getFoodName() "+breakfastFoods.get(position).getFoodCalorie());

        holder.foodname.setText(dinnerFoods.get(position).getFoodName());
        holder.foodCalorie.setText(dinnerFoods.get(position).getFoodCalorie()+" cal");
        holder.foodAmount.setText(dinnerFoods.get(position).getFoodAmount());
        holder.foodQuantity.setText("Quantity: x"+dinnerFoods.get(position).getFoodQuantity());
    }

    @Override
    public int getItemCount() {
        return dinnerFoods.size();
    }

    public class BreakfastViewHolder extends RecyclerView.ViewHolder{
        TextView foodname;
        TextView foodCalorie;
        TextView foodAmount;
        TextView foodQuantity;
        ImageView deleteIV;
        public BreakfastViewHolder(@NonNull View itemView) {
            super(itemView);
            foodname = itemView.findViewById(R.id.dinner_food_name);
            foodCalorie = itemView.findViewById(R.id.dinner_calorie);
            foodAmount = itemView.findViewById(R.id.dinner_food_amount);
            foodQuantity = itemView.findViewById(R.id.dinner_quantity);
            deleteIV = itemView.findViewById(R.id.delete_image_dinner);

            deleteIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dinnerListener.deleteItemDinner(getAdapterPosition());
                }
            });
        }
    }
    public interface DinnerListener{
        void deleteItemDinner(int position);
    }
}
