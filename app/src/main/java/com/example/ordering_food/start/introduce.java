package com.example.ordering_food.start;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.ordering_food.R;
import com.example.ordering_food.home.Home;

public class introduce extends AppCompatActivity {
    Button btnLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        getSupportActionBar().hide();
        anhXa();
        eventButton();

    }
    //region Ánh Xạ

    void anhXa()
    {
       btnLocation = (Button) findViewById(R.id.btnLocation);
    }
    //endgion

    // region Event
    void eventButton()
    {
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(introduce.this , Home.class);
                startActivity(intent);
            }
        });
    }
    // endgion





}