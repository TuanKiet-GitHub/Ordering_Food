package com.example.ordering_food.adapter;

import android.content.Context;
import android.net.Uri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.ordering_food.R;
import com.example.ordering_food.mode.Categories;
import com.example.ordering_food.mode.Slide;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class slide_adapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    private List<Slide> listSlide = new ArrayList<>();
    private DatabaseReference slide =  FirebaseDatabase.getInstance().getReference("Slide");
    public slide_adapter(Context context) {
        this.context = context;

        // NEU KHONG CO NOTIFYDATACHAGED thi list se bi xoa hinh hk hien thi ra ben ngoai.
        slide.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
                      final   Slide item =npsnapshot.getValue(Slide.class);
                        listSlide.add(item);
                        notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
//    int image [] = {R.drawable.hinh1 , R.drawable.hinh2, R.drawable.hinh3 , R.drawable.hinh4};
    @Override
    public int getCount() {
        return listSlide.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView imageView = view.findViewById(R.id.imageSlide);
        Glide.with(context).load(listSlide.get(position).getImage().toString().trim()).centerCrop().into(imageView);
        container.addView(view);
        return view;
    }
}
