package com.example.tbte4.exjobb;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Location> locations;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public LocationsAdapter(Context ctx, ArrayList<Location> locations) {
        this.ctx = ctx;
        this.locations = locations;
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    @Override
    public LocationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_location, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;
        final Location location = locations.get(position);

        TextView cardName = cardView.findViewById(R.id.card_name);
        cardName.setText(location.Name);

        TextView cardLoc = cardView.findViewById(R.id.card_loc);

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(ctx, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(location.Lat, location.Long, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0);

            cardLoc.setText(address);
        } catch (Exception e) {
            cardLoc.setText(location.Lat + ", " + location.Long);
        }

        if (location.isonline) {

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        } else {
            int color = ctx.getResources().getColor(R.color.colorDisabled);
            cardView.setCardBackgroundColor(color);
        }
    }
}

