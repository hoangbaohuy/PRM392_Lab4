package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    ImageView foodImageView;
    EditText foodNameEditText, foodPriceEditText;
    Button updateButton, deleteButton, backButton;

    int position;
    String foodName;
    double foodPrice;
    int foodImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw6_item_detail);

        foodImageView = findViewById(R.id.foodImageView);
        foodNameEditText = findViewById(R.id.foodNameEditText);
        foodPriceEditText = findViewById(R.id.foodPriceEditText);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        backButton = findViewById(R.id.backButton);

        // Retrieve the passed data
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        foodName = intent.getStringExtra("foodName");
        foodPrice = intent.getDoubleExtra("foodPrice", 0);
        foodImage = intent.getIntExtra("foodImage", 0);

        // Set the data into views
        foodNameEditText.setText(foodName);
        foodPriceEditText.setText(String.valueOf(foodPrice));
        foodImageView.setImageResource(foodImage);

        // Handle the Update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get updated data from EditTexts
                String updatedName = foodNameEditText.getText().toString();
                double updatedPrice = Double.parseDouble(foodPriceEditText.getText().toString());

                // Return the updated data to HomeWork6Activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("position", position);
                resultIntent.putExtra("updatedName", updatedName);
                resultIntent.putExtra("updatedPrice", updatedPrice);
                resultIntent.putExtra("isDeleted", false); // Not deleting
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        // Handle the Delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return data indicating the item should be deleted
                Intent resultIntent = new Intent();
                resultIntent.putExtra("position", position);
                resultIntent.putExtra("isDeleted", true); // Deleting
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        // Back button to return to the GridView without making changes
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Just go back to the previous activity
            }
        });
    }
}