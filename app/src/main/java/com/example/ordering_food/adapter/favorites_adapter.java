package com.example.ordering_food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordering_food.R;
import com.example.ordering_food.mode.Categories;
import com.example.ordering_food.mode.FoodMode;

import java.util.ArrayList;

public class favorites_adapter  extends RecyclerView.Adapter<favorites_adapter.ViewHolder> {
    private Context context ;
    private ArrayList<FoodMode> list;

    public favorites_adapter(Context context , ArrayList<FoodMode> list)
    {
        this.context = context ;
        this.list = list ;
    }
    @NonNull
    @Override
    public favorites_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorites, parent,false);
        return  new favorites_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull favorites_adapter.ViewHolder holder, int position) {
        FoodMode foodMode = list.get(position);
        holder.imageView.setImageResource(foodMode.getImage2());
        holder.name.setText(foodMode.getName());
        holder.title.setText(foodMode.getTitle());
        holder.price.setText(foodMode.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView title , name , price;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageFoodFavorites);
            title = itemView.findViewById(R.id.tvTitleFavorites);
            name = itemView.findViewById(R.id.tvNameFavorites);
            price = itemView.findViewById(R.id.tvPriceFavorites);


        }
    }
}
