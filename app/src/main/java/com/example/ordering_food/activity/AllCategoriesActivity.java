package com.example.ordering_food.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.ordering_food.R;
import com.example.ordering_food.adapter.categories_Adapter;
import com.example.ordering_food.adapter.trending_Adapter;
import com.example.ordering_food.home.Home;
import com.example.ordering_food.mode.Categories;
import com.example.ordering_food.mode.FoodMode;

import java.util.ArrayList;

public class AllCategoriesActivity extends AppCompatActivity {
    ImageView imgBackAllCategories ;
    ArrayList<Categories> listCategories ;
    categories_Adapter adapterCategories ;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        getSupportActionBar().hide();
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        anhXa();
        recyclerView.setLayoutManager(manager);
        ArrayList<Categories> list  = new ArrayList<>();
        Intent intentGet = getIntent();
        list = (ArrayList<Categories>) getIntent().getSerializableExtra("dataCategories");
        if (list!=null)
        {
            adapterCategories = new categories_Adapter(getApplicationContext(),list);
            recyclerView.setAdapter(adapterCategories);
        }
        imgBackAllCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Home.class);
                startActivity(intent);
            }
        });
//        listCategories = new ArrayList<>();
//        listCategories.add(new Categories("Vegetarian" , R.drawable.chay));
//        listCategories.add(new Categories("Pizza" , R.drawable.pizzaicon));
//        listCategories.add(new Categories("Fried Chicken" , R.drawable.chicken));
//        listCategories.add(new Categories("Rice" , R.drawable.rice));
//        listCategories.add(new Categories("Soup" , R.drawable.soup));
//        listCategories.add(new Categories("Burger" , R.drawable.burger));
//        listCategories.add(new Categories("Cake" , R.drawable.cake));
//        listCategories.add(new Categories("Water" , R.drawable.water));
//        listCategories.add(new Categories("Vegetarian" , R.drawable.chay));
//        listCategories.add(new Categories("Pizza" , R.drawable.pizzaicon));
//        listCategories.add(new Categories("Fried Chicken" , R.drawable.chicken));
//        listCategories.add(new Categories("Rice" , R.drawable.rice));
//        listCategories.add(new Categories("Soup" , R.drawable.soup));
//        listCategories.add(new Categories("Burger" , R.drawable.burger));
//        listCategories.add(new Categories("Cake" , R.drawable.cake));
//        listCategories.add(new Categories("Water" , R.drawable.water));
//        listCategories.add(new Categories("Vegetarian" , R.drawable.chay));
//        listCategories.add(new Categories("Pizza" , R.drawable.pizzaicon));
//        listCategories.add(new Categories("Fried Chicken" , R.drawable.chicken));
//        listCategories.add(new Categories("Rice" , R.drawable.rice));
//        listCategories.add(new Categories("Soup" , R.drawable.soup));
//        listCategories.add(new Categories("Burger" , R.drawable.burger));
//        listCategories.add(new Categories("Cake" , R.drawable.cake));
//        listCategories.add(new Categories("Water" , R.drawable.water));
//        listCategories.add(new Categories("Vegetarian" , R.drawable.chay));
//        listCategories.add(new Categories("Pizza" , R.drawable.pizzaicon));
//        listCategories.add(new Categories("Fried Chicken" , R.drawable.chicken));
//        listCategories.add(new Categories("Rice" , R.drawable.rice));
//        listCategories.add(new Categories("Soup" , R.drawable.soup));
//        listCategories.add(new Categories("Burger" , R.drawable.burger));
//        listCategories.add(new Categories("Cake" , R.drawable.cake));
//        listCategories.add(new Categories("Water" , R.drawable.water));
//        adapterCategories = new categories_Adapter(getApplicationContext(), listCategories);
//        recyclerView.setAdapter(adapterCategories);
//

    }
    void anhXa()
    {
        recyclerView = findViewById(R.id.recyclerViewAllCatogaries);
        imgBackAllCategories = findViewById(R.id.imgBackAllCategories);
    }
}