package com.teenpatti.teenpattipremium.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.teenpatti.teenpattipremium.R;
import com.teenpatti.teenpattipremium.api.JSONParser;
import com.teenpatti.teenpattipremium.databinding.TableAdapterNewBinding;
import com.teenpatti.teenpattipremium.files.BaseUtils;
import com.teenpatti.teenpattipremium.files.DialogProgress;
import com.teenpatti.teenpattipremium.files.SharedPrefs;
import com.teenpatti.teenpattipremium.files.URLS;
import com.teenpatti.teenpattipremium.model.Functions;
import com.teenpatti.teenpattipremium.model.NetworkManager;
import com.teenpatti.teenpattipremium.model.Table;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StartActivity extends AppCompatActivity {


    ImageView card, settings, rules, support, email;
    View cashout, totalChips;
    View Recharge;
    TextView addcash, anouncement;
    TextView userNameTv, chipBalanceTv;
    Dialog d;
    Dialog ppdialogue;
    View ppview;
    View flash, bind, bonus;
    CircleImageView profileIv;
    Button updateBtn   ;
    public static ArrayList<Table> arrtable = new ArrayList<>();

    //    table_adapter Adapter_table;
//    GridView GrdView;
    RecyclerView recyclerView;
    String announcementStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start);
        cashout = findViewById(R.id.cashout);
        updateBtn = findViewById(R.id.openUpadateBtn) ;
        card = findViewById(R.id.carda);
        Recharge = findViewById(R.id.b2container);
        addcash = findViewById(R.id.addCash);
        settings = findViewById(R.id.setting);
        rules = findViewById(R.id.rule);
        support = findViewById(R.id.support);
        email = findViewById(R.id.email);
        anouncement = findViewById(R.id.anouncement);
        userNameTv = findViewById(R.id.usernameTv);
        profileIv = findViewById(R.id.profileIv);
        chipBalanceTv = findViewById(R.id.chipBalTv);
        totalChips = findViewById(R.id.totalChips);
        flash = findViewById(R.id.flash);
        bind = findViewById(R.id.bind);
        bonus = findViewById(R.id.bonus);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(getString(R.string.anouncementUrl)); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        anouncement.setSelected(true);
        String defaultAmt = SharedPrefs.getString(getApplicationContext(), SharedPrefs.DEFAULTAMT) ;
        if(defaultAmt == "" || defaultAmt == null){
            SharedPrefs.save(this, SharedPrefs.DEFAULTAMT, "0") ;


        }

        d = new Dialog(StartActivity.this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.table_info_dialog);
        d.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        d.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        d.getWindow().setGravity(Gravity.BOTTOM);
        d.setCanceledOnTouchOutside(true);


        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://teenpattipremium.com/"));
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "support@teenpattipremium.com", null));
                startActivity(Intent.createChooser(emailIntent, null));

            }
        });


        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                letsplay();


            }
        });
        Recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCashPage();

            }
        });

        cashout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, WithdrawlActivity.class));
                overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);

            }
        });


        addcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCashPage();

            }
        });

        totalChips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCashPage();

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(StartActivity.this, getResources().getString(R.string.mp_buttonClick));

                startActivity(new Intent(StartActivity.this, UserEdit_Activity.class));
            }
        });

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(StartActivity.this, getResources().getString(R.string.mp_buttonClick));
                d.show();
            }
        });


        ImageView imgClose = d.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(StartActivity.this, getResources().getString(R.string.mp_buttonClick));
                d.dismiss();
            }
        });

        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCominSoonPopup();
            }
        });
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCominSoonPopup();
            }
        });
        bonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCominSoonPopup();
            }
        });

    }

    private void addCashPage() {
        startActivity(new Intent(StartActivity.this, AddcashActivity.class));
        overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeApiCall();
        getAllPostCall();
    }

    public void showCominSoonPopup() {
        final Dialog dialog = new Dialog(this, R.style.DialogAnimation2);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setGravity(Gravity.END | Gravity.TOP);
        }
        dialog.setContentView(R.layout.dialog_coming_soon);
        dialog.show();
        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            }, 1500);
        } catch (Exception e) {
            e.printStackTrace();
            dialog.dismiss();
        }
    }

    public void letsplay() {
        ppdialogue = new Dialog(this, R.style.DialogAnimation2);
        ppdialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // ppdialogue.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.b2)));
        ppdialogue.setContentView(R.layout.dialogue_layout);

        recyclerView = ppdialogue.findViewById(R.id.recyclerView);

        ImageView crossview = ppdialogue.findViewById(R.id.cnccl);
        crossview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ppdialogue.cancel();
            }
        });


        /* ImageView Playnext = ppdialogue.findViewById(R.id.plynext);
        ImageView ppplyaer = ppdialogue.findViewById(R.id.ppplyaer);
        ImageView newplyer = ppdialogue.findViewById(R.id.newplyer);
        ImageView tenplyer = ppdialogue.findViewById(R.id.tenplyer);
        ImageView table1 = ppdialogue.findViewById(R.id.table1);
        ImageView table2 = ppdialogue.findViewById(R.id.table2);
        Playnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // moveToTable();
            }
        });
        ppplyaer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToTable("5a6c91e82ccf76abdbd0b8ca", "1");
            }
        });
       */


        if (!NetworkManager.isInternetConnected(StartActivity.this)) {
            Toast.makeText(StartActivity.this, "Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
        } else {
            new table_private_asynctask().execute();
        }

        ppdialogue.show();

    }

    private void moveToTable(String tableId, String posi) {
        Intent intent = new Intent(StartActivity.this, Table6_Activity.class);
        intent.putExtra("table_id", tableId);
        intent.putExtra("position", posi);
        startActivity(intent);
        overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
    }

    class table_private_asynctask extends AsyncTask<String, String, String> {
        JSONParser jParser = new JSONParser();
        String jsonstr = "";
        Dialog dd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dd = new DialogProgress().DialogProgressInitialoz(StartActivity.this);
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
                jsonstr = jParser.makeHttpRequest(URLS.Domain + URLS.URL_TABLE, "GET", par);
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

                    if (jrow.has("tableSubType"))
                        tt.setTableType(jrow.optString("tableSubType"));
                    else tt.setTableType("");

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


                    if (tt.getType().equals(SharedPrefs.getString(StartActivity.this, SharedPrefs.TYPE, "")))
                        arrtable.add(tt);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void Setlayout() {
        TablesAdapter adapter = new TablesAdapter(StartActivity.this, arrtable);
        recyclerView.setLayoutManager(new LinearLayoutManager(StartActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    public class TablesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final Context context;
        private ArrayList<Table> list = new ArrayList<>();

        public TablesAdapter(Context context, ArrayList<Table> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(context, R.layout.table_adapter_new, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            final ViewHolder holder = (ViewHolder) viewHolder;
            final Table table = list.get(holder.getAdapterPosition());

            holder.dataBinding.txtTableAdp1.setText(table.getName());
            if (!table.getTableType().equalsIgnoreCase("public")) {
                holder.dataBinding.imgTableLock.setVisibility(View.VISIBLE);
            } else {
                holder.dataBinding.imgTableLock.setVisibility(View.GONE);
            }

            holder.dataBinding.LayoutTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("TAG", "onClick: " + table.getId());
//                    moveToTable(table.getId(), String.valueOf(holder.getAdapterPosition()));
                    showTableInfo(table.getId(), holder.getAdapterPosition(), table);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list != null && list.size() > 0 ? list.size() : 0;
        }

        public void addListItems(ArrayList<Table> arrayList) {
            list = new ArrayList<>();
            list.addAll(arrayList);
            notifyItemInserted(arrayList.size());
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TableAdapterNewBinding dataBinding;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                dataBinding = TableAdapterNewBinding.bind(itemView);
            }
        }
    }

    private void showTableInfo(final String Publicprivate, final int posi, final Table table) {
        if (ppdialogue != null) {
            //ppdialogue.dismiss();
        }
        final Dialog dStartTable = new Dialog(StartActivity.this);
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

        final EditText edtPassword = dStartTable.findViewById(R.id.edtPassword);
        TextView txtFullTable = dStartTable.findViewById(R.id.txtFullTable);
        TextView txtTableName = dStartTable.findViewById(R.id.txtTableName);
        TextView txtInfoBootValue = dStartTable.findViewById(R.id.txtInfoBootValue);
        TextView txtInfoMaxBlind = dStartTable.findViewById(R.id.txtInfoMaxBlind);
        TextView txtInfoChaalLimit = dStartTable.findViewById(R.id.txtInfoChaalLimit);
        TextView txtInfoPotLimit = dStartTable.findViewById(R.id.txtInfoPotLimit);

        TextView txtInfoTableUserConnected = dStartTable.findViewById(R.id.txtInfoTableUserConnected);
        ImageView imgInfoCancel = dStartTable.findViewById(R.id.imgInfoCancel);
        imgInfoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(StartActivity.this, getResources().getString(R.string.mp_buttonClick));
                dStartTable.cancel();
            }
        });

        TextView txtInfoStart = dStartTable.findViewById(R.id.txtInfoStart);

        txtTableName.setText(table.getName());
        txtInfoBootValue.setText(table.getBoot());
        txtInfoMaxBlind.setText(table.getMaximumblind());
        txtInfoChaalLimit.setText(table.getMaxbet());
        txtInfoPotLimit.setText(table.getPotLimit());

        if (!table.getPassword().equals("")) {
            edtPassword.setVisibility(View.VISIBLE);
        } else {
            edtPassword.setVisibility(View.GONE);
        }
        txtInfoStart.setAlpha(1f);
        txtInfoStart.setEnabled(true);

        txtInfoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Publicprivate.equals("public")) {
                    Functions.startplay(StartActivity.this, getResources().getString(R.string.mp_buttonClick));

                    dStartTable.dismiss();
                    Intent ii = new Intent(StartActivity.this, Table6_Activity.class);
                    ii.putExtra("table_id", table.getId());
                    ii.putExtra("position", posi + "");
                    ii.putExtra("selectTable", table);
                    startActivity(ii);
                    overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);

                } else {
                    Functions.startplay(StartActivity.this, getResources().getString(R.string.mp_buttonClick));
                    String pass = table.getPassword();
                    String enterPass = edtPassword.getText().toString();

                    if (!table.getPassword().equals("")) {
                        if (enterPass.equals("")) {
                            edtPassword.setError("Please Enter Password");
                        } else if (!enterPass.equals(pass)) {
                            edtPassword.setError("Please Enter Correct Password");
                        } else {
                            dStartTable.dismiss();
                            Intent ii = new Intent(StartActivity.this, Table6_Activity.class);
                            ii.putExtra("table_id", table.getId());
                            ii.putExtra("position", posi + "");
                            ii.putExtra("selectTable", table);
                            startActivity(ii);
                            overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
                        }
                    } else {
                        dStartTable.dismiss();
                        Intent ii = new Intent(StartActivity.this, Table6_Activity.class);
                        ii.putExtra("table_id", table.getId());
                        ii.putExtra("position", posi + "");
                        ii.putExtra("selectTable", table);
                        startActivity(ii);
                        overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
                    }
                }
            }
        });

        dStartTable.show();
    }

    private void makeApiCall() {
        new Asynctask_GetUserDetail().execute();
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
            par.add(new BasicNameValuePair("userId", SharedPrefs.getString(StartActivity.this, SharedPrefs.USER_ID, "")));
            par.add(new BasicNameValuePair("deviceId", SharedPrefs.getString(StartActivity.this, SharedPrefs.DEVICE_ID, "1234")));


            try {

                jsonstr = jParser
                        .makeHttpRequest(URLS.Domain + URLS.URL_GET_USERDETAIL, "POST", par);

                Log.e("response", URLS.Domain + URLS.URL_GET_USERDETAIL + " => " + jsonstr);

                json = new JSONObject(jsonstr);
                Sucess = json.getBoolean("success");
                if (Sucess) {
                    JSONObject jrow = json.getJSONObject("data");
                    SharedPrefs.save(StartActivity.this, SharedPrefs.PROFILEPIC, jrow.getString("profilePic"));
                    SharedPrefs.save(StartActivity.this, SharedPrefs.CHIPS, jrow.getString("chips"));
                    SharedPrefs.save(StartActivity.this, SharedPrefs.DISPLAY_NAME, jrow.getString("displayName"));
                }


            } catch (Exception e) {
                Log.e("errro", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            chipBalanceTv.setText(SharedPrefs.getString(StartActivity.this, SharedPrefs.CHIPS, "0"));
            userNameTv.setText(SharedPrefs.getString(StartActivity.this, SharedPrefs.DISPLAY_NAME, "Guest"));
            Glide.with(StartActivity.this).load(SharedPrefs.getString(StartActivity.this, SharedPrefs.PROFILEPIC, "0")).into(profileIv);


        }

    }

    private void getAllPostCall() {
        new AllPostsCall().execute();
    }

    private class AllPostsCall extends AsyncTask<String, String, String> {

        JSONParser jParser = new JSONParser();
        boolean Sucess = false;
        String jsonstr = null;
        JSONObject json;
        Dialog dd;
        String msg = "Something went wrong";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            dd = new DialogProgress().DialogProgressInitialoz(StartActivity.this);
//            dd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.e("urrllll", "call GET_ALL_POSTS api ");

            List<NameValuePair> par = new ArrayList<NameValuePair>();

            try {
                jsonstr = jParser.makeHttpRequest(URLS.GET_ALL_POSTS, "GET", par);
                Log.e("response", URLS.GET_ALL_POSTS + " => " + jsonstr);
                json = new JSONObject(jsonstr);
                Sucess = json.getBoolean("success");

                if (Sucess) {
                    JSONObject data = json.getJSONObject("data");
                    Log.e("startact", "data: " + data);
                    JSONArray postDataArray = data.getJSONArray("postData");
                    Log.e("startact", "postDataArray: " + postDataArray);
                    JSONObject announcementJson = postDataArray.getJSONObject(0);
                    Log.e("startact", "announcementJson: " + announcementJson);
                    announcementStr = announcementJson.getString("message");
                    Log.e("startact", "announcementStr: " + announcementStr);

                } else {
                    msg = json.getString("message");
                }

            } catch (Exception e) {
                Log.e("errro", "message: " + e.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            dd.dismiss();
            if (Sucess) {
                if (BaseUtils.checkNonNull(announcementStr)) {
                    anouncement.setText(announcementStr);
                }
            } else {
                Toast.makeText(StartActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }
    }
}