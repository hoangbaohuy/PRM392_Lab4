package com.example.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> foodName;  // Use ArrayList instead of arrays
    ArrayList<Double> foodPrice;
    ArrayList<Integer> image;

    LayoutInflater inflater;

    // Constructor with ArrayList parameters
    public GridAdapter(Context context, ArrayList<String> foodName, ArrayList<Double> foodPrice, ArrayList<Integer> image) {
        this.context = context;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.image = image;
    }

    @Override
    public int getCount() {
        return foodName.size();  // Return the size of the ArrayList
    }

    @Override
    public Object getItem(int position) {
        return foodName.get(position);  // Return the item at the given position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.hw6_item, null);  // Inflate the grid item layout
        }

        // Get references to the views in the layout
        ImageView imageView = convertView.findViewById(R.id.grid_image);
        TextView textView = convertView.findViewById(R.id.item_name);
        TextView textViewPrice = convertView.findViewById(R.id.item_price);

        // Set the image, name, and price for each grid item
        imageView.setImageResource(image.get(position));
        textView.setText(foodName.get(position));
        textViewPrice.setText(String.valueOf(foodPrice.get(position)));

        return convertView;
    }
}