package com.example.ordering_food.profile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ordering_food.MainActivity;
import com.example.ordering_food.R;
import com.example.ordering_food.fragment.AccountFragment;
import com.example.ordering_food.fragment.SuccessFragment;
import com.example.ordering_food.start.introduce;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.ordering_food.fragment.LoginFragment.passWord;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdatePassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdatePassFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseAuth auth ;
    FirebaseUser user ;
    View view;
    LinearLayout linearLayout ;
    ImageButton viewBack;
    ImageView imgCurrentPass, imgNewPass, imgConfirmPass;
    Button btnUpdatePassword ;
    EditText edCurrentPass , edNewPass, edConfirmPass;
    Boolean checkCurrennt = false, checkNewPass = false;

    public UpdatePassFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdatePassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdatePassFragment newInstance(String param1, String param2) {
        UpdatePassFragment fragment = new UpdatePassFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        view = inflater.inflate(R.layout.fragment_update_pass, container, false);
        viewBack = view.findViewById(R.id.viewBack);
        edCurrentPass = view.findViewById(R.id.edCurrentPass);
        edNewPass = view.findViewById(R.id.edNewPass);
        edConfirmPass = view.findViewById(R.id.edConfirmNewPass);
        btnUpdatePassword = view.findViewById(R.id.updatePassWord);
        imgNewPass = view.findViewById(R.id.imgNewPass);
        imgConfirmPass = view.findViewById(R.id.imgConfirmNewPass);
        imgCurrentPass = view.findViewById(R.id.imgCurrentPass);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        eventClick();
        return view ;
    }
    void eventClick()
    {

        viewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String newPassWord = edConfirmPass.getText().toString().trim();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.updatePassword(newPassWord).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            getFragmentManager().beginTransaction().add(R.id.fragmentUpdatePass , new SuccessFragment()).commit();
                            Toast.makeText(getContext(), "CLICK UPDATE", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "UPDATE PASSWORD FAIL !!! ", Toast.LENGTH_SHORT).show();
                        }
                    });


//                    getFragmentManager().beginTransaction().add(R.id.fragmentUpdatePass , new SuccessFragment()).commit();
//                    Toast.makeText(getContext(), "CLICK UPDATE", Toast.LENGTH_SHORT).show();
            }
        });
        edCurrentPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edCurrentPass.getText().toString().trim().equals(passWord))
                {
                    imgCurrentPass.setVisibility(View.VISIBLE);
                    checkCurrennt = true ;
                }
                else
                {
                    imgCurrentPass.setVisibility(View.INVISIBLE);
                }
            }
        });
        edNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 if(edNewPass.getText().length() > 8)
                 {
                     imgNewPass.setVisibility(View.VISIBLE);
                     checkNewPass =true ;
                 }
                 else
                 {
                     imgNewPass.setVisibility(View.INVISIBLE);
                 }
            }
        });
        edConfirmPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edNewPass.getText().toString().trim().equals(edConfirmPass.getText().toString().trim()))
                {
                    imgConfirmPass.setVisibility(View.VISIBLE);
                    btnUpdatePassword.setBackgroundColor(btnUpdatePassword.getContext().getResources().getColor(R.color.red));
                    btnUpdatePassword.setEnabled(true);
                }
                else {
                    imgConfirmPass.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}