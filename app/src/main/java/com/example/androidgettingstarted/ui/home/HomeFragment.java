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
        Button payNowButton =   root.findViewById(R.id.payNowBtn);

        myClass = new MyClass(this.getActivity());
        payNowButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                myClass.pay();
            }
        });
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