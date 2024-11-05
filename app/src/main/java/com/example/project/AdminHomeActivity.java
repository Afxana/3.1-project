package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_home);
        Button btnInsertProduct = findViewById(R.id.btn_insert_product);
        Button btnViewProduct = findViewById(R.id.btn_view_product);

        btnInsertProduct.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                Toast.makeText(AdminHomeActivity.this, "Insert your products!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminHomeActivity.this,InsertProductActivity.class);
                startActivity(intent);
            }
        });

        btnViewProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AdminHomeActivity.this,ViewProductActivity.class);
                startActivity(intent);
            }
        });



    }
}