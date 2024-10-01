package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DrinkListView extends AppCompatActivity {
    ListView listView;
    String drinkList[] = {"Pepsi", "Heineken", "Tiger", "Sài Gòn Đỏ"};
    int drinkImages [] = {R.drawable.pepsi, R.drawable.heineken,R.drawable.tiger,R.drawable.saigondo};
    String drinkDescription[] = {
            "Nước ngọt có gas nổi tiếng trên toàn cầu, vị ngọt mát, thường được dùng giải khát hoặc pha chế trong các loại đồ uống.",
            "Bia lager của Hà Lan, có hương vị cân bằng, vị đắng nhẹ, và thường được ưa chuộng trong các bữa tiệc, sự kiện.",
            "Bia lager phổ biến ở châu Á, có vị đắng nhẹ, hương thơm mạch nha, thích hợp để giải khát và thưởng thức cùng các món ăn.",
            "Bia nội địa của Việt Nam, có nồng độ cồn cao hơn một chút, vị đậm đà, thường được yêu thích trong các buổi liên hoan và gặp gỡ bạn bè."
    };
    Button btnOrderDrink;
    private int selectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_list);
        listView = findViewById(R.id.drinkListView);
        btnOrderDrink = findViewById(R.id.btnOrderDrink);
        DrinkBaseAdapter adapter = new DrinkBaseAdapter(this, drinkList, drinkImages, drinkDescription);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position; // Update the selected position
                // Show a Toast message when an item is selected
                String selectedItem = drinkList[selectedPosition];
                Toast.makeText(DrinkListView.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
        btnOrderDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    // Perform the action with the selected item
                    String selectedItem = drinkList[selectedPosition];
                    Toast.makeText(DrinkListView.this, "Ordered: " + selectedItem, Toast.LENGTH_SHORT).show();

                    // Send the selected item back to MainActivity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selectedItemDrink", selectedItem);
                    setResult(RESULT_OK, resultIntent);
                    finish(); // Close FoodListView and return to MainActivity
                } else {
                    // No item was selected
                    Toast.makeText(DrinkListView.this, "No item selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
