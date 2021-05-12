package com.example.ordering_food.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordering_food.activity.OrderPageActivity;
import com.example.ordering_food.activity.listCategoriesActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.ordering_food.R;
import com.example.ordering_food.mode.Account;
import com.example.ordering_food.mode.Categories;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class categories_Adapter extends RecyclerView.Adapter<categories_Adapter.ViewHolder>{
    private Context context ;
    private ArrayList<Categories> list;
    public categories_Adapter(Context context , ArrayList<Categories> list)
    {
        this.context = context ;
        this.list = list ;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categories,parent , false );
        return new ViewHolder(view);

    }
    int positionItem ;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Categories categories = list.get(position);
        holder.textView.setText(categories.getName());
        Uri uri = Uri.parse(categories.getImage().toString().trim());
        GlideToVectorYou
                .init()
                .with(context)
                .setPlaceHolder(-1, -1)
                .load(uri, holder.imageView);
        holder.imageView.setTag(position);
        holder.view.setTag(position);

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView ;
        TextView textView ;
        View view ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            textView = (TextView) itemView.findViewById(R.id.nameCategories);
            imageView = (ImageView) itemView.findViewById(R.id.ImageCategories);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
//            Intent intent = new Intent((Activity) context , listCategoriesActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra("CategoriesId",view.getTag() + " : 1" );
//            v.getContext().startActivity(intent);
            try{
                Activity activity= (Activity) v.getContext();
                Intent intent = new Intent(activity, listCategoriesActivity.class);
                int position = Integer.parseInt(v.getTag().toString()) + 1 ;
                intent.putExtra("CategoriesId", position+"");
                v.getContext().startActivity(intent);
            //    Log.e("LOI", "Vào catch try" + position);
            }
            // Trường hợp 2 từ activity chuyển sang activity phải có dòng code intent.setFlags
            catch (Exception e)
            {
                int position = Integer.parseInt(view.getTag().toString()) + 1 ;
                Intent intent = new Intent(context, listCategoriesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("CategoriesId",position +"");
                context.startActivity(intent);
           //     Log.e("LOI", "Vào catch trending " +view.getTag() + "|" +  position);
            }

        }
    }
}
