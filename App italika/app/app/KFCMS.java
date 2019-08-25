package com.example.italika.app;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.example.italika.Class.SplashScreen;
import com.example.italika.R;
import com.example.italika.Request.Login;
import com.example.italika.Request.Request;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class KFCMS extends FirebaseMessagingService {

    public static NotificationManager notificationManager;
    public static int idN=0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e("---> ", remoteMessage.getData().get("action"));

        showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), remoteMessage.getData().get("action"), remoteMessage.getData().get("key"));

    }

    private void showNotification(String title, String body, String action, String id) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL = "app.italika.com";
        idN=Integer.valueOf(id);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL, "NOTIFICATION_CHANNEL_NAME", NotificationManager.IMPORTANCE_MAX);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setPriority(NotificationManager.IMPORTANCE_MAX)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setColor(Color.TRANSPARENT);

        Intent resultIntent = new Intent(App.getContext() , SplashScreen.class);
        resultIntent.putExtra("action", action);
        resultIntent.putExtra("key", id);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(App.getContext(),
                0, resultIntent,
                PendingIntent.FLAG_ONE_SHOT);

        mBuilder.setContentIntent(resultPendingIntent);


        notificationManager.notify(idN, mBuilder.build());




    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Request.execute("ft/?token="+s+"&idLogin="+ Login.Instance().getId(), null);

    }


}
