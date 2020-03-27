package com.example.androidgettingstarted;

import android.content.Context;
import com.handpoint.api.*;

import java.math.BigInteger;
import java.util.List;

public class MyClass implements Events.Required {

    Hapi api;
    Device device;

    public MyClass(Context context) {
        initApi(context);
    }
    //An Android Context is required to be able to handle bluetooth
    public void initApi(Context context) {
        String sharedSecret = "ED9B69788639153D634C6A5E4AE25CAA015B540EB18BA40B04D74019A571A40D";
        this.api = HapiFactory.getAsyncInterface(this, context).defaultSharedSecret(sharedSecret);
        // The api is now initialized. Yay! we've even set a default shared secret!
        // The shared secret is a unique string shared between the card reader and your mobile application.
        // It prevents other people to connect to your card reader.
        // You have to replace this default shared secret by the one sent by our support team.
    }

    @Override
    public void deviceDiscoveryFinished(List<Device> list) {
        // here you get a list of Bluetooth devices paired with your android device
    }

    @Override
    public void signatureRequired(SignatureRequest signatureRequest, Device device) {
        // You'll be notified here if a sale process needs a signature verification
        // A signature verification is needed if the cardholder uses an MSR card or a chip & signature card
        // This method will not be invoked if a transaction is made with a Chip & PIN card
        // At this step, you should display the merchant receipt to the cardholder on the android device
        // The cardholder must have the possibility to accept or decline the transaction
        // If the cardholder clicks on decline, the transaction is VOID
        // If the cardholder clicks on accept he is then asked to sign electronically the receipt
        this.api.signatureResult(true);
        // This line means that the cardholder ALWAYS accepts to sign the receipt
        // For this sample app we are not going to implement the whole signature process
    }

    @Override
    public void endOfTransaction(TransactionResult transactionResult, Device device) {
        // The object TransactionResult holds the different receipts
        // Other information can be accessed through this object like the transaction ID, the amount...
    }


    public void discoverDevices(){
        this.api.listDevices(ConnectionMethod.BLUETOOTH);
        // This triggers the search for all the bluetooth devices around.
    }


    public void connect(){
        Device device = new Device("PP0918037922", "68:AA:D2:14:D9:98", "", ConnectionMethod.BLUETOOTH);
        //The address always has to be written in UPPER CASE
        //new Device("name", "address", "port", ConnectionMethod);

        this.api.useDevice(device);
    }

    public boolean pay(BigInteger a) {
        return this.api.sale(a, Currency.GBP);

        // Let´s start our first payment of 10 pounds
    }
    public void disconnect(){
        this.api.disconnect();
        //This disconnects the connection
    }



}