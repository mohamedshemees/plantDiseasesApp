package com.example.imageclassification;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    TextView diseaseName,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        diseaseName=findViewById(R.id.diseaseName);
        description=findViewById(R.id.description);

        String disease = getIntent().getStringExtra("diseaseName");
        String descript = getIntent().getStringExtra("description");

        diseaseName.setText(disease);
        description.setText(descript);
        description.setMovementMethod(new ScrollingMovementMethod());
    }
}