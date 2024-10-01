package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FoodListView extends AppCompatActivity {
    ListView listView;
    String foodList[] = {"Phở Hà Nội", "Bún Bò", "Mì Quảng", "Hủ Tíu Sài Gòn"};
    String foodDescription[] = {
            "Món phở truyền thống của Hà Nội với nước dùng trong, thơm mùi bò hầm, kết hợp với bánh phở mềm, thịt bò tái hoặc chín, và các loại rau thơm như hành, rau mùi.",
            "Món ăn đặc trưng của Huế, có nước dùng đậm đà từ xương bò, sả, và mắm ruốc. Bún bò thường có thịt bò, giò heo, chả cua, và được ăn kèm với rau sống, giá đỗ.",
            "Món ăn miền Trung, nổi tiếng với nước dùng đậm đà, ít nước, được nấu từ xương heo hoặc gà. Mì được làm từ bột gạo, dày và mềm, ăn kèm với thịt, tôm, trứng cút, đậu phộng, và rau sống.",
            "Món ăn phổ biến ở miền Nam, với nước dùng ngọt thanh từ xương hầm. Hủ tiếu có sợi mỏng, dai, được ăn với thịt heo, tôm, mực, và các loại rau sống, giá đỗ, hẹ."
    };
    int foodImages [] = {R.drawable.pho, R.drawable.bunbo,R.drawable.miquang,R.drawable.hutiu};
    Button btnOrderFood;
    private int selectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        listView = findViewById(R.id.foodListView);
        btnOrderFood = findViewById(R.id.btnOrderFood);
        FoodBaseAdapter adapter = new FoodBaseAdapter(this, foodList, foodImages, foodDescription);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position; // Update the selected position
                // Show a Toast message when an item is selected
                String selectedItem = foodList[selectedPosition];
                adapter.notifyDataSetChanged();
                Toast.makeText(FoodListView.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
        btnOrderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    // Perform the action with the selected item
                    String selectedItem = foodList[selectedPosition];
                    Toast.makeText(FoodListView.this, "Ordered: " + selectedItem, Toast.LENGTH_SHORT).show();
                    // Send the selected item back to MainActivity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selectedItemFood", selectedItem);
                    setResult(RESULT_OK, resultIntent);
                    finish(); // Close FoodListView and return to MainActivity
                } else {
                    // No item was selected
                    Toast.makeText(FoodListView.this, "No item selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
