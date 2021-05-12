package com.example.ordering_food.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ordering_food.R;
import com.example.ordering_food.fragment.AccountFragment;
import com.example.ordering_food.fragment.SuccessFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.ordering_food.fragment.LoginFragment.UId;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText edName , edPhone , edAddress ;
    View view;
    ImageButton imgBack;
    Button btnUpdateInformation ;

    public InformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
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
        view = inflater.inflate(R.layout.fragment_information, container, false);
        imgBack = view.findViewById(R.id.viewBackInformation);
        edAddress = view.findViewById(R.id.edAddressUpdateInformation);
        edName = view.findViewById(R.id.edNameUpdataInformation);
        edPhone = view.findViewById(R.id.edPhoneUpdatePhone);
        btnUpdateInformation = view.findViewById(R.id.updateInformation);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               getFragmentManager().popBackStack();

            }
        });
        btnUpdateInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference user =  FirebaseDatabase.getInstance().getReference("Users");
                user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            user.child(UId+"/phone").setValue(edPhone.getText().toString());
                            user.child(UId+"/address").setValue(edAddress.getText().toString());
                            user.child(UId+"/name").setValue(edName.getText().toString());
                            // dùng code này chứ không dùng code dưới không thể chuyển qua được
//                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                            fragmentTransaction.add(R.id.fragmentAccount, new UpdatePassFragment());
//                            fragmentTransaction.addToBackStack(null);
//                            fragmentTransaction.commit();
                            getFragmentManager().beginTransaction().add(R.id.fragmentInformation , new SuccessFragment()).commit();
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getContext(), "Cập nhật không thành công ! " , Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        return view;
    }
}