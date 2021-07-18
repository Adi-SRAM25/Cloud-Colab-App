package com.example.aditya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

public class Tokenservice extends FirebaseMessagingService {


    @Override
    public void onNewToken(String token) {
        Log.d("device token", "Refreshed token: " + token);

    }
}
