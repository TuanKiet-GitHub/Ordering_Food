package com.example.ordering_food.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ordering_food.R;
import com.example.ordering_food.adapter.categories_Adapter;
import com.example.ordering_food.adapter.trending_Adapter;
import com.example.ordering_food.home.Home;
import com.example.ordering_food.mode.FoodMode;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.ordering_food.adapter.trending_Adapter.listMonAn;

public class listCategoriesActivity extends AppCompatActivity {
    TextView txtId;
    ImageView imgBack;
    // trending_Adapter adapterFood ;
    RecyclerView recyclerView;
    ArrayList<FoodMode> list;
    FirebaseRecyclerAdapter<FoodMode, trending_Adapter.ViewHolder> adapter;
    Query post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);
        getSupportActionBar().hide();
        anhXa();
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        Intent intent = getIntent();
        String id = intent.getStringExtra("CategoriesId");
        //   txtId.setText(id);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listCategoriesActivity.this, Home.class);
                startActivity(intent);
            }
        });
        loadData(id);
    }

    void anhXa() {
        txtId = findViewById(R.id.txtID);
        imgBack = findViewById(R.id.imgBackListFood);
        recyclerView = findViewById(R.id.recyclerViewAllListFood);
    }

    void loadData(String id) {
       // int id1 = Integer.parseInt(id);
     //   Log.e("Loi",id + "");
     //   Log.e("Loi",id1 + "");
        String idFood = ""+id;
       if (Integer.parseInt(id) < 10){
            idFood = "0" + id;
       }

       Query query = FirebaseDatabase.getInstance().getReference().child("FoodMode").orderByChild("menuId").startAt(idFood).endAt(idFood+ "\uf8ff");
        FirebaseRecyclerOptions<FoodMode> options = new FirebaseRecyclerOptions.Builder<FoodMode>().setQuery(
               query , FoodMode.class).build();
        FirebaseRecyclerAdapter<FoodMode, FoodViewHolder> adapter = new FirebaseRecyclerAdapter<FoodMode, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull FoodMode model) {
                Uri uri = Uri.parse(model.getImage().toString().trim());
                Glide.with(getApplicationContext()).load(uri).fitCenter().into(holder.image);
                holder.name.setText(model.getName());
                holder.title.setText(model.getTitle());
                holder.price.setText(model.getPrice().toString());
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), OrderPageActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Bundle bundle = new Bundle();
                        //  int position = Integer.parseInt(v.getTag().toString()) ;
                        bundle.putSerializable("foodMode", (Serializable) model);
                        intent.putExtra("data", bundle);
                        v.getContext().startActivity(intent);
                    }
                });
            }
            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trending, parent,false);
                return  new FoodViewHolder(view);
            }
        };
        adapter.notifyDataSetChanged();
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        public ImageView image ;
        public TextView title , price, name;
        public  View view ;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            image = itemView.findViewById(R.id.imageFood);
            title = itemView.findViewById(R.id.titleFood);
            price = itemView.findViewById(R.id.priceFood);
            name = itemView.findViewById(R.id.name);
        }
    }
}