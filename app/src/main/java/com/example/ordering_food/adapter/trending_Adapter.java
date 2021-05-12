package com.example.ordering_food.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ordering_food.R;
import com.example.ordering_food.activity.AllCategoriesActivity;
import com.example.ordering_food.activity.OrderPageActivity;
import com.example.ordering_food.mode.FoodMode;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.ordering_food.R.drawable.heartdo;

public class trending_Adapter extends RecyclerView.Adapter<trending_Adapter.ViewHolder> {

    static Context context;
    public static ArrayList<FoodMode> listMonAn ;

    public trending_Adapter() {
    }

    public trending_Adapter(Context context, ArrayList<FoodMode> modes)
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
         final FoodMode foodMode = listMonAn.get(position);
//         holder.imageView.setImageResource(foodMode.getImage());
        Uri uri = Uri.parse(foodMode.getImage().toString().trim());
        Glide.with(context).load(uri).fitCenter().into(holder.image);
         holder.name.setText(foodMode.getName());
         holder.title.setText(foodMode.getTitle());
         holder.price.setText(foodMode.getPrice().toString());
         holder.view.setTag(position);
        Log.e("LOI", "on Bind View Holder Adapter: " + position);
    }
    @Override
    public int getItemCount() {
        return listMonAn.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView image ;
        public TextView title , price, name;
        public  View view ;
        public ImageView imgFavorites;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imgFavorites = itemView.findViewById(R.id.idFavorites);
            image = itemView.findViewById(R.id.imageFood);
            title = itemView.findViewById(R.id.titleFood);
            price = itemView.findViewById(R.id.priceFood);
            name = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
            imgFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     
                }
            });
        }
        @Override
        public void onClick(View v) {
            // Xét hai trường hợp : trường hợp 1 từ frament chuyển qua activity
            try
            {
                Activity activity= (Activity) v.getContext();
                Intent intent = new Intent(activity, OrderPageActivity.class);
                Bundle bundle = new Bundle();
                int position = Integer.parseInt(v.getTag().toString());
                bundle.putSerializable("foodMode", (Serializable) listMonAn.get(position));
                intent.putExtra("data", bundle);
                activity.startActivity(intent);
              //  Log.e("LOI", "Vào try trending : " + listMonAn.size());
            }
            // Trường hợp 2 từ activity chuyển sang activity phải có dòng code intent.setFlags
            catch (Exception e)
            {
                Intent intent = new Intent(context, OrderPageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
              //  int position = Integer.parseInt(v.getTag().toString()) ;
                bundle.putSerializable("foodMode", (Serializable) listMonAn.get(getAdapterPosition()));
                intent.putExtra("data", bundle);
                context.startActivity(intent);
              // Log.e("LOI", "Vào catch trending : " + listMonAn.size());
            }
        }
    }
}
