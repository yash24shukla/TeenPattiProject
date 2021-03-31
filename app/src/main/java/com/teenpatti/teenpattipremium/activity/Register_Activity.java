package com.teenpatti.teenpattipremium.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.teenpatti.teenpattipremium.R;
import com.teenpatti.teenpattipremium.api.JSONParser;
import com.teenpatti.teenpattipremium.files.DialogProgress;
import com.teenpatti.teenpattipremium.files.SharedPrefs;
import com.teenpatti.teenpattipremium.files.URLS;
import com.teenpatti.teenpattipremium.model.Functions;
import com.teenpatti.teenpattipremium.model.NetworkManager;
import com.teenpatti.teenpattipremium.model.ShowToast;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Register_Activity extends AppCompatActivity {

    EditText otp, edtLoginUsername, edtLoginDisplay, edtLoginPassword, edtLoginURL, edtLoginMobile;

    Button btnLoginSubmit;
    String username, password, displayname, mobileno;
    ImageView txtCardSee;
    ImageView imgBack;

    Dialog dAppUpdate;
    String Applink = "";
    String updatetext = "";
    TextView txtUpdateText;
    Button btnUpdateApp;

    TranslateAnimation mAnimation, mAnimation2, mAnimation3, mAnimation4;
    ImageView imgback1, imgback2, imgback3, imgback4;
    View promptsView ;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    AlertDialog.Builder alertDialogBuilder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.register_layout);

        String DEVICEIIDDD = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        SharedPrefs.save(Register_Activity.this, SharedPrefs.DEVICE_ID, DEVICEIIDDD);

        BindView();
        BindListner();

        new Asynctask_CheckUpdateApp().execute();

        LayoutInflater li = this.getLayoutInflater();
        promptsView = li.inflate(R.layout.dialog_phoneauth, null);
        mAuth = FirebaseAuth.getInstance() ;
        imgback1 = findViewById(R.id.imgback1);
        imgback2 = findViewById(R.id.imgback2);
        imgback3 = findViewById(R.id.imgback3);
        imgback4 = findViewById(R.id.imgback4);
        alertDialogBuilder = new AlertDialog.Builder(this);

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
        edtLoginMobile = findViewById(R.id.edtLoginMobile);
        edtLoginDisplay = findViewById(R.id.edtLoginMobile);
        imgBack = findViewById(R.id.imgBack);
        edtLoginURL = findViewById(R.id.edtLoginURL);
        edtLoginUsername = findViewById(R.id.edtLoginMobile);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginSubmit = findViewById(R.id.btnLoginSubmit);


        dAppUpdate = new Dialog(Register_Activity.this);
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


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Register_Activity.this, getResources().getString(R.string.mp_buttonClick));

                finish();
            }
        });

        btnLoginSubmit.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {



                if(edtLoginDisplay.getText().toString() != null && edtLoginDisplay.getText().toString() != ""
                && edtLoginMobile.getText().toString() != null && edtLoginMobile.getText().toString() != ""
                        && edtLoginUsername.getText().toString() != null && edtLoginUsername.getText().toString() != ""
                        && edtLoginPassword.getText().toString() != null && edtLoginPassword.getText().toString() != ""

                ){


                otp = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilder.setView(promptsView);

                phoneAuth() ;

//               Alert Dialog
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Submit",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        if(otp.getText().toString() != "" && otp.getText().toString() != null
                                        && otp.getText().length() >=6
                                        ){
                                            verifyVerificationCode(otp.getText().toString().trim());

                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });



                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                }
                else if(edtLoginDisplay.getText().toString() == null && edtLoginDisplay.getText().toString() == "") {
                    edtLoginDisplay.setError("Enter Display Name");
                }
                else if(edtLoginMobile.getText().toString() == null && edtLoginMobile.getText().toString() == "") {
                    edtLoginMobile.setError("Enter Phone no.");
                }
                else if(edtLoginUsername.getText().toString() == null && edtLoginUsername.getText().toString() == "") {
                    edtLoginUsername.setError("Enter username");
                }
                else if(edtLoginPassword.getText().toString() == null && edtLoginPassword.getText().toString() == "") {
                    edtLoginPassword.setError("Enter Password");
                }

            }
        });
    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();
            Log.d("verificationMsg", "verification completed") ;

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                if (otp.getText().toString() != null) {
                    otp.setText(code);
                }
                //verifying the code

                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Log.d("verificationMsg", "verification message sent") ;
            //storing the verification id that is sent to the user
            mVerificationId = s;
        }


    };

    private void phoneAuth() {
        Log.d("verificationMsg", "got to message sender = " +edtLoginMobile.getText().toString()) ;

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + edtLoginMobile.getText().toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


    }


    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        if (getApplicationContext() != null) {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = task.getResult().getUser();
                                registerUser();
                                // ...
                            } else {
                                // Sign in failed, display a message and update the UI
                                Toast.makeText(getApplicationContext(),"Something got wrong!",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }

    private void registerUser() {
        mobileno = edtLoginMobile.getText().toString();
        username = edtLoginUsername.getText().toString();
        password = edtLoginPassword.getText().toString();
        displayname = edtLoginUsername.getText().toString();
        String url = edtLoginURL.getText().toString();


        if (displayname.equals("")) {
            new ShowToast(Register_Activity.this, "Enter Display Name", "error");

        } else if (username.equals("")) {
            new ShowToast(Register_Activity.this, "Enter Username", "error");

        } else if (password.equals("")) {
            new ShowToast(Register_Activity.this, "Enter Password", "error");

        } else {

            if (!url.trim().equals("")) {
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                URLS.Domain = url;
                Log.e("url", URLS.Domain);
                Toast.makeText(Register_Activity.this, URLS.Domain, Toast.LENGTH_LONG).show();
            }
            if (!NetworkManager.isInternetConnected(Register_Activity.this)) {
                Toast.makeText(Register_Activity.this, "Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
            } else {
                new Login_Asynctask().execute();
            }
        }
    }

    class Login_Asynctask extends AsyncTask<String, String, String> {


        JSONParser jParser = new JSONParser();
        boolean Sucess = false;
        String jsonstr = null;
        JSONObject json;

        Dialog dd;
        String msg = "Something went wrong";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dd = new DialogProgress().DialogProgressInitialoz(Register_Activity.this);
            dd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            Log.e("urrllll", "call login api ");
            List<NameValuePair> par = new ArrayList<NameValuePair>();
            par.add(new BasicNameValuePair("displayName", displayname));
            par.add(new BasicNameValuePair("userName", username));
            par.add(new BasicNameValuePair("password", password));
            par.add(new BasicNameValuePair("chips", "10000"));
            par.add(new BasicNameValuePair("type", "premium"));
            par.add(new BasicNameValuePair("role", "user"));
            par.add(new BasicNameValuePair("mobile", mobileno));
            par.add(new BasicNameValuePair("profilePic", "http://107.23.43.209/admin/upload/user/dummy.jpg"));

            try {

                jsonstr = jParser
                        .makeHttpRequest(URLS.URL_REGISTER, "POST", par);

                Log.e("response", URLS.URL_REGISTER + " => " + jsonstr);

                json = new JSONObject(jsonstr);
                Sucess = json.getBoolean("success");
                if (Sucess) {
                   /* JSONObject jrow = json.getJSONObject("addedUser");
                    SharedPrefs.save(Register_Activity.this, SharedPrefs.USER_ID, jrow.getString("_id"));
                    SharedPrefs.save(Register_Activity.this, SharedPrefs.USERNAME, jrow.getString("userName"));
                    SharedPrefs.save(Register_Activity.this, SharedPrefs.PASSWORD, jrow.getString("password"));
                    SharedPrefs.save(Register_Activity.this, SharedPrefs.CLIENT_ID, jrow.getString("clientId"));
                    SharedPrefs.save(Register_Activity.this, SharedPrefs.CHIPS, jrow.getString("chips"));
                    SharedPrefs.save(Register_Activity.this, SharedPrefs.PROFILEPIC, jrow.getString("profilePic"));
                    SharedPrefs.save(Register_Activity.this, SharedPrefs.ISVIBRARE, "yes");
                    SharedPrefs.save(Register_Activity.this, SharedPrefs.ISSOUND, "yes");
                    SharedPrefs.save(Register_Activity.this, SharedPrefs.NOTIFICATION, "yes");
                    SharedPrefs.save(Register_Activity.this, SharedPrefs.TYPE, jrow.getString("type"));
                    SharedPrefs.save(Register_Activity.this, SharedPrefs.DISPLAY_NAME, jrow.getString("displayName"));

                    if (jrow.has("role"))
                        SharedPrefs.save(Register_Activity.this, SharedPrefs.ROLE, jrow.getString("role"));
                    else
                        SharedPrefs.save(Register_Activity.this, SharedPrefs.ROLE, "user");

                    if (SharedPrefs.getString(Register_Activity.this, SharedPrefs.TYPE, jrow.getString("type")).equals("admin")) {
                        SharedPrefs.save(Register_Activity.this, SharedPrefs.TYPE, "premium");
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

                new ShowToast(Register_Activity.this, "Register Successfully", "success");

                startActivity(new Intent(Register_Activity.this, Login_Activity.class));
                overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
                finish();
            } else {
                Toast.makeText(Register_Activity.this, msg, Toast.LENGTH_SHORT).show();
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
//                    }
//                });

            }

        }

    }

}
