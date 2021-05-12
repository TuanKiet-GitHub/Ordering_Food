package com.example.ordering_food.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ordering_food.R;
import com.example.ordering_food.adapter.trending_Adapter;
import com.example.ordering_food.home.Home;
import com.example.ordering_food.mode.FoodMode;

import java.util.ArrayList;
import java.util.List;

public class AllTrendingActivity extends AppCompatActivity {
    ImageView imgBackAllTrending ;
    ArrayList<FoodMode> list ;
    trending_Adapter adapterFood ;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trending);
        getSupportActionBar().hide();
        anhXa();
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
              list  = new ArrayList<>();
        Intent intentGet = getIntent();
// Dùng bundle truyền vào list bị null
//        Bundle bundle = intentGet.getBundleExtra("dataFood");
//        if (bundle!=null)
//        {
//            list = (ArrayList<FoodMode>) bundle.getSerializable("foodMode");
//            adapterFood = new trending_Adapter(getApplicationContext(), list);
//            recyclerView.setAdapter(adapterFood);
//        }
        list = (ArrayList<FoodMode>) getIntent().getSerializableExtra("dataFood");
        if (list!=null)
        {
            adapterFood = new trending_Adapter(getApplicationContext(), list);
            recyclerView.setAdapter(adapterFood);
        }




//        listFood = new ArrayList<>();
//        listFood.add(new FoodMode(R.drawable.hamburger,"Hambuger","KoreanBB", "25.00"));
//        listFood.add(new FoodMode(R.drawable.garan,"Gà Rán","KFC", "30.00"));
//        listFood.add(new FoodMode(R.drawable.pho,"Phở","Phở Hà Nội", "40.00"));
//        listFood.add(new FoodMode(R.drawable.pizza,"Pizza","Pizza Hurt", "20.00"));
//        listFood.add(new FoodMode(R.drawable.laubo,"Lẩu bò đặc biệt","Lẩu 99", "25.00"));
//        listFood.add(new FoodMode(R.drawable.lauhaisan,"Lẩu hải sản","Lẩu 99", "30.00"));
//        listFood.add(new FoodMode(R.drawable.bunbohue,"Bún bò huế","Bún sinh viên", "40.00"));
//        listFood.add(new FoodMode(R.drawable.comchien,"Cơm chiên","Cơm cây me", "20.00"));
//        listFood.add(new FoodMode(R.drawable.xoiga,"Xôi gà","Xôi gà ninh kiều", "30.00"));
//        listFood.add(new FoodMode(R.drawable.cafe,"Cafe","Cafe AMI", "40.00"));
//        listFood.add(new FoodMode(R.drawable.epkhom,"Ép khóm","Cafe AMI", "20.00"));
//        adapterFood = new trending_Adapter(getApplicationContext(), listFood);
//        recyclerView.setAdapter(adapterFood);

        imgBackAllTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Home.class);
                startActivity(intent);
            }
        });


    }
    void anhXa()
    {
        recyclerView = findViewById(R.id.recyclerViewAllTrending);
        imgBackAllTrending = findViewById(R.id.imgBackAllTrending);
    }
}