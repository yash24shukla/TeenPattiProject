package com.teenpatti.queendemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.teenpatti.queendemo.R;
import com.teenpatti.queendemo.api.JSONParser;
import com.teenpatti.queendemo.files.DialogProgress;
import com.teenpatti.queendemo.files.SharedPrefs;
import com.teenpatti.queendemo.files.URLS;
import com.teenpatti.queendemo.model.ShowToast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddcashActivity extends AppCompatActivity implements PaymentResultListener {
    ImageView renow, close;
    ImageView fullPaymentIv;
    EditText enteramount;
    View ist, second, third, fourth;
    String chips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_addcash);
        Checkout.preload(getApplicationContext());

        enteramount = findViewById(R.id.enteramount);
        renow = findViewById(R.id.renow);
        close = findViewById(R.id.crosss);
        fullPaymentIv = findViewById(R.id.fullpayment);
        ist = findViewById(R.id.ist);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        renow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(enteramount.getText().toString().trim())) {
                    Toast.makeText(AddcashActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                    return;
                }
                String samount = enteramount.getText().toString();
                if (samount.equalsIgnoreCase("100")) {
                    chips = "125";
                } else if (samount.equalsIgnoreCase("1500")) {
                    chips = "1530";
                } else {
                    chips = samount;
                }
                paymentCheckout(samount);
            }
        });

        fullPaymentIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://teenpattipremium.com/payment-help"));
                startActivity(intent);
            }
        });

        ist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chips = "10";
                paymentCheckout("10");
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chips = "125";
                paymentCheckout("100");
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chips = "500";
                paymentCheckout("500");
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chips = "1530";
                paymentCheckout("1500");
            }
        });
    }

    private void paymentCheckout(String samount) {
        final float amount = Float.parseFloat(samount) * 100;
        Checkout checkout = new Checkout();
        //test id
        checkout.setKeyID("rzp_test_NFkkabc9r4f5vs");
        //prod id
//                checkout.setKeyID("rzp_live_3joGs0kLPAziti");
        JSONObject object = new JSONObject();
        try {
            object.put("name", SharedPrefs.getString(AddcashActivity.this, SharedPrefs.USERNAME));
            object.put("description", "Teen Patti Premium");
            object.put("theme.color", "#0093DD");
            object.put("currency", "INR");
            object.put("amount", amount);
            object.put("prefill.contact", "03369068482");
            object.put("prefill.email", "test@gmail.com");
            checkout.open(AddcashActivity.this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
         makeApiCall();
    }


    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, " " + s, Toast.LENGTH_SHORT).show();

    }

    private void makeApiCall() {
        new MakePaymentApi().execute();
    }


    class MakePaymentApi extends AsyncTask<String, String, String> {
        JSONParser jParser = new JSONParser();
        boolean Sucess = false;
        String jsonstr = null;
        JSONObject json;

        Dialog dd;
        String msg = "Something went wrong";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dd = new DialogProgress().DialogProgressInitialoz(AddcashActivity.this);
            dd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            Log.e("urrllll", "call ADD_CHIPS api ");
            List<NameValuePair> par = new ArrayList<NameValuePair>();
            par.add(new BasicNameValuePair("userId", SharedPrefs.getString(AddcashActivity.this, SharedPrefs.USER_ID, "0")));
            par.add(new BasicNameValuePair("coins", chips));

            try {
                jsonstr = jParser.makeHttpRequest(URLS.ADD_CHIPS, "POST", par);
                Log.e("response", URLS.ADD_CHIPS + " => " + jsonstr);
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
                new ShowToast(AddcashActivity.this, "Payment successful", "success");
                finish();
            } else {
                Toast.makeText(AddcashActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        }
    }
}