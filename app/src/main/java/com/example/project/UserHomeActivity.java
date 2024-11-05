package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserHomeActivity extends AppCompatActivity {

    private ListView listViewProducts;
    private DatabaseHelper databaseHelper;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        editText = findViewById(R.id.et_search);
        listViewProducts = findViewById(R.id.list_view_product_user);
        Button Category = findViewById(R.id.btn_category);
        Button Cart = findViewById(R.id.add_to_cart);
        databaseHelper = new DatabaseHelper(this);

        displayProducts();

        Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this, ProductDisplay.class);
                startActivity(intent);
            }
        });

        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                Intent intent = new Intent(UserHomeActivity.this, CartActivity.class);
                intent.putExtra("editTextValue", text);
                startActivity(intent);
            }
        });


    }

    private void displayProducts(){
        Cursor cursor = databaseHelper.getAllProducts();
        ProductAdapter adapter = new ProductAdapter(this, cursor, 0);
        listViewProducts.setAdapter(adapter);
    }

}