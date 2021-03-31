package com.teenpatti.teenpattipremium.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.skydoves.elasticviews.ElasticLayout;
import com.squareup.picasso.Picasso;
import com.teenpatti.teenpattipremium.R;
import com.teenpatti.teenpattipremium.api.JSONParser;
import com.teenpatti.teenpattipremium.files.DialogProgress;
import com.teenpatti.teenpattipremium.files.SharedPrefs;
import com.teenpatti.teenpattipremium.files.URLS;
import com.teenpatti.teenpattipremium.model.Functions;
import com.teenpatti.teenpattipremium.model.NetworkManager;
import com.teenpatti.teenpattipremium.model.ShowToast;
import com.teenpatti.teenpattipremium.model.Table;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TableFetch_All_Activity extends AppCompatActivity {

    table_adapter Adapter_table;
    GridView GrdView;
    LinearLayout llview;
    ImageView imgTableInfo;

    ArrayList<String> arrBootAmount = new ArrayList<>();
    public static ArrayList<Table> arrtable = new ArrayList<>();
    public static Table SelectTable = new Table();
    Dialog d;

    Animation anim_round;

    ImageView img;

    RelativeLayout Rlpl5Card1, Rlpl5Card2, Rlpl5Card3, Rlpl6Card1, Rlpl6Card2, Rlpl6Card3,
            Rlpl2Card1, Rlpl2Card2, Rlpl2Card3, Rlpl3Card1, Rlpl3Card2, Rlpl3Card3, Rlpl4Card1, Rlpl4Card2, Rlpl4Card3;

    ArrayList<String> arrcurrentplayer = new ArrayList<>();
    LinearLayout llViewInternet;
    HorizontalScrollView hrScrollView;
    KenBurnsView imgLoginBack;
    RandomTransitionGenerator generator;
    ImageView imgTableSetting;

    ImageView imgprofilePic;
    TextView txtProfileName;

    String updatetext;

    TextView txtChips;

    Dialog dStartTable;
    EditText edtPassword;
    TextView txtTableName, txtFullTable, txtInfoBootValue, txtInfoMaxBlind, txtInfoChaalLimit, txtInfoPotLimit, txtInfoTableType, txtInfoTableUserConnected;
    ImageView imgInfoCancel;
    TextView txtInfoStart;
    int posi;
    String Selecttable_id = "";
    String TotalTablePlayer;
    int SelectBootAmout = 0;

    TextView txtPremiumTable, txtFreeTable;
    String Publicprivate = "private";

    Dialog dAppUpdate;
    String Applink = "";
    TextView txtUpdateText;
    Button btnUpdateApp;

    TranslateAnimation mAnimation, mAnimation2, mAnimation3, mAnimation4;
    ImageView imgback1, imgback2, imgback3, imgback4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.tablefetch_all_layout);


        anim_round = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.round);

        BindView();
        BindListner();


//        String json = SharedPrefs.getString(Table_Activity.this, "save_table_json", "");
//
//        if (!json.equals(""))
//            JsonParser(json);

        if (!NetworkManager.isInternetConnected(TableFetch_All_Activity.this)) {
            hrScrollView.setVisibility(View.GONE);
            llViewInternet.setVisibility(View.VISIBLE);
            Toast.makeText(TableFetch_All_Activity.this, "Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
        } else {
            new table_private_asynctask().execute();

        }

        new Asynctask_CheckUpdateApp().execute();


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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.intent_side_left, R.anim.intent_slide_to_right);

    }


    @Override
    protected void onResume() {
        super.onResume();
        txtChips.setText(SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.CHIPS, "0"));
        Picasso.get()
                .load(SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.PROFILEPIC, ""))
                .placeholder(R.drawable.ic_person_defult)
                .into(imgprofilePic);


        txtProfileName.setText(SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.DISPLAY_NAME));


        new CheckLogin_Asyctask().execute();
        new Asynctask_GetUserDetail().execute();

    }

    private void BindView() {

        txtFreeTable = findViewById(R.id.txtFreeTable);
        txtPremiumTable = findViewById(R.id.txtPremiumTable);
        dStartTable = new Dialog(TableFetch_All_Activity.this);
        dStartTable.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dStartTable.setContentView(R.layout.table_start_info_dialog);
        dStartTable.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dStartTable.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dStartTable.getWindow().setGravity(Gravity.CENTER);
        dStartTable.setCanceledOnTouchOutside(true);

        dStartTable.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dStartTable.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        dStartTable.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        edtPassword = dStartTable.findViewById(R.id.edtPassword);
        txtFullTable = dStartTable.findViewById(R.id.txtFullTable);
        txtTableName = dStartTable.findViewById(R.id.txtTableName);
        txtInfoBootValue = dStartTable.findViewById(R.id.txtInfoBootValue);
        txtInfoMaxBlind = dStartTable.findViewById(R.id.txtInfoMaxBlind);
        txtInfoChaalLimit = dStartTable.findViewById(R.id.txtInfoChaalLimit);
        txtInfoPotLimit = dStartTable.findViewById(R.id.txtInfoPotLimit);

        txtInfoTableUserConnected = dStartTable.findViewById(R.id.txtInfoTableUserConnected);
        imgInfoCancel = dStartTable.findViewById(R.id.imgInfoCancel);
        imgInfoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(TableFetch_All_Activity.this, getResources().getString(R.string.mp_buttonClick));
                dStartTable.cancel();
            }
        });

        txtInfoStart = dStartTable.findViewById(R.id.txtInfoStart);


       /* txtInfoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table_Activity.this, getResources().getString(R.string.mp_buttonClick));
                String pass = arrtable.get(posi).getPassword();
                String enterPass = edtPassword.getText().toString();

                if (!arrtable.get(posi).getPassword().equals("")) {
                    if (enterPass.equals("")) {
                        edtPassword.setError("Please Enter Password");
                    } else if (!enterPass.equals(pass)) {
                        edtPassword.setError("Please Enter Correct Password");
                    } else {
                        dStartTable.dismiss();
                        Intent ii = new Intent(Table_Activity.this, Table6_Activity.class);
                        ii.putExtra("table_id", arrtable.get(posi).getId());
                        ii.putExtra("position", posi + "");
                        startActivity(ii);
                        overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
                    }
                } else {
                    dStartTable.dismiss();
                    Intent ii = new Intent(Table_Activity.this, Table6_Activity.class);
                    ii.putExtra("table_id", arrtable.get(posi).getId());
                    ii.putExtra("position", posi + "");
                    startActivity(ii);
                    overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
                }

            }
        });
*/


        txtInfoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Publicprivate.equals("public")) {
                    Functions.startplay(TableFetch_All_Activity.this, getResources().getString(R.string.mp_buttonClick));

                    dStartTable.dismiss();
                    Intent ii = new Intent(TableFetch_All_Activity.this, Table6_Activity.class);
                    ii.putExtra("table_id", SelectTable.getId());
                    ii.putExtra("position", posi + "");
                    startActivity(ii);
                    overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);


                } else {


                    Functions.startplay(TableFetch_All_Activity.this, getResources().getString(R.string.mp_buttonClick));
                    String pass = arrtable.get(posi).getPassword();
                    String enterPass = edtPassword.getText().toString();

                    if (!arrtable.get(posi).getPassword().equals("")) {
                        if (enterPass.equals("")) {
                            edtPassword.setError("Please Enter Password");
                        } else if (!enterPass.equals(pass)) {
                            edtPassword.setError("Please Enter Correct Password");
                        } else {
                            dStartTable.dismiss();
                            Intent ii = new Intent(TableFetch_All_Activity.this, Table6_Activity.class);
                            ii.putExtra("table_id", arrtable.get(posi).getId());
                            ii.putExtra("position", posi + "");
                            startActivity(ii);
                            overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
                        }
                    } else {
                        dStartTable.dismiss();
                        Intent ii = new Intent(TableFetch_All_Activity.this, Table6_Activity.class);
                        ii.putExtra("table_id", arrtable.get(posi).getId());
                        ii.putExtra("position", posi + "");
                        startActivity(ii);
                        overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
                    }


                }


            }
        });


        txtChips = findViewById(R.id.txtChips);

        imgprofilePic = findViewById(R.id.imgprofilePic);
        txtProfileName = findViewById(R.id.txtProfileName);


        imgTableSetting = findViewById(R.id.imgTableSetting);

        llViewInternet = findViewById(R.id.llViewInternet);
        hrScrollView = findViewById(R.id.hrScrollView);


        imgTableInfo = findViewById(R.id.imgTableInfo);
        GrdView = findViewById(R.id.GrdView);
        Adapter_table = new table_adapter();
        GrdView.setAdapter(Adapter_table);
        llview = findViewById(R.id.llview);

        d = new Dialog(TableFetch_All_Activity.this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.table_info_dialog);
        d.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        d.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        d.getWindow().setGravity(Gravity.BOTTOM);
        d.setCanceledOnTouchOutside(true);

        ImageView imgClose = d.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(TableFetch_All_Activity.this, getResources().getString(R.string.mp_buttonClick));
                d.dismiss();
            }
        });


        dAppUpdate = new Dialog(TableFetch_All_Activity.this);
        dAppUpdate.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dAppUpdate.setContentView(R.layout.appupdate_dialog);
        dAppUpdate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dAppUpdate.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        dAppUpdate.getWindow().setGravity(Gravity.BOTTOM);
        dAppUpdate.setCanceledOnTouchOutside(false);

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

        txtUpdateText = dAppUpdate.findViewById(R.id.txtUpdateText);

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


    private void SetlayoutBootAmout() {

        llview.removeAllViews();

        LayoutInflater vi = (LayoutInflater) getApplicationContext()
                .getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);

        Log.e("layout,a", arrBootAmount.size() + "   ");

        for (int i = 0; i < arrBootAmount.size(); i++) {
            View vv = vi.inflate(R.layout.table_adapter, null);
            TextView txtTableAdp1 = vv.findViewById(R.id.txtTableAdp1);
            TextView txtTableAdp2 = vv.findViewById(R.id.txtTableAdp2);
            TextView txtTableAdp3 = vv.findViewById(R.id.txtTableAdp3);
            TextView txtTableAd4 = vv.findViewById(R.id.txtTableAd4);
            ImageView imgRound = vv.findViewById(R.id.imgRound);
            ImageView imgcolor = vv.findViewById(R.id.imgcolor);

            ElasticLayout LayoutTable = vv.findViewById(R.id.LayoutTable);

            LayoutTable.setTag(i);
            LayoutTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Functions.startplay(TableFetch_All_Activity.this, getResources().getString(R.string.mp_buttonClick));

                    posi = (int) v.getTag();
/*

                    SelectTable = arrtable.get(posi);
                    txtTableName.setText(SelectTable.getName());
                    txtInfoBootValue.setText(SelectTable.getBoot());
                    txtInfoMaxBlind.setText(SelectTable.getMaximumblind());
                    txtInfoChaalLimit.setText(SelectTable.getMaxbet());
                    txtInfoPotLimit.setText(SelectTable.getPotLimit());
*/


                    SelectBootAmout = Integer.parseInt(arrBootAmount.get(posi));


                    int Chipsss = (int) Float.parseFloat(SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.CHIPS, "0"));

                    SharedPrefs.save(TableFetch_All_Activity.this, SharedPrefs.CHIPS, Chipsss + "");

                    if (Integer.parseInt(SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.CHIPS, "0")) <= SelectBootAmout) {

                        new ShowToast(TableFetch_All_Activity.this, "You Don't have enough Money!", "error");

                    } else {


                        new Asynctask_SelectTablee().execute();
//                        dStartTable.show();
                    }

                }
            });

            ImageView imgTableLock = vv.findViewById(R.id.imgTableLock);
            imgTableLock.setVisibility(View.GONE);

           /*
            if (arrtable.get(i).getPassword().equals(""))
                imgTableLock.setVisibility(View.GONE);
            else
                imgTableLock.setVisibility(View.VISIBLE);*/

            txtTableAdp1.setText("Rs.\n" + arrBootAmount.get(i));
//          txtTableAdp2.setText("gtype: " + arrtable.get(i).getType());


            imgRound.startAnimation(anim_round);
            /*if (arrtable.get(i).getType().equals("0")) {
                imgRound.setImageResource(R.drawable.table_box_back);
            } else {
                imgRound.setImageResource(R.drawable.table_box_back);
            }
*/

            llview.addView(vv);

        }
    }


    private void Setlayout() {

      /*  txtInfoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Publicprivate.equals("public")) {

                } else {

                }
                Functions.startplay(Table_Activity.this, getResources().getString(R.string.mp_buttonClick));
                String pass = arrtable.get(posi).getPassword();
                String enterPass = edtPassword.getText().toString();

                if (!arrtable.get(posi).getPassword().equals("")) {
                    if (enterPass.equals("")) {
                        edtPassword.setError("Please Enter Password");
                    } else if (!enterPass.equals(pass)) {
                        edtPassword.setError("Please Enter Correct Password");
                    } else {
                        dStartTable.dismiss();
                        Intent ii = new Intent(Table_Activity.this, Table6_Activity.class);
                        ii.putExtra("table_id", arrtable.get(posi).getId());
                        ii.putExtra("position", posi + "");
                        startActivity(ii);
                        overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
                    }
                } else {
                    dStartTable.dismiss();
                    Intent ii = new Intent(Table_Activity.this, Table6_Activity.class);
                    ii.putExtra("table_id", arrtable.get(posi).getId());
                    ii.putExtra("position", posi + "");
                    startActivity(ii);
                    overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
                }

            }
        });
*/

        llview.removeAllViews();
        LayoutInflater vi = (LayoutInflater) getApplicationContext()
                .getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);

        Log.e("layout,a", arrtable.size() + "   ");

        for (int i = 0; i < arrtable.size(); i++) {
            View vv = vi.inflate(R.layout.table_adapter, null);
            TextView txtTableAdp1 = vv.findViewById(R.id.txtTableAdp1);
            TextView txtTableAdp2 = vv.findViewById(R.id.txtTableAdp2);
            TextView txtTableAdp3 = vv.findViewById(R.id.txtTableAdp3);
            TextView txtTableAd4 = vv.findViewById(R.id.txtTableAd4);
            ImageView imgRound = vv.findViewById(R.id.imgRound);
            ImageView imgcolor = vv.findViewById(R.id.imgcolor);

            ElasticLayout LayoutTable = vv.findViewById(R.id.LayoutTable);

            LayoutTable.setTag(i);
            LayoutTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Functions.startplay(TableFetch_All_Activity.this, getResources().getString(R.string.mp_buttonClick));

                    posi = (int) v.getTag();

                    SelectTable = arrtable.get(posi);
                    txtTableName.setText(SelectTable.getName());
                    txtInfoBootValue.setText(SelectTable.getBoot());
                    txtInfoMaxBlind.setText(SelectTable.getMaximumblind());
                    txtInfoChaalLimit.setText(SelectTable.getMaxbet());
                    txtInfoPotLimit.setText(SelectTable.getPotLimit());

                    if (!SelectTable.getPassword().equals("")) {
                        edtPassword.setVisibility(View.VISIBLE);
                    } else {
                        edtPassword.setVisibility(View.GONE);
                    }


                    int Chipsss = (int) Float.parseFloat(SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.CHIPS, "0"));

                    SharedPrefs.save(TableFetch_All_Activity.this, SharedPrefs.CHIPS, Chipsss + "");
                    if (Integer.parseInt(SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.CHIPS, "0")) <= Integer.parseInt(SelectTable.getBoot())) {

                        new ShowToast(TableFetch_All_Activity.this, "You Don't have enough Money!", "error");

                    } else {

                        Selecttable_id = SelectTable.getId();
                        Log.e("verify deviuce", "check user available");
                        new Check_Player_Asynctask().execute();
                        dStartTable.show();
                    }

                }
            });


            ImageView imgTableLock = vv.findViewById(R.id.imgTableLock);
            if (arrtable.get(i).getPassword().equals(""))
                imgTableLock.setVisibility(View.GONE);
            else
                imgTableLock.setVisibility(View.VISIBLE);

            txtTableAdp1.setText(arrtable.get(i).getName());
            txtTableAdp2.setText("gtype: " + arrtable.get(i).getType());

            Log.e("tabke color", arrtable.get(i).getColor_code() + "");

            imgcolor.setColorFilter(Color.parseColor(arrtable.get(i).getColor_code()));
            imgRound.startAnimation(anim_round);
            if (arrtable.get(i).getType().equals("0")) {
                imgRound.setImageResource(R.drawable.table_box_back);
            } else {
                imgRound.setImageResource(R.drawable.table_box_back);
            }

            txtTableAd4.setText("user " + arrtable.get(i).getSlotUsed() + "/" + arrtable.get(i).getMaxPlayers());
            txtTableAdp3.setText("boot " + arrtable.get(i).getBoot());
            llview.addView(vv);

        }
    }

    class table_adapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        class holder {
            TextView txtTableAdp1;
            TextView txtTableAdp2;
            TextView txtTableAdp3;
            LinearLayout LayoutTable;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater vi = (LayoutInflater) getApplicationContext()
                    .getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);
            holder h;
            if (convertView == null) {
                h = new holder();
                convertView = vi.inflate(R.layout.table_adapter, null);
                h.txtTableAdp1 = convertView.findViewById(R.id.txtTableAdp1);
                h.txtTableAdp2 = convertView.findViewById(R.id.txtTableAdp2);
                h.txtTableAdp3 = convertView.findViewById(R.id.txtTableAdp3);
                h.LayoutTable = convertView.findViewById(R.id.LayoutTable);

                convertView.setTag(h);

            } else {
                h = (holder) convertView.getTag();

            }
            return convertView;
        }
    }

    private void BindListner() {

        txtFreeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Publicprivate = "public";
                txtFreeTable.setBackgroundResource(R.drawable.a_table_left_select);
                txtPremiumTable.setBackgroundResource(R.drawable.a_table_right_unselect);
                if (!NetworkManager.isInternetConnected(TableFetch_All_Activity.this)) {
                    hrScrollView.setVisibility(View.GONE);
                    llViewInternet.setVisibility(View.VISIBLE);
                    Toast.makeText(TableFetch_All_Activity.this, "Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
                } else {
                    new table_private_asynctask().execute();
                }

            }
        });


        txtPremiumTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Publicprivate = "private";
                txtFreeTable.setBackgroundResource(R.drawable.a_table_left_unselect);
                txtPremiumTable.setBackgroundResource(R.drawable.a_table_right_select);
                if (!NetworkManager.isInternetConnected(TableFetch_All_Activity.this)) {
                    hrScrollView.setVisibility(View.GONE);
                    llViewInternet.setVisibility(View.VISIBLE);
                    Toast.makeText(TableFetch_All_Activity.this, "Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
                } else {
                    new table_private_asynctask().execute();
                }

            }
        });


        imgTableSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(TableFetch_All_Activity.this, getResources().getString(R.string.mp_buttonClick));

                startActivity(new Intent(TableFetch_All_Activity.this, UserEdit_Activity.class));
            }
        });
        llViewInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(TableFetch_All_Activity.this, getResources().getString(R.string.mp_buttonClick));

                if (!NetworkManager.isInternetConnected(TableFetch_All_Activity.this)) {
                    hrScrollView.setVisibility(View.GONE);
                    llViewInternet.setVisibility(View.VISIBLE);
                    Toast.makeText(TableFetch_All_Activity.this, "Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
                } else {
                    new table_asynctask().execute();
                }
            }
        });
        imgTableInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(TableFetch_All_Activity.this, getResources().getString(R.string.mp_buttonClick));
                d.show();
            }
        });

    }

    class table_asynctask extends AsyncTask<String, String, String> {


        JSONParser jParser = new JSONParser();
        String jsonstr = "";
        Dialog dd;
        KProgressHUD kprogress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            llViewInternet.setVisibility(View.GONE);
            hrScrollView.setVisibility(View.VISIBLE);

            dd = new DialogProgress().DialogProgressInitialoz(TableFetch_All_Activity.this);
            dd.show();

        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> par = new ArrayList<NameValuePair>();

            try {

                jsonstr = jParser
                        .makeHttpRequest(URLS.Domain + URLS.URL_TABLE_BOOTAMOUT, "POST", par);

//                SharedPrefs.save(Table_Activity.this, "save_table_json", jsonstr);
//                String jsonstring = Webservice.Send_data(key,"http://18.218.76.134:3000/api/users/login");
                Log.e("response", URLS.Domain + URLS.URL_TABLE_BOOTAMOUT + " =>" + jsonstr.toString());

                JsonParser_BootAmout(jsonstr);

            } catch (Exception e) {
                Log.e("errro", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                dd.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SetlayoutBootAmout();

        }
    }

    class table_private_asynctask extends AsyncTask<String, String, String> {


        JSONParser jParser = new JSONParser();
        String jsonstr = "";
        Dialog dd;
        KProgressHUD kprogress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            llViewInternet.setVisibility(View.GONE);
            hrScrollView.setVisibility(View.VISIBLE);

            dd = new DialogProgress().DialogProgressInitialoz(TableFetch_All_Activity.this);
           runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   dd.show();
               }
           });


        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> par = new ArrayList<NameValuePair>();

            try {

                jsonstr = jParser
                        .makeHttpRequest(URLS.Domain + URLS.URL_TABLE, "GET", par);

                Log.e("response", URLS.Domain + URLS.URL_TABLE + " =>" + jsonstr.toString());

                JsonParser(jsonstr);
            } catch (Exception e) {
                Log.e("errro", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                dd.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Setlayout();

        }
    }

    void JsonParser_BootAmout(String jsonstr) {
        try {
            boolean Sucess;
            JSONObject json = null;
            arrBootAmount = new ArrayList<>();
            json = new JSONObject(jsonstr);
            Sucess = json.getBoolean("success");
            if (Sucess) {
                JSONArray jsonarr = json.getJSONArray("data");
                arrtable = new ArrayList<>();
                for (int i = 0; i < jsonarr.length(); i++) {


                    try {
                        int chipppsss = (int) Float.parseFloat(SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.CHIPS, "0"));

                        if (chipppsss <= 25000) {

                            if (jsonarr.getInt(i) >= 0 && jsonarr.getInt(i) <= 200) {
                                arrBootAmount.add(jsonarr.getInt(i) + "");
                            }
                        } else if (chipppsss <= 50000) {
                            if (jsonarr.getInt(i) >= 0 && jsonarr.getInt(i) <= 500) {
                                arrBootAmount.add(jsonarr.getInt(i) + "");
                            }
                        } else if (chipppsss > 50000) {
                            if (jsonarr.getInt(i) >= 200 && jsonarr.getInt(i) <= 2000) {
                                arrBootAmount.add(jsonarr.getInt(i) + "");
                            }
                        }
                    } catch (Exception e) {
                        arrBootAmount.add(jsonarr.getInt(i) + "");
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    void JsonParser(String jsonstr) {
        try {
            String Sucess;
            arrtable = new ArrayList<>();
            JSONObject json = null;
            json = new JSONObject(jsonstr);
            Sucess = json.getString("status");
            if (Sucess.equals("success")) {
                JSONArray jsonarr = json.getJSONArray("data");
                arrtable = new ArrayList<>();
                for (int i = 0; i < jsonarr.length(); i++) {
                    JSONObject jrow = jsonarr.getJSONObject(i);
                    Table tt = new Table();
                    tt.setId(jrow.getString("_id"));
                    if (jrow.has("name"))
                        tt.setName(jrow.getString("name"));
                    if (jrow.has("maxPlayers"))
                        tt.setMaxPlayers(jrow.getString("maxPlayers"));
                    if (jrow.has("slotUsed"))
                        tt.setSlotUsed(jrow.getString("slotUsed"));
                    if (jrow.has("boot"))
                        tt.setBoot(jrow.getString("boot"));
                    if (jrow.has("maxBet"))
                        tt.setMaxbet(jrow.getString("maxBet"));

                    if (jrow.has("potLimit"))
                        tt.setPotLimit(jrow.getString("potLimit"));

                    if (jrow.has("amount"))
                        tt.setAmount(jrow.getString("amount"));

                    if (jrow.has("password"))
                        tt.setPassword(jrow.getString("password"));
                    else tt.setPassword("");

                    if (jrow.has("color_code"))
                        tt.setColor_code(jrow.getString("color_code"));
                    else
                        tt.setColor_code("#000000");

                    if (tt.getColor_code().equals(""))
                        tt.setColor_code("#000000");

                    if (jrow.has("type"))
                        tt.setType(jrow.getString("type"));
                    else
                        tt.setType("0");
                    tt.setMaximumblind("4");


                    if (tt.getType().equals(SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.TYPE, "")))
                        arrtable.add(tt);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    class CheckLogin_Asyctask extends AsyncTask<String, String, String> {

        JSONParser jParser = new JSONParser();
        Boolean Sucess = true;
        String jsonstr = null;
        JSONObject json;
        KProgressHUD kprogress;
        Dialog dd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> par = new ArrayList<NameValuePair>();
            par.add(new BasicNameValuePair("userName", SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.USERNAME, "")));
            par.add(new BasicNameValuePair("deviceId", SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.DEVICE_ID, "1234")));


            try {

                jsonstr = jParser
                        .makeHttpRequest(URLS.Domain + URLS.URL_VERIFY_DEVICE, "POST", par);

                Log.e("response", URLS.Domain + URLS.URL_VERIFY_DEVICE + " => " + jsonstr);

                json = new JSONObject(jsonstr);
                Sucess = json.getBoolean("success");


            } catch (Exception e) {
                Log.e("errro", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!Sucess) {
                SharedPrefs.clearPrefs(TableFetch_All_Activity.this);
                Intent ii = new Intent(TableFetch_All_Activity.this, Login_Activity.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(ii);
            } else {

            }
        }

    }

    class Check_Player_Asynctask extends AsyncTask<String, String, String> {


        JSONParser jParser = new JSONParser();
        String Sucess;
        JSONObject json = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> par = new ArrayList<NameValuePair>();

            par.add(new BasicNameValuePair("tableId", Selecttable_id));

            try {

                String jsonstr = jParser.makeHttpRequest(URLS.Domain + URLS.URL_TOTALPLAYER, "POST", par);

                Log.e("check user", URLS.Domain + URLS.URL_TOTALPLAYER + " :  " + jsonstr);

                json = new JSONObject(jsonstr);

                String Sucess = json.getString("status");

                if (Sucess.equals("success")) {
                    TotalTablePlayer = json.getString("totalpayer");
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("errro", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {


                if (Integer.parseInt(TotalTablePlayer) > 4) {
                    txtFullTable.setText("Table Full");
                    txtInfoStart.setVisibility(View.VISIBLE);
                    txtInfoStart.setAlpha(0.5f);
                    txtInfoStart.setEnabled(false);
                    Functions.displayToast("Table is Full ! Please Try in another Table");

                } else {
                    txtFullTable.setText("");
                    txtInfoStart.setVisibility(View.VISIBLE);
                    txtInfoStart.setAlpha(1f);
                    txtInfoStart.setEnabled(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Asynctask_SelectTablee extends AsyncTask<String, String, String> {


        JSONParser jParser = new JSONParser();
        boolean Sucess;
        JSONObject json = null;
        Dialog dd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dd = new DialogProgress().DialogProgressInitialoz(TableFetch_All_Activity.this);
            dd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> par = new ArrayList<NameValuePair>();

            par.add(new BasicNameValuePair("boot", SelectBootAmout + ""));

            try {

                String jsonstr = jParser.makeHttpRequest(URLS.Domain + URLS.URL_TABLE_FINAL_SELECT, "POST", par);

                Log.e("TableActivity.this", URLS.Domain + URLS.URL_TABLE_FINAL_SELECT + " :  " + jsonstr);

                json = new JSONObject(jsonstr);


                JSONObject json = null;
                json = new JSONObject(jsonstr);
                Sucess = json.getBoolean("success");
                if (Sucess) {
                    JSONArray jsonarr = json.getJSONArray("data");
                    arrtable = new ArrayList<>();
                    for (int i = 0; i < jsonarr.length(); i++) {
                        JSONObject jrow = jsonarr.getJSONObject(i);

                        SelectTable.setId(jrow.getString("_id"));
                        if (jrow.has("name"))
                            SelectTable.setName("Table Rs. " + SelectBootAmout);
                        if (jrow.has("maxPlayers"))
                            SelectTable.setMaxPlayers(jrow.getString("maxPlayers"));
                        if (jrow.has("slotUsed"))
                            SelectTable.setSlotUsed(jrow.getString("slotUsed"));
                        if (jrow.has("boot"))
                            SelectTable.setBoot(jrow.getString("boot"));
                        if (jrow.has("maxBet"))
                            SelectTable.setMaxbet(jrow.getString("maxBet"));

                        if (jrow.has("potLimit"))
                            SelectTable.setPotLimit(jrow.getString("potLimit"));

                        if (jrow.has("amount"))
                            SelectTable.setAmount(jrow.getString("amount"));

                        if (jrow.has("password"))
                            SelectTable.setPassword(jrow.getString("password"));
                        else SelectTable.setPassword("");

                        if (jrow.has("color_code"))
                            SelectTable.setColor_code(jrow.getString("color_code"));
                        else
                            SelectTable.setColor_code("#000000");

                        if (SelectTable.getColor_code().equals(""))
                            SelectTable.setColor_code("#000000");

                        if (jrow.has("type"))
                            SelectTable.setType(jrow.getString("type"));
                        else
                            SelectTable.setType("0");
                        SelectTable.setMaximumblind("4");


                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
                Log.e("errro", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dd.dismiss();

//            SelectTable = arrtable.get(posi);
            txtTableName.setText(SelectTable.getName());
            txtInfoBootValue.setText(SelectTable.getBoot());
            txtInfoMaxBlind.setText(SelectTable.getMaximumblind());
            txtInfoChaalLimit.setText(SelectTable.getMaxbet());
            txtInfoPotLimit.setText(SelectTable.getPotLimit());
            edtPassword.setVisibility(View.GONE);
            Selecttable_id = SelectTable.getId();

            dStartTable.show();
            txtFullTable.setText("");
            txtInfoStart.setVisibility(View.VISIBLE);
            txtInfoStart.setAlpha(1f);
            txtInfoStart.setEnabled(true);

        }
    }

    class Asynctask_GetUserDetail extends AsyncTask<String, String, String> {

        JSONParser jParser = new JSONParser();
        Boolean Sucess = true;
        String jsonstr = null;
        JSONObject json;
        KProgressHUD kprogress;
        Dialog dd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> par = new ArrayList<NameValuePair>();
            par.add(new BasicNameValuePair("userId", SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.USER_ID, "")));
            par.add(new BasicNameValuePair("deviceId", SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.DEVICE_ID, "1234")));


            try {

                jsonstr = jParser
                        .makeHttpRequest(URLS.Domain + URLS.URL_GET_USERDETAIL, "POST", par);

                Log.e("response", URLS.Domain + URLS.URL_GET_USERDETAIL + " => " + jsonstr);

                json = new JSONObject(jsonstr);
                Sucess = json.getBoolean("success");
                if (Sucess) {
                    JSONObject jrow = json.getJSONObject("data");
                    SharedPrefs.save(TableFetch_All_Activity.this, SharedPrefs.CHIPS, jrow.getString("chips"));

                }


            } catch (Exception e) {
                Log.e("errro", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtChips.setText(SharedPrefs.getString(TableFetch_All_Activity.this, SharedPrefs.CHIPS, "0"));


        }

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
