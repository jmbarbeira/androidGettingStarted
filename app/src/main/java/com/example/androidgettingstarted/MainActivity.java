package com.example.androidgettingstarted;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;


public class MainActivity extends AppCompatActivity {


        View navView;
        NavController navController;
        AppBarConfiguration appBarConfiguration;

        /**
         * Called when the activity is first created.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);


                NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
                AppBarConfiguration appBarConfiguration =
                        new AppBarConfiguration.Builder(navController.getGraph()).build();
                BottomNavigationView BottomNavigationView = findViewById(R.id.nav_view);


                NavigationUI.setupActionBarWithNavController(this, navController);
                NavigationUI.setupWithNavController(BottomNavigationView,navController);


        }


        public void callReceiptDialog(final String customerReceipt) {
                this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                String positiveButton = "OK";
                                WebView webView = new WebView(MainActivity.this); // Create a webView to display the receipt
                                webView.loadData(customerReceipt, "text/html", "UTF-8");
                                webView.getSettings().setDefaultFontSize(20);
                                webView.setVerticalScrollBarEnabled(true);
                                new AlertDialog.Builder(MainActivity.this)// Defines an AlertDialog that will popup
                                        .setTitle("Transaction result")
                                        .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.dismiss();
                                                }
                                        })
                                        .setView(webView)
                                        .show();
                        }
                });
        }



        @Override
        public void onResume() {
                super.onResume();  // Always call the superclass method first
        }

        @Override
        public void onBackPressed() {
                moveTaskToBack(true); // The application is moved in the background
        }

        @Override
        public void onStop() {
                super.onStop();  // Always call the superclass method first
        }



        @Override
        public void onDestroy() {
                super.onDestroy();
        }
}
