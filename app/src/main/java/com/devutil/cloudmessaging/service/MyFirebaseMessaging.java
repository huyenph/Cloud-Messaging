package com.devutil.cloudmessaging.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.devutil.cloudmessaging.R;
import com.devutil.cloudmessaging.view.SecondActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessaging extends FirebaseMessagingService {
    public static String token;

//    @Override
//    public void onNewToken(String s) {
//        super.onNewToken(s);
//        token = s;
//        Log.d("aaa", "onNewToken: " + s);
//    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Using data
        if (remoteMessage.getData().size() > 0) {
            JSONObject jsonObject = new JSONObject(remoteMessage.getData());
            try {
                String title = jsonObject.getString("title");
                String msg = jsonObject.getString("message");
                sendNotification(title, msg, null, null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // Using notification
//        if (remoteMessage.getNotification() != null) {
//            String clickAction = remoteMessage.getNotification().getClickAction();
//            sendNotification(remoteMessage.getNotification(), clickAction);
//        }
    }

    /**
     * use data remove notification
     * {
     * 	    "data":
     *      {
     * 		    "title":"test",
     * 		    "message":"{hello/}"
     *      },
     * 	    "to":"/topics/MyTopic"
     * 	    or
     * 	    "to":"device_token"
     * }
     *
     *
     * Send FCM via data to handle click fcm
     * @param title
     * @param msg
     *
     * Send FCM via notification using  and click_action to handle click
     * @param notification
     * @param clickAction
     * add this in AndroidManifest to open special activity
     * <intent-filter>
     *     <action android:name="click_action value" />
     *     <category android:name="android.intent.category.DEFAULT" />
     * </intent-filter>
     *
     */
    private void sendNotification(String title, String msg, RemoteMessage.Notification notification, String clickAction) {
        Intent intent;
//        if (clickAction.equals("SecondActivity")) {
//            intent = new Intent(this, SecondActivity.class);
//            intent.putExtra("data", "aaabbb");
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        } else if (clickAction.equals("MainActivity")) {
//            intent = new Intent(this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        }

        intent = new Intent(this, SecondActivity.class);
        intent.putExtra("data", "Message Receive");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelID = "channel id";
        long[] pattern = {0, 100, 200, 300};
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setVibrate(pattern)
                .setDefaults(Notification.BADGE_ICON_SMALL)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelID, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }
            notificationManager.notify(0, builder.build());
        }
    }
}
