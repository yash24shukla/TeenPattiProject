package com.teenpatti.queendemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.teenpatti.queendemo.R;
import com.teenpatti.queendemo.api.JSONParser;
import com.teenpatti.queendemo.files.DialogProgress;
import com.teenpatti.queendemo.files.SharedPrefs;
import com.teenpatti.queendemo.files.URLS;
import com.teenpatti.queendemo.model.Functions;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WithdrawlActivity extends AppCompatActivity implements PaymentResultListener {
    ImageView suppt, cancel, rules;
    EditText amount;
    ImageView bnkcard;
    TextView balncemoneyval, withdmoenyval;
    MaterialButton widbutton, allbtn;
    float defaultChips ;
    float chipsinfloat ;
    Dialog d;
    String coins ="";
    float totalwithdaw ;
    String totalwithdawstring ;
    String defaultAmt, bname, ifsc , accnum ;
    Float fdefaultAmt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_withdrawl);
        widbutton = findViewById(R.id.widbutton);
        suppt = findViewById(R.id.suppt);
        amount = findViewById(R.id.amount);
        allbtn = findViewById(R.id.allbtn);
        cancel = findViewById(R.id.cancel);
        rules = findViewById(R.id.rules);
        bnkcard = findViewById(R.id.bnkcard);
        defaultChips = Float.parseFloat(getString(R.string.defaultMoney));
        balncemoneyval = findViewById(R.id.balncemoneyval);
        withdmoenyval = findViewById(R.id.withdmoenyval);


        d = new Dialog(WithdrawlActivity.this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.table_info_dialog);
        d.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        d.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        d.getWindow().setGravity(Gravity.BOTTOM);
        d.setCanceledOnTouchOutside(true);

        defaultAmt = SharedPrefs.getString(WithdrawlActivity.this, SharedPrefs.DEFAULTAMT) ;


        fdefaultAmt = Float.valueOf(defaultAmt) ;


        ImageView imgClose = d.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(WithdrawlActivity.this, getResources().getString(R.string.mp_buttonClick));
                d.dismiss();
            }
        });


        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(WithdrawlActivity.this, getResources().getString(R.string.mp_buttonClick));
                d.show();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String balance = String.valueOf(Math.round(Float.parseFloat(SharedPrefs.getString(WithdrawlActivity.this, SharedPrefs.CHIPS, "0"))));
        float balancefloat = Math.round(Float.parseFloat(SharedPrefs.getString(WithdrawlActivity.this, SharedPrefs.CHIPS, "0")));
        balncemoneyval.setText(balance);
        chipsinfloat = Float.parseFloat(SharedPrefs.getString(WithdrawlActivity.this, SharedPrefs.CHIPS, "0")) ;


        if(chipsinfloat == fdefaultAmt || fdefaultAmt < 0) {
            totalwithdaw = 0 ;
        }
        else if(chipsinfloat > fdefaultAmt){

            totalwithdaw =fdefaultAmt ;

        }
        else if (chipsinfloat < fdefaultAmt) {

            totalwithdaw = fdefaultAmt ;


        }


        totalwithdaw = Math.round(totalwithdaw) ;
        totalwithdawstring = String.valueOf(totalwithdaw);


        withdmoenyval.setText(totalwithdawstring);

        allbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String allAmount = withdmoenyval.getText().toString();

                amount.setText(allAmount);
            }
        });


        suppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://teenpattipremium.com/"));
                startActivity(intent);
            }
        });

        widbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                coins = amount.getText().toString().trim();
                float fcoins= Float.valueOf(coins) ;
                Log.d("withdrawTest", String.valueOf(fcoins));
                if(fcoins >= 50 && fcoins <= 10000) {


                if(fcoins <= totalwithdaw && fcoins <= chipsinfloat) {



                if (TextUtils.isEmpty(coins)) {
                    Toast.makeText(WithdrawlActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                    return;
                }
              String samount = amount.getText().toString();

                final int amount = Math.round(Float.parseFloat(samount) * 100);
//                Checkout checkout = new Checkout();
//                checkout.setKeyID("rzp_test_NFkkabc9r4f5vs");
//                JSONObject object = new JSONObject();
//                try {
////                    object.put("name", SharedPrefs.getString(WithdrawlActivity.this, SharedPrefs.USERNAME));
////                    object.put("description", "testpayment");
////                    object.put("theme.color", "#0093DD");
////                    object.put("currency", "INR");
////                    object.put("amount", amount);
////                    object.put("prefill.contact", "03369068482");
////                    object.put("prefill.email", "test@gmail.com");
////                    checkout.open(WithdrawlActivity.this, object);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                    if(fcoins > fdefaultAmt){
                        Toast.makeText(getApplicationContext(), "Enter valid amount" , Toast.LENGTH_SHORT).show();

                    }else{
                        fdefaultAmt = fdefaultAmt - fcoins ;

                    SharedPrefs.save(getApplicationContext(), SharedPrefs.DEFAULTAMT, String.valueOf(fdefaultAmt)) ;
                    float temp = chipsinfloat - fcoins;
                    SharedPrefs.save(getApplicationContext(), SharedPrefs.CHIPS, String.valueOf(temp));

                    makeApiCall(); }
            }

                else {
                    Toast.makeText(getApplicationContext(), "Can't Withdraw more than Withdraw money", Toast.LENGTH_SHORT).show();
                }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Withdrawable amount should be minimum Rs50", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bnkcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup();
            }
        });

    }

    private void showPopup() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_bank);
        ImageView closeIv = dialog.findViewById(R.id.closeIv);
        MaterialButton saveBtn = dialog.findViewById(R.id.saveBtn);

        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showWithdrawalPopup() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_withdrawal);
        ImageView closeIv = dialog.findViewById(R.id.closeIv);
        MaterialButton saveBtn = dialog.findViewById(R.id.saveBtn);

        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    @Override
    public void onPaymentSuccess(String s) {

        Toast.makeText(this, "Withdrawal placed, check your email for more details", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, " " + s, Toast.LENGTH_SHORT).show();

    }


    private void makeApiCall() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_bank);
        final EditText bnamelayout = dialog.findViewById(R.id.beneNameEt) ;
        final EditText accnumlayout = dialog.findViewById(R.id.accNumEt) ;
        final EditText ifsclayout = dialog.findViewById(R.id.ifscCodeEt) ;

        ImageView closeIv = dialog.findViewById(R.id.closeIv);
        MaterialButton saveBtn = dialog.findViewById(R.id.saveBtn);

        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bname =  bnamelayout.getText().toString() ;
                accnum = accnumlayout.getText().toString();
                ifsc = ifsclayout.getText().toString();
                new WithdrawalCall().execute();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    class WithdrawalCall extends AsyncTask<String, String, String> {
        JSONParser jParser = new JSONParser();
        boolean Sucess = false;
        String jsonstr = null;
        JSONObject json;

        Dialog dd;
        String msg = "Something went wrong";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dd = new DialogProgress().DialogProgressInitialoz(WithdrawlActivity.this);
            dd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            Log.e("urrllll", "call WITHDRAWAL_REQUEST api ");
            List<NameValuePair> par = new ArrayList<NameValuePair>();
            par.add(new BasicNameValuePair("userName", SharedPrefs.getString(WithdrawlActivity.this, SharedPrefs.USERNAME, "")

            +" \n accnum = "+ accnum + " \n bname = "+ bname+" \n ifsc = " + ifsc));
            par.add(new BasicNameValuePair("userId", SharedPrefs.getString(WithdrawlActivity.this, SharedPrefs.USER_ID, "0")));
            par.add(new BasicNameValuePair("coins", coins));
            par.add(new BasicNameValuePair("status", "Pending" ));


            try {
                jsonstr = jParser.makeHttpRequest(URLS.WITHDRAWAL_REQUEST, "POST", par);
                Log.e("response", URLS.WITHDRAWAL_REQUEST + " => " + jsonstr);
                json = new JSONObject(jsonstr);
                Sucess = json.getBoolean("success");
                if (Sucess) {

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
                showWithdrawalPopup();
//                new ShowToast(WithdrawlActivity.this, "Payment successful", "success");
//                finish();
            } else {
                Toast.makeText(WithdrawlActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

}