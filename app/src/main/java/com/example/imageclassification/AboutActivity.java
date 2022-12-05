package com.example.imageclassification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    TextView diseaseName,treatment,information;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(AboutActivity.this, R.color.white_transparent));


        diseaseName=findViewById(R.id.diseaseName);
        treatment=findViewById(R.id.treatment);
        information=findViewById(R.id.information);
        String disease = getIntent().getStringExtra("diseaseName");
        String info = getIntent().getStringExtra("information");
        String treat = getIntent().getStringExtra("treatment");

        diseaseName.setText(disease);
        information.setText(info);
        information.setMovementMethod(new ScrollingMovementMethod());
        treatment.setText(treat);
        treatment.setMovementMethod(new ScrollingMovementMethod());
    }
}