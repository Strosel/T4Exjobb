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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private static String tag = "ExJobb CartAdapter";

    private Context ctx;
    private ArrayList<FoodItem> cart;
    private cacheFileHelper order;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public CartAdapter(Context ctx) {
        this.ctx = ctx;
        this.order = new cacheFileHelper(ctx, R.string.cache_file_name);
        this.order.load();
        try {
            this.cart = order.getCart();
        } catch (Exception e) {
            Log.v(tag, e.toString());
            Toast toast = Toast.makeText(ctx, R.string.err_conn, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_cart, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CartAdapter adapter = this;
        final CardView cardView = holder.cardView;
        final FoodItem foodItem = cart.get(position);

        TextView cardName = cardView.findViewById(R.id.cart_name);
        cardName.setText(foodItem.Name);

        ImageView imageView =  cardView.findViewById(R.id.cart_img);
        Glide.with(ctx).load(foodItem.Img).into(imageView);

        TextView cardVol = cardView.findViewById(R.id.cart_vol);
        int vol = -1;
        try {
            vol = order.getOrder(foodItem.id);
        } catch (Exception e) {}
        cardVol.setText(String.valueOf(vol));

        Button dec = cardView.findViewById(R.id.cart_dec);
        dec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView cardVol = cardView.findViewById(R.id.cart_vol);
                int vol = -1;
                try {
                    order.decOrder(foodItem.id);
                    vol = order.getOrder(foodItem.id);
                } catch (Exception e) {}
                if (vol > 0)
                    cardVol.setText(String.valueOf(vol));
                else
                    adapter.onItemDismiss(position);
                order.save();
            }
        });

        Button inc = cardView.findViewById(R.id.cart_inc);
        inc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView cardVol = cardView.findViewById(R.id.cart_vol);
                int vol = -1;
                try {
                    order.incOrder(foodItem.id);
                    vol = order.getOrder(foodItem.id);
                } catch (Exception e) {}
                cardVol.setText(String.valueOf(vol));
                order.save();
            }
        });

        Button rem = cardView.findViewById(R.id.cart_rem);
        rem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                order.remOrder(foodItem.id);
                adapter.onItemDismiss(position);
                order.save();
            }
        });
    }

    public void onItemDismiss(final int position) {
        try {
            cart.remove(position);
            notifyItemRemoved(position);
        } catch (Exception e) {
            Log.v(tag, e.toString());
            Toast toast = Toast.makeText(ctx, R.string.err_cart, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

