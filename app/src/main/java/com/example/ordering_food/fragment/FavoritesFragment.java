package com.example.ordering_food.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ordering_food.R;
import com.example.ordering_food.adapter.discount_Adapter;
import com.example.ordering_food.adapter.favorites_adapter;
import com.example.ordering_food.mode.FoodMode;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<FoodMode> list ;
    RecyclerView recyclerView ;
    favorites_adapter adapter ;
    private View view ;
    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewFavorites);
        list = new ArrayList<>();
        list.add(new FoodMode(R.drawable.pizza,"Pizza","Pizza Hurt", "20.00"));
        list.add(new FoodMode(R.drawable.bunbohue,"Bún bò huế","Bún sinh viên", "40.00"));
        list.add(new FoodMode(R.drawable.comchien,"Cơm chiên","Cơm cây me", "20.00"));
        list.add(new FoodMode(R.drawable.cafe,"Cafe","Cafe AMI", "40.00"));
        list.add(new FoodMode(R.drawable.epkhom,"Ép khóm","Cafe AMI", "20.00"));
        adapter= new favorites_adapter(container.getContext() , list);


        recyclerView.setAdapter(adapter);
        return view;
    }
}