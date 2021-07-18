package com.example.aditya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import com.example.aditya.MainActivity;
import com.example.aditya.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Cloudmessaging extends FirebaseMessagingService {
    public static int NOTIFICATION_ID =1;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        generateNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());

    }


    private void generateNotification(String body,String title)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri sounduri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(sounduri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(NOTIFICATION_ID>1073741824)
        {
            NOTIFICATION_ID=0;

        }

        notificationManager.notify(NOTIFICATION_ID++,notification.build());
    }
}
