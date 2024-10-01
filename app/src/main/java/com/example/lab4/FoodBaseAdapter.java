package com.example.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodBaseAdapter extends BaseAdapter {
    Context context;
    String listFood[];
    int listImages[];
    String listDescription[];
    LayoutInflater inflater;
    public FoodBaseAdapter(Context ctx, String foodList[],int images[], String descriptionList[]){
        this.context = ctx;
        this.listFood = foodList;
        this.listImages = images;
        this.listDescription = descriptionList;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return listFood.length;
    }

    @Override
    public Object getItem(int position) {
        return listFood[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.food_item, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.textView);
        TextView txtDescription = (TextView) convertView.findViewById(R.id.textDescription);
        ImageView fruitImg = (ImageView) convertView.findViewById(R.id.imageIcon);
        txtView.setText(listFood[position]);
        txtDescription.setText(listDescription[position]);
        fruitImg.setImageResource(listImages[position]);
        return convertView;
    }
}
