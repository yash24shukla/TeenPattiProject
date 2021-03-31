package com.teenpatti.teenpattipremium.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.plattysoft.leonids.ParticleSystem;
import com.teenpatti.teenpattipremium.R;
import com.teenpatti.teenpattipremium.files.SharedPrefs;
import com.teenpatti.teenpattipremium.files.URLS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Splash_Activity extends AppCompatActivity {

    ParticleSystem ps, ps2;
    ImageView img;
    int posi = 0;

    TranslateAnimation mAnimation, mAnimation2, mAnimation3, mAnimation4;
    ImageView imgback1, imgback2, imgback3, imgback4;
    String version ;
    String latestVersionName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_layout);

        version = getString(R.string.verison) ;
        img = findViewById(R.id.img);

        imgback1 = findViewById(R.id.imgback1);
        imgback2 = findViewById(R.id.imgback2);
        imgback3 = findViewById(R.id.imgback3);
        imgback4 = findViewById(R.id.imgback4);

        mAnimation2 = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.RELATIVE_TO_PARENT, -1f, TranslateAnimation.RELATIVE_TO_PARENT, 1.0f);
        mAnimation2.setDuration(10000);
        mAnimation2.setRepeatCount(-1);
        mAnimation2.setRepeatMode(Animation.INFINITE);
        mAnimation2.setInterpolator(new LinearInterpolator());
        imgback1.startAnimation(mAnimation2);
        imgback2.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAnimation3 = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.RELATIVE_TO_PARENT, -1f, TranslateAnimation.RELATIVE_TO_PARENT, 1.0f);
                mAnimation3.setDuration(10000);
                mAnimation3.setRepeatCount(-1);
                mAnimation3.setRepeatMode(Animation.INFINITE);
                mAnimation3.setInterpolator(new LinearInterpolator());
                imgback2.setVisibility(View.VISIBLE);
                imgback2.startAnimation(mAnimation3);
            }
        }, 5000);


        mAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.RELATIVE_TO_PARENT, -1f, TranslateAnimation.RELATIVE_TO_PARENT, 1.0f);
        mAnimation.setDuration(14000);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.INFINITE);
        mAnimation.setInterpolator(new LinearInterpolator());
        imgback3.startAnimation(mAnimation);
        imgback4.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAnimation4 = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.RELATIVE_TO_PARENT, -1f, TranslateAnimation.RELATIVE_TO_PARENT, 1.0f);
                mAnimation4.setDuration(14000);
                mAnimation4.setRepeatCount(-1);
                mAnimation4.setRepeatMode(Animation.INFINITE);
                mAnimation4.setInterpolator(new LinearInterpolator());
                imgback4.setVisibility(View.VISIBLE);
                imgback4.startAnimation(mAnimation4);
            }
        }, 7000);


        String DEVICEIIDDD = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        SharedPrefs.save(Splash_Activity.this, SharedPrefs.DEVICE_ID, DEVICEIIDDD);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                String DEVICEIIDDD = Settings.Secure.getString(getContentResolver(),
                        Settings.Secure.ANDROID_ID);


                SharedPrefs.save(Splash_Activity.this, SharedPrefs.DEVICE_ID, DEVICEIIDDD);
                AndroidNetworking.get(URLS.URL_VERSION)
                        .addHeaders("Content-Type" , "application/json")
                        .setTag("test")
                        .setPriority(Priority.IMMEDIATE)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                latestVersionName = version  ;
                                Log.d("responseWithdraw", URLS.REMOVE_CHIPS + " => " + response.toString());
                                if(response.has("data")) {
                                    try {
                                        JSONArray data = (JSONArray) response.get("data");
                                        int lastIndex = data.length() - 1 ;
                                        JSONObject latestObj  = (JSONObject) data.get(lastIndex);

                                        if(latestObj.has("version")){
                                            latestVersionName  = latestObj.getString("version") ;

                                        }else{
                                            latestVersionName = getString(R.string.verison)  ;
                                        };
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                if(Double.parseDouble(latestVersionName) == Double.parseDouble(version)){




                if (!SharedPrefs.getString(Splash_Activity.this, SharedPrefs.USER_ID).equals("")) {
                    Intent i = new Intent(Splash_Activity.this, StartActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                } else {
                    SharedPrefs.save(Splash_Activity.this, SharedPrefs.ISVIBRARE, "yes");
                    SharedPrefs.save(Splash_Activity.this, SharedPrefs.ISSOUND, "yes");
                    SharedPrefs.save(Splash_Activity.this, SharedPrefs.NOTIFICATION, "yes");
                    Intent i = new Intent(Splash_Activity.this, Login_Activity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                }
                }
                else{
                    Log.d("versionCheck" , "current version = " + Double.valueOf(version)) ;
                    Log.d("versionCheck" , "latestVersionName version = " + Double.valueOf(latestVersionName)) ;
                    Intent i = new Intent(Splash_Activity.this, forcedupdate.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                }
                }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        }, 6000);

    }


    public static final String md5(final String s) {


        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            Log.e("error", "admob errorrrrrr");
        }
        return "";
    }
}
