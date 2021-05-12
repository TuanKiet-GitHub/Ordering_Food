package com.example.ordering_food.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordering_food.R;
import com.example.ordering_food.adapter.Cart_Adapter;
import com.example.ordering_food.home.Home;

import java.util.ArrayList;

import static com.example.ordering_food.fragment.HomeFragment.listCart;
import static com.example.ordering_food.fragment.HomeFragment.sumCart;

public class FoodCartActivity extends AppCompatActivity {
    public RecyclerView recyclerViewFoodCart ;
    ImageView imgBack ;
    public TextView txtPriceCartFoodCart ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_cart);
        getSupportActionBar().hide();
        recyclerViewFoodCart = findViewById(R.id.recyclerViewCartFood);
        imgBack = findViewById(R.id.imgBackFoodCart);
        txtPriceCartFoodCart = findViewById(R.id.priceCartFoodCart);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });


        //        listDisCount = new ArrayList<>();
//        listDisCount.add(new FoodMode(R.drawable.hamburger,"Hambuger","KoreanBB", "25.00"));
//        listDisCount.add(new FoodMode(R.drawable.garan,"Gà Rán","KFC", "30.00"));
//        listDisCount.add(new FoodMode(R.drawable.pho,"Phở","Phở Hà Nội", "40.00"));
//        listDisCount.add(new FoodMode(R.drawable.pizza,"Pizza","Pizza Hurt", "20.00"));
//        listDisCount.add(new FoodMode(R.drawable.laubo,"Lẩu bò đặc biệt","Lẩu 99", "25.00"));
//        listDisCount.add(new FoodMode(R.drawable.lauhaisan,"Lẩu hải sản","Lẩu 99", "30.00"));
//        listDisCount.add(new FoodMode(R.drawable.bunbohue,"Bún bò huế","Bún sinh viên", "40.00"));
//        listDisCount.add(new FoodMode(R.drawable.comchien,"Cơm chiên","Cơm cây me", "20.00"));
//        listDisCount.add(new FoodMode(R.drawable.xoiga,"Xôi gà","Xôi gà ninh kiều", "30.00"));
//        listDisCount.add(new FoodMode(R.drawable.cafe,"Cafe","Cafe AMI", "40.00"));
//        listDisCount.add(new FoodMode(R.drawable.epkhom,"Ép khóm","Cafe AMI", "20.00"));
            Cart_Adapter cart = new Cart_Adapter(getApplicationContext(), listCart);
            recyclerViewFoodCart.setAdapter(cart);
            sumCart = 0.0 ;
            for (int i = 0 ; i < listCart.size() ; i ++)
            {
                sumCart += Integer.parseInt(listCart.get(i).getCount()) * Double.parseDouble(listCart.get(i).getPrice());
            }
            txtPriceCartFoodCart.setText(sumCart +"$");


            //Log.e("TAG" , "" + ) ;





    }
//    public void sumCart()
//    {
//        for (int i = 0 ; i < listCart.size() ; i ++)
//        {
//            sumCart += Integer.parseInt(listCart.get(i).getCount()) * Double.parseDouble(listCart.get(i).getPrice());
//        }
//        txtPriceCartFoodCart.setText(sumCart +"$");
//    }
//    public void resetRecyclerview(int position )
//    {
//        listCart.remove(position);
//        Cart_Adapter cart = new Cart_Adapter(this.getApplicationContext(), listCart);
//        recyclerViewFoodCart.setAdapter(cart);
//    }
}