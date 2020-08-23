package com.gameonanil.dailycaloriescalculator.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gameonanil.dailycaloriescalculator.Model.BreakfastFood;
import com.gameonanil.dailycaloriescalculator.Model.LunchFood;
import com.gameonanil.dailycaloriescalculator.R;

import java.util.ArrayList;


public class LunchAdapter extends RecyclerView.Adapter<LunchAdapter.BreakfastViewHolder> {
    private static final String TAG = "LunchAdapter";

    ArrayList<LunchFood> lunchFoods;
    LunchListener lunchListener;

    public LunchAdapter(ArrayList<LunchFood> lunchFoods,LunchListener lunchListener) {
        this.lunchFoods = lunchFoods;
        this.lunchListener = lunchListener;
    }

    @NonNull
    @Override
    public BreakfastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lunch_list,parent,false);
        return new BreakfastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastViewHolder holder, int position) {
//        Log.d(TAG, "onBindViewHolder: breakfastFoods.get(position).getFoodName() "+breakfastFoods.get(position).getFoodName());
//        Log.d(TAG, "onBindViewHolder: breakfastcalorie.get(position).getFoodName() "+breakfastFoods.get(position).getFoodCalorie());

        holder.foodname.setText(lunchFoods.get(position).getFoodName());
        holder.foodCalorie.setText(String.valueOf(lunchFoods.get(position).getFoodCalorie())+" cal");
        holder.foodAmount.setText(lunchFoods.get(position).getFoodAmount());
        holder.foodQuantity.setText("Quantity: x"+lunchFoods.get(position).getFoodQuantity());
    }

    @Override
    public int getItemCount() {
        return lunchFoods.size();
    }

    public class BreakfastViewHolder extends RecyclerView.ViewHolder{
        TextView foodname;
        TextView foodCalorie;
        TextView foodAmount;
        TextView foodQuantity;
        ImageView deleteIV;
        public BreakfastViewHolder(@NonNull View itemView) {
            super(itemView);
            foodname = itemView.findViewById(R.id.lunch_food_name);
            foodCalorie = itemView.findViewById(R.id.lunch_calorie);
            foodAmount = itemView.findViewById(R.id.lunch_food_amount);
            foodQuantity = itemView.findViewById(R.id.lunch_quantity);
            deleteIV = itemView.findViewById(R.id.delete_image_lunch);

            deleteIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lunchListener.deleteItemLunch(getAdapterPosition());
                }
            });
        }
    }

    public interface LunchListener{
        void deleteItemLunch(int position);
    }
}
