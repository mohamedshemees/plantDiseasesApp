package com.example.imageclassification;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imageclassification.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DamageActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(DamageActivity.this, R.color.white_transparent));

        Button button = findViewById(R.id.CropDamagePredictionbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    EditText num1 = findViewById(R.id.EstimatedInsectsCount);
                    EditText num2 = findViewById(R.id.CropType);
                    EditText num3 = findViewById(R.id.SoilType);
                    EditText num4 = findViewById(R.id.PesticideUseCategory);
                    EditText num5 = findViewById(R.id.NumberDosesWeek);
                    EditText num6 = findViewById(R.id.NumberWeeksUsed);
                    EditText num7 = findViewById(R.id.NumberWeeksQuit);
                    EditText num8 = findViewById(R.id.Season);

                    String value1 = num1.getText().toString();
                    String value2 = num2.getText().toString();
                    String value3 = num3.getText().toString();
                    String value4 = num4.getText().toString();
                    String value5 = num5.getText().toString();
                    String value6 = num6.getText().toString();
                    String value7 = num7.getText().toString();
                    String value8 = num8.getText().toString();

                    String All=value1+","+value2+","+value3+","+value4+","+value5+","+value6+","+value7+","+value8;



                    if (All != null ) {


                        OkHttpClient okHttpClient = new OkHttpClient();

                        RequestBody formbody = new FormBody.Builder().add("CropInputs", All).build();
    //                            .add("num2", value2)
    //                            .add("num3",value3)
    //                            .add("num4", value4)
    //                            .add("num5",value5)
    //                            .add("num6", value6)
    //                            .add("num7",value7)
    //                            .add("num8", value8)
    //                            .build();

                        try {


                            Request request = new Request.Builder().url("http://192.168.1.16:5000/CropDamage").post(formbody).build();
                            okHttpClient.newCall(request).enqueue(new Callback() {

                                @Override
                                public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) throws IOException {
                                    TextView textView = findViewById(R.id.textview);

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                textView.setText(response.body().string());
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(DamageActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }

                            });
                        } catch (Exception e) {

                        }
                    }
            }
        });


    }
    }



