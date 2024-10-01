package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnOrderFood, btnOrderDrink, btnExit;
    TextView txtOrderFood, txtOrderDrink;
    private static final int REQUEST_CODE_FOOD = 1;
    private static final int REQUEST_CODE_DRINK = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOrderFood = findViewById(R.id.button1);
        btnOrderDrink = findViewById(R.id.button2);
        btnExit = findViewById(R.id.button3);
        txtOrderFood = findViewById(R.id.bottomTextView);
        txtOrderDrink = findViewById(R.id.secondTextView);
        btnOrderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodListView.class);
                startActivityForResult(intent, REQUEST_CODE_FOOD);
            }
        });
        btnOrderDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrinkListView.class);
                startActivityForResult(intent, REQUEST_CODE_DRINK);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOOD && resultCode == RESULT_OK) {
            if (data != null) {
                String selectedItemFood = data.getStringExtra("selectedItemFood");
                txtOrderFood.setText("" + selectedItemFood);
            }
        }
        if (requestCode == REQUEST_CODE_DRINK && resultCode == RESULT_OK) {
            if (data != null) {
                String selectedItemDrink = data.getStringExtra("selectedItemDrink");
                txtOrderDrink.setText("" + selectedItemDrink);
            }
        }
    }
}