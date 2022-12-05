package com.example.imageclassification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoriesActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(CategoriesActivity.this, R.color.white_transparent));

        //       Start Plant Button

        Button plantbutton = findViewById(R.id.plantBtn);
        plantbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //       Start animal Button

        Button animalsbutton = findViewById(R.id.animalsBtn);
        animalsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this,AnimalsActivity.class);
                startActivity(intent);
            }
        });

        //       Start milk Button

        Button milkbutton = findViewById(R.id.milkBtn);
        milkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this,MilkActivity.class);
                startActivity(intent);
            }
        });

        //       Start animal Button

        Button damagebutton = findViewById(R.id.damageBtn);
        damagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesActivity.this,DamageActivity.class);
                startActivity(intent);
            }
        });
    }
}