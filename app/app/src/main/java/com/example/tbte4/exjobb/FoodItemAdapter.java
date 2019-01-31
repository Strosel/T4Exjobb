package com.example.tbte4.exjobb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {

    Context ctx;
    ArrayList<FoodItem> foodItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public FoodItemAdapter(Context ctx, ArrayList<FoodItem> foodItems) {
        this.ctx = ctx;
        this.foodItems = foodItems;
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    @Override
    public FoodItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_food, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;
        final FoodItem foodItem = foodItems.get(position);

        TextView cardName = cardView.findViewById(R.id.food_name);
        cardName.setText(foodItem.Name);

        ImageView imageView =  cardView.findViewById(R.id.food_img);
        Glide.with(ctx).load(foodItem.Img).into(imageView);
    }
}

