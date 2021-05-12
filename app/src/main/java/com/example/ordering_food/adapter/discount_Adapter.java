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
import com.example.ordering_food.mode.FoodMode;

import java.util.ArrayList;

public class discount_Adapter extends RecyclerView.Adapter<discount_Adapter.ViewHolder> {
    Context context;
    ArrayList<FoodMode> listMonAn ;
    public discount_Adapter(Context context, ArrayList<FoodMode> modes)
    {
        this.context = context ;
        this.listMonAn = modes ;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trending, parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         FoodMode foodMode = listMonAn.get(position);
         holder.imageView.setImageResource(foodMode.getImage2());
         holder.name.setText(foodMode.getName());

         holder.title.setText(foodMode.getTitle());
         holder.price.setText(foodMode.getPrice());
    }

    @Override
    public int getItemCount() {
        return listMonAn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;

        TextView title , price, name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageFood);
            title = itemView.findViewById(R.id.titleFood);
            price = itemView.findViewById(R.id.priceFood);
            name = itemView.findViewById(R.id.name);
        }
    }
}
