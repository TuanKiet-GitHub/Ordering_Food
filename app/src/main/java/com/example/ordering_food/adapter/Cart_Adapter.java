package com.example.ordering_food.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ordering_food.R;
import com.example.ordering_food.activity.FoodCartActivity;
import com.example.ordering_food.mode.FoodMode;

import java.util.ArrayList;

import static com.example.ordering_food.fragment.HomeFragment.listCart;
import static com.example.ordering_food.fragment.HomeFragment.sumCart;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.ViewHolder> {
    Context context;
    public static ArrayList<FoodMode> listMonAn ;

    public Cart_Adapter() {

    }

    public Cart_Adapter(Context context, ArrayList<FoodMode> modes)
    {
        this.context = context ;
        this.listMonAn = modes ;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_food, parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FoodMode foodMode = listMonAn.get(position);
//         holder.imageView.setImageResource(foodMode.getImage());
        Uri uri = Uri.parse(foodMode.getImage().toString().trim());
        Glide.with(context).load(uri).fitCenter().into(holder.img);
        holder.txtNameFood.setText(foodMode.getTitle());
        holder.txtPrice.setText(foodMode.getPrice().toString());
        holder.txtCount.setText(foodMode.getCount().toString());
        holder.view.setTag(position);
    }

    @Override
    public int getItemCount() {
        return listMonAn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img ;
        View view ;
        TextView txtNameFood, txtPrice , txtCount , txtPriceCart;
        ImageButton imgBtnAdd , imgBtnRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            img = itemView.findViewById(R.id.imgCartFood);
            txtNameFood = itemView.findViewById(R.id.txtNameCartFood);
            txtPrice = itemView.findViewById(R.id.txtPriceCartFood);
            txtCount = itemView.findViewById(R.id.txtCountCartFood);
            imgBtnAdd = itemView.findViewById(R.id.ImgAddCountCartFood);
            imgBtnRemove = itemView.findViewById(R.id.ImgRemoveCountCartFood);
            txtPriceCart = itemView.findViewById(R.id.priceCartFoodCart);

            imgBtnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   txtCount.setText(Integer.parseInt(txtCount.getText().toString()) + 1 + "" );
                  // Double gia = Double.parseDouble(listMonAn.get(Integer.parseInt(view.getTag().toString())).getPrice()) + sumCart;
              //     Log.e("GIA" , gia + "");
             //      txtPriceCart.setText(gia+"$");
                }
            });
            imgBtnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt(txtCount.getText().toString()) > 1)
                    {
                        txtCount.setText(Integer.parseInt(txtCount.getText().toString()) - 1 +"");

                      //  Log.e("tag", view.getTag() + "");
                    }
                    else
                    {
                        // CODE Xóa item RecyclerView
                        listMonAn.remove(Integer.parseInt(view.getTag().toString()));
                        notifyItemRemoved(Integer.parseInt(view.getTag().toString()));
                        notifyItemRangeChanged(Integer.parseInt(view.getTag().toString()), listMonAn.size());
//                       activity.resetRecyclerview(Integer.parseInt(view.getTag().toString()));
                     //  notifyDataSetChanged();
                    }
                    //Log.e("Cart1", "" + Integer.parseInt(view.getTag().toString()) + " | " + txtPrice.getText());
                }
            });
        }
    }
}
