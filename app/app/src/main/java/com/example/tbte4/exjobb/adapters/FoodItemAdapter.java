package com.example.tbte4.exjobb.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tbte4.exjobb.items.FoodItem;
import com.example.tbte4.exjobb.R;
import com.example.tbte4.exjobb.helpers.cacheFileHelper;

import java.util.ArrayList;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {

    private static String tag = "ExJobb FoodItemAdapter";

    private Context ctx;
    private ArrayList<FoodItem> foodItems;
    protected cacheFileHelper order;

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
        this.order = new cacheFileHelper(ctx, R.string.cache_file_name);
        this.order.load();
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

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ctx);
                LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_buy, null);

                Button inc = dialogView.findViewById(R.id.buy_inc);
                Button dec = dialogView.findViewById(R.id.buy_dec);
                Button buy = dialogView.findViewById(R.id.buy_buy);

                ImageView img =  dialogView.findViewById(R.id.buy_img);
                Glide.with(ctx).load(foodItem.Img).into(img);

                TextView name = dialogView.findViewById(R.id.buy_name);
                name.setText(foodItem.Name);

                final TextView vol = dialogView.findViewById(R.id.buy_vol);
                vol.setText("1");

                builder.setView(dialogView);
                final android.app.AlertDialog dialog = builder.create();
                dialog.show();

                inc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            int volI = Integer.parseInt(vol.getText().toString());
                            volI++;
                            vol.setText(String.valueOf(volI));
                        }catch (Exception e) {
                            Log.v(tag, e.toString());
                        }
                    }
                });

                dec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            int volI = Integer.parseInt(vol.getText().toString());
                            volI--;
                            vol.setText(String.valueOf(volI));
                        } catch (Exception e) {
                            Log.v(tag, e.toString());
                        }
                    }
                });

                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            int volI = Integer.parseInt(vol.getText().toString());
                            int exists = order.getOrder(foodItem.id);
                            if (exists != -1)
                                order.setOrder(foodItem.id, exists+volI);
                            else
                                order.setOrder(foodItem.id, volI);
                            order.save();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Log.v(tag, e.toString());
                            Toast toast = Toast.makeText(ctx, R.string.err_cart, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
            }
        });
    }
}

