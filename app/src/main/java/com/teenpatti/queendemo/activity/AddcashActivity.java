package com.teenpatti.queendemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.paykun.sdk.PaykunApiCall;
import com.paykun.sdk.eventbus.Events;
import com.paykun.sdk.eventbus.GlobalBus;
import com.paykun.sdk.helper.PaykunHelper;
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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddcashActivity extends AppCompatActivity implements PaymentResultListener {
    ImageView renow, close;
    ImageView fullPaymentIv;
    Button renowPaykun;
    EditText enteramount;
    View ist, second, third, fourth;
    String chips;
    View promptsView ;
    Button paykunselectbtn , razorpayselectbtn;
    AlertDialog.Builder alertDialogBuilder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_addcash);
        Checkout.preload(getApplicationContext());
        LayoutInflater li = this.getLayoutInflater();

        enteramount = findViewById(R.id.enteramount);
        renow = findViewById(R.id.renow);
        close = findViewById(R.id.crosss);
        fullPaymentIv = findViewById(R.id.fullpayment);
        ist = findViewById(R.id.ist);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        renowPaykun = findViewById(R.id.renowPaykun) ;
        promptsView = li.inflate(R.layout.select_aymentmethod, null);
        alertDialogBuilder = new AlertDialog.Builder(this);

        paykunselectbtn = (Button) promptsView.findViewById(R.id.selectPaykunBtn);
        razorpayselectbtn = (Button) promptsView.findViewById(R.id.selectRazorpayBtn);
        alertDialogBuilder.setView(promptsView);

        renowPaykun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                paykunPayment(samount);

            }
        });


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
                buildDialogBox("10");

//                paymentCheckout("10");
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chips = "125";
                buildDialogBox("100");
//                paymentCheckout("100");
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chips = "500";
                buildDialogBox("500");
//                paymentCheckout("500");
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chips = "1530";
                buildDialogBox("1500");
//                paymentCheckout("1500");
            }
        });
    }

    private void buildDialogBox(final String finalamount) {

        paykunselectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paykunPayment(finalamount)  ;
            }
        });
        razorpayselectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentCheckout(finalamount);

            }
        });
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Ok",
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

    private void paykunPayment(String samount) {
        final float amount = Float.parseFloat(samount) * 100;

        JSONObject object = new JSONObject();
        try {
            object.put("merchant_id","515923232856163");
            object.put("access_token","757D3122F51AA854783080BEE5A87FFE");
            object.put("customer_name","");
            object.put("customer_email","");
            object.put("customer_phone","");
            object.put("product_name","Teenpatti Premium");
            object.put("order_no",System.currentTimeMillis()); // order no. should have 10 to 30 character in numeric format
            object.put("amount",samount);  // minimum amount should be 10
            object.put("isLive",true); // need to send false if you are in sandbox mode
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new PaykunApiCall.Builder(AddcashActivity.this).sendJsonObject(object); // Paykun api to initialize your payment and send info.
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.getBus().register(AddcashActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(AddcashActivity.this);
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getResults(Events.PaymentMessage message) {
        if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_SUCCESS)){
            // do your stuff here
            // message.getTransactionId() will return your failed or succeed transaction id
            /* if you want to get your transaction detail call message.getTransactionDetail()
             *  getTransactionDetail return all the field from server and you can use it here as per your need
             *  For Example you want to get Order id from detail use message.getTransactionDetail().order.orderId */
            if(!TextUtils.isEmpty(message.getTransactionId())) {
                Toast.makeText(getApplicationContext(), "Your Transaction is succeed with transaction id : "+message.getTransactionId() , Toast.LENGTH_SHORT).show();
                Log.v("order id"," getting order id value : "+message.getTransactionDetail().order.orderId);
                makeApiCall() ;
            }
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_FAILED)){
            // do your stuff here
            Toast.makeText(getApplicationContext(),"Your Transaction is failed",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_SERVER_ISSUE)){
            // do your stuff here
            Toast.makeText(getApplicationContext(),PaykunHelper.MESSAGE_SERVER_ISSUE,Toast.LENGTH_SHORT).show();
        }else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_ACCESS_TOKEN_MISSING)){
            // do your stuff here
            Toast.makeText(getApplicationContext(),"Access Token missing",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_MERCHANT_ID_MISSING)){
            // do your stuff here
            Toast.makeText(getApplicationContext(),"Merchant Id is missing",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_INVALID_REQUEST)){
            Toast.makeText(getApplicationContext(),"Invalid Request",Toast.LENGTH_SHORT).show();
        }
        else if(message.getResults().equalsIgnoreCase(PaykunHelper.MESSAGE_NETWORK_NOT_AVAILABLE)){
            Toast.makeText(getApplicationContext(),"Network is not available",Toast.LENGTH_SHORT).show();
        }
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