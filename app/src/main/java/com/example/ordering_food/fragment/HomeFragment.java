package com.example.ordering_food.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordering_food.R;
import com.example.ordering_food.activity.AllCategoriesActivity;
import com.example.ordering_food.activity.AllTrendingActivity;
import com.example.ordering_food.activity.FoodCartActivity;
import com.example.ordering_food.adapter.categories_Adapter;
import com.example.ordering_food.adapter.discount_Adapter;
import com.example.ordering_food.adapter.slide_adapter;
import com.example.ordering_food.adapter.trending_Adapter;
import com.example.ordering_food.mode.Categories;
import com.example.ordering_food.mode.FoodMode;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters


    private String mParam1;
    private String mParam2;
    public static Double sumCart = 0.0 ;
    ImageView imgCartFood;
    EditText edWantToEat ;
    TextView ImgviewAllTrending , ImgviewAllCategories;
    ViewPager viewPager ;
    LinearLayout linearLayout ;
    RecyclerView recyclerFood , recyclerCategories , recyclerDisCount ;
    ArrayList<FoodMode> listFood;
    ArrayList<Categories> listCategories;
    ArrayList<FoodMode> listDisCount;
    trending_Adapter adapterFood ;
    discount_Adapter adapterDiscount ;
    categories_Adapter adapterCategories ;
    public static ArrayList<FoodMode> listCart = new ArrayList<>();
    private  View view ;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_home, container, false);
         imgCartFood = view.findViewById(R.id.imgCartFoodHomeFragment);
          recyclerFood = view.findViewById(R.id.recycTrending);
          recyclerDisCount = view.findViewById(R.id.recycDiscount);
          recyclerCategories = view.findViewById(R.id.recycCategories);
          recyclerCategories.setHasFixedSize(true);


          imgCartFood.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(view.getContext(), FoodCartActivity.class);
                  startActivity(intent);
              }
          });
        final DatabaseReference food =  FirebaseDatabase.getInstance().getReference("FoodMode");
        listFood = new ArrayList<>();
        food.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
                         FoodMode item =npsnapshot.getValue(FoodMode.class);
                         item.setId(snapshot.getKey());
                         listFood.add(item);
                   //      Toast.makeText(getContext(), "ONDATACHANGE", Toast.LENGTH_SHORT).show();
                    }
                    adapterFood = new trending_Adapter(getContext(), listFood);
                    recyclerFood.setAdapter(adapterFood);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final DatabaseReference categories =  FirebaseDatabase.getInstance().getReference("Categories");
        listCategories = new ArrayList<>();
        categories.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
                        Categories item =npsnapshot.getValue(Categories.class);
                        listCategories.add(item);
                    }
                    adapterCategories = new categories_Adapter(getContext(),listCategories);
                    recyclerCategories.setAdapter(adapterCategories);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        viewPager = view.findViewById(R.id.viewPager);
        linearLayout = view.findViewById(R.id.linerViewPager);
        slide_adapter slide_adapter = new slide_adapter(getContext());
        viewPager.setAdapter(slide_adapter);
        listDisCount = new ArrayList<>();
        listDisCount.add(new FoodMode(R.drawable.pizza,"Pizza","Pizza Hurt", "20.00"));
        listDisCount.add(new FoodMode(R.drawable.bunbohue,"Bún bò huế","Bún sinh viên", "40.00"));
        listDisCount.add(new FoodMode(R.drawable.comchien,"Cơm chiên","Cơm cây me", "20.00"));
        listDisCount.add(new FoodMode(R.drawable.cafe,"Cafe","Cafe AMI", "40.00"));
        listDisCount.add(new FoodMode(R.drawable.epkhom,"Ép khóm","Cafe AMI", "20.00"));
        adapterDiscount = new discount_Adapter(container.getContext() , listDisCount);
        recyclerDisCount.setAdapter(adapterDiscount);
//        adapterFood = new discount_Adapter(container.getContext(), listDisCount);
//        recyclerDisCount.setAdapter(adapterFood);

        ImgviewAllTrending = view.findViewById(R.id.idtvallTrendingHome);
        ImgviewAllTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AllTrendingActivity.class);
                intent.putExtra("dataFood", listFood);
                //SỬ DỤNG BUNDLE TRUYỀN VÀO LIST BỊ NULL NÊN DÙNG intent.putExtra
//                Bundle bundle = new Bundle();
//
//                bundle.putSerializable("listFood", listFood);
//                intent.putExtra("dataFood", bundle);
                startActivity(intent);
            }
        });
        ImgviewAllCategories = view.findViewById(R.id.idtvallCategoriesHome);
        ImgviewAllCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AllCategoriesActivity.class);
                intent.putExtra("dataCategories", listCategories);
                startActivity(intent);
            }
        });
        edWantToEat = view.findViewById(R.id.edWantToeat);
        edWantToEat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                edWantToEat.setHint("");
            }
        });
        // Inflate the layout for this fragment
        return  view ;
    }
}