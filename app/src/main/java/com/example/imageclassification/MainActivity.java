package com.example.imageclassification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button selectBtn,predictBtn,captureBtn;
    TextView result;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.white_transparent));


//       Start Wheat Button

        Button wheatbutton = findViewById(R.id.wheatbtn);
        wheatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WheatActivity.class);
                startActivity(intent);
            }
        });
//       End Wheat Button
//       Start Potato Button
        Button potatobutton = findViewById(R.id.potatoBtn);
        potatobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PotatoActivity.class);
                startActivity(intent);
            }
        });
//       End Potato Button
//       Start Tomato Button
        Button tomatobutton = findViewById(R.id.tomatoBtn);
        tomatobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TomatoActivity.class);
                startActivity(intent);
            }
        });
//       Start Tomato Button
//       Start Paper Button
        Button paperbutton = findViewById(R.id.paperBtn);
        paperbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PaperActivity.class);
                startActivity(intent);
            }
        });
//       End Paper Button
    }
}






























//package com.example.imageclassification;
//
//        import android.content.Intent;
//        import android.os.Build;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.Button;
//
//        import androidx.annotation.RequiresApi;
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.core.content.ContextCompat;
//
//public class IntroActivity extends AppCompatActivity {
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_intro);
//
//        getWindow().setStatusBarColor(ContextCompat.getColor(IntroActivity.this, R.color.green));
//
//
////       Start Wheat Button
//
//        Button getbutton = findViewById(R.id.getbtn);
//        getbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
////       End Wheat Button
//
//    }
//}



