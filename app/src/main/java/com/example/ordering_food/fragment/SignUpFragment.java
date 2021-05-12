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
import android.widget.Toast;

import com.example.ordering_food.R;
import com.example.ordering_food.activity.AllCategoriesActivity;
import com.example.ordering_food.mode.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText editEmail , editPass , editConfirm ;
    View view ;
    Button btnSignUp ;
    ImageView facebookSignUp , googleSignUp ;

    FirebaseAuth auth ;

    public SignUpFragment() {
        // Required empty public constructor
        auth = FirebaseAuth.getInstance();



    }
    void eventClick()
    {
         editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
             @Override
             public void onFocusChange(View v, boolean hasFocus) {
                  editEmail.setHint("");
             }
         });

         editPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
             @Override
             public void onFocusChange(View v, boolean hasFocus) {
                 editPass.setHint("");
             }
         });
         editConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
             @Override
             public void onFocusChange(View v, boolean hasFocus) {
                 editConfirm.setHint("");
             }
         });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =editEmail.getText().toString().trim();
                String pass = editPass.getText().toString().trim();
                String confirm = editConfirm.getText().toString().trim();

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
                if(confirm.isEmpty())
                {
                    Toast.makeText(getContext() , "Confirm Password không được để trống !!!" , Toast.LENGTH_LONG).show();
                    editConfirm.requestFocus();
                    return;
                }
                if(pass.length() < 8)
                {
                    Toast.makeText(getContext(), "Password phải lớn hơn 8 kí tự !!!" , Toast.LENGTH_SHORT).show();
                }
                if (pass.equals(confirm)) {
                    auth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Account account = new Account(email,"", "", "");
                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(account).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            if (task.isSuccessful())
                                                            {
                                                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                                if (user.isEmailVerified())
                                                                {
                                                                    Toast.makeText(getContext(), "TÀI KHOẢN ĐÃ TỒN TẠI !" , Toast.LENGTH_SHORT).show();
                                                                }
                                                                else
                                                                {
                                                                    user.sendEmailVerification();
                                                                    Toast.makeText(getContext(),"Vui lòng xác nhận email của bạn !" , Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        }
                                                    });
                                                }
                                                else
                                                {
                                                    Toast.makeText(getContext(), "LỖI TRONG NE !!!!", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });

                                    } else {
                                        Toast.makeText(getContext(), "LỖI NGOÀI NÈ  !!!!", Toast.LENGTH_LONG).show();
                                    }

                                }

                            });
                }
                else
                {
                    Toast.makeText(getContext(), email +" | "+ confirm+" | " +  "Mật khẩu và mật khẩu nhập lại không trùng nhau !!!", Toast.LENGTH_SHORT).show();
                }





            }


        });
        googleSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext() , "Sign Up Google ON CLICK ", Toast.LENGTH_SHORT).show();
            }
        });
        facebookSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext() , "Sign Up Facebook ON CLICK ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    void Register(String username , String pass)
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_up , container , false) ;
        editEmail = view.findViewById(R.id.emailSignUp) ;
        editPass = view.findViewById(R.id.passSignUp);
        editConfirm = view.findViewById(R.id.confirmSignUp);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        facebookSignUp = view.findViewById(R.id.facebookSignUp);
        googleSignUp = view.findViewById(R.id.googeSignUp);
        eventClick();

        // Inflate the layout for this fragment
        return view ;

    }
}