package com.teenpatti.queendemo.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.teenpatti.queendemo.R;
import com.teenpatti.queendemo.api.JSONParser;
import com.teenpatti.queendemo.files.BaseUtils;
import com.teenpatti.queendemo.files.DialogProgress;
import com.teenpatti.queendemo.files.SharedPrefs;
import com.teenpatti.queendemo.files.URLS;
import com.teenpatti.queendemo.model.Functions;
import com.teenpatti.queendemo.model.NetworkManager;
import com.teenpatti.queendemo.model.ShowToast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Login_Activity extends AppCompatActivity {

    private static final String TAG = Login_Activity.class.getSimpleName();
    EditText edtLoginUsername, edtLoginPassword, edtLoginURL;
    Button btnLoginSubmit;
    String username, password;
    String fbUsername;
    ImageView txtCardSee;
    ImageView imgBack;

    Dialog dAppUpdate;
    String Applink = "";
    String updatetext = "Please check Server url";
    TextView txtUpdateText;
    Button btnUpdateApp;

    TranslateAnimation mAnimation, mAnimation2, mAnimation3, mAnimation4;
    ImageView imgback1, imgback2, imgback3, imgback4;
    TextView btnRegister;
    View forgotPasswordTv;
    View fab;
    View signup;

    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.test);

        String DEVICEIIDDD = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        SharedPrefs.save(Login_Activity.this, SharedPrefs.DEVICE_ID, DEVICEIIDDD);

        BindView();

        initFbLogin();
        BindListner();

        new Asynctask_CheckUpdateApp().execute();


//        imgback1 = findViewById(R.id.imgback1);
//        imgback2 = findViewById(R.id.imgback2);
//        imgback3 = findViewById(R.id.imgback3);
//        imgback4 = findViewById(R.id.imgback4);
//
//        mAnimation2 = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.RELATIVE_TO_PARENT, -1f, TranslateAnimation.RELATIVE_TO_PARENT, 1.0f);
//        mAnimation2.setDuration(10000);
//        mAnimation2.setRepeatCount(-1);
//        mAnimation2.setRepeatMode(Animation.INFINITE);
//        mAnimation2.setInterpolator(new LinearInterpolator());
//        imgback1.startAnimation(mAnimation2);
//        imgback2.setVisibility(View.GONE);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mAnimation3 = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.RELATIVE_TO_PARENT, -1f, TranslateAnimation.RELATIVE_TO_PARENT, 1.0f);
//                mAnimation3.setDuration(10000);
//                mAnimation3.setRepeatCount(-1);
//                mAnimation3.setRepeatMode(Animation.INFINITE);
//                mAnimation3.setInterpolator(new LinearInterpolator());
//                imgback2.setVisibility(View.VISIBLE);
//                imgback2.startAnimation(mAnimation3);
//            }
//        }, 5000);
//
//
//        mAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.RELATIVE_TO_PARENT, -1f, TranslateAnimation.RELATIVE_TO_PARENT, 1.0f);
//        mAnimation.setDuration(14000);
//        mAnimation.setRepeatCount(-1);
//        mAnimation.setRepeatMode(Animation.INFINITE);
//        mAnimation.setInterpolator(new LinearInterpolator());
//        imgback3.startAnimation(mAnimation);
//        imgback4.setVisibility(View.GONE);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mAnimation4 = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.RELATIVE_TO_PARENT, -1f, TranslateAnimation.RELATIVE_TO_PARENT, 1.0f);
//                mAnimation4.setDuration(14000);
//                mAnimation4.setRepeatCount(-1);
//                mAnimation4.setRepeatMode(Animation.INFINITE);
//                mAnimation4.setInterpolator(new LinearInterpolator());
//                imgback4.setVisibility(View.VISIBLE);
//                imgback4.startAnimation(mAnimation4);
//            }
//        }, 7000);
//

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void initFbLogin() {
        mCallbackManager = CallbackManager.Factory.create();
    }

    private void fbLogin(View v) {
        if (NetworkManager.isInternetConnected(Login_Activity.this)) {
            LoginManager.getInstance().logInWithReadPermissions(Login_Activity.this,
                    Arrays.asList("public_profile", "email"));

            handleFbLogin();
        } else {
            Toast.makeText(Login_Activity.this, "Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleFbLogin() {
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                if (loginResult.getAccessToken() != null) {

                    // fb permissions denied
                    Set<String> deniedPermissions = loginResult.getRecentlyDeniedPermissions();
                    if (deniedPermissions.size() != 0) {
                        if (deniedPermissions.contains("email")) {
                            LoginManager.getInstance().logInWithReadPermissions
                                    (Login_Activity.this, Collections.singletonList("email"));

                        } else if (deniedPermissions.contains("public_profile")) {
                            LoginManager.getInstance().logInWithReadPermissions
                                    (Login_Activity.this, Collections.singletonList("public_profile"));
                        }
                    } else {
                        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject jsonObject, GraphResponse response) {
                                        BaseUtils.showLog(TAG, "Json: " + jsonObject.toString());
                                        final String email = jsonObject.optString("email");
                                        fbUsername = jsonObject.optString("name");
                                        BaseUtils.showLog(TAG, "email: " + email);

                                        new Register_Asynctask().execute();
                                    }
                                });

                        //setting parameters for fetching user info
                        Bundle bundle = new Bundle();
                        bundle.putString("fields", "id,name,email,picture.type(large)");
                        graphRequest.setParameters(bundle);
                        graphRequest.executeAsync();
                    }
                }

            }

            @Override
            public void onCancel() {
                BaseUtils.showLog(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                BaseUtils.showLog(TAG, "onError" + error.getLocalizedMessage());
            }
        });
    }

    class Register_Asynctask extends AsyncTask<String, String, String> {


        JSONParser jParser = new JSONParser();
        boolean Sucess = false;
        String jsonstr = null;
        JSONObject json;

        Dialog dd;
        String msg = "Something went wrong";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dd = new DialogProgress().DialogProgressInitialoz(Login_Activity.this);
            dd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            Log.e("urrllll", "call login api ");
            List<NameValuePair> par = new ArrayList<NameValuePair>();
            par.add(new BasicNameValuePair("displayName", fbUsername));
            par.add(new BasicNameValuePair("userName", fbUsername));
            par.add(new BasicNameValuePair("password", ""));
            par.add(new BasicNameValuePair("chips", "100"));
            par.add(new BasicNameValuePair("type", "premium"));
            par.add(new BasicNameValuePair("role", "user"));
            par.add(new BasicNameValuePair("mobile", ""));
            par.add(new BasicNameValuePair("profilePic", "http://107.23.43.209/admin/upload/user/dummy.jpg"));

            try {

                jsonstr = jParser
                        .makeHttpRequest(URLS.URL_REGISTER, "POST", par);

                Log.e("response", URLS.URL_REGISTER + " => " + jsonstr);

                json = new JSONObject(jsonstr);
                Sucess = json.getBoolean("success");
                if (Sucess) {
                   /* JSONObject jrow = json.getJSONObject("addedUser");
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.USER_ID, jrow.getString("_id"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.USERNAME, jrow.getString("userName"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.PASSWORD, jrow.getString("password"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.CLIENT_ID, jrow.getString("clientId"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.CHIPS, jrow.getString("chips"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.PROFILEPIC, jrow.getString("profilePic"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.ISVIBRARE, "yes");
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.ISSOUND, "yes");
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.NOTIFICATION, "yes");
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.TYPE, jrow.getString("type"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.DISPLAY_NAME, jrow.getString("displayName"));

                    if (jrow.has("role"))
                        SharedPrefs.save(Login_Activity.this, SharedPrefs.ROLE, jrow.getString("role"));
                    else
                        SharedPrefs.save(Login_Activity.this, SharedPrefs.ROLE, "user");

                    if (SharedPrefs.getString(Login_Activity.this, SharedPrefs.TYPE, jrow.getString("type")).equals("admin")) {
                        SharedPrefs.save(Login_Activity.this, SharedPrefs.TYPE, "premium");
                    }
*/
                } else {
                    msg = json.getString("message");
                }

            } catch (Exception e) {
                Log.e("errro", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dd.dismiss();
            if (Sucess) {

                new ShowToast(Login_Activity.this, "Register Successfully", "success");

                startActivity(new Intent(Login_Activity.this, Login_Activity.class));
                overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
                finish();
            } else {
                Toast.makeText(Login_Activity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void BindView() {
//        imgBack = findViewById(R.id.imgBack);
        btnRegister = findViewById(R.id.btnRegister);
        edtLoginURL = findViewById(R.id.edtLoginURL);
        edtLoginUsername = findViewById(R.id.edtLoginUsername);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginSubmit = findViewById(R.id.btnLoginSubmit);
        forgotPasswordTv = findViewById(R.id.forgotPasswordTv);
        fab = findViewById(R.id.fab);
        signup = findViewById(R.id.signup);


        dAppUpdate = new Dialog(Login_Activity.this);
        dAppUpdate.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dAppUpdate.setContentView(R.layout.appupdate_dialog);
        dAppUpdate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dAppUpdate.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        dAppUpdate.getWindow().setGravity(Gravity.BOTTOM);
        dAppUpdate.setCanceledOnTouchOutside(false);
        txtUpdateText = dAppUpdate.findViewById(R.id.txtUpdateText);
        dAppUpdate.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dAppUpdate.dismiss();
                    finish();
                }
                return true;
            }
        });

        btnUpdateApp = dAppUpdate.findViewById(R.id.btnUpdateApp);
        btnUpdateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String market_uri = Applink;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(market_uri));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent
                        .FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });


    }

    private void BindListner() {

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Login_Activity.this, Register_Activity.class));

            }
        });

//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Functions.startplay(Login_Activity.this, getResources().getString(R.string.mp_buttonClick));
//
//                finish();
//            }
//        });

        btnLoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Login_Activity.this, getResources().getString(R.string.mp_buttonClick));

                username = edtLoginUsername.getText().toString();
                password = edtLoginPassword.getText().toString();
                String url = edtLoginURL.getText().toString();
                if (username.equals("")) {
                    new ShowToast(Login_Activity.this, "Enter Username", "error");

                } else if (password.equals("")) {
                    new ShowToast(Login_Activity.this, "Enter Password", "error");

                } else {

                    if (!url.trim().equals("")) {
                        if (!url.startsWith("http://") && !url.startsWith("https://"))
                            url = "http://" + url;
                        URLS.Domain = url;
                        Log.e("url", URLS.Domain);
                        Toast.makeText(Login_Activity.this, URLS.Domain, Toast.LENGTH_LONG).show();
                    }
                    if (!NetworkManager.isInternetConnected(Login_Activity.this)) {
                        Toast.makeText(Login_Activity.this, "Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
                    } else {
                        new Login_Asynctask().execute();
                    }
                }
            }
        });

        forgotPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fbLogin(view);
            }
        });
    }

    class Login_Asynctask extends AsyncTask<String, String, String> {


        JSONParser jParser = new JSONParser();
        String Sucess = "";
        String jsonstr = null;
        JSONObject json;
        KProgressHUD kprogress;
        Dialog dd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dd = new DialogProgress().DialogProgressInitialoz(Login_Activity.this);
            dd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            Log.e("urrllll", "call login api ");
            List<NameValuePair> par = new ArrayList<NameValuePair>();
            par.add(new BasicNameValuePair("userName", username));
            par.add(new BasicNameValuePair("deviceId", SharedPrefs.getString(Login_Activity.this, SharedPrefs.DEVICE_ID, "1234")));
            par.add(new BasicNameValuePair("password", password));

            try {

                jsonstr = jParser
                        .makeHttpRequest(URLS.Domain + URLS.URL_LOGIN, "POST", par);

                Log.e("response", URLS.Domain + URLS.URL_LOGIN + " => " + jsonstr);

                json = new JSONObject(jsonstr);
                Sucess = json.getString("status");
                if (Sucess.equals("success")) {
                    JSONObject jrow = json.getJSONObject("data");
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.USER_ID, jrow.getString("_id"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.USERNAME, jrow.getString("userName"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.PASSWORD, jrow.getString("password"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.CLIENT_ID, jrow.getString("clientId"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.CHIPS, jrow.getString("chips"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.PROFILEPIC, jrow.getString("profilePic"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.ISVIBRARE, "yes");
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.ISSOUND, "yes");
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.NOTIFICATION, "yes");
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.TYPE, jrow.getString("type"));
                    SharedPrefs.save(Login_Activity.this, SharedPrefs.DISPLAY_NAME, jrow.getString("displayName"));

                    if (jrow.has("role"))
                        SharedPrefs.save(Login_Activity.this, SharedPrefs.ROLE, jrow.getString("role"));
                    else
                        SharedPrefs.save(Login_Activity.this, SharedPrefs.ROLE, "user");

                    if (SharedPrefs.getString(Login_Activity.this, SharedPrefs.TYPE, jrow.getString("type")).equals("admin")) {
                        SharedPrefs.save(Login_Activity.this, SharedPrefs.TYPE, "premium");
                    }

                }

            } catch (Exception e) {
                Log.e("errro", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dd.dismiss();
            if (Sucess.equals("success")) {
                startActivity(new Intent(Login_Activity.this, StartActivity.class));
                overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
                finish();
            } else {
                Toast.makeText(Login_Activity.this, "Please Check Your Username and Password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.intent_side_left, R.anim.intent_slide_to_right);
    }


    class Asynctask_CheckUpdateApp extends AsyncTask<String, String, String> {

        JSONParser jParser = new JSONParser();
        Boolean Sucess = true;
        String jsonstr = null;
        JSONObject json;
        KProgressHUD kprogress;
        Dialog dd;

        String webversion = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> par = new ArrayList<NameValuePair>();


            try {

                jsonstr = jParser
                        .makeHttpRequest(URLS.URL_CHECKAPPUPDATE, "GET", par);

                Log.e("response", URLS.URL_CHECKAPPUPDATE + " => " + jsonstr);
                json = new JSONObject(jsonstr);

                webversion = json.getString("v");
                Applink = json.getString("link");
                updatetext = json.getString("text");

            } catch (Exception e) {
                Log.e("errro", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            Log.e("web version", webversion);
            if (webversion.equals(URLS.CURRENT_VERSION)) {
               /* runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtUpdateText.setText(updatetext);
                        dAppUpdate.show();
                    }
                });*/
            } else {
                Log.e("web version", "diff");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        txtUpdateText.setText(updatetext);
//                        if (Applink.equals(""))
//                            btnUpdateApp.setVisibility(View.GONE);
//                        else
//                            btnUpdateApp.setVisibility(View.VISIBLE);
//                        dAppUpdate.show();
            }
//                });

        }

    }

}


