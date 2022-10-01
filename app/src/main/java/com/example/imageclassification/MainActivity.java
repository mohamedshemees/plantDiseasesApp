package com.example.imageclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button selectBtn,predictBtn,captureBtn;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button wheatbutton = findViewById(R.id.wheatbtn);
        wheatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WheatActivity.class);
                startActivity(intent);
            }
        });

        Button potatobutton = findViewById(R.id.potatoBtn);
        potatobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PotatoActivity.class);
                startActivity(intent);
            }
        });
        Button tomatobutton = findViewById(R.id.tomatoBtn);
        tomatobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TomatoActivity.class);
                startActivity(intent);
            }
        });
        Button paperbutton = findViewById(R.id.paperBtn);
        paperbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PaperActivity.class);
                startActivity(intent);
            }
        });
    }
}