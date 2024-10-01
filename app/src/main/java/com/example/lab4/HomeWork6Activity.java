package com.example.lab4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class HomeWork6Activity extends AppCompatActivity {
    GridView gridView;
    ArrayList<String> foodList;
    ArrayList<Double> foodPrice;
    ArrayList<Integer> foodImages;

    GridAdapter gridAdapter;

    private static final int REQUEST_CODE_DETAIL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw6); // Set your layout file

        gridView = findViewById(R.id.gridView);

        // Initialize the ArrayLists
        foodList = new ArrayList<>();
        foodPrice = new ArrayList<>();
        foodImages = new ArrayList<>();

        // Add initial data
        foodList.add("Phở Hà Nội");
        foodList.add("Bún Bò");
        foodList.add("Mì Quảng");
        foodList.add("Hủ Tíu Sài Gòn");

        foodPrice.add(30000.0);
        foodPrice.add(25000.0);
        foodPrice.add(20000.0);
        foodPrice.add(35000.0);

        foodImages.add(R.drawable.pho);
        foodImages.add(R.drawable.bunbo);
        foodImages.add(R.drawable.miquang);
        foodImages.add(R.drawable.hutiu);

        // Set up adapter with the ArrayLists
        gridAdapter = new GridAdapter(HomeWork6Activity.this, foodList, foodPrice, foodImages);
        gridView.setAdapter(gridAdapter);

        // Optional: If you're using a custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Set an item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start ItemDetailActivity and pass the clicked item's data
                Intent intent = new Intent(HomeWork6Activity.this, ItemDetailActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("foodName", foodList.get(position));
                intent.putExtra("foodPrice", foodPrice.get(position));
                intent.putExtra("foodImage", foodImages.get(position));
                startActivityForResult(intent, REQUEST_CODE_DETAIL);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_DETAIL && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1);
            String updatedName = data.getStringExtra("updatedName");
            double updatedPrice = data.getDoubleExtra("updatedPrice", -1);
            boolean isDeleted = data.getBooleanExtra("isDeleted", false);

            if (position != -1) {
                if (isDeleted) {
                    // Remove the item from the ArrayLists
                    foodList.remove(position);
                    foodPrice.remove(position);
                    foodImages.remove(position);
                } else {
                    // Update the item in the ArrayLists
                    foodList.set(position, updatedName);
                    foodPrice.set(position, updatedPrice);
                }

                // Notify the adapter of the change
                gridAdapter.notifyDataSetChanged();
            }
        }
    }

    // Inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // Handle menu item click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            showAddItemDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Method to show the dialog to add a new item
    private void showAddItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.hw6_item_add, null);
        builder.setView(customLayout);
        builder.setTitle("Add New Item");

        // Handle dialog buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText nameInput = customLayout.findViewById(R.id.edit_text_name);
                EditText priceInput = customLayout.findViewById(R.id.edit_text_price);

                String newName = nameInput.getText().toString();
                double newPrice = Double.parseDouble(priceInput.getText().toString());

                // Add the new item to the lists
                foodList.add(newName);
                foodPrice.add(newPrice);
                foodImages.add(R.drawable.replace); // Add a default image

                // Notify the adapter of the change
                gridAdapter.notifyDataSetChanged();

                Toast.makeText(HomeWork6Activity.this, "Item added", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
