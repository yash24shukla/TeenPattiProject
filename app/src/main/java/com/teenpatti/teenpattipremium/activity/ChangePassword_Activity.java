package com.teenpatti.teenpattipremium.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.teenpatti.teenpattipremium.R;
import com.teenpatti.teenpattipremium.api.JSONParser;
import com.teenpatti.teenpattipremium.files.SharedPrefs;
import com.teenpatti.teenpattipremium.files.URLS;
import com.teenpatti.teenpattipremium.model.Functions;
import com.teenpatti.teenpattipremium.model.ShowToast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChangePassword_Activity extends AppCompatActivity /*implements Runnable*/ {

    int process;
    EditText edtCpPassword, edtCpPasswordNew, edtCpPasswordConfirm;
    String cppassword, cppasswordnew, cppasswordconfirm;
    Button btnCpSubmit;

    TranslateAnimation mAnimation, mAnimation2,mAnimation3,mAnimation4;
    ImageView imgback1, imgback2, imgback3, imgback4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.change_password_layout);
        BindView();
        BindListner();


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

    }

    private void BindView() {

        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(ChangePassword_Activity.this, getResources().getString(R.string.mp_buttonClick));
                finish();
            }
        });


        edtCpPassword = findViewById(R.id.edtCpPassword);
        edtCpPasswordNew = findViewById(R.id.edtCpPasswordNew);
        edtCpPasswordConfirm = findViewById(R.id.edtCpPasswordConfirm);
        btnCpSubmit = findViewById(R.id.btnCpSubmit);

    }

    private void BindListner() {

        btnCpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Functions.startplay(ChangePassword_Activity.this, getResources().getString(R.string.mp_buttonClick));
                cppassword = edtCpPassword.getText().toString();
                cppasswordnew = edtCpPasswordNew.getText().toString();
                cppasswordconfirm = edtCpPasswordConfirm.getText().toString();

                if (cppassword.equals("")) {
                    new ShowToast(ChangePassword_Activity.this, "Enter old password", "error");
                } else if (cppasswordnew.equals("")) {
                    new ShowToast(ChangePassword_Activity.this, "Enter new password", "error");
                } else if (cppasswordconfirm.equals("")) {
                    new ShowToast(ChangePassword_Activity.this, "Enter confirm password", "error");
                } else if (!cppasswordconfirm.equals(cppasswordnew)) {
                    new ShowToast(ChangePassword_Activity.this, "Confirm password must be same as new password", "error");
                } else {
                    new ChangePass_Asynctask().execute();
                }

            }
        });
    }

    class ChangePass_Asynctask extends AsyncTask {

        JSONParser jParser = new JSONParser();
        boolean Sucess = false;
        JSONObject json = null;
        String jsonStr = "";

        @Override
        protected Object doInBackground(Object[] objects) {

            try {

                List<NameValuePair> par = new ArrayList<NameValuePair>();
                par.add(new BasicNameValuePair("userId", SharedPrefs.getString(ChangePassword_Activity.this, SharedPrefs.USER_ID)));
                par.add(new BasicNameValuePair("oldPassword", cppassword));
                par.add(new BasicNameValuePair("newPassword", cppasswordconfirm));
                par.add(new BasicNameValuePair("role", SharedPrefs.getString(ChangePassword_Activity.this, SharedPrefs.ROLE, "")));

                try {
                    jsonStr = jParser
                            .makeHttpRequest(URLS.Domain + URLS.URL_CHANGEPASS, "POST", par);
                    Log.e("response", URLS.Domain + URLS.URL_CHANGEPASS + " =>" + jsonStr);
                    json = new JSONObject(jsonStr);
                    Sucess = json.getBoolean("success");
                } catch (Exception e) {
                    Log.e("errro", e.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (Sucess) {
                new ShowToast(ChangePassword_Activity.this, "Password updated", "success");
                finish();
            } else {
                new ShowToast(ChangePassword_Activity.this, "Old password doesn't match", "error");
            }
        }
    }

}
