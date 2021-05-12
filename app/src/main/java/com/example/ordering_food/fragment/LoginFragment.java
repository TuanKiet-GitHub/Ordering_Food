package com.example.ordering_food.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordering_food.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static boolean check = false;
    public static String passWord = "";
    public static String UId = "";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view ;
    EditText editEmail , editPass ;
    Button btnLogin ;
    ImageView facebookLogin , googleLogin ;
    TextView forgot ;

    FirebaseAuth auth ;
    public LoginFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        editEmail = view.findViewById(R.id.emailLogin);
        editPass = view.findViewById(R.id.passwordLogin);
        btnLogin = view.findViewById(R.id.btnLogin);
        facebookLogin = view.findViewById(R.id.facebookLogin);
        googleLogin = view.findViewById(R.id.googleLogin);
        forgot = view.findViewById(R.id.tvforgotPassword);
        auth = FirebaseAuth.getInstance();
        eventClick();
        return view ;
    }
    void eventClick()
    {
        editPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editPass.setHint("");
            }
        });
        editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editEmail.setHint("");
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =editEmail.getText().toString().trim();
                String pass = editPass.getText().toString().trim();
                if(email.isEmpty())
                {
                    Toast.makeText(getContext() , "Email không được để trống !!!" , Toast.LENGTH_LONG).show();
                    editEmail.requestFocus();
                    return;
                }
                if(pass.isEmpty())
                {
                    Toast.makeText(getContext() , "Password không được để trống !!!" , Toast.LENGTH_LONG).show();
                    editPass.requestFocus();
                    return;
                }
                login(email, pass);

            }
        });
        facebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext() , "Login Facebook ON CLICK ", Toast.LENGTH_SHORT).show();
            }
        });
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext() , "Login Google ON CLICK ", Toast.LENGTH_SHORT).show();
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "CLick Forgot " , Toast.LENGTH_SHORT).show();
            }
        });


    }
    void login(String user , String password)
    {

         auth.signInWithEmailAndPassword(user, password ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    UId = user.getUid();
                    if (user.isEmailVerified())
                    {
                        check = true;
                        passWord = password ;
                        getFragmentManager().beginTransaction().replace(R.id.fragmentProfile, new AccountFragment()).commit();
                    }
                    else
                    {
                        user.sendEmailVerification();
                        Toast.makeText(getContext(),"Bạn vừa tạo tài khoản vui lòng xác nhận email trước khi đăng nhập!" , Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getContext() , "Login FAIL !!!", Toast.LENGTH_SHORT).show();
                }
             }
         });
    }

}