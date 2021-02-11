package com.teenpatti.queendemo.activity;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.teenpatti.queendemo.R;
import com.teenpatti.queendemo.api.JSONParser;
import com.teenpatti.queendemo.files.BaseUtils;
import com.teenpatti.queendemo.files.DialogProgress;
import com.teenpatti.queendemo.files.URLS;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ForgotPasswordActivity extends AppCompatActivity {

    private String emailId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);

        ImageView closeIv = findViewById(R.id.closeIv);
        final EditText beneNameEt = findViewById(R.id.beneNameEt);
        MaterialButton saveBtn = findViewById(R.id.saveBtn);
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailId = beneNameEt.getText().toString().trim();
                if (!BaseUtils.isValidEmail(emailId)) {
                    Toast.makeText(ForgotPasswordActivity.this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    callApi();
                }
            }
        });
    }

    private void callApi() {
        new ForgotPasswordCall().execute();
    }

    private void showPopup() {
        final Dialog dialog = new Dialog(this, R.style.DialogAnimation2);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
        }
        dialog.setContentView(R.layout.dialog_forgot_password);

        MaterialButton saveBtn = dialog.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    private class ForgotPasswordCall extends AsyncTask<String, String, String> {

        JSONParser jParser = new JSONParser();
        boolean Sucess = false;
        String jsonstr = null;
        JSONObject json;
        Dialog dd;
        String msg = "Something went wrong";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dd = new DialogProgress().DialogProgressInitialoz(ForgotPasswordActivity.this);
            dd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.e("urrllll", "call forgot api ");

            List<NameValuePair> par = new ArrayList<NameValuePair>();
            par.add(new BasicNameValuePair("mobile", ""));
            par.add(new BasicNameValuePair("email", emailId));

            try {
                jsonstr = jParser.makeHttpRequest( URLS.FORGOT_PASSWORD, "POST", par);
                Log.e("response", URLS.FORGOT_PASSWORD + " => " + jsonstr);
                JSONObject json1 = new JSONObject(jsonstr);
                json = json1.getJSONObject("emailRes");
                Sucess = json.getBoolean("success");

                if (Sucess) {

                } else {
                    msg = json.getString("msg");
                }

            } catch (Exception e) {
                Log.e("errro", "message: " + e.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dd.dismiss();
            if (Sucess) {
                showPopup();

            } else {
                Toast.makeText(ForgotPasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }
    }
}