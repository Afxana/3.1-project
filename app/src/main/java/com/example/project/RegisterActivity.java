package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etUsername = findViewById(R.id.et_reg_username);
        EditText etEmail = findViewById(R.id.et_reg_email);
        EditText etPassword = findViewById(R.id.et_reg_password);
        EditText etConfirmPassword = findViewById(R.id.et_reg_con_pass);

        Button btnRegister = findViewById(R.id.btn_register);
        Button btnLogin = findViewById(R.id.btn_login);

        btnRegister.setOnClickListener(v->{
            String username = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            if(password.equals(confirmPassword) && !password.isEmpty() && !username.isEmpty()){
                Toast.makeText(RegisterActivity.this, "Well done! Let me insert your info in DB",Toast.LENGTH_SHORT).show();

                DatabaseHelper dbHelper = new DatabaseHelper(RegisterActivity.this);
                boolean isInserted = dbHelper.insertUser(username, email, password);
                if(isInserted){
                    Toast.makeText(RegisterActivity.this, "Registration successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(RegisterActivity.this, "Registration failed",Toast.LENGTH_SHORT).show();
                }

            } else{
                Toast.makeText(RegisterActivity.this, "Password do not match or empty password or empty username",Toast.LENGTH_SHORT).show();
            }

        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(RegisterActivity.this, "Login button clicked!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}