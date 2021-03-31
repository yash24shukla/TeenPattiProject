package com.teenpatti.teenpattipremium.files;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.teenpatti.teenpattipremium.R;
import com.teenpatti.teenpattipremium.activity.Splash_Activity;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    static int notificationId = 1;
    private NotificationUtils notificationUtils;
    String url_image = "";
    String url_open = "";
    String title = "";
    String desc = "";
    String is_image = "";
    String is_url = "";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.e(TAG, "From : " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);

            notificationManager.createNotificationChannel(mChannel);

        }

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        try {
            String body = remoteMessage.getData().toString();
            JSONObject json = new JSONObject(remoteMessage.getData());
            Log.d("json", json.toString());
            url_open = json.getString("open_url");
            title = json.getString("title");
            desc = json.getString("desc");
            is_image = json.getString("is_image");
            is_url = json.getString("is_url_open");
            url_image = json.getString("image_url");


            Log.e("notifica", "notificationnn sentnnnnn");


            Intent intent;
            if (is_url.equals("1")) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_open));

            } else {
                intent = new Intent(this, Splash_Activity.class);
            }


            NotificationCompat.Builder builder;
            if (is_image.equals("1")) {

                Bitmap b = getBitmapfromUrl();

                builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                        .setSmallIcon(R.drawable.logooo_spalsh)
                        .setSound(defaultSoundUri)
                        .setColor(getResources().getColor(R.color.colorAccent))

                        .setContentTitle(title)
                        .setLargeIcon(b)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(b)
                                .bigLargeIcon(null))
                        .setContentText(desc);

            } else {

                builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                        .setSmallIcon(R.drawable.logooo_spalsh)
                        .setSound(defaultSoundUri)
                        .setContentTitle(title)
                        .setColor(getResources().getColor(R.color.colorAccent))
                        .setPriority(Notification.PRIORITY_MAX)
                        .setContentText(desc);

            }

            builder.setVibrate(new long[]{500, 500});
            builder.setLights(Color.RED, 3000, 3000);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
            stackBuilder.addNextIntent(intent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            builder.setContentIntent(resultPendingIntent);
            notificationId++;
            notificationManager.notify(notificationId, builder.build());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public Bitmap getBitmapfromUrl() {

        try {
            URL url = new URL(url_image);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            Log.e("awesome", "Error in getting notification image: " + e.getLocalizedMessage());
            return null;
        }
    }


  /*  private class sendNotification extends AsyncTask<String, Void, Bitmap> {

        String message;

        @Override
        protected Bitmap doInBackground(String... params) {


            Log.e("send noti", "send nottiiii");

            InputStream in;
            message = "message";
            try {

                URL url = new URL("http://api.astechnolabs.com//image//advertize-as-valentine.jpg");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

            super.onPostExecute(result);


            CustomNotification(result);

        }
    }*/


    public void CustomNotification() {


    }


}
