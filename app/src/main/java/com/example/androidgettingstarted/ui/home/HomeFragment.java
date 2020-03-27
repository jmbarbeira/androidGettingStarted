package com.example.androidgettingstarted.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.androidgettingstarted.MyClass;
import com.example.androidgettingstarted.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.math.BigInteger;

public class HomeFragment extends Fragment {
    private Button payNowButton;
    private Button connectButton;
    private Button disconnectButton;
    public  MyClass myClass;
    private HomeViewModel homeViewModel;
    View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        myClass = new MyClass(this.getActivity());

        Button connectButton = root.findViewById(R.id.connectCardReader);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClass.connect();
            }
        });
        Button disconnectButton = root.findViewById(R.id.disconnectCardReader);
        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myClass.disconnect();
            }
        });
        final TextView calc = root.findViewById(R.id.calc);
        Button btn1 = root.findViewById(R.id.btnN1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.setText(calc.getText()+"1");
            }
        });
        Button btn2 = root.findViewById(R.id.btnN2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.setText(calc.getText()+"2");
            }
        });
        Button btn3= root.findViewById(R.id.btnN3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.setText(calc.getText()+"3");
            }
        });
        Button btn4= root.findViewById(R.id.btnN4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.setText(calc.getText()+"4");
            }
        });
        Button btn5= root.findViewById(R.id.btnN5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.setText(calc.getText()+"5");
            }
        });
        Button btn6= root.findViewById(R.id.btnN6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.setText(calc.getText()+"6");
            }
        });
        Button btn7= root.findViewById(R.id.btnN7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.setText(calc.getText()+"7");
            }
        });
        Button btn8= root.findViewById(R.id.btnN8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.setText(calc.getText()+"8");
            }
        });
        Button btn9= root.findViewById(R.id.btnN9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.setText(calc.getText()+"9");
            }
        });
        Button payNowButton =   root.findViewById(R.id.payNowBtn);
        payNowButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                myClass.pay(BigInteger.valueOf(Integer.valueOf(calc.getText().toString()).intValue()));
            }
        });
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        disconnect();

    }


    public void disconnect(){
        myClass.disconnect(); // disconnects from the card reader if the application is paused
    }



    public void initializeButtons() {




    }

}