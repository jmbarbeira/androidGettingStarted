package com.example.androidgettingstarted;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;
import com.handpoint.api.TransactionResult;



public class MainActivity extends FragmentActivity {
        private Button payNowButton;
        private Button connectButton;
        private Button disconnectButton;
        MyClass myClass;

        /**
         * Called when the activity is first created.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                myClass = new MyClass(this);
                initializeButtons();
        }
        public void initializeButtons() {
                payNowButton = (Button) findViewById(R.id.payNowBtn);
                /*payNowButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                                myClass.pay();
                        }
                });*/
                connectButton = (Button) findViewById(R.id.connectCardReader);
                connectButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                               // myClass.discoverDevices();

                                 myClass.connect(); // For a direct connection to the card reader
                        }
                });
                disconnectButton = (Button) findViewById(R.id.disconnectCardReader);
                disconnectButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                                myClass.disconnect();
                        }
                });
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
        public void onPause() {
                super.onPause();  // Always call the superclass method first
                myClass.disconnect(); // disconnects from the card reader if the application is paused
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
        public void onRestart() {
                super.onRestart();  // Always call the superclass method first
                initializeButtons(); // initialize the buttons again after restarting the app
        }

        @Override
        public void onDestroy() {
                super.onDestroy();
        }
}
