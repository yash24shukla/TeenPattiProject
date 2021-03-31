package com.teenpatti.teenpattipremium.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.teenpatti.teenpattipremium.R;
import com.teenpatti.teenpattipremium.api.JSONParser;
import com.teenpatti.teenpattipremium.files.DialogProgress;
import com.teenpatti.teenpattipremium.files.URLS;
import com.teenpatti.teenpattipremium.model.NetworkManager;
import com.teenpatti.teenpattipremium.model.ShowToast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ForgotPasswordActivity extends AppCompatActivity {

    private String emailId = "";
    View promptsView ;
    AlertDialog.Builder alertDialogBuilder ;
    EditText otp , newpassword ;
    Button submitBtn  ;
     EditText beneNameEt;
    String username, password, displayname, mobileno;
    private FirebaseAuth mAuth;
    private String mVerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);
        LayoutInflater li = this.getLayoutInflater();
        promptsView = li.inflate(R.layout.dialog_forgetpassword, null);
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptsView);
        otp = promptsView.findViewById(R.id.forgetOtpEditText) ;
        newpassword = promptsView.findViewById(R.id.newpswdEditText);
        mAuth = FirebaseAuth.getInstance() ;


        ImageView closeIv = findViewById(R.id.closeIv);
     beneNameEt = findViewById(R.id.beneNameEt);
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

                if(!beneNameEt.getText().toString().isEmpty() && beneNameEt.getText().toString()!= null){
                    showForgetPopup() ;

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
    private void showForgetPopup() {
//        submitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                  ;
//            }
//        });
        phoneAuth() ;

        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Submit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                if((otp.getText().toString() != "" && otp.getText().toString() != null
                                        && otp.getText().length() >=6)&&(!newpassword.getText().toString().isEmpty() && newpassword.getText().toString() != null)
                                ){
                                    verifyVerificationCode(otp.getText().toString().trim());
                                    ((ViewGroup)promptsView.getParent()).removeView(promptsView);
                                    dialog.cancel();
                                }

                            }
                        });



        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }
    private void phoneAuth() {
        Log.d("verificationMsg", "got to message sender = " +beneNameEt.getText().toString()) ;

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + beneNameEt.getText().toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


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
        mobileno = beneNameEt.getText().toString();
        username = beneNameEt.getText().toString();
        password = newpassword.getText().toString();
        displayname = beneNameEt.getText().toString();
        String url = beneNameEt.getText().toString();


        if (displayname.equals("")) {
            new ShowToast(getApplicationContext(), "Enter Display Name", "error");

        } else if (username.equals("")) {
            new ShowToast(getApplicationContext(), "Enter Username", "error");

        } else if (password.equals("")) {
            new ShowToast(getApplicationContext(), "Enter Password", "error");

        } else {

//            if (!url.trim().equals("")) {
//                if (!url.startsWith("http://") && !url.startsWith("https://"))
//                    url = "http://" + url;
//                URLS.Domain = url;
//                Log.e("url", URLS.Domain);
//                Toast.makeText(getApplicationContext(), URLS.Domain, Toast.LENGTH_LONG).show();
//            }
            if (!NetworkManager.isInternetConnected(getApplicationContext())) {
                Toast.makeText(getApplicationContext(), "Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
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
//            dd = new DialogProgress().DialogProgressInitialoz(getApplicationContext());
//            dd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            Log.e("urrllll", "call login api ");
            List<NameValuePair> par = new ArrayList<NameValuePair>();
            par.add(new BasicNameValuePair("newPassword", password));

            par.add(new BasicNameValuePair("mobile", mobileno));

            try {

                jsonstr = jParser
                        .makeHttpRequest(URLS.URL_CHANGEPASSWITHEMAil, "POST", par);

                Log.e("response", URLS.URL_CHANGEPASSWITHEMAil + " => " + jsonstr);

                json = new JSONObject(jsonstr);
                Sucess = json.getBoolean("success");
                if (Sucess) {
                    showPopup();
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

            if (Sucess) {

                showPopup();
            } else {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        }
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