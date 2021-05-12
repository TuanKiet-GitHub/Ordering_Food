package com.example.ordering_food.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ordering_food.R;
import com.example.ordering_food.profile.InformationFragment;
import com.example.ordering_food.profile.UpdatePassFragment;

import static com.example.ordering_food.fragment.LoginFragment.check;


public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnLogOut ;
    LinearLayout linerPass , linerInformation , linerLanguage, linerDeliveryAddress ;
    View view ;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);
        btnLogOut = view.findViewById(R.id.btnLogOut);
        linerPass = view.findViewById(R.id.linerPassword);
        linerInformation = view.findViewById(R.id.linerInformation);
        linerDeliveryAddress = view.findViewById(R.id.linerDeliveryAddress);
        linerLanguage = view.findViewById(R.id.linerLanguage);

        eventClick();
        return view ;
    }
    void eventClick()
    {
        Fragment fragment = new ProfileFragment();
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentAccount, new ProfileFragment()).commit();
              //  getFragmentManager().popBackStack();

               Toast.makeText(getContext(),"CLICK" , Toast.LENGTH_SHORT).show();
                check = false ;
            }
        });
        linerPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragmentAccount, new UpdatePassFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        linerLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setCancelable(false);
                 dialog.setContentView(R.layout.language_dialog);
                 LinearLayout linearEnglish = dialog.findViewById(R.id.linerEnglish);
                 LinearLayout linearVietNam = dialog.findViewById(R.id.linerVietNam);
                ImageView imgVietNam = dialog.findViewById(R.id.imgVietNam);
                ImageView imgEnglish = dialog.findViewById(R.id.imgEnglish);
                LinearLayout linerCannel = dialog.findViewById(R.id.linerBtnCannel);

                 linearEnglish.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                          imgEnglish.setVisibility(View.VISIBLE);
                          imgVietNam.setVisibility(View.INVISIBLE);
                     }
                 });
                 linearVietNam.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         imgEnglish.setVisibility(View.INVISIBLE);
                         imgVietNam.setVisibility(View.VISIBLE);
                     }
                 });
                 linerCannel.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         dialog.cancel();
                     }
                 });
                 dialog.show();


            }
        });
        linerDeliveryAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        linerInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                 fragmentTransaction.add(R.id.fragmentAccount, new InformationFragment());
                fragmentTransaction.addToBackStack(null);
                 fragmentTransaction.commit();
            }
        });
    }
}