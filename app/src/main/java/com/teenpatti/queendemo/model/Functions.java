package com.teenpatti.queendemo.model;


import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.teenpatti.queendemo.R;
import com.teenpatti.queendemo.files.SharedPrefs;

import java.io.InputStream;

/**
 * Created by pmcommu on 18/1/2018.
 */

public class Functions {
    public static InputStream ims;
    public static Drawable d;
    public static Toast toast;
    public static TextView toast_textmessage;

    public static Toast toast_game;
    public static TextView toast_textmessage_game;



    public static MediaPlayer mp;
    private static int posi = 0;
    public static int Stop_Player_Turn = 0;
    public static Context mcontext;
    public static CountDownTimer timer;

    public static Animation anim_playerturn;

    public static void setcard(Context context, ImageView imgback, ImageView imgnum, ImageView imgicon, ImageView imgbig, String islast, String type, String name) {
        try {

            mcontext = context;
            ims = context.getAssets().open("card/WhiteBase.png");
            d = Drawable.createFromStream(ims, null);
            imgback.setImageDrawable(d);

            if (type.equals("spade")) {
                ims = context.getAssets().open("card/SmallImage/4.png");
                d = Drawable.createFromStream(ims, null);
                imgicon.setImageDrawable(d);
                if (name.equals("1")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/13.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/1.png");
                } else if (name.equals("2")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/2.png");
                } else if (name.equals("3")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/3.png");
                } else if (name.equals("4")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/4.png");
                } else if (name.equals("5")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/5.png");
                } else if (name.equals("6")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/6.png");
                } else if (name.equals("7")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/7.png");
                } else if (name.equals("8")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/8.png");
                } else if (name.equals("9")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/9.png");
                } else if (name.equals("10")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/10.png");
                } else if (name.equals("11")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/10_4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/11.png");
                } else if (name.equals("12")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/11_4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/12.png");
                } else if (name.equals("13")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/12_4.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/13.png");
                }
                d = Drawable.createFromStream(ims, null);
                imgnum.setImageDrawable(d);

            } else if (type.equals("club")) {
                ims = context.getAssets().open("card/SmallImage/2.png");
                d = Drawable.createFromStream(ims, null);
                imgicon.setImageDrawable(d);


                if (name.equals("1")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/1.png");
                } else if (name.equals("2")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/2.png");
                } else if (name.equals("3")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/3.png");
                } else if (name.equals("4")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/4.png");
                } else if (name.equals("5")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/5.png");
                } else if (name.equals("6")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/6.png");
                } else if (name.equals("7")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/7.png");
                } else if (name.equals("8")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/8.png");
                } else if (name.equals("9")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/9.png");
                } else if (name.equals("10")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/10.png");
                } else if (name.equals("11")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/10_2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/11.png");
                } else if (name.equals("12")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/11_2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/12.png");
                } else if (name.equals("13")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/12_2.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/BlackRank/13.png");
                }
                d = Drawable.createFromStream(ims, null);
                imgnum.setImageDrawable(d);


            } else if (type.equals("heart")) {
                ims = context.getAssets().open("card/SmallImage/3.png");
                d = Drawable.createFromStream(ims, null);
                imgicon.setImageDrawable(d);

                if (name.equals("1")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/1.png");

                } else if (name.equals("2")) {

                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/2.png");
                } else if (name.equals("3")) {

                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/3.png");
                } else if (name.equals("4")) {

                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/4.png");
                } else if (name.equals("5")) {

                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/5.png");
                } else if (name.equals("6")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/6.png");
                } else if (name.equals("7")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/7.png");

                } else if (name.equals("8")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/8.png");

                } else if (name.equals("9")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/9.png");

                } else if (name.equals("10")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/10.png");

                } else if (name.equals("11")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/10_3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/11.png");

                } else if (name.equals("12")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/11_3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/12.png");

                } else if (name.equals("13")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/12_3.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/13.png");

                }
                d = Drawable.createFromStream(ims, null);
                imgnum.setImageDrawable(d);

            } else if (type.equals("diamond")) {
                ims = context.getAssets().open("card/SmallImage/1.png");
                d = Drawable.createFromStream(ims, null);
                imgicon.setImageDrawable(d);

                if (name.equals("1")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/1.png");
                } else if (name.equals("2")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/2.png");
                } else if (name.equals("3")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/3.png");
                } else if (name.equals("4")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/4.png");
                } else if (name.equals("5")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/5.png");
                } else if (name.equals("6")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/6.png");
                } else if (name.equals("7")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/7.png");
                } else if (name.equals("8")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/8.png");
                } else if (name.equals("9")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/9.png");
                } else if (name.equals("10")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/10.png");
                } else if (name.equals("11")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/10_1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/11.png");
                } else if (name.equals("12")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/11_1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/12.png");
                } else if (name.equals("13")) {
                    if (islast.equals("true")) {
                        ims = context.getAssets().open("card/BigImage/12_1.png");
                        d = Drawable.createFromStream(ims, null);
                        imgbig.setImageDrawable(d);
                    }
                    ims = context.getAssets().open("card/RedRank/13.png");
                }
                d = Drawable.createFromStream(ims, null);
                imgnum.setImageDrawable(d);

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("CardSetError", e.getMessage());
        }
    }

    public static Toast setUpToast_game(Context context) {
        LayoutInflater myInflater = LayoutInflater.from(context);
        View view = myInflater.inflate(R.layout.toast_layout, null);
        toast_textmessage_game = (TextView) view.findViewById(R.id.toast_textmessage);
        toast_game = new Toast(context);
        toast_game.setView(view);
        toast_game.setDuration(Toast.LENGTH_SHORT);
        toast_game.setGravity(Gravity.TOP, 0, 0);
        return toast;
    }


    public static Toast setUpToast(Context context) {
        LayoutInflater myInflater = LayoutInflater.from(context);
        View view = myInflater.inflate(R.layout.toast_layout, null);
        toast_textmessage = (TextView) view.findViewById(R.id.toast_textmessage);
        toast = new Toast(context);
        toast.setView(view);
        toast.setDuration((int) 5);
        toast.setGravity(Gravity.TOP, 0, 0);
        return toast;
    }

    public static void PlayVibrator(Context context) {

        // Get instance of Vibrator from current Context

        if (SharedPrefs.getString(context, SharedPrefs.ISVIBRARE).equals("yes")) {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(400);
        } else {

        }

// Vibrate for 400 milliseconds

    }

    public static void HideNotification(Context context) {

      /*

        NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notifManager.cancelAll();

        */

    }

    public static void ShowNotification(Context context, String message) {

      /*  if (SharedPrefs.getString(context, SharedPrefs.NOTIFICATION).equals("yes")) {
            Intent resultIntent = ((Activity) context).getIntent();
            resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resultIntent.setAction(Intent.ACTION_MAIN);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)

                    .setContentTitle("Teen Patti")
                    .setContentText(message)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManager notificationmanager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            notificationmanager.notify(0, builder.build());
        }

   */

    }


    public static void displayToast_game(String message) {
        toast_textmessage_game.setText(message);
        toast_game.show();
        Log.e("display toast", message);
    }




    public static void displayToast(String message) {
        toast_textmessage.setText(message);
        toast.show();
        Log.e("display toast", message);
    }


    public static void startGiftSoundPlay(Context context, String filename) {
        if (SharedPrefs.getString(context, SharedPrefs.ISSOUND).equals("yes")) {
            try {

                try {
                    mp = new MediaPlayer();
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = new MediaPlayer();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mp = new MediaPlayer();
                }
                AssetFileDescriptor descriptor = context.getAssets().openFd(filename);
                mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                descriptor.close();
                mp.prepare();
                mp.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void startplay(Context context, String filename) {
        if (SharedPrefs.getString(context, SharedPrefs.ISSOUND).equals("yes")) {
            try {

                try {
                    mp = new MediaPlayer();
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = new MediaPlayer();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mp = new MediaPlayer();
                }
                AssetFileDescriptor descriptor = context.getAssets().openFd(filename);
                mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                descriptor.close();
                mp.prepare();
                mp.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void StopMusic() {


        try {
            mp = new MediaPlayer();
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = new MediaPlayer();
            }
        } catch (Exception e) {
            e.printStackTrace();
            mp = new MediaPlayer();
        }
    }

    public static void ShowPlayerTurn(final LinearLayout llview, String ismylayout) {

        if (ismylayout.equals("true")) {
            CountDownTimer timer;
            final long millisInFuture = 100000000; //30 seconds
            long countDownInterval = 400; //1 second
            timer = new CountDownTimer(millisInFuture, countDownInterval) {

                public void onTick(long millisUntilFinished) {
                    if (Stop_Player_Turn == 1) {
                        cancel();
                        posi = 0;
                    }
                    posi++;
                    if (posi % 4 == 0) {
                        llview.setBackgroundResource(R.drawable.myplayerbackgroundglow1);
                    } else if (posi % 4 == 1)
                        llview.setBackgroundResource(R.drawable.myplayerbackgroundglow2);
                    else if (posi % 4 == 2)
                        llview.setBackgroundResource(R.drawable.myplayerbackgroundglow3);
                    else
                        llview.setBackgroundResource(R.drawable.myplayerbackgroundglow4);

                }

                public void onFinish() {

                }

            }.start();
        } else {
            CountDownTimer timer;
            final long millisInFuture = 100000000; //30 seconds
            long countDownInterval = 400; //1 second
            timer = new CountDownTimer(millisInFuture, countDownInterval) {
                public void onTick(long millisUntilFinished) {
                    if (Stop_Player_Turn == 1) {
                        cancel();
                        posi = 0;
                    }
                    posi++;
                    if (posi % 4 == 0) {
                        llview.setBackgroundResource(R.drawable.playerbackgroundglow1);
                    } else if (posi % 4 == 1)
                        llview.setBackgroundResource(R.drawable.playerbackgroundglow2);
                    else if (posi % 4 == 2)
                        llview.setBackgroundResource(R.drawable.playerbackgroundglow3);
                    else
                        llview.setBackgroundResource(R.drawable.playerbackgroundglow4);

                }

                public void onFinish() {

                }
            }.start();
        }
    }

    public static void ShowPlayerTurnImage(final ImageView llview, String ismylayout) {

        if (ismylayout.equals("true")) {
            CountDownTimer timer;
            final long millisInFuture = 100000000; //30 seconds
            long countDownInterval = 400; //1 second
            timer = new CountDownTimer(millisInFuture, countDownInterval) {
                public void onTick(long millisUntilFinished) {
                    if (Stop_Player_Turn == 1) {
                        cancel();
                        posi = 0;
                    }
                    posi++;
                    if (posi % 4 == 0) {
                        llview.setImageResource(R.drawable.myplayerbackgroundglow1);
                    } else if (posi % 4 == 1)
                        llview.setImageResource(R.drawable.myplayerbackgroundglow2);
                    else if (posi % 4 == 2)
                        llview.setImageResource(R.drawable.myplayerbackgroundglow3);
                    else
                        llview.setImageResource(R.drawable.myplayerbackgroundglow4);

                }

                public void onFinish() {

                }
            }.start();
        } else {
            CountDownTimer timer;
            final long millisInFuture = 100000000; //30 seconds
            long countDownInterval = 400; //1 second
            timer = new CountDownTimer(millisInFuture, countDownInterval) {
                public void onTick(long millisUntilFinished) {
                    if (Stop_Player_Turn == 1) {
                        cancel();
                        posi = 0;
                    }
                    posi++;
                    if (posi % 4 == 0) {
                        llview.setBackgroundResource(R.drawable.playerbackgroundglow1);
                    } else if (posi % 4 == 1)
                        llview.setBackgroundResource(R.drawable.playerbackgroundglow2);
                    else if (posi % 4 == 2)
                        llview.setBackgroundResource(R.drawable.playerbackgroundglow3);
                    else
                        llview.setBackgroundResource(R.drawable.playerbackgroundglow4);

                }

                public void onFinish() {

                }
            }.start();
        }
    }

    public static void ShowPlayerWinner(final LinearLayout llview, String ismylayout) {

      /*  if (ismylayout.equals("true")) {
            CountDownTimer timer;
            final long millisInFuture = 100000000; //30 seconds
            long countDownInterval = 400; //1 second
            timer = new CountDownTimer(millisInFuture, countDownInterval) {
                public void onTick(long millisUntilFinished) {
                    if (Stop_Player_Turn == 1) {
                        cancel();
                        posi = 0;
                    }
                    posi++;
                    if (posi % 2 == 0) {
                        llview.setBackgroundResource(R.drawable.play_winn_1);
                    } else if (posi % 2 == 1)
                        llview.setBackgroundResource(R.drawable.play_winn_2);
                    else if (posi % 2 == 2)
                        llview.setBackgroundResource(R.drawable.play_winn_3);


                }

                public void onFinish() {

                }
            }.start();
        } else {
            CountDownTimer timer;
            final long millisInFuture = 100000000; //30 seconds
            long countDownInterval = 400; //1 second
            timer = new CountDownTimer(millisInFuture, countDownInterval) {
                public void onTick(long millisUntilFinished) {
                    if (Stop_Player_Turn == 1) {
                        cancel();
                        posi = 0;
                    }
                    posi++;
                    if (posi % 2 == 0) {
                        llview.setBackgroundResource(R.drawable.play_winn_1);
                    } else if (posi % 2 == 1)
                        llview.setBackgroundResource(R.drawable.play_winn_2);
                    else if (posi % 2 == 2)
                        llview.setBackgroundResource(R.drawable.play_winn_3);


                }

                public void onFinish() {

                }
            }.start();
        }*/
    }

    public static void ShowPlayerWinnerImage(final ImageView llview, String ismylayout) {

        if (ismylayout.equals("true")) {
            CountDownTimer timer;
            final long millisInFuture = 100000000; //30 seconds
            long countDownInterval = 400; //1 second
            timer = new CountDownTimer(millisInFuture, countDownInterval) {
                public void onTick(long millisUntilFinished) {
                    if (Stop_Player_Turn == 1) {
                        cancel();
                        posi = 0;
                    }
                    posi++;
                    if (posi % 3 == 0) {
                        llview.setImageResource(R.drawable.nmywinanim1);
                    } else if (posi % 3 == 1)
                        llview.setImageResource(R.drawable.nmywinanim2);
                    else if (posi % 3 == 2)
                        llview.setImageResource(R.drawable.nmywinanim3);


                }

                public void onFinish() {

                }
            }.start();
        } else {
            CountDownTimer timer;
            final long millisInFuture = 100000000; //30 seconds
            long countDownInterval = 400; //1 second
            timer = new CountDownTimer(millisInFuture, countDownInterval) {
                public void onTick(long millisUntilFinished) {
                    if (Stop_Player_Turn == 1) {
                        cancel();
                        posi = 0;
                    }
                    posi++;
                    if (posi % 3 == 0) {
                        llview.setBackgroundResource(R.drawable.nwinanim1);
                    } else if (posi % 3 == 1)
                        llview.setBackgroundResource(R.drawable.nwinanim2);
                    else if (posi % 3 == 2)
                        llview.setBackgroundResource(R.drawable.nwinanim3);


                }

                public void onFinish() {

                }
            }.start();
        }
    }
}
