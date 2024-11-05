package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CartActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextName;
    private EditText editTextProductQuantity;

    private TextView textViewProductPrice;
    private TextView textViewProductId;
    private TextView textViewTotal;

    private ImageView imageViewProduct;

    private Button buttonSearch;
    private Button buttonOrder;
    private Button buttonSelectImage;

    private DatabaseHelper databaseHelper;
    private byte[] productImageByteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);



        editTextName = findViewById(R.id.edit_text_product_name);
        String value = getIntent().getStringExtra("editTextValue");
        editTextName.setText(value);
        editTextProductQuantity = findViewById(R.id.edit_text_product_quantity);
        textViewProductPrice = findViewById(R.id.text_view_product_price);
        textViewProductId = findViewById(R.id.text_view_product_id);
        textViewTotal = findViewById(R.id.text_view_total);
        imageViewProduct = findViewById(R.id.image_view_product);

        Button buttonOrder = findViewById(R.id.button_order);
        Button buttonSearch = findViewById(R.id.button_search);

        databaseHelper = new DatabaseHelper(this);

        buttonSearch.setOnClickListener(view -> searchCartProduct());
        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Order confirmed successfully!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchCartProduct(){
        String productName = editTextName.getText().toString().trim();
        if(productName.isEmpty()){
            Toast.makeText(this, "Please enter a product name to search", Toast.LENGTH_SHORT).show();
            return;
        }
        Cursor cursor = databaseHelper.getProductByName(productName);
        if(cursor != null && cursor.moveToFirst()){
            int productId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ID));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PRODUCT_PRICE));
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PRODUCT_QUANTITY));
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PRODUCT_IMAGE_URI));

            textViewProductPrice.setText("Product Price: " + price);
            if(quantity == 0){
                editTextProductQuantity.setText("Out of stock");
            } else{
                editTextProductQuantity.setText("Quantity: " + quantity);
            }

            textViewProductId.setText("Product ID: " + productId);

            double Total = quantity * price;
            textViewTotal.setText("Total: " + Total);


            if(image != null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageViewProduct.setImageBitmap(bitmap);
                productImageByteArray = image;
            }
            cursor.close();
        } else {
            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
        }

    }





}