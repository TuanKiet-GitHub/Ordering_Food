package com.example.ordering_food.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ordering_food.R;
import com.example.ordering_food.home.Home;
import com.example.ordering_food.mode.FoodMode;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp;

import java.util.ArrayList;

import static com.example.ordering_food.fragment.HomeFragment.listCart;

public class OrderPageActivity extends AppCompatActivity {
    public Activity activity;
    LinearLayout linearBack;
    Button btnAddCart ;
    ImageView imgBackgroundFood ;
    ImageButton btnAdd , btnRemove;
    TextView tvCount , tvSumCount;
    FoodMode foodMode;
    TextView tvTitleFood , tvNameFood, tvPriceFood ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        getSupportActionBar().hide();
        anhXa();


        Intent intentGet = getIntent();
        Bundle bundle = intentGet.getBundleExtra("data");
        if (bundle!=null)
        {
            foodMode = (FoodMode) bundle.getSerializable("foodMode");
            String title = foodMode.getTitle().toString().trim() ;
            //Toast.makeText(OrderPageActivity.this , "" + foodMode.getTitle(), Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse(foodMode.getImage().toString().trim());
            Glide.with(getApplicationContext()).load(uri).dontAnimate().centerCrop().into(imgBackgroundFood);
            imgBackgroundFood.setImageResource(R.drawable.pizza);
          //  Toast.makeText(getApplicationContext(), uri.toString() , Toast.LENGTH_SHORT).show();
            tvTitleFood.setText(foodMode.getTitle());
            tvNameFood.setText(foodMode.getName());
            tvPriceFood.setText(foodMode.getPrice());
            Log.e("ListCart" , "Oncreate :" + foodMode.getId());



        }

        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderPageActivity.this , Home.class) ;
                startActivity(intent);

            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCount.setText((Integer.parseInt(tvCount.getText().toString()) + 1 ) + "");
                double count =Double.parseDouble(tvCount.getText().toString());
                double Sum =count *(Double.parseDouble(tvPriceFood.getText().toString()));
                tvSumCount.setText(Sum + "");

            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tvCount.getText().toString()) > 0 )
                {
                    tvCount.setText((Integer.parseInt(tvCount.getText().toString()) - 1 ) + "");
                    double count =Double.parseDouble(tvCount.getText().toString());
                    double Sum =count *(Double.parseDouble(tvPriceFood.getText().toString()));
                    tvSumCount.setText(Sum + "");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Bạn không thể giảm số lượng âm được !!!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listCart.add(new FoodMode(foodMode.getId(),foodMode.getTitle(),foodMode.getName(), foodMode.getImage() ,foodMode.getPrice() , tvCount.getText().toString()));
                Log.e("ListCart" ,"ID : "  +   foodMode.getId() + "|" + foodMode.getTitle());

            }
        });

    }
    void anhXa()
    {
        linearBack = findViewById(R.id.linerBack);
        imgBackgroundFood = findViewById(R.id.imgFoodBackgroundOrderPage);
        tvNameFood = findViewById(R.id.tvNameOrderPage);
        tvPriceFood = findViewById(R.id.tvPriceOrderPage);
        tvTitleFood = findViewById(R.id.tvTitleOrderPage);
        btnAdd = findViewById(R.id.ImgButtonaddCount);
        btnRemove = findViewById(R.id.ImgButtonremoveCount);
        tvCount = findViewById(R.id.txtCount);
        tvSumCount = findViewById(R.id.txtSumCount);
        btnAddCart = findViewById(R.id.btnAddCartOrderPage);

    }
//    public int getImage(String imageName) {
//
//        int drawableResourceId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());
//
//        return drawableResourceId;
//    }




}