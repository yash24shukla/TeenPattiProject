package com.teenpatti.queendemo.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;
import com.teenpatti.queendemo.R;
import com.teenpatti.queendemo.api.JSONParser;
import com.teenpatti.queendemo.files.ConnectivityReceiver;
import com.teenpatti.queendemo.files.SharedPrefs;
import com.teenpatti.queendemo.files.URLS;
import com.teenpatti.queendemo.model.Functions;
import com.teenpatti.queendemo.model.GifImageView;
import com.teenpatti.queendemo.model.Gifts;
import com.teenpatti.queendemo.model.NetworkManager;
import com.teenpatti.queendemo.model.PlayerInfo;
import com.teenpatti.queendemo.model.Table;
import com.teenpatti.queendemo.model.TableJoined;
import com.teenpatti.queendemo.model.User;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table6_Activity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    ArrayList<Gifts> arrGifts = new ArrayList();
    private String TAG = Table6_Activity.class.getSimpleName();
    Animation anim_tab6_win_1, anim_tab6_win_2, anim_tab6_win_3, anim_tab6_win_4, anim_tab6_win_5;
    Animation ani2, ani3, ani4, ani5, ani6;
    Animation ani2_back, ani3_back, ani4_back, ani5_back, ani6_back;
    LinearLayout llCoinTable2, llCoinTable3, llCoinTable4, llCoinTable5, llCoinTable6;
    private Socket mSocket;
    LinearLayout llPl6CardDisplay;
    String TotalTablePlayer = "0";
    LinearLayout llViewBottomBarInfoView;
    TextView txtBottomBootValue, txtBottomPotLimit, txtBottomMaxValue;
    TextView txtPlay2, txtPlay2Rs, txtPlay3, txtPlay3Rs, txtPlay4, txtPlay4Rs, txtPlay5, txtPlay5Rs, txtPlay6, txtPlay6Rs;
    TextView txtPlay2Slot, txtPlay3Slot, txtPlay4Slot, txtPlay5Slot, txtPlay6Slot;
    String table_id;
    String Arg_id;
    CountDownTimer timer;
    int GameTimerStop = 0;
    TextView txtGameWait;
    TextView txtPlayRs;
    Button txtPlayShowSlideshow, txtPlayBlind, txtPlayPack, txtPlayMinus, txtPlayPlue, txtPlayShow;
    String Myturn = "false", iscardseen = "false", MyturnSideShow = "false";
    JSONObject Json_Player_Info, JsonSeeAllCardsInfo, Json_Table_Info, Json_Current_Player;
    TextView txtTableAmount, txtPlayChaal;
    TextView txtPlay6Id, txtPlay5Id, txtPlay4Id, txtPlay3Id, txtPlay2Id, txtCardSee;
    ImageView imgcard3Big, imgcard1back, imgcard1num, imgcard1icon, imgcard2back, imgcard2num, imgcard2icon, imgcard3back, imgcard3num, imgcard3icon;
    ImageView imgpl2card3Big, imgpl2card1back, imgpl2card1num, imgpl2card1icon, imgpl2card2back, imgpl2card2num, imgpl2card2icon, imgpl2card3back, imgpl2card3num, imgpl2card3icon;
    ImageView imgpl3card3Big, imgpl3card1back, imgpl3card1num, imgpl3card1icon, imgpl3card2back, imgpl3card2num, imgpl3card2icon, imgpl3card3back, imgpl3card3num, imgpl3card3icon;
    ImageView imgpl4card3Big, imgpl4card1back, imgpl4card1num, imgpl4card1icon, imgpl4card2back, imgpl4card2num, imgpl4card2icon, imgpl4card3back, imgpl4card3num, imgpl4card3icon;
    ImageView imgpl5card3Big, imgpl5card1back, imgpl5card1num, imgpl5card1icon, imgpl5card2back, imgpl5card2num, imgpl5card2icon, imgpl5card3back, imgpl5card3num, imgpl5card3icon;
    LinearLayout llPlayer6, llPlayer5, llPlayer4, llPlayer3, llPlayer2;
    LinearLayout llViewBottomBar;
    LinearLayout llTableAmountTransfer;
    TextView txtTableAmountTransfer;
    TextView txtCoinTable2, txtCoinTable3, txtCoinTable4, txtCoinTable5, txtCoinTable6;
    int totalplayer = 1;
    boolean iscardstop = false;
    Dialog d;
    Button txtSlideAccept, txtSlideDecline;
    TextView txtSlideDesc;
    ArcProgress ProgressTimer;
    int process = 0;
    CountDownTimer Timerrr;
    int StopTimer = 0;
    int StopTimerSideShow = 0;
    int MaximumBlindcount = 0;
    LinearLayout llPlayer2WaitPlayer, llPlayer3WaitPlayer, llPlayer4WaitPlayer, llPlayer5WaitPlayer;
    ImageView imgSetting;
    String lastbet = "0", lastAction = "", LastSideshowplaceBy = "";
    String CurrentAction = "";
    Animation anim_round;
    RelativeLayout Rlpl5Card1, Rlpl5Card2, Rlpl5Card3, Rlpl6Card1, Rlpl6Card2, Rlpl6Card3,
            Rlpl2Card1, Rlpl2Card2, Rlpl2Card3, Rlpl3Card1, Rlpl3Card2, Rlpl3Card3, Rlpl4Card1, Rlpl4Card2, Rlpl4Card3;
    Animation anim_pl2_tra, anim_pl2_2_tra, anim_pl2_3_tra, anim_pl3_tra, anim_pl3_2_tra, anim_pl3_3_tra, anim_pl4_tra,
            anim_pl4_2_tra, anim_pl4_3_tra, anim_pl5_tra, anim_pl5_2_tra, anim_pl5_3_tra, anim_pl6_tra, anim_pl6_2_tra, anim_pl6_3_tra;
    ArrayList<String> arrcurrentplayer = new ArrayList<>();
    private static Table6_Activity instance;
    Dialog dNetWork;
    LinearLayout llTimerForPlayer;
    TextView txtTimerForPlayer;
    //    KProgressHUD ProgressbarHud;
    String tableposition;
    public static Table SelectTable = new Table();
    int CurrentPlayerCount = 0, isSlideshow = 0 , isPackedTrack;
    ImageView imgInformation;
    Dialog dInfoValue;
    TextView txtInfoBootValue, txtInfoMaxBlind, txtInfoChaalLimit, txtInfoPotLimit, txtInfoTableType, txtInfoTableUserConnected;
    ImageView imgInfoCancel;
    public static int NotplayingCount = 0;
    private String NotiMessage = "";
    int seconds;
    GifImageView gifFireworks;
    ImageView imgPl2Pic, imgPl3Pic, imgPl4Pic, imgPl5Pic, imgPl6Pic;
    Button txtshowallcards, txtReplaceCards;
    JSONObject CurrentCardss;
    JSONArray ReplaceCurrentCardss;
    GridView GiftGrdView;
    Dialog dGift;
    Adapter_Gifts AdapterGiftCards;

    // Replace card dialog
    GridView grdDiaReplaceCard;
    Dialog dReplaceCards;
    Adapter_ReplaceCard AdapterReplaceCard;
    Button txtDiaReplaceCardClear;
    Button txtDiaReplaceCardDone;
    int ReplaceCardselectionnumber = 0;

    //
    String GiftToId = "";
    String GiftFromId = "";
    ImageView imgGift2, imgGift3, imgGift4, imgGift5, imgGift6;
    ProgressBar Progresspl2, Progresspl3, Progresspl4, Progresspl5, Progresspl6;
    int pStatus = 0;
    TextView txtPl2ChalBlind, txtPl3ChalBlind, txtPl4ChalBlind, txtPl5ChalBlind;
    String LastBetPlaceSideShow;
    TextView txtTips;
    ImageView imgpl2Gift, imgpl3Gift, imgpl4Gift, imgpl5Gift, imgpl6Gift;
    ImageView imgpl2Gift2, imgpl3Gift2, imgpl4Gift2, imgpl5Gift2, imgpl6Gift2;
    ImageView imgpl2Gift3, imgpl3Gift3, imgpl4Gift3, imgpl5Gift3, imgpl6Gift3;
    ImageView imgpl2Gift4, imgpl3Gift4, imgpl4Gift4, imgpl5Gift4, imgpl6Gift4;
    ImageView imgpl2Gift5, imgpl3Gift5, imgpl4Gift5, imgpl5Gift5, imgpl6Gift5;
    int GiftMoveDuration = 1000;

    ImageView imgWinningImage6, imgWinningImage5, imgWinningImage4, imgWinningImage3, imgWinningImage2;
    Animation anim_round2, anim_round3, anim_round4, anim_round5, anim_round6;

    Animation anim_startGame;
    Dialog dCloseExit;
    RelativeLayout rlTable;
    RelativeLayout rlGameWait;
    ImageView imggame_start_2;
    double convertedChipformat , convertedTableCoins ;
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected)
            NetworkConnected();
        else NetworkNotConnected();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.new_table6_layout);

        Functions.setUpToast(Table6_Activity.this);
        Functions.setUpToast_game(Table6_Activity.this);
        table_id = getIntent().getStringExtra("table_id");
        tableposition = getIntent().getStringExtra("position");
        Log.e(TAG, "table_id: " + table_id);
        Log.e(TAG, "tableposition: " + tableposition);
        SelectTable = (Table) getIntent().getSerializableExtra("selectTable");
        Log.e(TAG, "getPotLimit: " + SelectTable.getPotLimit());

        BindView();
        BindListner();
        SocketON();

        new Asynctask_Giftss().execute();
        new Check_Player_Asynctask().execute();
        new CheckLogin_Asyctask().execute();
        txtPlay6.setText(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.DISPLAY_NAME, ""));

    }

    private void SocketON() {

        Log.e(TAG, "connect  call to    " + URLS.Domain);

        try {
            IO.Options options = new IO.Options();
            options.forceNew = true;
            options.reconnection = true;

            mSocket = IO.socket(URLS.Domain, options);
            mSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.on("doneReplaceCards", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.e(TAG + " doneReplaceCards", "\nreceive ion  " + args[0].toString());
            }
        });

        mSocket.on("GiftSended", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {

                Log.e(TAG + " GiftSended", "\n receive   " + args[0].toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonReplay = new JSONObject(args[0].toString());
                            JSONObject jsonObArgs = jsonReplay.getJSONObject("args");

                            String FromId = jsonObArgs.getString("fromId");
                            String ToId = jsonObArgs.getString("toId");
                            final String gifturl = jsonObArgs.getString("Gifturl");

                            Log.e("sound play", jsonObArgs.getString("GiftAudio"));
                            Functions.startGiftSoundPlay(Table6_Activity.this, jsonObArgs.getString("GiftAudio"));

                            JSONObject jsObjUser = jsonReplay.getJSONObject("user");
                            if (jsObjUser.getString("_id").equals(txtPlay6Id.getText().toString())) {
                                convertedChipformat = Math.round(Double.parseDouble(jsObjUser.getString("chips"))  * 100.0) / 100.0;

                                txtPlay6Rs.setText(String.valueOf(convertedChipformat));                            }


                            if (ToId.equals(txtPlay6Id.getText().toString())) {


                                if (FromId.equals(txtPlay2Id.getText().toString())) {
                                    // pl2 to pl6
                                    TranslateAnimation translateAnimation = new TranslateAnimation(-420.0f, 0.0f, -80.0f, 0.0f);
                                    translateAnimation.setDuration(GiftMoveDuration);
                                    imgpl6Gift.startAnimation(translateAnimation);
                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl6Gift.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {
                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl6Gift.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift6.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }


                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {
                                        }
                                    });

                                } else if (FromId.equals(txtPlay3Id.getText().toString())) {

                                    // pl3 to pl6
                                    TranslateAnimation translateAnimation = new TranslateAnimation(-390.0f, 0.0f, -320.0f, 0.0f);
                                    translateAnimation.setDuration(GiftMoveDuration);
                                    imgpl6Gift2.startAnimation(translateAnimation);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl6Gift2.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl6Gift2.setImageDrawable(null);


                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift6.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }


                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay4Id.getText().toString())) {
                                    // pl4 to pl6
                                    TranslateAnimation translateAnimation = new TranslateAnimation(380.0f, 0.0f, -350.0f, 0.0f);
                                    translateAnimation.setDuration(GiftMoveDuration);
                                    imgpl6Gift3.startAnimation(translateAnimation);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl6Gift3.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl6Gift3.setImageDrawable(null);


                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift6.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay5Id.getText().toString())) {
                                    //  pl5 to pl6
                                    TranslateAnimation translateAnimation = new TranslateAnimation(400.0f, 0.0f, -100.0f, 0.0f);
                                    translateAnimation.setDuration(GiftMoveDuration);
                                    imgpl6Gift4.startAnimation(translateAnimation);

                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl6Gift4.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl6Gift4.setImageDrawable(null);


                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift6.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else if (FromId.equals(txtPlay6Id.getText().toString())) {
                                    // pl6 to pl6
                                    TranslateAnimation translateAnimation = new TranslateAnimation(-200.0f, 0.0f, -200.0f, 0.0f);
                                    translateAnimation.setDuration(GiftMoveDuration);
                                    imgpl6Gift5.startAnimation(translateAnimation);

                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl6Gift5.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl6Gift5.setImageDrawable(null);


                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift6.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }


                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                            } else if (ToId.equals(txtPlay5Id.getText().toString())) {


                                if (FromId.equals(txtPlay2Id.getText().toString())) {
                                    // pl2 to pl5
                                    TranslateAnimation translateAnimation2 = new TranslateAnimation(-900.0f, 0.0f, 0.0f, 0.0f);
                                    translateAnimation2.setDuration(GiftMoveDuration);
                                    imgpl5Gift.startAnimation(translateAnimation2);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl5Gift.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                    translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl5Gift.setImageDrawable(null);


                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift5.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay3Id.getText().toString())) {

                                    // pl3 to pl5
                                    TranslateAnimation translateAnimation2 = new TranslateAnimation(-900.0f, 0.0f, -300.0f, 0.0f);
                                    translateAnimation2.setDuration(GiftMoveDuration);
                                    imgpl5Gift2.startAnimation(translateAnimation2);

                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl5Gift2.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl5Gift2.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift5.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay4Id.getText().toString())) {
                                    // pl4 to pl5
                                    TranslateAnimation translateAnimation1 = new TranslateAnimation(-15.0f, 0.0f, -260.0f, 0.0f);
                                    translateAnimation1.setDuration(GiftMoveDuration);
                                    imgpl5Gift3.startAnimation(translateAnimation1);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl5Gift3.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl5Gift3.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift5.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay5Id.getText().toString())) {
                                    //  pl5 to pl5
                                    TranslateAnimation translateAnimation = new TranslateAnimation(-300.0f, 0.0f, -200.0f, 0.0f);
                                    translateAnimation.setDuration(GiftMoveDuration);
                                    imgpl5Gift4.startAnimation(translateAnimation);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl5Gift4.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl5Gift4.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift5.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay6Id.getText().toString())) {
                                    // pl6 to pl5
                                    TranslateAnimation translateAnimation2 = new TranslateAnimation(-500.0f, 0.0f, 30.0f, 0.0f);
                                    translateAnimation2.setDuration(GiftMoveDuration);
                                    imgpl5Gift5.startAnimation(translateAnimation2);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl5Gift5.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl5Gift5.setImageDrawable(null);
                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift5.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                }
                            } else if (ToId.equals(txtPlay3Id.getText().toString())) {


                                if (FromId.equals(txtPlay2Id.getText().toString())) {
                                    // pl2 to pl3
                                    TranslateAnimation translateAnimation4 = new TranslateAnimation(-10.0f, 0.0f, 200.0f, 0.0f);
                                    translateAnimation4.setDuration(GiftMoveDuration);
                                    imgpl3Gift.startAnimation(translateAnimation4);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl3Gift.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation4.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl3Gift.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift3.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay3Id.getText().toString())) {

                                    // pl3 to pl3
                                    TranslateAnimation translateAnimation4 = new TranslateAnimation(300.0f, 0.0f, 200.0f, 0.0f);
                                    translateAnimation4.setDuration(GiftMoveDuration);
                                    imgpl3Gift2.startAnimation(translateAnimation4);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl3Gift2.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation4.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl3Gift2.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift3.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay4Id.getText().toString())) {
                                    // pl4 to pl3
                                    TranslateAnimation translateAnimation3 = new TranslateAnimation(850.0f, 0.0f, 0.0f, 0.0f);
                                    translateAnimation3.setDuration(GiftMoveDuration);
                                    imgpl3Gift3.startAnimation(translateAnimation3);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl3Gift3.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation3.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl3Gift3.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift3.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay5Id.getText().toString())) {
                                    // pl5 to pl3
                                    TranslateAnimation translateAnimation4 = new TranslateAnimation(400.0f, 0.0f, 350.0f, 0.0f);
                                    translateAnimation4.setDuration(GiftMoveDuration);
                                    imgpl3Gift4.startAnimation(translateAnimation4);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl3Gift4.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation4.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl3Gift4.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift3.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay6Id.getText().toString())) {
                                    // pl6 to pl3
                                    TranslateAnimation translateAnimation4 = new TranslateAnimation(400.0f, 0.0f, 350.0f, 0.0f);
                                    translateAnimation4.setDuration(GiftMoveDuration);
                                    imgpl3Gift5.startAnimation(translateAnimation4);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl3Gift5.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation4.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl3Gift5.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift3.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                            } else if (ToId.equals(txtPlay2Id.getText().toString())) {


                                if (FromId.equals(txtPlay2Id.getText().toString())) {
                                    // pl2 to pl2
                                    TranslateAnimation translateAnimation1 = new TranslateAnimation(300.0f, 0.0f, -200.0f, 0.0f);
                                    translateAnimation1.setDuration(GiftMoveDuration);
                                    imgpl2Gift.startAnimation(translateAnimation1);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl2Gift.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl2Gift.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift2.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay3Id.getText().toString())) {

                                    // pl3 to pl2
                                    TranslateAnimation translateAnimation1 = new TranslateAnimation(70.0f, 0.0f, -265.0f, 0.0f);
                                    translateAnimation1.setDuration(GiftMoveDuration);
                                    imgpl2Gift2.startAnimation(translateAnimation1);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl2Gift2.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {
                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl2Gift2.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift2.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {
                                        }

                                    });


                                } else if (FromId.equals(txtPlay4Id.getText().toString())) {
                                    // pl4 to pl2
                                    TranslateAnimation translateAnimation1 = new TranslateAnimation(850.0f, 0.0f, -280.0f, 0.0f);
                                    translateAnimation1.setDuration(GiftMoveDuration);
                                    imgpl2Gift3.startAnimation(translateAnimation1);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl2Gift3.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {
                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl2Gift3.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift2.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {
                                        }
                                    });


                                } else if (FromId.equals(txtPlay5Id.getText().toString())) {
                                    // pl5 to pl2
                                    TranslateAnimation translateAnimation1 = new TranslateAnimation(850.0f, 0.0f, -280.0f, 0.0f);
                                    translateAnimation1.setDuration(GiftMoveDuration);
                                    imgpl2Gift4.startAnimation(translateAnimation1);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl2Gift4.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl2Gift4.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift2.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay6Id.getText().toString())) {
                                    // pl6 to pl2
                                    TranslateAnimation translateAnimation1 = new TranslateAnimation(400.0f, 0.0f, 80.0f, 0.0f);
                                    translateAnimation1.setDuration(GiftMoveDuration);
                                    imgpl2Gift5.startAnimation(translateAnimation1);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl2Gift5.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation1.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl2Gift5.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift2.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }

                                    });


                                }
                            } else if (ToId.equals(txtPlay4Id.getText().toString())) {


                                if (FromId.equals(txtPlay2Id.getText().toString())) {
                                    // pl2 to pl4
                                    TranslateAnimation translateAnimation2 = new TranslateAnimation(-900.0f, 0.0f, 280.0f, 0.0f);
                                    translateAnimation2.setDuration(GiftMoveDuration);
                                    imgpl4Gift.startAnimation(translateAnimation2);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl4Gift.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl4Gift.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift4.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay3Id.getText().toString())) {
                                    // pl3 to pl4
                                    TranslateAnimation translateAnimation2 = new TranslateAnimation(-900.0f, 0.0f, 0.0f, 0.0f);
                                    translateAnimation2.setDuration(GiftMoveDuration);
                                    imgpl4Gift2.startAnimation(translateAnimation2);

                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl4Gift2.setImageDrawable(drawa);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {
                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl4Gift2.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift4.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay4Id.getText().toString())) {
                                    // pl4 to pl4
                                    TranslateAnimation translateAnimation2 = new TranslateAnimation(-300.0f, 0.0f, 200.0f, 0.0f);
                                    translateAnimation2.setDuration(GiftMoveDuration);
                                    imgpl4Gift3.startAnimation(translateAnimation2);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl4Gift3.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl4Gift3.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift4.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay5Id.getText().toString())) {
                                    // pl5 to pl4
                                    TranslateAnimation translateAnimation2 = new TranslateAnimation(10.0f, 0.0f, 280.0f, 0.0f);
                                    translateAnimation2.setDuration(GiftMoveDuration);
                                    imgpl4Gift4.startAnimation(translateAnimation2);


                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl4Gift4.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl4Gift4.setImageDrawable(null);


                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift4.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });


                                } else if (FromId.equals(txtPlay6Id.getText().toString())) {
                                    // pl6 to pl4
                                    TranslateAnimation translateAnimation2 = new TranslateAnimation(-400.0f, 0.0f, 300.0f, 0.0f);
                                    translateAnimation2.setDuration(GiftMoveDuration);
                                    imgpl4Gift5.startAnimation(translateAnimation2);

                                    try {
                                        InputStream ims = getAssets().open(gifturl);
                                        Drawable drawa = Drawable.createFromStream(ims, null);
                                        imgpl4Gift5.setImageDrawable(drawa);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            imgpl4Gift5.setImageDrawable(null);

                                            try {
                                                InputStream ims = getAssets().open(gifturl);
                                                Drawable drawa = Drawable.createFromStream(ims, null);
                                                imgGift4.setImageDrawable(drawa);

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });

                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("error", e.getMessage());
                        }


                    }
                });


            }
        });


        mSocket.on("sendTips", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {

                Log.e(TAG + " sendTips", "\n receive :     " + args[0].toString());


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            JSONObject jsonReplay = new JSONObject(args[0].toString());
                            JSONObject jsObjUser = jsonReplay.getJSONObject("user");
                            if (jsObjUser.getString("_id").equals(txtPlay2Id.getText().toString())) {
                                llCoinTable2.setVisibility(View.VISIBLE);
                                llCoinTable2.startAnimation(ani2_back);
                                txtCoinTable2.setText(jsonReplay.getInt("tip") + "");
                            } else if (jsObjUser.getString("_id").equals(txtPlay3Id.getText().toString())) {
                                llCoinTable3.setVisibility(View.VISIBLE);
                                llCoinTable3.startAnimation(ani3_back);
                                txtCoinTable3.setText(jsonReplay.getInt("tip") + "");
                            } else if (jsObjUser.getString("_id").equals(txtPlay4Id.getText().toString())) {
                                llCoinTable4.setVisibility(View.VISIBLE);
                                llCoinTable4.startAnimation(ani4_back);
                                txtCoinTable4.setText(jsonReplay.getInt("tip") + "");
                            } else if (jsObjUser.getString("_id").equals(txtPlay5Id.getText().toString())) {
                                llCoinTable5.setVisibility(View.VISIBLE);
                                llCoinTable5.startAnimation(ani5_back);
                                txtCoinTable5.setText(jsonReplay.getInt("tip") + "");
                            } else if (jsObjUser.getString("_id").equals(txtPlay6Id.getText().toString())) {
                                llCoinTable6.setVisibility(View.VISIBLE);
                                llCoinTable6.startAnimation(ani6_back);
//                                txtPlay6Rs.setText(jsObjUser.getString("chips"));
                                convertedChipformat = Math.round(Double.parseDouble(jsObjUser.getString("chips"))  * 100.0) / 100.0;

                                txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                txtCoinTable6.setText(jsonReplay.getInt("tip") + "");
                            }

                            Functions.displayToast(jsObjUser.getString("displayName") + " , Thanks !!! My luck is with you.");


                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("error", e.getMessage());
                        }


                    }
                });


            }
        });

        mSocket.on("allCards", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.e(TAG + "allCards", " receive on :  " + args[0].toString());

                        try {

                            JSONArray jsonTable = new JSONArray(args[0].toString());
                            for (int i = 0; i < jsonTable.length(); i++) {
                                String ss = SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID);
                                JSONArray jjPlayerrr = jsonTable.getJSONArray(i);
                                //    for (int j = 0; j < jjPlayerrr.length(); j++) {
                                Log.d("showwjj ", jjPlayerrr.toString());
                                JSONObject jj = new JSONObject();
                                String iddds = "";
                                jj = jjPlayerrr.getJSONObject(0);
                                iddds = jj.getString("id");
                                Log.e("idddss", iddds);
                                JSONObject jcards = jjPlayerrr.getJSONObject(1);
                                Log.e("cards dis", jcards.toString());
                                Log.e("idds and ss", iddds + "  :   " + ss);
                                if (iddds.equals(ss)) {
                                    CurrentCardss = jcards;
                                    Log.d("iCurrentcardssss", CurrentCardss.toString());
                                }


                                JSONObject ObjPlayerInfo = jjPlayerrr.getJSONObject(0);
                                if (ObjPlayerInfo.getString("id").equals(txtPlay2Id.getText().toString())) {
                                    JSONObject ObjCards = jjPlayerrr.getJSONObject(1);
                                    JSONArray Arrayjcards = ObjCards.getJSONArray("cards");
                                    for (int jjkk = 0; jjkk < Arrayjcards.length(); jjkk++) {
                                        JSONObject jrowcard = Arrayjcards.getJSONObject(jjkk);
                                        if (jjkk == 0)
                                            Functions.setcard(Table6_Activity.this, imgpl2card1back, imgpl2card1num, imgpl2card1icon, imgpl2card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        if (jjkk == 1)
                                            Functions.setcard(Table6_Activity.this, imgpl2card2back, imgpl2card2num, imgpl2card2icon, imgpl2card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        if (jjkk == 2)
                                            Functions.setcard(Table6_Activity.this, imgpl2card3back, imgpl2card3num, imgpl2card3icon, imgpl2card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                    }
                                } else if (ObjPlayerInfo.getString("id").equals(txtPlay3Id.getText().toString())) {
                                    JSONObject ObjCards = jjPlayerrr.getJSONObject(1);
                                    JSONArray Arrayjcards = ObjCards.getJSONArray("cards");
                                    for (int jjkk = 0; jjkk < Arrayjcards.length(); jjkk++) {
                                        JSONObject jrowcard = Arrayjcards.getJSONObject(jjkk);
                                        if (jjkk == 0)
                                            Functions.setcard(Table6_Activity.this, imgpl3card1back, imgpl3card1num, imgpl3card1icon, imgpl3card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        if (jjkk == 1)
                                            Functions.setcard(Table6_Activity.this, imgpl3card2back, imgpl3card2num, imgpl3card2icon, imgpl3card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        if (jjkk == 2)
                                            Functions.setcard(Table6_Activity.this, imgpl3card3back, imgpl3card3num, imgpl3card3icon, imgpl3card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                    }
                                } else if (ObjPlayerInfo.getString("id").equals(txtPlay4Id.getText().toString())) {
                                    JSONObject ObjCards = jjPlayerrr.getJSONObject(1);
                                    JSONArray Arrayjcards = ObjCards.getJSONArray("cards");
                                    for (int jjkk = 0; jjkk < Arrayjcards.length(); jjkk++) {
                                        JSONObject jrowcard = Arrayjcards.getJSONObject(jjkk);
                                        if (jjkk == 0)
                                            Functions.setcard(Table6_Activity.this, imgpl4card1back, imgpl4card1num, imgpl4card1icon, imgpl4card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        if (jjkk == 1)
                                            Functions.setcard(Table6_Activity.this, imgpl4card2back, imgpl4card2num, imgpl4card2icon, imgpl4card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        if (jjkk == 2)
                                            Functions.setcard(Table6_Activity.this, imgpl4card3back, imgpl4card3num, imgpl4card3icon, imgpl4card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                    }
                                } else if (ObjPlayerInfo.getString("id").equals(txtPlay5Id.getText().toString())) {
                                    JSONObject ObjCards = jjPlayerrr.getJSONObject(1);
                                    JSONArray Arrayjcards = ObjCards.getJSONArray("cards");
                                    for (int jjkk = 0; jjkk < Arrayjcards.length(); jjkk++) {
                                        JSONObject jrowcard = Arrayjcards.getJSONObject(jjkk);
                                        if (jjkk == 0)
                                            Functions.setcard(Table6_Activity.this, imgpl5card1back, imgpl5card1num, imgpl5card1icon, imgpl5card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        if (jjkk == 1)
                                            Functions.setcard(Table6_Activity.this, imgpl5card2back, imgpl5card2num, imgpl5card2icon, imgpl5card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        if (jjkk == 2)
                                            Functions.setcard(Table6_Activity.this, imgpl5card3back, imgpl5card3num, imgpl5card3icon, imgpl5card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                    }
                                } else if (ObjPlayerInfo.getString("id").equals(txtPlay6Id.getText().toString())) {
                                    JSONObject ObjCards = jjPlayerrr.getJSONObject(1);
                                    JSONArray Arrayjcards = ObjCards.getJSONArray("cards");
                                    for (int jjkk = 0; jjkk < Arrayjcards.length(); jjkk++) {
                                        JSONObject jrowcard = Arrayjcards.getJSONObject(jjkk);

                                        if (jjkk == 0)
                                            Functions.setcard(Table6_Activity.this, imgcard1back, imgcard1num, imgcard1icon, imgcard3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        if (jjkk == 1)
                                            Functions.setcard(Table6_Activity.this, imgcard2back, imgcard2num, imgcard2icon, imgcard3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        if (jjkk == 2) {
                                            Functions.setcard(Table6_Activity.this, imgcard3back, imgcard3num, imgcard3icon, imgcard3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                            Log.e("calll", "open last card");
                                        }


                                    }
                                }


                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("errorr", e.getMessage());
                        }
                    }
                });

            }
        });

        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.e(TAG + "socket Timeout", "connect  failed  " + args[0].toString());
                if (NetworkManager.isInternetConnected(Table6_Activity.this)) {
                    mSocket.connect();
                } else {
                    Functions.displayToast("No Internet Connection");
                    CloseGame();
                }
            }
        });
        mSocket.on(Socket.EVENT_RECONNECT_FAILED, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("Network Connection", NetworkManager.isInternetConnected(Table6_Activity.this) + " ");
                if (NetworkManager.isInternetConnected(Table6_Activity.this)) {
                    mSocket.connect();
                } else {
                    Functions.displayToast("No Internet Connection");
                    CloseGame();
                }

            }
        });
        mSocket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (NetworkManager.isInternetConnected(Table6_Activity.this)) {
                    mSocket.connect();
                } else {
                    Functions.displayToast("No Internet Connection");
                    CloseGame();
                }
                Log.e(TAG + "socket Error", "connect  failed  " + args.toString());
            }
        });
        mSocket.on(Socket.EVENT_RECONNECTING, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                CloseGame();
                Log.e(TAG + "socket Reconnecting", "connect  failed to reconnect ");
            }
        });
        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.e(TAG + "socket connection", "connect    ");
            }
        });


        mSocket.on("connectionSuccess", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                Log.e("  connection success", args[0] + "   ");
                try {
                    JSONObject json = new JSONObject(args[0] + "");
                    Arg_id = json.getString("id");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtTips.setEnabled(true);
                        txtTips.setAlpha(1f);
                    }
                });
            }
        });

        mSocket.on("gameCountDown", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.e(TAG + "  on", "gameCountDown  " + args[0]);
                try {


                    if ( Float.parseFloat(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.CHIPS, "0")) <= Float.parseFloat(SelectTable.getBoot())) {

                        Functions.displayToast("You Don't have enough Money!");
                        CloseGame();
                    }

                    new CheckLogin_Asyctask().execute();
                    final JSONObject json = new JSONObject(args[0] + "");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                if (timer != null) {
                                    timer.cancel();
                                }
                                rlGameWait.setVisibility(View.VISIBLE);
//                                Functions.displayToast("game will begin");
                                GameTimerStop = 0;

                                imggame_start_2.startAnimation(anim_startGame);
                                imggame_start_2.setVisibility(View.VISIBLE);

                                seconds = (int) Float.parseFloat(json.getString("counter"));

                                txtGameWait.setVisibility(View.VISIBLE);
                                final long millisInFuture = (int) Float.parseFloat(json.getString("counter")) * 1000; //30 seconds
                                long countDownInterval = 1000; //1 second
                                timer = new CountDownTimer(millisInFuture, countDownInterval) {
                                    public void onTick(long millisUntilFinished) {
                                        Log.e("gamecountdown", "timer caling");

                                        if (GameTimerStop == 0) {
                                            if (seconds != 0) {
                                                Log.e("gamecountdown", "second 0 ");
                                                seconds--;
                                                txtGameWait.setText("" + seconds);
                                            } else {
                                                txtGameWait.setVisibility(View.GONE);
                                            }
                                        } else {
                                            txtGameWait.setVisibility(View.GONE);
                                            rlGameWait.setVisibility(View.GONE);
                                        }
                                    }


                                    public void onFinish() {
                                        txtGameWait.setVisibility(View.GONE);
                                        rlGameWait.setVisibility(View.GONE);
                                    }


                                }.start();

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("error", e.getMessage() + "");
                            } catch (Exception e) {

                            }

                        }
                    });

                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        mSocket.on("newJoinAndstartNew", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.e(TAG + "  on", "newJoinAndstartNew  " + args[0]);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_coinfilp));

                        try {
                            setNewTable();

                            llPlayer2WaitPlayer.setVisibility(View.VISIBLE);
                            llPlayer3WaitPlayer.setVisibility(View.VISIBLE);
                            llPlayer4WaitPlayer.setVisibility(View.VISIBLE);
                            llPlayer5WaitPlayer.setVisibility(View.VISIBLE);

                            llPlayer2.setVisibility(View.GONE);
                            llPlayer3.setVisibility(View.GONE);
                            llPlayer4.setVisibility(View.GONE);
                            llPlayer5.setVisibility(View.GONE);

                            final JSONObject json = new JSONObject(args[0] + "");

                            JSONObject jsonTable = json.getJSONObject("table");

                            Json_Table_Info = jsonTable;

                            txtPlayMinus.setAlpha(0.5f);
                            txtPlayMinus.setEnabled(false);
                            txtPlayPlue.setAlpha(1f);
                            txtPlayPlue.setEnabled(true);

                            lastbet = jsonTable.getString("lastBet");
                            txtPlayRs.setText(jsonTable.getString("lastBet"));
                           float defaultAmt = Float.valueOf(SharedPrefs.getString(getApplicationContext(), SharedPrefs.DEFAULTAMT)) ;
                           float temp = Float.valueOf(lastbet) + defaultAmt ;
                            SharedPrefs.save(getApplicationContext(), SharedPrefs.DEFAULTAMT, String.valueOf(temp)) ;

                            txtPlayBlind.setText("Blind\n" + String.valueOf(Math.round(Double.parseDouble(jsonTable.getString("lastBet"))  * 100.0) / 100.0));
                            txtPlayChaal.setText("Chaal\n" + String.valueOf(Math.round(Double.parseDouble(jsonTable.getString("lastBet"))  * 100.0) / 100.0));

                            txtCoinTable2.setText(SelectTable.getBoot());
                            txtCoinTable3.setText(SelectTable.getBoot());
                            txtCoinTable4.setText(SelectTable.getBoot());
                            txtCoinTable5.setText(SelectTable.getBoot());
                            txtCoinTable6.setText(SelectTable.getBoot());

                            JSONObject jsonPlayer = json.getJSONObject("players");
                            Json_Player_Info = jsonPlayer;
                            JsonSeeAllCardsInfo = jsonPlayer;


                            ArrayList<User> arrotheruser = new ArrayList<>();
                            Iterator<String> iterator = jsonPlayer.keys();
                            CurrentPlayerCount = 0;
                            isSlideshow = 0;
                            llPl6CardDisplay.setVisibility(View.VISIBLE);
                            while (iterator.hasNext()) {

                                String key = iterator.next();
                                final JSONObject jObje_Otherplayer = new JSONObject(jsonPlayer.optString(key));
                                User otheruser = new User();
                                otheruser.setId(jObje_Otherplayer.getString("id"));
                                otheruser.setSlot(jObje_Otherplayer.getString("slot"));
                                otheruser.setActive(jObje_Otherplayer.getString("active"));


                                PlayerInfo playerin = new PlayerInfo();
                                JSONObject jsonObject = new JSONObject(jObje_Otherplayer.getString("playerInfo"));
                                playerin.setUserId(jsonObject.getString("_id"));
                                playerin.setUserName(jsonObject.getString("displayName"));
                                playerin.setChips(jsonObject.getString("chips"));
                                playerin.setProfilePics(jsonObject.getString("profilePic"));
                                otheruser.setPlayerinfo(playerin);
                                arrotheruser.add(otheruser);

                                CurrentPlayerCount++;
                                if (otheruser.getPlayerinfo().getUserId().equals(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID))) {
                                    Json_Current_Player = jObje_Otherplayer;

                                    Functions.PlayVibrator(Table6_Activity.this);
                                    Log.e("turn", jObje_Otherplayer.getString("turn"));
                                    Myturn = jObje_Otherplayer.getString("turn");
                                    if (Myturn.equals("true")) {
                                        NotiMessage = "My Turn";
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Functions.PlayVibrator(Table6_Activity.this);

//                                                StopTimer = 0;
//                                                StartTimer(Progresspl6);
                                                try {
                                                    if (jObje_Otherplayer.getString("isSideShowAvailable").equals("true")) {
                                                        isSlideshow = 1;
                                                    } else {
                                                        txtPlayShowSlideshow.setVisibility(View.GONE);
//                                                        txtPlayShowSlideshow.setAlpha(0.5f);
//                                                        txtPlayShowSlideshow.setEnabled(false);
                                                        txtPlayShow.setVisibility(View.VISIBLE);
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                                llViewBottomBar.setVisibility(View.VISIBLE);
                                                llViewBottomBarInfoView.setVisibility(View.GONE);


                                                Functions.Stop_Player_Turn = 1;
                                                Functions.PlayVibrator(Table6_Activity.this);
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        imgWinningImage2.setVisibility(View.GONE);
                                                        imgWinningImage3.setVisibility(View.GONE);
                                                        imgWinningImage4.setVisibility(View.GONE);
                                                        imgWinningImage5.setVisibility(View.GONE);
                                                        imgWinningImage6.setVisibility(View.GONE);

                                                        StartTimer(Progresspl6);
//                                                        Functions.ShowPlayerTurn(llPlayer6ShowAnim, "true");
//                                                        Functions.Stop_Player_Turn = 0;
                                                    }
                                                }, 500);
                                            }
                                        }, 500);

                                    } else {
//                                        llViewBottomBar.animate().translationY(0);
                                        llViewBottomBar.setVisibility(View.GONE);
                                        llViewBottomBarInfoView.setVisibility(View.VISIBLE);
                                    }
                                }

                                try {
                                    if (otheruser.getPlayerinfo().getUserId().equals(txtPlay2Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            StopTimer = 0;
                                            StartTimer(Progresspl2);
                                        }
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay3Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            StopTimer = 0;
                                            StartTimer(Progresspl3);
                                        }
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay4Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            StopTimer = 0;
                                            StartTimer(Progresspl4);
                                        }
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay5Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            StopTimer = 0;
                                            StartTimer(Progresspl5);
                                        }
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay6Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            StopTimer = 0;
                                            StartTimer(Progresspl6);
                                        }
                                    }


                                } catch (Exception e) {
                                    Log.e("error ", "error on player turnnn");
                                }

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            JSONObject jsontable = new JSONObject(json.getString("table"));

                                           convertedTableCoins = Math.round(Double.parseDouble(jsontable.getString("amount"))  * 100.0) / 100.0;
                                            txtTableAmount.setText(String.valueOf(convertedTableCoins));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 400);


                                if (otheruser.getPlayerinfo().getUserId().equals(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID))) {
                                    llCoinTable6.setVisibility(View.VISIBLE);
                                    llCoinTable6.startAnimation(ani6_back);
                                    ani6.setStartOffset(300 * totalplayer);
                                    totalplayer++;
                                    arrcurrentplayer.add("6");
                                    txtPlay6Id.setText(jObje_Otherplayer.getString("id"));
//                                    txtPlay6Rs.setText(otheruser.getPlayerinfo().getChips());
                                    convertedChipformat = Math.round(Double.parseDouble(otheruser.getPlayerinfo().getChips())  * 100.0) / 100.0;

                                    txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                    SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, otheruser.getPlayerinfo().getChips());

                                    txtPlay6.setText(otheruser.getPlayerinfo().getUserName());
                                    if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                    if (otheruser.getSlot().equals("slot5")) {
                                        txtPlay6Slot.setText("slot5");
                                        txtPlay5Slot.setText("slot4");
                                        txtPlay4Slot.setText("slot3");
                                        txtPlay3Slot.setText("slot2");
                                        txtPlay2Slot.setText("slot1");
                                    } else if (otheruser.getSlot().equals("slot4")) {
                                        txtPlay6Slot.setText("slot4");
                                        txtPlay5Slot.setText("slot3");
                                        txtPlay4Slot.setText("slot2");
                                        txtPlay3Slot.setText("slot1");
                                        txtPlay2Slot.setText("slot5");
                                    } else if (otheruser.getSlot().equals("slot3")) {
                                        txtPlay6Slot.setText("slot3");
                                        txtPlay5Slot.setText("slot2");
                                        txtPlay4Slot.setText("slot1");
                                        txtPlay3Slot.setText("slot5");
                                        txtPlay2Slot.setText("slot4");
                                    } else if (otheruser.getSlot().equals("slot2")) {
                                        txtPlay6Slot.setText("slot2");
                                        txtPlay5Slot.setText("slot1");
                                        txtPlay4Slot.setText("slot5");
                                        txtPlay3Slot.setText("slot4");
                                        txtPlay2Slot.setText("slot3");
                                    } else if (otheruser.getSlot().equals("slot1")) {
                                        txtPlay6Slot.setText("slot1");
                                        txtPlay5Slot.setText("slot5");
                                        txtPlay4Slot.setText("slot4");
                                        txtPlay3Slot.setText("slot3");
                                        txtPlay2Slot.setText("slot2");
                                    }

                                } else {
                                    if (otheruser.getSlot().equals(txtPlay2Slot.getText().toString())) {
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        txtPlay2.setText(otheruser.getPlayerinfo().getUserName());
                                        llPlayer2WaitPlayer.setVisibility(View.GONE);
                                        llPlayer2.setVisibility(View.VISIBLE);
                                        ani2.setStartOffset(300 * totalplayer);
                                        totalplayer++;
                                        arrcurrentplayer.add("2");
                                        llCoinTable2.setVisibility(View.VISIBLE);
                                        llCoinTable2.startAnimation(ani2_back);
                                        txtPlay2Id.setText(jObje_Otherplayer.getString("id"));
                                        Log.e(TAG, otheruser.getSlot() + "  " + txtPlay2Slot.getText().toString() + "   id   " + jObje_Otherplayer.getString("id"));
                                        txtPlay2Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay3Slot.getText().toString())) {
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        txtPlay3.setText(otheruser.getPlayerinfo().getUserName());
                                        llPlayer3WaitPlayer.setVisibility(View.GONE);
                                        llPlayer3.setVisibility(View.VISIBLE);
                                        ani3.setStartOffset(300 * totalplayer);
                                        totalplayer++;
                                        arrcurrentplayer.add("3");
                                        llCoinTable3.setVisibility(View.VISIBLE);
                                        llCoinTable3.startAnimation(ani3_back);
                                        txtPlay3Id.setText(jObje_Otherplayer.getString("id"));
                                        Log.e(TAG, otheruser.getSlot() + "  " + txtPlay3Slot.getText().toString() + "   id   " + jObje_Otherplayer.getString("id"));
                                        txtPlay3Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay4Slot.getText().toString())) {
                                        llCoinTable4.setVisibility(View.VISIBLE);
                                        arrcurrentplayer.add("4");
//                                        if (jObje_Otherplayer.getString("turn").equals("true"))
//                                            llPlayer4.setBackgroundResource(R.drawable.player_back);
                                        ani4.setStartOffset(300 * totalplayer);
                                        totalplayer++;
                                        llCoinTable4.startAnimation(ani4_back);
                                        llPlayer4WaitPlayer.setVisibility(View.GONE);
                                        llPlayer4.setVisibility(View.VISIBLE);
                                        txtPlay4.setText(otheruser.getPlayerinfo().getUserName());
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        Log.e(TAG, otheruser.getSlot() + "  " + txtPlay4Slot.getText().toString() + "   id   " + jObje_Otherplayer.getString("id"));
                                        txtPlay4Id.setText(jObje_Otherplayer.getString("id"));
                                        txtPlay4Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay5Slot.getText().toString())) {
                                        llCoinTable5.setVisibility(View.VISIBLE);
                                        llCoinTable5.startAnimation(ani5_back);

//                                        if (jObje_Otherplayer.getString("turn").equals("true"))
//                                            llPlayer5.setBackgroundResource(R.drawable.player_back);
                                        ani5.setStartOffset(300 * totalplayer);
                                        totalplayer++;
                                        arrcurrentplayer.add("5");
                                        llPlayer5WaitPlayer.setVisibility(View.GONE);
                                        llPlayer5.setVisibility(View.VISIBLE);
                                        txtPlay5.setText(otheruser.getPlayerinfo().getUserName());
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        Log.e(TAG, otheruser.getSlot() + "  " + txtPlay5Slot.getText().toString() + "   id   " + jObje_Otherplayer.getString("id"));
                                        txtPlay5Id.setText(jObje_Otherplayer.getString("id"));
                                        txtPlay5Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay6Slot.getText().toString())) {
                                        txtPlay6.setText(otheruser.getPlayerinfo().getUserName());
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        arrcurrentplayer.add("6");
                                        llCoinTable6.startAnimation(ani6_back);
                                        if (jObje_Otherplayer.getString("turn").equals("true"))
                                            ani6.setStartOffset(300 * totalplayer);
                                        totalplayer++;
                                        Log.e(TAG, otheruser.getSlot() + "  " + txtPlay6Slot.getText().toString() + "   id   " + jObje_Otherplayer.getString("id"));
                                        txtPlay6Id.setText(jObje_Otherplayer.getString("id"));
                                        convertedChipformat = Math.round(Double.parseDouble(otheruser.getPlayerinfo().getChips())  * 100.0) / 100.0;

                                        txtPlay6Rs.setText(String.valueOf(convertedChipformat));




                                        SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, otheruser.getPlayerinfo().getChips());

                                    }
                                }
                            }

                            Log.e("Visible", "visible " + isSlideshow + "         " + CurrentPlayerCount);
                            if (CurrentPlayerCount == 2) {
                                Log.e("Visible", "Visible player 2");
                                txtPlayShow.setVisibility(View.VISIBLE);
                                txtPlayShowSlideshow.setVisibility(View.GONE);
//                                txtPlayShowSlideshow.setAlpha(0.5f);
//                                txtPlayShowSlideshow.setEnabled(false);

                            } else {
                                Log.e("Visible", "Visible player 3");

                                txtPlayShow.setVisibility(View.INVISIBLE);
                                if (isSlideshow == 1) {
                                    txtPlayShowSlideshow.setVisibility(View.VISIBLE);
                                } else {
                                    txtPlayShowSlideshow.setVisibility(View.GONE);
//                                    txtPlayShowSlideshow.setAlpha(0.5f);
//                                    txtPlayShowSlideshow.setEnabled(false);
                                }
                            }


                            CardServe();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


        mSocket.on("startNew", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.e(TAG + "  on", "startNew  " + args[0]);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        txtTableAmount.setVisibility(View.VISIBLE);
                        rlGameWait.setVisibility(View.GONE);

                        Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_coinfilp));

                        try {
                            setNewTable();

                            llPlayer2WaitPlayer.setVisibility(View.VISIBLE);
                            llPlayer3WaitPlayer.setVisibility(View.VISIBLE);
                            llPlayer4WaitPlayer.setVisibility(View.VISIBLE);
                            llPlayer5WaitPlayer.setVisibility(View.VISIBLE);

                            llPlayer2.setVisibility(View.GONE);
                            llPlayer3.setVisibility(View.GONE);
                            llPlayer4.setVisibility(View.GONE);
                            llPlayer5.setVisibility(View.GONE);

                            final JSONObject json = new JSONObject(args[0] + "");

                            JSONObject jsonTable = json.getJSONObject("table");

                            Json_Table_Info = jsonTable;

                            txtPlayMinus.setAlpha(0.5f);
                            txtPlayMinus.setEnabled(false);
                            txtPlayPlue.setAlpha(1f);
                            txtPlayPlue.setEnabled(true);

                            lastbet = jsonTable.getString("lastBet");
                            txtPlayRs.setText(jsonTable.getString("lastBet"));
                            float defaultAmt = Float.valueOf(SharedPrefs.getString(getApplicationContext(), SharedPrefs.DEFAULTAMT)) ;
                            float temp = Float.valueOf(lastbet) + defaultAmt ;
                            SharedPrefs.save(getApplicationContext(), SharedPrefs.DEFAULTAMT, String.valueOf(temp)) ;
                            txtPlayBlind.setText("Blind\n" + String.valueOf(Math.round(Double.parseDouble(jsonTable.getString("lastBet"))  * 100.0) / 100.0));
                            txtPlayChaal.setText("Chaal\n" + String.valueOf(Math.round(Double.parseDouble(jsonTable.getString("lastBet"))  * 100.0) / 100.0));

                            txtCoinTable2.setText(SelectTable.getBoot());
                            txtCoinTable3.setText(SelectTable.getBoot());
                            txtCoinTable4.setText(SelectTable.getBoot());
                            txtCoinTable5.setText(SelectTable.getBoot());
                            txtCoinTable6.setText(SelectTable.getBoot());

                            JSONObject jsonPlayer = json.getJSONObject("players");
                            Json_Player_Info = jsonPlayer;
                            JsonSeeAllCardsInfo = jsonPlayer;


                            ArrayList<User> arrotheruser = new ArrayList<>();
                            Iterator<String> iterator = jsonPlayer.keys();
                            CurrentPlayerCount = 0;
                            isSlideshow = 0;
                            llPl6CardDisplay.setVisibility(View.VISIBLE);
                            while (iterator.hasNext()) {

                                String key = iterator.next();
                                final JSONObject jObje_Otherplayer = new JSONObject(jsonPlayer.optString(key));
                                User otheruser = new User();
                                otheruser.setId(jObje_Otherplayer.getString("id"));
                                otheruser.setSlot(jObje_Otherplayer.getString("slot"));
                                otheruser.setActive(jObje_Otherplayer.getString("active"));


                                PlayerInfo playerin = new PlayerInfo();
                                JSONObject jsonObject = new JSONObject(jObje_Otherplayer.getString("playerInfo"));
                                playerin.setUserId(jsonObject.getString("_id"));
                                playerin.setUserName(jsonObject.getString("displayName"));
                                playerin.setChips(jsonObject.getString("chips"));
                                playerin.setProfilePics(jsonObject.getString("profilePic"));
                                otheruser.setPlayerinfo(playerin);
                                arrotheruser.add(otheruser);

                                CurrentPlayerCount++;
                                if (otheruser.getPlayerinfo().getUserId().equals(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID))) {
                                    Json_Current_Player = jObje_Otherplayer;

                                    Functions.PlayVibrator(Table6_Activity.this);
                                    Log.e("turn", jObje_Otherplayer.getString("turn"));
                                    Myturn = jObje_Otherplayer.getString("turn");
                                    if (Myturn.equals("true")) {
                                        NotiMessage = "My Turn";
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Functions.PlayVibrator(Table6_Activity.this);

//                                                StopTimer = 0;
//                                                StartTimer(Progresspl6);
                                                try {
                                                    if (jObje_Otherplayer.getString("isSideShowAvailable").equals("true")) {
                                                        isSlideshow = 1;
                                                    } else {
                                                        txtPlayShowSlideshow.setVisibility(View.GONE);
//                                                        txtPlayShowSlideshow.setAlpha(0.5f);
//                                                        txtPlayShowSlideshow.setEnabled(false);
                                                        txtPlayShow.setVisibility(View.VISIBLE);
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                                llViewBottomBar.setVisibility(View.VISIBLE);
                                                llViewBottomBarInfoView.setVisibility(View.GONE);


                                                Functions.Stop_Player_Turn = 1;
                                                Functions.PlayVibrator(Table6_Activity.this);
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        imgWinningImage2.setVisibility(View.GONE);
                                                        imgWinningImage3.setVisibility(View.GONE);
                                                        imgWinningImage4.setVisibility(View.GONE);
                                                        imgWinningImage5.setVisibility(View.GONE);
                                                        imgWinningImage6.setVisibility(View.GONE);

                                                        StartTimer(Progresspl6);
//                                                        Functions.ShowPlayerTurn(llPlayer6ShowAnim, "true");
//                                                        Functions.Stop_Player_Turn = 0;
                                                    }
                                                }, 500);
                                            }
                                        }, 500);

                                    } else {
//                                        llViewBottomBar.animate().translationY(0);
                                        llViewBottomBar.setVisibility(View.GONE);
                                        llViewBottomBarInfoView.setVisibility(View.VISIBLE);
                                    }
                                }

                                try {
                                    if (otheruser.getPlayerinfo().getUserId().equals(txtPlay2Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            StopTimer = 0;
                                            StartTimer(Progresspl2);
                                        }
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay3Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            StopTimer = 0;
                                            StartTimer(Progresspl3);
                                        }
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay4Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            StopTimer = 0;
                                            StartTimer(Progresspl4);
                                        }
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay5Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            StopTimer = 0;
                                            StartTimer(Progresspl5);
                                        }
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay6Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            StopTimer = 0;
                                            StartTimer(Progresspl6);
                                        }
                                    }


                                } catch (Exception e) {
                                    Log.e("error ", "error on player turnnn");
                                }

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            JSONObject jsontable = new JSONObject(json.getString("table"));

//                                            txtTableAmount.setText(jsontable.getString("amount"));
                                            convertedTableCoins = Math.round(Double.parseDouble(jsontable.getString("amount"))  * 100.0) / 100.0;
                                            txtTableAmount.setText(String.valueOf(convertedTableCoins));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 400);


                                if (otheruser.getPlayerinfo().getUserId().equals(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID))) {
                                    llCoinTable6.setVisibility(View.VISIBLE);
                                    llCoinTable6.startAnimation(ani6_back);
                                    ani6.setStartOffset(300 * totalplayer);
                                    totalplayer++;
                                    arrcurrentplayer.add("6");
                                    txtPlay6Id.setText(jObje_Otherplayer.getString("id"));
//                                    txtPlay6Rs.setText(otheruser.getPlayerinfo().getChips());

                                    convertedChipformat = Math.round(Double.parseDouble(otheruser.getPlayerinfo().getChips())  * 100.0) / 100.0;

                                    txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                    SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, otheruser.getPlayerinfo().getChips());

                                    txtPlay6.setText(otheruser.getPlayerinfo().getUserName());
                                    if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                    if (otheruser.getSlot().equals("slot5")) {
                                        txtPlay6Slot.setText("slot5");
                                        txtPlay5Slot.setText("slot4");
                                        txtPlay4Slot.setText("slot3");
                                        txtPlay3Slot.setText("slot2");
                                        txtPlay2Slot.setText("slot1");
                                    } else if (otheruser.getSlot().equals("slot4")) {
                                        txtPlay6Slot.setText("slot4");
                                        txtPlay5Slot.setText("slot3");
                                        txtPlay4Slot.setText("slot2");
                                        txtPlay3Slot.setText("slot1");
                                        txtPlay2Slot.setText("slot5");
                                    } else if (otheruser.getSlot().equals("slot3")) {
                                        txtPlay6Slot.setText("slot3");
                                        txtPlay5Slot.setText("slot2");
                                        txtPlay4Slot.setText("slot1");
                                        txtPlay3Slot.setText("slot5");
                                        txtPlay2Slot.setText("slot4");
                                    } else if (otheruser.getSlot().equals("slot2")) {
                                        txtPlay6Slot.setText("slot2");
                                        txtPlay5Slot.setText("slot1");
                                        txtPlay4Slot.setText("slot5");
                                        txtPlay3Slot.setText("slot4");
                                        txtPlay2Slot.setText("slot3");
                                    } else if (otheruser.getSlot().equals("slot1")) {
                                        txtPlay6Slot.setText("slot1");
                                        txtPlay5Slot.setText("slot5");
                                        txtPlay4Slot.setText("slot4");
                                        txtPlay3Slot.setText("slot3");
                                        txtPlay2Slot.setText("slot2");
                                    }

                                } else {
                                    if (otheruser.getSlot().equals(txtPlay2Slot.getText().toString())) {
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        txtPlay2.setText(otheruser.getPlayerinfo().getUserName());
                                        llPlayer2WaitPlayer.setVisibility(View.GONE);
                                        llPlayer2.setVisibility(View.VISIBLE);
                                        ani2.setStartOffset(300 * totalplayer);
                                        totalplayer++;
                                        arrcurrentplayer.add("2");
                                        llCoinTable2.setVisibility(View.VISIBLE);
                                        llCoinTable2.startAnimation(ani2_back);
                                        txtPlay2Id.setText(jObje_Otherplayer.getString("id"));
                                        Log.e(TAG, otheruser.getSlot() + "  " + txtPlay2Slot.getText().toString() + "   id   " + jObje_Otherplayer.getString("id"));
                                        txtPlay2Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay3Slot.getText().toString())) {
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        txtPlay3.setText(otheruser.getPlayerinfo().getUserName());
                                        llPlayer3WaitPlayer.setVisibility(View.GONE);
                                        llPlayer3.setVisibility(View.VISIBLE);
                                        ani3.setStartOffset(300 * totalplayer);
                                        totalplayer++;
                                        arrcurrentplayer.add("3");
                                        llCoinTable3.setVisibility(View.VISIBLE);
                                        llCoinTable3.startAnimation(ani3_back);
                                        txtPlay3Id.setText(jObje_Otherplayer.getString("id"));
                                        Log.e(TAG, otheruser.getSlot() + "  " + txtPlay3Slot.getText().toString() + "   id   " + jObje_Otherplayer.getString("id"));
                                        txtPlay3Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay4Slot.getText().toString())) {
                                        llCoinTable4.setVisibility(View.VISIBLE);
                                        arrcurrentplayer.add("4");
//                                        if (jObje_Otherplayer.getString("turn").equals("true"))
//                                            llPlayer4.setBackgroundResource(R.drawable.player_back);
                                        ani4.setStartOffset(300 * totalplayer);
                                        totalplayer++;
                                        llCoinTable4.startAnimation(ani4_back);
                                        llPlayer4WaitPlayer.setVisibility(View.GONE);
                                        llPlayer4.setVisibility(View.VISIBLE);
                                        txtPlay4.setText(otheruser.getPlayerinfo().getUserName());
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        Log.e(TAG, otheruser.getSlot() + "  " + txtPlay4Slot.getText().toString() + "   id   " + jObje_Otherplayer.getString("id"));
                                        txtPlay4Id.setText(jObje_Otherplayer.getString("id"));
                                        txtPlay4Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay5Slot.getText().toString())) {
                                        llCoinTable5.setVisibility(View.VISIBLE);
                                        llCoinTable5.startAnimation(ani5_back);

//                                        if (jObje_Otherplayer.getString("turn").equals("true"))
//                                            llPlayer5.setBackgroundResource(R.drawable.player_back);
                                        ani5.setStartOffset(300 * totalplayer);
                                        totalplayer++;
                                        arrcurrentplayer.add("5");
                                        llPlayer5WaitPlayer.setVisibility(View.GONE);
                                        llPlayer5.setVisibility(View.VISIBLE);
                                        txtPlay5.setText(otheruser.getPlayerinfo().getUserName());
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        Log.e(TAG, otheruser.getSlot() + "  " + txtPlay5Slot.getText().toString() + "   id   " + jObje_Otherplayer.getString("id"));
                                        txtPlay5Id.setText(jObje_Otherplayer.getString("id"));
                                        txtPlay5Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay6Slot.getText().toString())) {
                                        txtPlay6.setText(otheruser.getPlayerinfo().getUserName());
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        arrcurrentplayer.add("6");
                                        llCoinTable6.startAnimation(ani6_back);
                                        if (jObje_Otherplayer.getString("turn").equals("true"))
                                            ani6.setStartOffset(300 * totalplayer);
                                        totalplayer++;
                                        Log.e(TAG, otheruser.getSlot() + "  " + txtPlay6Slot.getText().toString() + "   id   " + jObje_Otherplayer.getString("id"));
                                        txtPlay6Id.setText(jObje_Otherplayer.getString("id"));
//                                        txtPlay6Rs.setText(otheruser.getPlayerinfo().getChips());
                                        convertedChipformat = Math.round(Double.parseDouble(otheruser.getPlayerinfo().getChips())  * 100.0) / 100.0;

                                        txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                        SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, otheruser.getPlayerinfo().getChips());

                                    }
                                }
                            }

                            Log.e("Visible", "visible " + isSlideshow + "         " + CurrentPlayerCount);
                            if (CurrentPlayerCount == 2) {
                                Log.e("Visible", "Visible player 2");
                                txtPlayShow.setVisibility(View.VISIBLE);
                                txtPlayShowSlideshow.setVisibility(View.GONE);
//                                txtPlayShowSlideshow.setAlpha(0.5f);
//                                txtPlayShowSlideshow.setEnabled(false);

                            } else {
                                Log.e("Visible", "Visible player 3");

                                txtPlayShow.setVisibility(View.INVISIBLE);
                                if (isSlideshow == 1) {
                                    txtPlayShowSlideshow.setVisibility(View.VISIBLE);
                                } else {
                                    txtPlayShowSlideshow.setVisibility(View.GONE);
//                                    txtPlayShowSlideshow.setAlpha(0.5f);
//                                    txtPlayShowSlideshow.setEnabled(false);
                                }
                            }


                            CardServe();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


        mSocket.on("notification", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.e(TAG + "  on", "notification  " + args[0]);
                try {
                    final JSONObject json = new JSONObject(args[0] + "");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                txtTableAmount.setText("0");
                                if (json.getString("message").equals("Please wait for more players to join")) {
                                    if (timer != null)
                                        timer.cancel();
                                    GameTimerStop = 1;
                                    txtGameWait.setVisibility(View.GONE);
                                    rlGameWait.setVisibility(View.GONE);
                                }
                                Functions.displayToast(json.getString("message"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("error", e.getMessage() + "");
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
            }
        });


        mSocket.on("cardsSeen", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {

                Log.e(TAG + "  on", "cardsSeen  " + args[0]);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(args[0] + "");
                            JSONArray jrow = json.getJSONArray("cardsInfo");
                            JSONObject cardtypeObj = (JSONObject) json.get("cardType");
                            if(!cardtypeObj.toString().isEmpty()){
                                Log.d("cardType" , "cardTypeObject = " + cardtypeObj.toString()) ;
                            }
                            String displayTypeName = cardtypeObj.getString("displayName") ;
                            txtCardSee.setText(displayTypeName);
                            txtCardSee.setClickable(false);
                            Log.e("cardseen rs", txtPlayRs.getText().toString());
                            if (Myturn.equals("true")) {
                                lastbet = String.valueOf(Float.parseFloat(txtPlayRs.getText().toString()) + Float.parseFloat(txtPlayRs.getText().toString())) + "";
                                txtPlayRs.setText(lastbet);
                                txtPlayBlind.setText("Blind\n" + String.valueOf(Math.round(Double.parseDouble(lastbet)  * 100.0) / 100.0));
                                txtPlayChaal.setText("Chaal\n" + String.valueOf(Math.round(Double.parseDouble(lastbet)  * 100.0) / 100.0));
                            }
                            CurrentAction = "Chaal";

                            for (int i = 0; i < jrow.length(); i++) {
                                JSONObject row = jrow.getJSONObject(i);
                                if (i == 0) {
                                    Functions.setcard(Table6_Activity.this, imgcard1back, imgcard1num, imgcard1icon, imgcard3Big, "false", row.getString("type"), row.getString("rank"));
                                } else if (i == 1) {
                                    Functions.setcard(Table6_Activity.this, imgcard2back, imgcard2num, imgcard2icon, imgcard3Big, "false", row.getString("type"), row.getString("rank"));
                                } else {
                                    Functions.setcard(Table6_Activity.this, imgcard3back, imgcard3num, imgcard3icon, imgcard3Big, "true", row.getString("type"), row.getString("rank"));
                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("error", e.getMessage());

                        }
                    }
                });
            }
        });


        mSocket.on("playerCardSeen", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.e(TAG + "  on", "playerCardSeen  " + args[0]);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            txtPlayShow.setEnabled(true);
                            txtPlayPlue.setEnabled(true);
                            txtPlayMinus.setEnabled(false);
                            txtPlayPack.setEnabled(true);
                            txtPlayBlind.setEnabled(true);
                            txtPlayShowSlideshow.setEnabled(true);

                            JSONObject json = new JSONObject(args[0] + "");

                            String PlayerCardSeen = json.getString("id");
                            if (txtPlay2Id.getText().toString().trim().equals(PlayerCardSeen)) {
                                txtPl2ChalBlind.setText("CardSeen");
                            } else if (txtPlay3Id.getText().toString().trim().equals(PlayerCardSeen)) {
                                txtPl3ChalBlind.setText("CardSeen");
                            } else if (txtPlay4Id.getText().toString().trim().equals(PlayerCardSeen)) {
                                txtPl4ChalBlind.setText("CardSeen");
                            } else if (txtPlay5Id.getText().toString().trim().equals(PlayerCardSeen)) {
                                txtPl5ChalBlind.setText("CardSeen");
                            } else if (txtPlay6Id.getText().toString().trim().equals(PlayerCardSeen)) {
                                txtPlay6.setText("CardSeen");
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        mSocket.on("showWinner", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.e(TAG + "  on", "showWinner" + args[0]);
                try {
                    Thread.sleep(800);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                runOnUiThread(new Runnable() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void run() {
                        try {
                            Progresspl6.setVisibility(View.GONE);
                            txtTableAmount.setVisibility(View.GONE);
                            txtReplaceCards.setAlpha(0.5f);
                            txtshowallcards.setAlpha(0.5f);
                            txtshowallcards.setEnabled(false);
                            txtReplaceCards.setEnabled(false);


                            txtTableAmount.setText("0");
                            Progresspl2.setVisibility(View.GONE);
                            Progresspl3.setVisibility(View.GONE);
                            Progresspl4.setVisibility(View.GONE);
                            Progresspl5.setVisibility(View.GONE);
                            Progresspl6.setVisibility(View.GONE);


                            StopTimer = 1;
                            llViewBottomBar.setVisibility(View.GONE);
                            llViewBottomBarInfoView.setVisibility(View.VISIBLE);
                            JSONObject json = new JSONObject(args[0] + "");
                            if (json.has("message"))
                                Functions.displayToast(json.getString("message"));

                            txtCardSee.setVisibility(View.GONE);
                            Log.d("showwinner1", "show winnnerrr");
                            JSONObject jjob = json.getJSONObject("bet");
                            String Placedby = json.getString("placedBy");
                            JSONObject jsonPlayer = json.getJSONObject("players");
                            ArrayList<User> arrotheruser = new ArrayList<>();
                            Iterator<String> iterator = jsonPlayer.keys();
                            while (iterator.hasNext()) {
                                Log.e("showwinner2", "show winnnerrr");
                                String key = iterator.next();
                                JSONObject jObje_Otherplayer = new JSONObject(jsonPlayer.optString(key));
                                User otheruser = new User();
                                otheruser.setId(jObje_Otherplayer.getString("id"));
                                JSONObject jsonObject = new JSONObject(jObje_Otherplayer.getString("cardSet"));
                                if (jsonObject.getString("closed").equals("false")) {
                                    JSONArray jcards = jsonObject.getJSONArray("cards");
                                    Log.e("cards dis", jcards.toString());
                                    Log.e("cards dis", jObje_Otherplayer.getString("id") + "     text      " + txtPlay2Id.getText().toString());
                                    if (jObje_Otherplayer.getString("id").equals(txtPlay2Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("id").equals(Placedby)) {
                                            txtCoinTable2.setText(jjob.getString("amount"));
//                                            llCoinTable2.setVisibility(View.VISIBLE);
//                                            llCoinTable2.startAnimation(ani2_back);
                                        }
                                        for (int i = 0; i < jcards.length(); i++) {
                                            JSONObject jrowcard = jcards.getJSONObject(i);
                                            if (i == 0)
                                                Functions.setcard(Table6_Activity.this, imgpl2card1back, imgpl2card1num, imgpl2card1icon, imgpl2card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                            if (i == 1)
                                                Functions.setcard(Table6_Activity.this, imgpl2card2back, imgpl2card2num, imgpl2card2icon, imgpl2card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                            if (i == 2)
                                                Functions.setcard(Table6_Activity.this, imgpl2card3back, imgpl2card3num, imgpl2card3icon, imgpl2card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        }
                                    } else if (jObje_Otherplayer.getString("id").equals(txtPlay3Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("id").equals(Placedby)) {
                                            txtCoinTable3.setText(jjob.getString("amount"));
//                                            llCoinTable3.setVisibility(View.VISIBLE);
//                                            llCoinTable3.startAnimation(ani3_back);
                                        }
                                        for (int i = 0; i < jcards.length(); i++) {
                                            JSONObject jrowcard = jcards.getJSONObject(i);
                                            if (i == 0)
                                                Functions.setcard(Table6_Activity.this, imgpl3card1back, imgpl3card1num, imgpl3card1icon, imgpl3card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                            if (i == 1)
                                                Functions.setcard(Table6_Activity.this, imgpl3card2back, imgpl3card2num, imgpl3card2icon, imgpl3card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                            if (i == 2)
                                                Functions.setcard(Table6_Activity.this, imgpl3card3back, imgpl3card3num, imgpl3card3icon, imgpl3card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        }
                                    } else if (jObje_Otherplayer.getString("id").equals(txtPlay4Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("id").equals(Placedby)) {
                                            txtCoinTable4.setText(jjob.getString("amount"));
//                                            llCoinTable4.setVisibility(View.VISIBLE);
//                                            llCoinTable4.startAnimation(ani4_back);
                                        }
                                        for (int i = 0; i < jcards.length(); i++) {
                                            JSONObject jrowcard = jcards.getJSONObject(i);
                                            if (i == 0)
                                                Functions.setcard(Table6_Activity.this, imgpl4card1back, imgpl4card1num, imgpl4card1icon, imgpl4card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                            if (i == 1)
                                                Functions.setcard(Table6_Activity.this, imgpl4card2back, imgpl4card2num, imgpl4card2icon, imgpl4card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                            if (i == 2)
                                                Functions.setcard(Table6_Activity.this, imgpl4card3back, imgpl4card3num, imgpl4card3icon, imgpl4card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        }
                                    } else if (jObje_Otherplayer.getString("id").equals(txtPlay5Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("id").equals(Placedby)) {
                                            txtCoinTable5.setText(jjob.getString("amount"));
//                                            llCoinTable5.setVisibility(View.VISIBLE);
//                                            llCoinTable5.startAnimation(ani5_back);
                                        }
                                        for (int i = 0; i < jcards.length(); i++) {
                                            JSONObject jrowcard = jcards.getJSONObject(i);
                                            if (i == 0)
                                                Functions.setcard(Table6_Activity.this, imgpl5card1back, imgpl5card1num, imgpl5card1icon, imgpl5card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                            if (i == 1)
                                                Functions.setcard(Table6_Activity.this, imgpl5card2back, imgpl5card2num, imgpl5card2icon, imgpl5card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                            if (i == 2)
                                                Functions.setcard(Table6_Activity.this, imgpl5card3back, imgpl5card3num, imgpl5card3icon, imgpl5card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                        }
                                    } else if (jObje_Otherplayer.getString("id").equals(txtPlay6Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("id").equals(Placedby)) {
                                            txtCoinTable6.setText(jjob.getString("amount"));
                                            llCoinTable6.setVisibility(View.VISIBLE);
                                            llCoinTable6.startAnimation(ani6_back);
                                        }

                                        for (int i = 0; i < jcards.length(); i++) {
                                            JSONObject jrowcard = jcards.getJSONObject(i);
                                            CurrentAction = "Chaal";
                                            if (i == 0)
                                                Functions.setcard(Table6_Activity.this, imgcard1back, imgcard1num, imgcard1icon, imgcard3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                            if (i == 1)
                                                Functions.setcard(Table6_Activity.this, imgcard2back, imgcard2num, imgcard2icon, imgcard3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                            if (i == 2) {
                                                Functions.setcard(Table6_Activity.this, imgcard3back, imgcard3num, imgcard3icon, imgcard3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                Log.e("calll", "open last card");
                                            }
                                        }
                                    }

                                }
                                JSONObject jsontable = new JSONObject(json.getString("table"));
                                txtTableAmountTransfer.setText(String.valueOf(Math.round(Double.parseDouble(jsontable.getString("amount"))  * 100.0) / 100.0));
                                final JSONObject jsonObject_playerinfo = new JSONObject(jObje_Otherplayer.getString("playerInfo"));
                                llTableAmountTransfer.setVisibility(View.VISIBLE);
                                Log.e("transfer won", txtPlay6Id.getText().toString() + "   " + jObje_Otherplayer.getString("id"));
                                if (jObje_Otherplayer.has("winner")) {
                                    Log.e("showwinner6", "show winnnerrr");
                                    if (jObje_Otherplayer.getString("id").equals(txtPlay2Id.getText().toString())) {
                                        Log.d("playerwon" , " textplay = " + txtPlay2Id.getText().toString()) ;

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                llTableAmountTransfer.startAnimation(anim_tab6_win_1);
                                                try {
                                                    txtPlay2Rs.setText(jsonObject_playerinfo.getString("chips"));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                    Log.e("error", e.getMessage());
                                                }
                                            }
                                        }, 800);


                                        Functions.Stop_Player_Turn = 1;
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
//
                                                imgWinningImage2.startAnimation(anim_round2);
                                                imgWinningImage2.setVisibility(View.VISIBLE);
                                                imgWinningImage3.setVisibility(View.GONE);
                                                imgWinningImage4.setVisibility(View.GONE);
                                                imgWinningImage5.setVisibility(View.GONE);
                                                imgWinningImage6.setVisibility(View.GONE);

//                                                NotiMessage= jObje_Otherplayer.getString("name") + " ";
//                                                Functions.ShowPlayerWinner(llPlayer2ShowAnim, "false");
                                                Functions.Stop_Player_Turn = 0;
                                            }
                                        }, 500);

                                    } else if (jObje_Otherplayer.getString("id").equals(txtPlay3Id.getText().toString())) {
                                        Log.d("playerwon" , " textplay = " + txtPlay3Id.getText().toString()) ;

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                llTableAmountTransfer.startAnimation(anim_tab6_win_2);
                                                try {
                                                    txtPlay3Rs.setText(jsonObject_playerinfo.getString("chips"));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                    Log.e("error", e.getMessage());
                                                }
                                            }
                                        }, 800);


                                        Functions.Stop_Player_Turn = 1;
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {

                                                imgWinningImage2.setVisibility(View.GONE);
                                                imgWinningImage3.startAnimation(anim_round3);
                                                imgWinningImage3.setVisibility(View.VISIBLE);
                                                imgWinningImage4.setVisibility(View.GONE);
                                                imgWinningImage5.setVisibility(View.GONE);
                                                imgWinningImage6.setVisibility(View.GONE);

//                                                Functions.ShowPlayerWinner(llPlayer3ShowAnim, "false");
                                                Functions.Stop_Player_Turn = 0;
                                            }
                                        }, 500);
                                    } else if (jObje_Otherplayer.getString("id").equals(txtPlay4Id.getText().toString())) {
                                        Log.d("playerwon" , " textplay = " + txtPlay4Id.getText().toString()) ;

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                llTableAmountTransfer.startAnimation(anim_tab6_win_3);
                                                try {
                                                    txtPlay4Rs.setText(jsonObject_playerinfo.getString("chips"));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                    Log.e("error", e.getMessage());
                                                }
                                            }
                                        }, 800);


                                        Functions.Stop_Player_Turn = 1;
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {


                                                imgWinningImage2.setVisibility(View.GONE);
                                                imgWinningImage3.setVisibility(View.GONE);
                                                imgWinningImage4.startAnimation(anim_round4);
                                                imgWinningImage4.setVisibility(View.VISIBLE);
                                                imgWinningImage5.setVisibility(View.GONE);
                                                imgWinningImage6.setVisibility(View.GONE);


//                                                Functions.ShowPlayerWinner(llPlayer4ShowAnim, "false");
                                                Functions.Stop_Player_Turn = 0;
                                            }
                                        }, 500);

                                    } else if (jObje_Otherplayer.getString("id").equals(txtPlay5Id.getText().toString())) {
                                        Log.d("playerwon" , " textplay = " + txtPlay5Id.getText().toString()) ;
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                llTableAmountTransfer.startAnimation(anim_tab6_win_4);
                                                try {
                                                    txtPlay5Rs.setText(jsonObject_playerinfo.getString("chips"));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                    Log.e("error", e.getMessage());
                                                }
                                            }
                                        }, 800);


                                        Functions.Stop_Player_Turn = 1;
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {

                                                imgWinningImage2.setVisibility(View.GONE);
                                                imgWinningImage3.setVisibility(View.GONE);
                                                imgWinningImage4.setVisibility(View.GONE);
                                                imgWinningImage5.startAnimation(anim_round5);
                                                imgWinningImage5.setVisibility(View.VISIBLE);
                                                imgWinningImage6.setVisibility(View.GONE);


//                                                Functions.ShowPlayerWinner(llPlayer5ShowAnim, "false");
                                                Functions.Stop_Player_Turn = 0;
                                            }
                                        }, 500);
                                    } else if (jObje_Otherplayer.getString("id").equals(txtPlay6Id.getText().toString())) {
                                        Log.d("playerwon" , " textplay = " + txtPlay6Id.getText().toString()) ;

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                llTableAmountTransfer.startAnimation(anim_tab6_win_5);
                                                try {
//                                                    txtPlay6Rs.setText(jsonObject_playerinfo.getString("chips"));
                                                    convertedChipformat = Math.round(Double.parseDouble(jsonObject_playerinfo.getString("chips"))  * 100.0) / 100.0;

                                                    txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                                    SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, jsonObject_playerinfo.getString("chips"));

                                                } catch (JSONException e) {
                                                    Log.e("error", e.getMessage());
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, 800);


                                        Functions.Stop_Player_Turn = 1;
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {


                                                imgWinningImage2.setVisibility(View.GONE);
                                                imgWinningImage3.setVisibility(View.GONE);
                                                imgWinningImage4.setVisibility(View.GONE);
                                                imgWinningImage5.setVisibility(View.GONE);
                                                imgWinningImage6.startAnimation(anim_round6);
                                                imgWinningImage6.setVisibility(View.VISIBLE);
                                                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_playerWinning));

//                                                Functions.ShowPlayerWinner(llPlayer6ShowAnim, "false");
                                                Functions.Stop_Player_Turn = 0;
                                            }
                                        }, 500);
                                    }
                                }

                            }

                            Log.e("showwinner 3", "show winnnerrr");
                            txtTableAmount.setText("0");
                            txtPlayShow.setEnabled(true);
                            txtPlayPlue.setEnabled(true);
                            txtPlayMinus.setEnabled(false);
                            txtPlayPack.setEnabled(true);
                            txtPlayBlind.setEnabled(true);
                            txtPlayShowSlideshow.setEnabled(true);
                            Log.e("print ", "Show winner");
                        } catch (Exception e) {
                            Log.e("print error", "Show winner");
                            Log.e("print error", e.getMessage());

                        }
                    }
                });
            }
        });


        mSocket.on("playerPacked", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Progresspl2.setVisibility(View.GONE);
                        Progresspl3.setVisibility(View.GONE);
                        Progresspl4.setVisibility(View.GONE);
                        Progresspl5.setVisibility(View.GONE);
                        Progresspl6.setVisibility(View.GONE);

                        Log.e(TAG + " on", "playerPacked" + args[0]);

                       /* txtPlayShow.setEnabled(true);
                        txtPlayPlue.setEnabled(true);
                        txtPlayMinus.setEnabled(true);
                        txtPlayPack.setEnabled(true);
                        txtPlayBlind.setEnabled(true);
                        txtPlayShowSlideshow.setEnabled(true);

                        llPlayer6.setAlpha(0.5f);
                        txtPlay6.setText("Pack");

*/
                        String leftaction = "Pack";

                        try {

                            final JSONObject json = new JSONObject(args[0] + "");
                            ArrayList<User> arrotheruser = new ArrayList<>();

                            JSONObject jsonPlayer = json.getJSONObject("players");
                            Json_Player_Info = jsonPlayer;
                            arrotheruser = new ArrayList<>();
                            Iterator<String> iterator = jsonPlayer.keys();
                            CurrentPlayerCount = 0;
                             isPackedTrack = 0 ;
                            isSlideshow = 0;
                            while (iterator.hasNext()) {
                                String key = iterator.next();


                                JSONObject jObje_Otherplayer = new JSONObject(jsonPlayer.optString(key));

                                User otheruser = new User();
                                otheruser.setId(jObje_Otherplayer.getString("id"));
                                otheruser.setSlot(jObje_Otherplayer.getString("slot"));
                                otheruser.setActive(jObje_Otherplayer.getString("active"));
                                PlayerInfo playerin = new PlayerInfo();
                                JSONObject jsonObject = new JSONObject(jObje_Otherplayer.getString("playerInfo"));
                                playerin.setUserId(jsonObject.getString("_id"));
                                playerin.setUserName(jsonObject.getString("displayName"));
//                                playerin.setPassword(jsonObject.getString("displayName"));
                                playerin.setChips(jsonObject.getString("chips"));
                                playerin.setProfilePics(jsonObject.getString("profilePic"));
                                otheruser.setPlayerinfo(playerin);
                                arrotheruser.add(otheruser);

                                if (jObje_Otherplayer.getString("active").equals("true")) {
                                    if (jObje_Otherplayer.getString("packed").equals("false"))
                                        CurrentPlayerCount++;

                                    if (otheruser.getPlayerinfo().getUserId().equals(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID))) {

                                        if (jObje_Otherplayer.getString("packed").equals("true")) {
                                            txtPlay6.setText(leftaction);
                                            txtPlay6Rs.setText("");
                                            llPlayer6.setAlpha(0.5f);

                                        }
                                        if (jObje_Otherplayer.getString("active").equals("true"))

                                            Log.e("turn", jObje_Otherplayer.getString("turn"));
                                        Myturn = jObje_Otherplayer.getString("turn");
                                        txtPlay6Rs.setText(jsonObject.getString("chips"));
                                        convertedChipformat = Math.round(Double.parseDouble(jsonObject.getString("chips"))  * 100.0) / 100.0;

                                        txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                        SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, jsonObject.getString("chips"));

                                        NotiMessage = "My Turn";
                                        if (Myturn.equals("true")) {
                                            StopTimer = 0;
                                            StartTimer(Progresspl6);
                                            Functions.PlayVibrator(Table6_Activity.this);

                                            if (jObje_Otherplayer.getString("isSideShowAvailable").equals("true")) {

                                                isSlideshow = 1;
                                            } else {
                                                txtPlayShowSlideshow.setVisibility(View.GONE);
//                                                txtPlayShowSlideshow.setAlpha(0.5f);
//                                                txtPlayShowSlideshow.setEnabled(false);
                                                txtPlayShow.setVisibility(View.INVISIBLE);

                                            }
                                            if (MaximumBlindcount == Float.parseFloat(SelectTable.getMaximumblind())) {
                                                txtCardSee.performClick();
                                            }
                                            llViewBottomBar.setVisibility(View.VISIBLE);
                                            llViewBottomBarInfoView.setVisibility(View.GONE);

                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            Functions.PlayVibrator(Table6_Activity.this);
                                            Functions.Stop_Player_Turn = 1;
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl6);
//                                                    Functions.ShowPlayerTurn(llPlayer6ShowAnim, "true");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);


                                        } else {
                                            llViewBottomBar.setVisibility(View.GONE);
                                            llViewBottomBarInfoView.setVisibility(View.VISIBLE);
                                        }
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay2Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("packed").equals("true")) {
                                            txtPl2ChalBlind.setText(leftaction);
                                            isPackedTrack++ ;
                                            txtPlay2Rs.setText("");
                                            llPlayer2.setAlpha(0.5f);


                                        }
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = jsonObject.getString("displayName") + " Turn";

                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl2);
//                                                    Functions.ShowPlayerTurn(llPlayer2ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);

                                        }
                                        txtPlay2Rs.setText(jsonObject.getString("chips"));
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay3Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("packed").equals("true")) {
                                            txtPl3ChalBlind.setText(leftaction);
                                            txtPlay3Rs.setText("");
                                            llPlayer3.setAlpha(0.5f);
                                            isPackedTrack++ ;

                                        }
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl3);
//                                                    Functions.ShowPlayerTurn(llPlayer3ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);
                                        }
                                        txtPlay3Rs.setText(jsonObject.getString("chips"));
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay4Id.getText().toString())) {

                                        if (jObje_Otherplayer.getString("turn").equals("true")) {

                                            if (jObje_Otherplayer.getString("packed").equals("true")) {
                                                isPackedTrack++ ;
                                                txtPl4ChalBlind.setText(leftaction);
                                                txtPlay4Rs.setText("");
                                                llPlayer4.setAlpha(0.5f);


                                            }
                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl4);
//                                                    Functions.ShowPlayerTurn(llPlayer3ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);
                                        }
                                        txtPlay4Rs.setText(jsonObject.getString("chips"));
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay5Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("packed").equals("true")) {
                                            isPackedTrack++ ;
                                            txtPl5ChalBlind.setText(leftaction);
                                            txtPlay5Rs.setText("");
                                            llPlayer5.setAlpha(0.5f);

                                        }
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl5);
//                                                    Functions.ShowPlayerTurn(llPlayer5ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);
                                        }
                                        txtPlay5Rs.setText(jsonObject.getString("chips"));
                                    }
                                    if(isPackedTrack == CurrentPlayerCount) {
                                        txtCardSee.performClick();
                                    }


                                }

                                final JSONObject jsontable = new JSONObject(json.getString("table"));
                                Json_Table_Info = jsontable;
                                lastbet = jsontable.getString("lastBet");

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
//                                            txtTableAmount.setText(jsontable.getString("amount") + "");
                                            convertedTableCoins = Math.round(Double.parseDouble(jsontable.getString("amount"))  * 100.0) / 100.0;
                                            txtTableAmount.setText(String.valueOf(convertedTableCoins) +"");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 400);

                                txtPlayRs.setText(lastbet);
                                txtPlayBlind.setText("Blind\n" + String.valueOf(Math.round(Double.parseDouble(lastbet)  * 100.0) / 100.0));
                                txtPlayChaal.setText("Chaal\n" + String.valueOf(Math.round(Double.parseDouble(lastbet)  * 100.0) / 100.0));
                                txtPlayMinus.setAlpha(0.5f);
                                txtPlayMinus.setEnabled(false);
                                txtPlayPlue.setAlpha(1f);
                                txtPlayPlue.setEnabled(true);

                            }

                            if (CurrentPlayerCount == 2) {
                                txtPlayShow.setVisibility(View.VISIBLE);
                                txtPlayShowSlideshow.setVisibility(View.GONE);
//                                txtPlayShowSlideshow.setAlpha(0.5f);
//                                txtPlayShowSlideshow.setEnabled(false);

                            } else {
                                txtPlayShow.setVisibility(View.INVISIBLE);
                                if (isSlideshow == 1) {
                                    txtPlayShowSlideshow.setVisibility(View.VISIBLE);
                                    txtPlayShowSlideshow.setAlpha(1f);
                                    txtPlayShowSlideshow.setEnabled(true);
                                } else {
                                    txtPlayShowSlideshow.setVisibility(View.GONE);
//                                    txtPlayShowSlideshow.setAlpha(0.5f);
//                                    txtPlayShowSlideshow.setEnabled(false);
                                }
                            }

                            txtPlayShow.setEnabled(true);
                            txtPlayPlue.setEnabled(true);
                            txtPlayMinus.setEnabled(false);
                            txtPlayPack.setEnabled(true);
                            txtPlayBlind.setEnabled(true);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_playerleft));


                    }
                });

            }
        });


        mSocket.on("resetTable", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG + "  on", "resetTable  " + args[0]);
                        txtPlayShow.setEnabled(true);
                        txtPlayPlue.setEnabled(true);
                        txtPlayMinus.setEnabled(false);
                        txtPlayPack.setEnabled(true);
                        txtPlayBlind.setEnabled(true);
                        txtPlayShowSlideshow.setEnabled(true);
                    }
                });

            }
        });


        mSocket.on("betPlaced", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.e(TAG + "  on", "betPlaced  " + args[0]);


                String message = args[0].toString();

                int maxLogSize = 1000;
                for (int i = 0; i <= message.length() / maxLogSize; i++) {
                    int start = i * maxLogSize;
                    int end = (i + 1) * maxLogSize;
                    end = end > message.length() ? message.length() : end;
                    Log.e("betPlaced : ", message.substring(start, end));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {


                            Progresspl2.setVisibility(View.GONE);
                            Progresspl3.setVisibility(View.GONE);
                            Progresspl4.setVisibility(View.GONE);
                            Progresspl5.setVisibility(View.GONE);
                            Progresspl6.setVisibility(View.GONE);


                            Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_coinfilp));
                            final JSONObject json = new JSONObject(args[0] + "");
                            Log.e("betPlaced: ", "json----" + json);
                            String placedby = json.getString("placedBy");
//                            String totalPlayedAmount = json.getString("totalPlayedAmount");
                            JSONObject jjob = json.getJSONObject("bet");
                            lastAction = jjob.getString("action");
                            LastBetPlaceSideShow = placedby;
                            Log.e("LastBetPlaceSideShow", "betplace" + LastBetPlaceSideShow + " ");
//                            Log.d("totalPlayedAmount", "totalPlayedAmount" + totalPlayedAmount + " ");

                            Log.e("Place By", LastBetPlaceSideShow + " ");
                            if (txtPlay2Id.getText().toString().equals(placedby)) {
                                txtCoinTable2.setText(jjob.getString("amount"));
                                llCoinTable2.setVisibility(View.VISIBLE);
                                llCoinTable2.startAnimation(ani2_back);
                                txtPl2ChalBlind.setText(jjob.getString("action"));

                            } else if (txtPlay3Id.getText().toString().equals(placedby)) {
                                txtCoinTable3.setText(jjob.getString("amount"));
                                llCoinTable3.setVisibility(View.VISIBLE);
                                llCoinTable3.startAnimation(ani3_back);
                                txtPl3ChalBlind.setText(jjob.getString("action"));
                            } else if (txtPlay4Id.getText().toString().equals(placedby)) {
                                txtCoinTable4.setText(jjob.getString("amount"));
                                llCoinTable4.setVisibility(View.VISIBLE);
                                llCoinTable4.startAnimation(ani4_back);
                                txtPl4ChalBlind.setText(jjob.getString("action"));
                            } else if (txtPlay5Id.getText().toString().equals(placedby)) {
                                txtCoinTable5.setText(jjob.getString("amount"));
                                llCoinTable5.setVisibility(View.VISIBLE);
                                llCoinTable5.startAnimation(ani5_back);
                                txtPl5ChalBlind.setText(jjob.getString("action"));
                            } else if (txtPlay6Id.getText().toString().equals(placedby)) {
                                txtCoinTable6.setText(jjob.getString("amount"));
                                llCoinTable6.setVisibility(View.VISIBLE);
                                llCoinTable6.startAnimation(ani6_back);
                            }


                            JSONObject jsonPlayer = json.getJSONObject("players");
                            Json_Player_Info = jsonPlayer;
                            ArrayList<User> arrotheruser = new ArrayList<>();
                            Iterator<String> iterator = jsonPlayer.keys();
                            CurrentPlayerCount = 0;
                            isSlideshow = 0;
                            while (iterator.hasNext()) {
                                String key = iterator.next();

                                JSONObject jObje_Otherplayer = new JSONObject(jsonPlayer.optString(key));

                                User otheruser = new User();
                                otheruser.setId(jObje_Otherplayer.getString("id"));
                                otheruser.setSlot(jObje_Otherplayer.getString("slot"));
                                otheruser.setActive(jObje_Otherplayer.getString("active"));
                                PlayerInfo playerin = new PlayerInfo();
                                JSONObject jsonObject = new JSONObject(jObje_Otherplayer.getString("playerInfo"));
                                playerin.setUserId(jsonObject.getString("_id"));
                                playerin.setUserName(jsonObject.getString("displayName"));
                                playerin.setChips(jsonObject.getString("chips"));
                                playerin.setProfilePics(jsonObject.getString("profilePic"));
                                otheruser.setPlayerinfo(playerin);
                                arrotheruser.add(otheruser);
                                final JSONObject jsontable = new JSONObject(json.getString("table"));
                                if (jObje_Otherplayer.getString("active").equals("true")) {
                                    if (jObje_Otherplayer.getString("packed").equals("false"))
                                        CurrentPlayerCount++;
                                    if (otheruser.getPlayerinfo().getUserId().equals(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID))) {

                                        if (jObje_Otherplayer.getString("active").equals("true"))
                                        Log.e("turn", jObje_Otherplayer.getString("turn"));
                                        Myturn = jObje_Otherplayer.getString("turn");
//                                        txtPlay6Rs.setText(jsonObject.getString("chips"));
                                        convertedChipformat = Math.round(Double.parseDouble(jsonObject.getString("chips"))  * 100.0) / 100.0;

                                        txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                        SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, jsonObject.getString("chips"));

                                            SharedPrefs.save(getApplicationContext(), SharedPrefs.DEFAULTAMT, jsontable.getString("amount")) ;




                                        if (Myturn.equals("true")) {
                                            StopTimer = 0;
                                            Functions.PlayVibrator(Table6_Activity.this);
                                            NotiMessage = "My Turn";
                                            Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_playerjointtable));
                                            StartTimer(Progresspl6);
                                            Log.e("slideshw", "slideshowwailable true   " + jObje_Otherplayer.getString("isSideShowAvailable"));
                                            if (jObje_Otherplayer.getString("isSideShowAvailable").equals("true")) {


                                                isSlideshow = 1;
                                            } else {
                                                txtPlayShowSlideshow.setVisibility(View.GONE);
//                                                txtPlayShowSlideshow.setAlpha(0.5f);
//                                                txtPlayShowSlideshow.setEnabled(false);
                                                txtPlayShow.setVisibility(View.INVISIBLE);

                                            }

                                            if (MaximumBlindcount == Float.parseFloat(SelectTable.getMaximumblind())) {
                                                txtCardSee.performClick();
                                            }
                                            llViewBottomBar.setVisibility(View.VISIBLE);
                                            llViewBottomBarInfoView.setVisibility(View.GONE);

                                            Functions.Stop_Player_Turn = 1;
                                            Functions.PlayVibrator(Table6_Activity.this);
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl6);
//                                                    Functions.ShowPlayerTurn(llPlayer6ShowAnim, "true");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);


                                        } else {
                                            llViewBottomBar.setVisibility(View.GONE);
                                            llViewBottomBarInfoView.setVisibility(View.VISIBLE);
                                        }
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay2Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {

                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            Functions.Stop_Player_Turn = 1;
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl2);
//                                                    Functions.ShowPlayerTurn(llPlayer2ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);

                                        }
                                        txtPlay2Rs.setText(jsonObject.getString("chips"));
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay3Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {


                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl3);
//                                                    Functions.ShowPlayerTurn(llPlayer3ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);
                                        }
                                        txtPlay3Rs.setText(jsonObject.getString("chips"));
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay4Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {

                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl4);
//                                                    Functions.ShowPlayerTurn(llPlayer3ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);
                                        }
                                        txtPlay4Rs.setText(jsonObject.getString("chips"));
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay5Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {

                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    StartTimer(Progresspl5);
//                                                    Functions.ShowPlayerTurn(llPlayer5ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);
                                        }
                                        txtPlay5Rs.setText(jsonObject.getString("chips"));
                                    }

                                }



                                Log.e(TAG, "jsontable--- " + jsontable);
                                Json_Table_Info = jsontable;
                                lastbet = jsontable.getString("lastBet");

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {

//                                            txtTableAmount.setText(jsontable.getString("amount"));
                                            convertedTableCoins = Math.round(Double.parseDouble(jsontable.getString("amount"))  * 100.0) / 100.0;
                                            txtTableAmount.setText(String.valueOf(convertedTableCoins));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 400);

                                Log.e(TAG, "getPotLimit--- " + SelectTable.getPotLimit());
                                if ( Float.parseFloat(jsontable.getString("amount")) >  Float.parseFloat(SelectTable.getPotLimit()))
                                    txtPlayShow.performClick();
                                lastAction = jjob.getString("action");

                                Log.e("lastaction", lastAction + "  curr  " + CurrentAction + "    " + lastbet);

                                if (lastAction.equals("Blind")) {
                                    if (CurrentAction.equals("Chaal")) {
                                        lastbet = ( Float.parseFloat(lastbet) +  Float.parseFloat(lastbet)) + "";
                                    }
                                } else if (lastAction.equals("Chaal")) {
                                    if (CurrentAction.equals("Blind")) {
                                        float minusamount = Float.parseFloat(lastbet) / 2;
                                        float minus =  Float.parseFloat(lastbet) - minusamount;
                                        lastbet = minus + "";
                                    }
                                }
                                txtPlayRs.setText(lastbet);
                                txtPlayBlind.setText("Blind\n" + String.valueOf(Math.round(Double.parseDouble(lastbet)  * 100.0) / 100.0));

                                txtPlayChaal.setText("Chaal\n" + String.valueOf(Math.round(Double.parseDouble(lastbet)  * 100.0) / 100.0));
                                txtPlayMinus.setAlpha(0.5f);
                                txtPlayMinus.setEnabled(false);
                                txtPlayPlue.setAlpha(1f);
                                txtPlayPlue.setEnabled(true);

                            }

                            Log.e("Visible", "visible " + isSlideshow);
                            if (CurrentPlayerCount == 2) {
                                txtPlayShow.setVisibility(View.VISIBLE);
                                txtPlayShowSlideshow.setVisibility(View.GONE);
//                                txtPlayShowSlideshow.setAlpha(0.5f);
//                                txtPlayShowSlideshow.setEnabled(false);

                            } else {
                                txtPlayShow.setVisibility(View.INVISIBLE);
                                if (isSlideshow == 1) {
                                    txtPlayShowSlideshow.setVisibility(View.VISIBLE);
                                    txtPlayShowSlideshow.setAlpha(1f);
                                    txtPlayShowSlideshow.setEnabled(true);
                                } else {
                                    txtPlayShowSlideshow.setVisibility(View.GONE);
//                                    txtPlayShowSlideshow.setAlpha(0.5f);
//                                    txtPlayShowSlideshow.setEnabled(false);
                                }
                            }


                            txtPlayShow.setEnabled(true);
                            txtPlayPlue.setEnabled(true);
                            txtPlayMinus.setEnabled(false);
                            txtPlayPack.setEnabled(true);
                            txtPlayBlind.setEnabled(true);
//                            txtPlayShowSlideshow.setEnabled(true);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        mSocket.on("sideShowResponded", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.e(TAG + "  on", "sideShowResponded  " + args[0]);

                String message = args[0].toString();

                int maxLogSize = 1000;
                for (int i = 0; i <= message.length() / maxLogSize; i++) {
                    int start = i * maxLogSize;
                    int end = (i + 1) * maxLogSize;
                    end = end > message.length() ? message.length() : end;
                    Log.e("responded slide show", message.substring(start, end));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject json = new JSONObject(args[0] + "");

                            if (json.has("message")) {
                                Functions.displayToast(json.getString("message"));
                            }
                            String placedTo = json.getString("placeTo");
                            String PlaceBy = json.getString("placedBy");
                            String loserplayerid = "";

                            if (json.has("player")) {
                                JSONObject jsonwinplayer = json.getJSONObject("player");
                                String winplayerid = jsonwinplayer.getString("_id");


                                if (json.has("cardsToShow")) {
                                    JSONObject jsonPlayer = json.getJSONObject("cardsToShow");
                                    ArrayList<User> arrotheruser = new ArrayList<>();
                                    Iterator<String> iterator = jsonPlayer.keys();
                                    while (iterator.hasNext()) {

                                        String key = iterator.next();
                                        JSONObject jObje_Otherplayer = new JSONObject(jsonPlayer.optString(key));
                                        User otheruser = new User();

                                        LastBetPlaceSideShow = winplayerid;
                                        Log.e("LastBetPlaceSideShow", "winner" + LastBetPlaceSideShow + " ");

                                        if (!winplayerid.equals(key))
                                            loserplayerid = key;

                                        Log.e("key id", key + "   loseeee.................");
                                        Log.e("winnign id", winplayerid + "   loseeee.................");
                                        Log.e("loser id", loserplayerid + "   loseeee.................");

                                        otheruser.setId(key);
                                        arrotheruser.add(otheruser);
                                        if (txtPlay6Id.getText().toString().equals(placedTo) || txtPlay6Id.getText().toString().equals(PlaceBy)) {
                                            JSONArray jrow = jObje_Otherplayer.getJSONArray("cardSet");
                                            if (key.equals(txtPlay2Id.getText().toString())) {
                                                for (int i = 0; i < jrow.length(); i++) {
                                                    JSONObject jrowcard = jrow.getJSONObject(i);
                                                    if (i == 0)
                                                        Functions.setcard(Table6_Activity.this, imgpl2card1back, imgpl2card1num, imgpl2card1icon, imgpl2card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                    if (i == 1)
                                                        Functions.setcard(Table6_Activity.this, imgpl2card2back, imgpl2card2num, imgpl2card2icon, imgpl2card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                    if (i == 2)
                                                        Functions.setcard(Table6_Activity.this, imgpl2card3back, imgpl2card3num, imgpl2card3icon, imgpl2card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                }

                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                imgpl2card3Big.setImageResource(R.drawable.greybase);
                                                                imgpl2card1back.setImageResource(R.drawable.greybase);
                                                                imgpl2card1num.setImageResource(R.drawable.greybase);
                                                                imgpl2card1icon.setImageResource(R.drawable.greybase);
                                                                imgpl2card2back.setImageResource(R.drawable.greybase);
                                                                imgpl2card2num.setImageResource(R.drawable.greybase);
                                                                imgpl2card2icon.setImageResource(R.drawable.greybase);
                                                                imgpl2card3back.setImageResource(R.drawable.greybase);
                                                                imgpl2card3num.setImageResource(R.drawable.greybase);
                                                                imgpl2card3icon.setImageResource(R.drawable.greybase);
                                                            }
                                                        });


                                                    }
                                                }, 10000);

                                            } else if (key.equals(txtPlay3Id.getText().toString())) {
                                                for (int i = 0; i < jrow.length(); i++) {
                                                    JSONObject jrowcard = jrow.getJSONObject(i);
                                                    if (i == 0)
                                                        Functions.setcard(Table6_Activity.this, imgpl3card1back, imgpl3card1num, imgpl3card1icon, imgpl3card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                    if (i == 1)
                                                        Functions.setcard(Table6_Activity.this, imgpl3card2back, imgpl3card2num, imgpl3card2icon, imgpl3card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                    if (i == 2)
                                                        Functions.setcard(Table6_Activity.this, imgpl3card3back, imgpl3card3num, imgpl3card3icon, imgpl3card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                }


                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                imgpl3card3Big.setImageResource(R.drawable.greybase);
                                                                imgpl3card1back.setImageResource(R.drawable.greybase);
                                                                imgpl3card1num.setImageResource(R.drawable.greybase);
                                                                imgpl3card1icon.setImageResource(R.drawable.greybase);
                                                                imgpl3card2back.setImageResource(R.drawable.greybase);
                                                                imgpl3card2num.setImageResource(R.drawable.greybase);
                                                                imgpl3card2icon.setImageResource(R.drawable.greybase);
                                                                imgpl3card3back.setImageResource(R.drawable.greybase);
                                                                imgpl3card3num.setImageResource(R.drawable.greybase);
                                                                imgpl3card3icon.setImageResource(R.drawable.greybase);
                                                            }
                                                        });


                                                    }
                                                }, 10000);

                                            } else if (key.equals(txtPlay4Id.getText().toString())) {
                                                for (int i = 0; i < jrow.length(); i++) {
                                                    JSONObject jrowcard = jrow.getJSONObject(i);

                                                    if (i == 0)
                                                        Functions.setcard(Table6_Activity.this, imgpl4card1back, imgpl4card1num, imgpl4card1icon, imgpl4card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                    if (i == 1)
                                                        Functions.setcard(Table6_Activity.this, imgpl4card2back, imgpl4card2num, imgpl4card2icon, imgpl4card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                    if (i == 2)
                                                        Functions.setcard(Table6_Activity.this, imgpl4card3back, imgpl4card3num, imgpl4card3icon, imgpl4card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));

                                                }

                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                imgpl4card3Big.setImageResource(R.drawable.greybase);
                                                                imgpl4card1back.setImageResource(R.drawable.greybase);
                                                                imgpl4card1num.setImageResource(R.drawable.greybase);
                                                                imgpl4card1icon.setImageResource(R.drawable.greybase);
                                                                imgpl4card2back.setImageResource(R.drawable.greybase);
                                                                imgpl4card2num.setImageResource(R.drawable.greybase);
                                                                imgpl4card2icon.setImageResource(R.drawable.greybase);
                                                                imgpl4card3back.setImageResource(R.drawable.greybase);
                                                                imgpl4card3num.setImageResource(R.drawable.greybase);
                                                                imgpl4card3icon.setImageResource(R.drawable.greybase);
                                                            }
                                                        });


                                                    }
                                                }, 10000);

                                            } else if (key.equals(txtPlay5Id.getText().toString())) {
                                                for (int i = 0; i < jrow.length(); i++) {
                                                    JSONObject jrowcard = jrow.getJSONObject(i);

                                                    if (i == 0)
                                                        Functions.setcard(Table6_Activity.this, imgpl5card1back, imgpl5card1num, imgpl5card1icon, imgpl5card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                    if (i == 1)
                                                        Functions.setcard(Table6_Activity.this, imgpl5card2back, imgpl5card2num, imgpl5card2icon, imgpl5card3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                    if (i == 2)
                                                        Functions.setcard(Table6_Activity.this, imgpl5card3back, imgpl5card3num, imgpl5card3icon, imgpl5card3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                }

                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                imgpl5card3Big.setImageResource(R.drawable.greybase);
                                                                imgpl5card1back.setImageResource(R.drawable.greybase);
                                                                imgpl5card1num.setImageResource(R.drawable.greybase);
                                                                imgpl5card1icon.setImageResource(R.drawable.greybase);
                                                                imgpl5card2back.setImageResource(R.drawable.greybase);
                                                                imgpl5card2num.setImageResource(R.drawable.greybase);
                                                                imgpl5card2icon.setImageResource(R.drawable.greybase);
                                                                imgpl5card3back.setImageResource(R.drawable.greybase);
                                                                imgpl5card3num.setImageResource(R.drawable.greybase);
                                                                imgpl5card3icon.setImageResource(R.drawable.greybase);
                                                            }
                                                        });


                                                    }
                                                }, 10000);


                                            } else if (key.equals(txtPlay6Id.getText().toString())) {
                                                txtCardSee.setVisibility(View.GONE);
                                                for (int i = 0; i < jrow.length(); i++) {
                                                    JSONObject jrowcard = jrow.getJSONObject(i);

                                                    CurrentAction = "Chaal";
                                                    if (i == 0)
                                                        Functions.setcard(Table6_Activity.this, imgcard1back, imgcard1num, imgcard1icon, imgcard3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                    if (i == 1)
                                                        Functions.setcard(Table6_Activity.this, imgcard2back, imgcard2num, imgcard2icon, imgcard3Big, "false", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                    if (i == 2) {
                                                        Functions.setcard(Table6_Activity.this, imgcard3back, imgcard3num, imgcard3icon, imgcard3Big, "true", jrowcard.getString("type"), jrowcard.getString("rank"));
                                                        Log.e("calll", "open last card");
                                                    }
                                                }

                                            }

                                        }

                                    }
                                }
                            }


                            JSONObject jsonPlayer = json.getJSONObject("players");
                            Json_Player_Info = jsonPlayer;
                            ArrayList<User> arrotheruser = new ArrayList<>();
                            Iterator<String> iterator = jsonPlayer.keys();
                            CurrentPlayerCount = 0;
                            isSlideshow = 0;
                            while (iterator.hasNext()) {
                                String key = iterator.next();
                                JSONObject jObje_Otherplayer = new JSONObject(jsonPlayer.optString(key));
                                User otheruser = new User();
                                otheruser.setId(jObje_Otherplayer.getString("id"));
                                otheruser.setSlot(jObje_Otherplayer.getString("slot"));
                                otheruser.setActive(jObje_Otherplayer.getString("active"));
                                PlayerInfo playerin = new PlayerInfo();
                                JSONObject jsonObject = new JSONObject(jObje_Otherplayer.getString("playerInfo"));
                                playerin.setUserId(jsonObject.getString("_id"));
                                playerin.setUserName(jsonObject.getString("displayName"));
                                playerin.setChips(jsonObject.getString("chips"));
                                playerin.setProfilePics(jsonObject.getString("profilePic"));
                                otheruser.setPlayerinfo(playerin);
                                arrotheruser.add(otheruser);


                                if (jObje_Otherplayer.getString("active").equals("true")) {

                                    if (jObje_Otherplayer.getString("packed").equals("false"))
                                        CurrentPlayerCount++;


                                    if (loserplayerid.trim() != "") {
                                        if (otheruser.getId().equals(loserplayerid)) {
                                            if (otheruser.getSlot().equals(txtPlay2Slot.getText().toString())) {
                                                txtPlay2Rs.setText(otheruser.getPlayerinfo().getChips());
                                                txtPl2ChalBlind.setText("packed");
                                                llPlayer2.setAlpha(0.5f);

                                            } else if (otheruser.getSlot().equals(txtPlay3Slot.getText().toString())) {
                                                txtPl3ChalBlind.setText("packed");
                                                txtPlay3Rs.setText(otheruser.getPlayerinfo().getChips());
                                                llPlayer3.setAlpha(0.5f);


                                            } else if (otheruser.getSlot().equals(txtPlay4Slot.getText().toString())) {
                                                txtPl4ChalBlind.setText("packed");
                                                txtPlay4Rs.setText(otheruser.getPlayerinfo().getChips());
                                                llPlayer4.setAlpha(0.5f);


                                            } else if (otheruser.getSlot().equals(txtPlay5Slot.getText().toString())) {
                                                txtPl5ChalBlind.setText("packed");
                                                txtPlay5Rs.setText(otheruser.getPlayerinfo().getChips());
                                                llPlayer5.setAlpha(0.5f);

                                            } else if (otheruser.getSlot().equals(txtPlay6Slot.getText().toString())) {
                                                txtPlay6.setText("packed");
                                                txtPlay6Rs.setText(otheruser.getPlayerinfo().getChips());
                                                convertedChipformat = Math.round(Double.parseDouble(otheruser.getPlayerinfo().getChips())  * 100.0) / 100.0;

                                                txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                                llPlayer6.setAlpha(0.5f);
                                                llViewBottomBar.setVisibility(View.GONE);

                                            }

                                        } else {
                                            if (jObje_Otherplayer.getString("active").equals("true")) {

                                                if (otheruser.getPlayerinfo().getUserId().equals(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID))) {

                                                    if (jObje_Otherplayer.getString("active").equals("true"))

                                                        Log.e("turn", jObje_Otherplayer.getString("turn"));
                                                    Myturn = jObje_Otherplayer.getString("turn");
                                                    txtPlay6Rs.setText(jsonObject.getString("chips"));
                                                    convertedChipformat = Math.round(Double.parseDouble(jsonObject.getString("chips"))  * 100.0) / 100.0;

                                                    txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                                    SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, jsonObject.getString("chips"));

                                                    if (Myturn.equals("true")) {
                                                        StopTimer = 0;
                                                        StartTimer(Progresspl6);
                                                        Functions.PlayVibrator(Table6_Activity.this);
                                                        NotiMessage = "My Turn";
                                                        if (jObje_Otherplayer.getString("isSideShowAvailable").equals("true")) {
                                                            isSlideshow = 1;
                                                        } else {
                                                            txtPlayShowSlideshow.setVisibility(View.GONE);
//                                                            txtPlayShowSlideshow.setAlpha(0.5f);
//                                                            txtPlayShowSlideshow.setEnabled(false);
                                                            txtPlayShow.setVisibility(View.INVISIBLE);

                                                        }
                                                        if (MaximumBlindcount == Float.parseFloat(SelectTable.getMaximumblind())) {
                                                            txtCardSee.performClick();
                                                        }
                                                        llViewBottomBar.setVisibility(View.VISIBLE);
                                                        llViewBottomBarInfoView.setVisibility(View.GONE);

                                                        Functions.Stop_Player_Turn = 1;
                                                        Functions.PlayVibrator(Table6_Activity.this);
                                                        new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {

                                                                StartTimer(Progresspl6);
//                                                                Functions.ShowPlayerTurn(llPlayer6ShowAnim, "true");
//                                                                Functions.Stop_Player_Turn = 0;
                                                            }
                                                        }, 500);


                                                    } else {
                                                        llViewBottomBar.setVisibility(View.GONE);
                                                        llViewBottomBarInfoView.setVisibility(View.VISIBLE);
                                                    }
                                                } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay2Id.getText().toString())) {
                                                    if (jObje_Otherplayer.getString("turn").equals("true")) {

                                                        StopTimer = 0;
//                                                        StartTimer(Progresspl2);
                                                        Functions.Stop_Player_Turn = 1;
                                                        NotiMessage = jsonObject.getString("displayName") + " Turn";
                                                        new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {


                                                                StartTimer(Progresspl2);
//                                                                Functions.ShowPlayerTurn(llPlayer2ShowAnim, "false");
//                                                                Functions.Stop_Player_Turn = 0;
                                                            }
                                                        }, 500);

                                                    }
                                                    txtPlay2Rs.setText(jsonObject.getString("chips"));
                                                } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay3Id.getText().toString())) {
                                                    if (jObje_Otherplayer.getString("turn").equals("true")) {

                                                        StopTimer = 0;
//                                                        StartTimer(Progresspl3);
                                                        NotiMessage = jsonObject.getString("displayName") + " Turn";
                                                        Functions.Stop_Player_Turn = 1;
                                                        new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {

                                                                                                             /*      llPlayer6.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer5.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer4.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer3.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer2.setBackgroundColor(R.drawable.player_back);*/

                                                                StartTimer(Progresspl3);
//                                                                Functions.ShowPlayerTurn(llPlayer3ShowAnim, "false");
                                                                Functions.Stop_Player_Turn = 0;
                                                            }
                                                        }, 500);
                                                    }
                                                    txtPlay3Rs.setText(jsonObject.getString("chips"));
                                                } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay4Id.getText().toString())) {
                                                    if (jObje_Otherplayer.getString("turn").equals("true")) {


                                                        StopTimer = 0;
//                                                        StartTimer(Progresspl4);
                                                        NotiMessage = jsonObject.getString("displayName") + " Turn";
                                                        Functions.Stop_Player_Turn = 1;
                                                        new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {


                                                                StartTimer(Progresspl4);
//                                                                Functions.ShowPlayerTurn(llPlayer3ShowAnim, "false");
//                                                                Functions.Stop_Player_Turn = 0;
                                                            }
                                                        }, 500);
                                                    }
                                                    txtPlay4Rs.setText(jsonObject.getString("chips"));
                                                } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay5Id.getText().toString())) {
                                                    if (jObje_Otherplayer.getString("turn").equals("true")) {
//                                            llPlayer5.setBackgroundResource(R.drawable.player_back_select);

                                   /*         Functions.Stop_Player_Turn = 0;
                                            Functions.ShowPlayerTurn(llPlayer5,"false");*/

                                                        StopTimer = 0;
//                                                        StartTimer(Progresspl5);
                                                        NotiMessage = jsonObject.getString("displayName") + " Turn";
                                                        Functions.Stop_Player_Turn = 1;
                                                        new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {

                                               /*     llPlayer6.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer5.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer4.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer3.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer2.setBackgroundColor(R.drawable.player_back);*/


                                                                StartTimer(Progresspl5);
//                                                                Functions.ShowPlayerTurn(llPlayer5ShowAnim, "false");
//                                                                Functions.Stop_Player_Turn = 0;
                                                            }
                                                        }, 500);
                                                    }
                                                    txtPlay5Rs.setText(jsonObject.getString("chips"));
                                                }

                                            }
                                        }
                                    } else {

                                        if (jObje_Otherplayer.getString("active").equals("true")) {

                                            if (otheruser.getPlayerinfo().getUserId().equals(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID))) {

                                                if (jObje_Otherplayer.getString("active").equals("true"))

                                                    Log.e("turn", jObje_Otherplayer.getString("turn"));
                                                Myturn = jObje_Otherplayer.getString("turn");
                                                txtPlay6Rs.setText(jsonObject.getString("chips"));
                                                convertedChipformat = Math.round(Double.parseDouble(jsonObject.getString("chips"))  * 100.0) / 100.0;

                                                txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                                SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, jsonObject.getString("chips"));

                                                if (Myturn.equals("true")) {
                                                    StopTimer = 0;
//                                                    StartTimer(Progresspl6);
                                                    Functions.PlayVibrator(Table6_Activity.this);

                                                    if (jObje_Otherplayer.getString("isSideShowAvailable").equals("true")) {

                                                        isSlideshow = 1;
                                                    } else {
                                                        txtPlayShowSlideshow.setVisibility(View.GONE);
//                                                        txtPlayShowSlideshow.setAlpha(0.5f);
//                                                        txtPlayShowSlideshow.setEnabled(false);
                                                        txtPlayShow.setVisibility(View.INVISIBLE);

                                                    }
                                                    if (MaximumBlindcount == Float.parseFloat(SelectTable.getMaximumblind())) {
                                                        txtCardSee.performClick();
                                                    }
                                                    llViewBottomBar.setVisibility(View.VISIBLE);
                                                    llViewBottomBarInfoView.setVisibility(View.GONE);

                                                    Functions.Stop_Player_Turn = 1;
                                                    NotiMessage = "My Turn";
                                                    Functions.PlayVibrator(Table6_Activity.this);
                                                    new Handler().postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {

                                                            StartTimer(Progresspl6);
//                                                            Functions.ShowPlayerTurn(llPlayer6ShowAnim, "true");
//                                                            Functions.Stop_Player_Turn = 0;
                                                        }
                                                    }, 500);


                                                } else {
                                                    llViewBottomBar.setVisibility(View.GONE);
                                                    llViewBottomBarInfoView.setVisibility(View.VISIBLE);
                                                }
                                            } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay2Id.getText().toString())) {
                                                if (jObje_Otherplayer.getString("turn").equals("true")) {

                                                    Functions.Stop_Player_Turn = 1;
                                                    NotiMessage = jsonObject.getString("displayName") + " Turn";
                                                    new Handler().postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {


                                                            StartTimer(Progresspl2);
//                                                            Functions.ShowPlayerTurn(llPlayer2ShowAnim, "false");
//                                                            Functions.Stop_Player_Turn = 0;
                                                        }
                                                    }, 500);

                                                }
                                                txtPlay2Rs.setText(jsonObject.getString("chips"));
                                            } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay3Id.getText().toString())) {
                                                if (jObje_Otherplayer.getString("turn").equals("true")) {


                                                    NotiMessage = jsonObject.getString("displayName") + " Turn";
                                                    Functions.Stop_Player_Turn = 1;
                                                    new Handler().postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {


                                                            StartTimer(Progresspl3);
//                                                            Functions.ShowPlayerTurn(llPlayer3ShowAnim, "false");
//                                                            Functions.Stop_Player_Turn = 0;
                                                        }
                                                    }, 500);
                                                }
                                                txtPlay3Rs.setText(jsonObject.getString("chips"));
                                            } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay4Id.getText().toString())) {
                                                if (jObje_Otherplayer.getString("turn").equals("true")) {


                                                    Functions.Stop_Player_Turn = 1;
                                                    NotiMessage = jsonObject.getString("displayName") + " Turn";
                                                    new Handler().postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {


                                                            StartTimer(Progresspl4);
//                                                            Functions.ShowPlayerTurn(llPlayer3ShowAnim, "false");
//                                                            Functions.Stop_Player_Turn = 0;
                                                        }
                                                    }, 500);
                                                }
                                                txtPlay4Rs.setText(jsonObject.getString("chips"));
                                            } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay5Id.getText().toString())) {
                                                if (jObje_Otherplayer.getString("turn").equals("true")) {
                                                    Functions.Stop_Player_Turn = 1;
                                                    NotiMessage = jsonObject.getString("displayName") + " Turn";
                                                    new Handler().postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {

                                                            StartTimer(Progresspl5);
//                                                            Functions.ShowPlayerTurn(llPlayer5ShowAnim, "false");
//                                                            Functions.Stop_Player_Turn = 0;
                                                        }
                                                    }, 500);
                                                }
                                                txtPlay5Rs.setText(jsonObject.getString("chips"));
                                            }

                                        }


                                    }

                                }


                                final JSONObject jsontable = new JSONObject(json.getString("table"));
                                Json_Table_Info = jsontable;
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
//                                            txtTableAmount.setText(jsontable.getString("amount"));
                                            convertedTableCoins = Math.round(Double.parseDouble(jsontable.getString("amount"))  * 100.0) / 100.0;
                                            txtTableAmount.setText(String.valueOf(convertedTableCoins));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 400);


                                txtPlayRs.setText(jsontable.getString("lastBet"));
                                txtPlayBlind.setText("Blind\n" + String.valueOf(Math.round(Double.parseDouble(jsontable.getString("lastBet"))  * 100.0) / 100.0));
                                txtPlayChaal.setText("Chaal\n" + String.valueOf(Math.round(Double.parseDouble(jsontable.getString("lastBet"))  * 100.0) / 100.0));
                                lastbet = jsontable.getString("lastBet");
                                txtPlayMinus.setAlpha(0.5f);
                                txtPlayMinus.setEnabled(false);
                                txtPlayPlue.setAlpha(1f);
                                txtPlayPlue.setEnabled(true);

                            }

                            if (CurrentPlayerCount == 2) {
                                txtPlayShow.setVisibility(View.VISIBLE);
                                txtPlayShowSlideshow.setVisibility(View.GONE);
//                                txtPlayShowSlideshow.setAlpha(0.5f);
//                                txtPlayShowSlideshow.setEnabled(false);


                            } else {
                                txtPlayShow.setVisibility(View.INVISIBLE);
                                if (isSlideshow == 1) {
                                    txtPlayShowSlideshow.setVisibility(View.VISIBLE);
                                    txtPlayShowSlideshow.setAlpha(1f);
                                    txtPlayShowSlideshow.setEnabled(true);
                                } else {
                                    txtPlayShowSlideshow.setVisibility(View.GONE);
//                                    txtPlayShowSlideshow.setAlpha(0.5f);
//                                    txtPlayShowSlideshow.setEnabled(false);
                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

//                        d.show();
                    }
                });
            }
        });

        mSocket.on("sideShowPlaced", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.e(TAG + "  on", "sideShowPlaced  " + args[0]);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(args[0] + "");
                            JSONObject jsonPlayer = json.getJSONObject("players");
                            ArrayList<User> arrotheruser = new ArrayList<>();
                            Iterator<String> iterator = jsonPlayer.keys();
                            String placedby = json.getString("placedBy");
                            LastSideshowplaceBy = json.getString("placedBy");

                            LastBetPlaceSideShow = json.getString("placedBy");
                            Log.e("LastBetPlaceSideShow", "sideshoplaced" + LastBetPlaceSideShow + " ");


                            String placedto = json.getString("placedTo");
                            String PlaceToName = "";
                            String PlaceByName = "";
                            while (iterator.hasNext()) {
                                String key = iterator.next();
                                JSONObject jObje_Otherplayer = new JSONObject(jsonPlayer.optString(key));
                                User otheruser = new User();
                                otheruser.setId(jObje_Otherplayer.getString("id"));
                                JSONObject jjob = json.getJSONObject("bet");
                                if (placedto.equals(txtPlay6Id.getText().toString())) {
                                    if (jObje_Otherplayer.has("sideShowTurn"))
                                        if (jObje_Otherplayer.getString("sideShowTurn").equals("true")) {
                                            txtSlideDesc.setText(json.getString("message"));
                                            d.show();
                                            MyturnSideShow = "true";
                                            StopTimerSideShow = 0;
                                            StartTimerForProgress(Progresspl6);
                                        }

                                    PlaceByName = txtPlay6.getText().toString();
                                } else if (placedto.equals(txtPlay2Id.getText().toString())) {
                                    StopTimerSideShow = 0;
                                    StartTimerForProgress(Progresspl2);
                                    PlaceByName = txtPlay2.getText().toString();
                                } else if (placedto.equals(txtPlay3Id.getText().toString())) {
                                    StopTimerSideShow = 0;
                                    StartTimerForProgress(Progresspl3);
                                    PlaceByName = txtPlay3.getText().toString();
                                } else if (placedto.equals(txtPlay4Id.getText().toString())) {
                                    StopTimerSideShow = 0;
                                    StartTimerForProgress(Progresspl4);
                                    PlaceByName = txtPlay4.getText().toString();
                                } else if (placedto.equals(txtPlay5Id.getText().toString())) {
                                    StopTimerSideShow = 0;
                                    StartTimerForProgress(Progresspl5);
                                    PlaceByName = txtPlay5.getText().toString();
                                }


                                if (placedby.equals(txtPlay2Id.getText().toString())) {
                                    PlaceToName = txtPlay2.getText().toString();
                                } else if (placedby.equals(txtPlay3Id.getText().toString())) {
                                    PlaceToName = txtPlay3.getText().toString();
                                } else if (placedby.equals(txtPlay4Id.getText().toString())) {
                                    PlaceToName = txtPlay4.getText().toString();
                                } else if (placedby.equals(txtPlay5Id.getText().toString())) {
                                    PlaceToName = txtPlay5.getText().toString();
                                } else if (placedby.equals(txtPlay6Id.getText().toString())) {
                                    PlaceToName = txtPlay6.getText().toString();
                                }

                                final JSONObject jsontable = new JSONObject(json.getString("table"));
                                Json_Table_Info = jsontable;
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
//                                            txtTableAmount.setText(jsontable.getString("amount"));
                                            convertedTableCoins = Math.round(Double.parseDouble(jsontable.getString("amount"))  * 100.0) / 100.0;
                                            txtTableAmount.setText(String.valueOf(convertedTableCoins));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 400);

                                if (placedby.equals(txtPlay2Id.getText().toString())) {
                                    txtCoinTable2.setText(jjob.getString("amount"));
                                    llCoinTable2.setVisibility(View.VISIBLE);
                                    llCoinTable2.startAnimation(ani2_back);
                                } else if (placedby.equals(txtPlay3Id.getText().toString())) {
                                    txtCoinTable3.setText(jjob.getString("amount"));
                                    llCoinTable3.setVisibility(View.VISIBLE);
                                    llCoinTable3.startAnimation(ani3_back);
                                } else if (placedby.equals(txtPlay4Id.getText().toString())) {
                                    txtCoinTable4.setText(jjob.getString("amount"));
                                    llCoinTable4.setVisibility(View.VISIBLE);
                                    llCoinTable4.startAnimation(ani4_back);
                                } else if (placedby.equals(txtPlay5Id.getText().toString())) {
                                    txtCoinTable5.setText(jjob.getString("amount"));
                                    llCoinTable5.setVisibility(View.VISIBLE);
                                    llCoinTable5.startAnimation(ani5_back);
                                } else if (placedby.equals(txtPlay6Id.getText().toString())) {
                                    txtCoinTable6.setText(jjob.getString("amount"));
                                    llCoinTable6.setVisibility(View.VISIBLE);
                                    llCoinTable6.startAnimation(ani6_back);
                                }
                            }

                            Functions.displayToast(PlaceToName + " Place Side Show to " + PlaceByName);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }


        });

        mSocket.on("newPlayerJoined", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.e(TAG + "  on", "newPlayerJoined  " + args[0]);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_playerjointtable));
                            JSONObject json = new JSONObject(args[0] + "");
                            JSONObject jsonPlayer = json.getJSONObject("otherPlayers");
                            Json_Player_Info = jsonPlayer;
                            ArrayList<User> arrotheruser = new ArrayList<>();
                            Iterator<String> iterator = jsonPlayer.keys();
                            while (iterator.hasNext()) {
                                String key = iterator.next();
                                JSONObject jObje_Otherplayer = new JSONObject(jsonPlayer.optString(key));
                                User otheruser = new User();
                                otheruser.setId(jObje_Otherplayer.getString("id"));
                                otheruser.setSlot(jObje_Otherplayer.getString("slot"));
                                otheruser.setActive(jObje_Otherplayer.getString("active"));
                                PlayerInfo playerin = new PlayerInfo();
                                JSONObject jsonObject = new JSONObject(jObje_Otherplayer.getString("playerInfo"));
                                playerin.setUserId(jsonObject.getString("_id"));
                                playerin.setUserName(jsonObject.getString("displayName"));
                                playerin.setChips(jsonObject.getString("chips"));
                                playerin.setProfilePics(jsonObject.getString("profilePic"));
                                otheruser.setPlayerinfo(playerin);
                                arrotheruser.add(otheruser);

                                if (otheruser.getPlayerinfo().getUserId().equals(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID))) {
                                    txtPlay6Id.setText(jObje_Otherplayer.getString("id"));
                                    txtPlay6Rs.setText(otheruser.getPlayerinfo().getChips());
                                    convertedChipformat = Math.round(Double.parseDouble(otheruser.getPlayerinfo().getChips())  * 100.0) / 100.0;

                                    txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                    SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, otheruser.getPlayerinfo().getChips());

                                    txtPlay6.setText(otheruser.getPlayerinfo().getUserName());
                                    if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                    if (otheruser.getSlot().equals("slot5")) {
                                        txtPlay6Slot.setText("slot5");
                                        txtPlay5Slot.setText("slot4");
                                        txtPlay4Slot.setText("slot3");
                                        txtPlay3Slot.setText("slot2");
                                        txtPlay2Slot.setText("slot1");
                                    } else if (otheruser.getSlot().equals("slot4")) {
                                        txtPlay6Slot.setText("slot4");
                                        txtPlay5Slot.setText("slot3");
                                        txtPlay4Slot.setText("slot2");
                                        txtPlay3Slot.setText("slot1");
                                        txtPlay2Slot.setText("slot5");
                                    } else if (otheruser.getSlot().equals("slot3")) {
                                        txtPlay6Slot.setText("slot3");
                                        txtPlay5Slot.setText("slot2");
                                        txtPlay4Slot.setText("slot1");
                                        txtPlay3Slot.setText("slot5");
                                        txtPlay2Slot.setText("slot4");
                                    } else if (otheruser.getSlot().equals("slot2")) {
                                        txtPlay6Slot.setText("slot2");
                                        txtPlay5Slot.setText("slot1");
                                        txtPlay4Slot.setText("slot5");
                                        txtPlay3Slot.setText("slot4");
                                        txtPlay2Slot.setText("slot3");
                                    } else if (otheruser.getSlot().equals("slot1")) {
                                        txtPlay6Slot.setText("slot1");
                                        txtPlay5Slot.setText("slot5");
                                        txtPlay4Slot.setText("slot4");
                                        txtPlay3Slot.setText("slot3");
                                        txtPlay2Slot.setText("slot2");
                                    }

                                } else {
                                    if (otheruser.getSlot().equals(txtPlay2Slot.getText().toString())) {

                                        txtPlay2.setText(otheruser.getPlayerinfo().getUserName());
                                        llPlayer2WaitPlayer.setVisibility(View.GONE);
                                        llPlayer2.setVisibility(View.VISIBLE);
                                        llPlayer2.setAlpha(1f);
//                                        txtPl2ChalBlind.setText("New");

                                        txtPlay2Id.setText(jObje_Otherplayer.getString("id"));
                                        txtPlay2Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay3Slot.getText().toString())) {
                                        txtPlay3.setText(otheruser.getPlayerinfo().getUserName());

                                        llPlayer3WaitPlayer.setVisibility(View.GONE);
                                        llPlayer3.setVisibility(View.VISIBLE);
                                        llPlayer3.setAlpha(1f);
//                                        txtPl3ChalBlind.setText("New");

//                                        llCoinTable3.setVisibility(View.VISIBLE);
                                        txtPlay3Id.setText(jObje_Otherplayer.getString("id"));
                                        txtPlay3Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay4Slot.getText().toString())) {
                                        llPlayer4WaitPlayer.setVisibility(View.GONE);
                                        llPlayer4.setAlpha(1f);
                                        llPlayer4.setVisibility(View.VISIBLE);
                                        txtPlay4.setText(otheruser.getPlayerinfo().getUserName());

                                        txtPlay4Id.setText(jObje_Otherplayer.getString("id"));
//                                        txtPl4ChalBlind.setText("New");
                                        txtPlay4Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay5Slot.getText().toString())) {
                                        llPlayer5.setAlpha(1f);

                                        llPlayer5WaitPlayer.setVisibility(View.GONE);
                                        llPlayer5.setVisibility(View.VISIBLE);
//                                        txtPl5ChalBlind.setText("New");
                                        txtPlay5.setText(otheruser.getPlayerinfo().getUserName());
                                        txtPlay5Id.setText(jObje_Otherplayer.getString("id"));
                                        txtPlay5Rs.setText(otheruser.getPlayerinfo().getChips());
                                    } else if (otheruser.getSlot().equals(txtPlay6Slot.getText().toString())) {
                                        txtPlay6.setText(otheruser.getPlayerinfo().getUserName());

                                        txtPlay6Id.setText(jObje_Otherplayer.getString("id"));
                                        llPlayer6.setAlpha(1f);
//                                        txtPl6ChalBlind.setText("new");
                                        txtPlay6Rs.setText(otheruser.getPlayerinfo().getChips());
                                        convertedChipformat = Math.round(Double.parseDouble(otheruser.getPlayerinfo().getChips())  * 100.0) / 100.0;

                                        txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                        SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, otheruser.getPlayerinfo().getChips());

                                    }
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


        mSocket.on("playerLeft", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.e(TAG + "  on", " playerLeft " + args[0]);

                String message = args[0].toString();

                int maxLogSize = 1000;
                for (int i = 0; i <= message.length() / maxLogSize; i++) {
                    int start = i * maxLogSize;
                    int end = (i + 1) * maxLogSize;
                    end = end > message.length() ? message.length() : end;
                    Log.e("responded slide show", message.substring(start, end));
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String leftaction = "";
                        try {
                            JSONObject json = new JSONObject(args[0] + "");
                            JSONObject jsonPlayer = json.getJSONObject("removedPlayer");
                            ArrayList<User> arrotheruser = new ArrayList<>();
                            User otheruser = new User();
                            otheruser.setId(jsonPlayer.getString("id"));
                            otheruser.setSlot(jsonPlayer.getString("slot"));
                            otheruser.setActive(jsonPlayer.getString("active"));


                            JSONObject jjbet = json.getJSONObject("bet");
                            leftaction = jjbet.getString("lastAction");
                            PlayerInfo playerin = new PlayerInfo();
                            JSONObject jsonObject = new JSONObject(jsonPlayer.getString("playerInfo"));
                            playerin.setUserId(jsonObject.getString("_id"));
                            playerin.setUserName(jsonObject.getString("displayName"));
                            playerin.setChips(jsonObject.getString("chips"));
                            playerin.setProfilePics(jsonObject.getString("profilePic"));

                            otheruser.setPlayerinfo(playerin);

                            arrotheruser.add(otheruser);

                            if (otheruser.getSlot().equals(txtPlay2Slot.getText().toString())) {
                                txtPlay2Rs.setText("");
                                txtPl2ChalBlind.setText(leftaction);
                                llPlayer2.setAlpha(0.5f);

                            } else if (otheruser.getSlot().equals(txtPlay3Slot.getText().toString())) {
                                txtPl3ChalBlind.setText(leftaction);
                                txtPlay3Rs.setText("");
                                llPlayer3.setAlpha(0.5f);

                            } else if (otheruser.getSlot().equals(txtPlay4Slot.getText().toString())) {
                                txtPl4ChalBlind.setText(leftaction);
                                txtPlay4Rs.setText("");
                                llPlayer4.setAlpha(0.5f);

                            } else if (otheruser.getSlot().equals(txtPlay5Slot.getText().toString())) {
                                txtPl5ChalBlind.setText(leftaction);
                                txtPlay5Rs.setText("");
                                llPlayer5.setAlpha(0.5f);

                            } else if (otheruser.getSlot().equals(txtPlay6Slot.getText().toString())) {
                                txtPlay6.setText(leftaction);
                                txtPlay6Rs.setText("");
                                llPlayer6.setAlpha(0.5f);

                            }


                            jsonPlayer = json.getJSONObject("players");
                            Json_Player_Info = jsonPlayer;
                            arrotheruser = new ArrayList<>();
                            Iterator<String> iterator = jsonPlayer.keys();
                            CurrentPlayerCount = 0;
                            isPackedTrack = 0 ;
                            isSlideshow = 0;


                            int PlayerSize = 0;


                           /* while (iterator.hasNext()) {
                                PlayerSize++;
                                iterator.next();
                            }*/

                            Log.e("player sizeee", PlayerSize + "    ");

                            while (iterator.hasNext()) {
                                String key = iterator.next();

                                JSONObject jObje_Otherplayer = new JSONObject(jsonPlayer.optString(key));

                                otheruser = new User();
                                otheruser.setId(jObje_Otherplayer.getString("id"));
                                otheruser.setSlot(jObje_Otherplayer.getString("slot"));
                                otheruser.setActive(jObje_Otherplayer.getString("active"));
                                playerin = new PlayerInfo();
                                jsonObject = new JSONObject(jObje_Otherplayer.getString("playerInfo"));
                                playerin.setUserId(jsonObject.getString("_id"));
                                playerin.setUserName(jsonObject.getString("displayName"));
                                playerin.setChips(jsonObject.getString("chips"));
                                playerin.setProfilePics(jsonObject.getString("profilePic"));
                                otheruser.setPlayerinfo(playerin);
                                arrotheruser.add(otheruser);

                                if (jObje_Otherplayer.getString("active").equals("true")) {
                                    if (jObje_Otherplayer.getString("packed").equals("false"))
                                        CurrentPlayerCount++;
                                    if (otheruser.getPlayerinfo().getUserId().equals(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID))) {

                                        if (jObje_Otherplayer.getString("active").equals("true"))

                                            if (jObje_Otherplayer.has("turn")) {
                                                Log.e("turn", jObje_Otherplayer.getString("turn"));
                                                Myturn = jObje_Otherplayer.getString("turn");
                                            }
//                                        txtPlay6Rs.setText(jsonObject.getString("chips"));
                                        convertedChipformat = Math.round(Double.parseDouble(jsonObject.getString("chips"))  * 100.0) / 100.0;

                                        txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                        SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, jsonObject.getString("chips"));


                                        if (Myturn.equals("true")) {

                                            Log.e("turn ture", "my turnnnn : " + otheruser.getPlayerinfo().getDisplayName());

                                            StopTimer = 0;
//                                            StartTimer(Progresspl6);
                                            Functions.PlayVibrator(Table6_Activity.this);


                                            if (jObje_Otherplayer.getString("isSideShowAvailable").equals("true")) {

                                                isSlideshow = 1;
                                            } else {
                                                txtPlayShowSlideshow.setVisibility(View.GONE);
                                                txtPlayShow.setVisibility(View.INVISIBLE);
//                                                txtPlayShowSlideshow.setAlpha(0.5f);
//                                                txtPlayShowSlideshow.setEnabled(false);

                                            }
                                            if (MaximumBlindcount ==  Float.parseFloat(SelectTable.getMaximumblind())) {
                                                txtCardSee.performClick();
                                            }
                                            llViewBottomBar.setVisibility(View.VISIBLE);
                                            llViewBottomBarInfoView.setVisibility(View.GONE);

                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = "My Turn";
                                            Functions.PlayVibrator(Table6_Activity.this);
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    StartTimer(Progresspl6);
//                                                    Functions.ShowPlayerTurn(llPlayer6ShowAnim, "true");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);

                                        } else {
                                            llViewBottomBar.setVisibility(View.GONE);
                                            llViewBottomBarInfoView.setVisibility(View.VISIBLE);
                                        }


                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay2Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            isPackedTrack++ ;

                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl2);

//                                                    Functions.ShowPlayerTurn(llPlayer2ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);

                                        }
                                        llPlayer2.setVisibility(View.GONE);
                                        txtPlay2Rs.setText(jsonObject.getString("chips"));
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay3Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            isPackedTrack++ ;
                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl3);
//                                                    Functions.ShowPlayerTurn(llPlayer3ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);
                                        }
                                        llPlayer3.setVisibility(View.GONE);

                                        txtPlay3Rs.setText(jsonObject.getString("chips"));
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay4Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
                                            isPackedTrack++ ;

                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {


                                                    StartTimer(Progresspl4);
//                                                    Functions.ShowPlayerTurn(llPlayer3ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);
                                        }
                                        llPlayer4.setVisibility(View.GONE);
                                        txtPlay4Rs.setText(jsonObject.getString("chips"));
                                    } else if (otheruser.getPlayerinfo().getUserId().equals(txtPlay5Id.getText().toString())) {
                                        if (jObje_Otherplayer.getString("turn").equals("true")) {
//                                            llPlayer5.setBackgroundResource(R.drawable.player_back_select);

                                   /*         Functions.Stop_Player_Turn = 0;
                                            Functions.ShowPlayerTurn(llPlayer5,"false");*/
                                            isPackedTrack++ ;

                                            Functions.Stop_Player_Turn = 1;
                                            NotiMessage = jsonObject.getString("displayName") + " Turn";
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                               /*     llPlayer6.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer5.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer4.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer3.setBackgroundColor(R.drawable.player_back);
                                                    llPlayer2.setBackgroundColor(R.drawable.player_back);*/

                                                    StartTimer(Progresspl5);
//                                                    Functions.ShowPlayerTurn(llPlayer5ShowAnim, "false");
//                                                    Functions.Stop_Player_Turn = 0;
                                                }
                                            }, 500);
                                        }
                                        llPlayer5.setVisibility(View.GONE);
                                        txtPlay5Rs.setText(jsonObject.getString("chips"));
                                    }
                                    if(isPackedTrack == CurrentPlayerCount -1){
                                        txtCardSee.performClick();
                                    }


                                }
                                final JSONObject jsontable = new JSONObject(json.getString("table"));
                                Json_Table_Info = jsontable;
                               /* new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            txtTableAmount.setText(jsontable.getString("amount"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 400);*/


                                if (Float.parseFloat(jsontable.getString("amount")) > Float.parseFloat(SelectTable.getPotLimit()))
                                    txtPlayShow.performClick();
                                lastbet = jsontable.getString("lastBet");

                                txtPlayRs.setText(lastbet);
                                txtPlayBlind.setText("Blind\n" +  String.valueOf(Math.round(Double.parseDouble(lastbet)  * 100.0) / 100.0));
                                txtPlayChaal.setText("Chaal\n" + String.valueOf(Math.round(Double.parseDouble(lastbet)  * 100.0) / 100.0));
                                txtPlayMinus.setAlpha(0.5f);
                                txtPlayMinus.setEnabled(false);
                                txtPlayPlue.setAlpha(1f);
                                txtPlayPlue.setEnabled(true);

                            }
                            if (CurrentPlayerCount == 2) {
                                txtPlayShow.setVisibility(View.VISIBLE);
                                txtPlayShowSlideshow.setVisibility(View.GONE);
//                                txtPlayShowSlideshow.setAlpha(0.5f);
//                                txtPlayShowSlideshow.setEnabled(false);

                            } else {
                                txtPlayShow.setVisibility(View.INVISIBLE);
                                if (isSlideshow == 1) {
                                    txtPlayShowSlideshow.setVisibility(View.VISIBLE);
//                                    txtPlayShowSlideshow.setAlpha(1f);
//                                    txtPlayShowSlideshow.setEnabled(true);
                                } else {
                                    txtPlayShowSlideshow.setVisibility(View.GONE);
//                                    txtPlayShowSlideshow.setAlpha(0.5f);
//                                    txtPlayShowSlideshow.setEnabled(false);
                                }
                            }


                            txtPlayShow.setEnabled(true);
                            txtPlayPlue.setEnabled(true);
                            txtPlayMinus.setEnabled(false);
                            txtPlayPack.setEnabled(true);
                            txtPlayBlind.setEnabled(true);
//                            txtPlayShowSlideshow.setEnabled(true);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_playerleft));


                    }
                });

            }
        });


        mSocket.on("tableJoined", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                Log.e(TAG + "  on", " tableJoined " + args[0]);

                new ClientidUpdae_Asynctask().execute();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(args[0] + "");
                            TableJoined table = new TableJoined();


                            table.setId(json.getString("id"));
                            table.setSlot(json.getString("slot"));

                            if (table.getSlot().equals("slot5")) {
                                txtPlay6Slot.setText("slot5");
                                txtPlay5Slot.setText("slot4");
                                txtPlay4Slot.setText("slot3");
                                txtPlay3Slot.setText("slot2");
                                txtPlay2Slot.setText("slot1");
                            } else if (table.getSlot().equals("slot4")) {
                                txtPlay6Slot.setText("slot4");
                                txtPlay5Slot.setText("slot3");
                                txtPlay4Slot.setText("slot2");
                                txtPlay3Slot.setText("slot1");
                                txtPlay2Slot.setText("slot5");
                            } else if (table.getSlot().equals("slot3")) {
                                txtPlay6Slot.setText("slot3");
                                txtPlay5Slot.setText("slot2");
                                txtPlay4Slot.setText("slot1");
                                txtPlay3Slot.setText("slot5");
                                txtPlay2Slot.setText("slot4");
                            } else if (table.getSlot().equals("slot2")) {
                                txtPlay6Slot.setText("slot2");
                                txtPlay5Slot.setText("slot1");
                                txtPlay4Slot.setText("slot5");
                                txtPlay3Slot.setText("slot4");
                                txtPlay2Slot.setText("slot3");
                            } else if (table.getSlot().equals("slot1")) {
                                txtPlay6Slot.setText("slot1");
                                txtPlay5Slot.setText("slot5");
                                txtPlay4Slot.setText("slot4");
                                txtPlay3Slot.setText("slot3");
                                txtPlay2Slot.setText("slot2");
                            }

                            JSONObject jsonPlayer = json.getJSONObject("otherPlayers");
                            Json_Player_Info = jsonPlayer;
                            ArrayList<User> arrotheruser = new ArrayList<>();
                            Iterator<String> iterator = jsonPlayer.keys();
                            llPlayer2WaitPlayer.setVisibility(View.VISIBLE);
                            llPlayer3WaitPlayer.setVisibility(View.VISIBLE);
                            llPlayer4WaitPlayer.setVisibility(View.VISIBLE);
                            llPlayer5WaitPlayer.setVisibility(View.VISIBLE);
                            llPlayer2.setVisibility(View.GONE);
                            llPlayer3.setVisibility(View.GONE);
                            llPlayer4.setVisibility(View.GONE);
                            llPlayer5.setVisibility(View.GONE);


                            while (iterator.hasNext()) {
                                String key = iterator.next();
                                JSONObject jObje_Otherplayer = new JSONObject(jsonPlayer.optString(key));
                                User otheruser = new User();
                                otheruser.setId(jObje_Otherplayer.getString("id"));
                                otheruser.setSlot(jObje_Otherplayer.getString("slot"));
                                otheruser.setActive(jObje_Otherplayer.getString("active"));
                                PlayerInfo playerin = new PlayerInfo();
                                JSONObject jsonObject = new JSONObject(jObje_Otherplayer.getString("playerInfo"));
                                playerin.setUserId(jsonObject.getString("_id"));
                                playerin.setUserName(jsonObject.getString("displayName"));
                                playerin.setChips(jsonObject.getString("chips"));
                                playerin.setProfilePics(jsonObject.getString("profilePic"));
                                otheruser.setPlayerinfo(playerin);
                                arrotheruser.add(otheruser);


                                Log.e("profile pic", jsonObject.getString("profilePic"));

                                if (otheruser.getPlayerinfo().getUserId().equals(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID))) {
                                    txtPlay6Id.setText(jObje_Otherplayer.getString("id"));
//                                    txtPlay6Rs.setText(otheruser.getPlayerinfo().getChips());
                                    convertedChipformat = Math.round(Double.parseDouble(otheruser.getPlayerinfo().getChips())  * 100.0) / 100.0;

                                    txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                    SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, otheruser.getPlayerinfo().getChips());

                                    txtPlay6.setText(otheruser.getPlayerinfo().getUserName());
                                    Log.e("profilePic nuskds.", otheruser.getPlayerinfo().getProfilePics());
                                    if (!otheruser.getPlayerinfo().getProfilePics().trim().equals("")) {


                                    }

                                    if (otheruser.getSlot().equals("slot5")) {
                                        txtPlay6Slot.setText("slot5");
                                        txtPlay5Slot.setText("slot4");
                                        txtPlay4Slot.setText("slot3");
                                        txtPlay3Slot.setText("slot2");
                                        txtPlay2Slot.setText("slot1");
                                    } else if (otheruser.getSlot().equals("slot4")) {
                                        txtPlay6Slot.setText("slot4");
                                        txtPlay5Slot.setText("slot3");
                                        txtPlay4Slot.setText("slot2");
                                        txtPlay3Slot.setText("slot1");
                                        txtPlay2Slot.setText("slot5");
                                    } else if (otheruser.getSlot().equals("slot3")) {
                                        txtPlay6Slot.setText("slot3");
                                        txtPlay5Slot.setText("slot2");
                                        txtPlay4Slot.setText("slot1");
                                        txtPlay3Slot.setText("slot5");
                                        txtPlay2Slot.setText("slot4");
                                    } else if (otheruser.getSlot().equals("slot2")) {
                                        txtPlay6Slot.setText("slot2");
                                        txtPlay5Slot.setText("slot1");
                                        txtPlay4Slot.setText("slot5");
                                        txtPlay3Slot.setText("slot4");
                                        txtPlay2Slot.setText("slot3");
                                    } else if (otheruser.getSlot().equals("slot1")) {
                                        txtPlay6Slot.setText("slot1");
                                        txtPlay5Slot.setText("slot5");
                                        txtPlay4Slot.setText("slot4");
                                        txtPlay3Slot.setText("slot3");
                                        txtPlay2Slot.setText("slot2");
                                    }

                                } else {
                                    if (otheruser.getSlot().equals(txtPlay2Slot.getText().toString())) {
                                        txtPlay2Id.setText(jObje_Otherplayer.getString("id"));
                                        txtPlay2Rs.setText(otheruser.getPlayerinfo().getChips());
                                        txtPlay2.setText(otheruser.getPlayerinfo().getUserName());

                                        llPlayer2WaitPlayer.setVisibility(View.GONE);
                                        llPlayer2.setVisibility(View.VISIBLE);
                                    } else if (otheruser.getSlot().equals(txtPlay3Slot.getText().toString())) {
                                        txtPlay3.setText(otheruser.getPlayerinfo().getUserName());
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        txtPlay3Id.setText(jObje_Otherplayer.getString("id"));
                                        txtPlay3Rs.setText(otheruser.getPlayerinfo().getChips());
                                        llPlayer3WaitPlayer.setVisibility(View.GONE);
                                        llPlayer3.setVisibility(View.VISIBLE);
                                    } else if (otheruser.getSlot().equals(txtPlay4Slot.getText().toString())) {

                                        txtPlay4.setText(otheruser.getPlayerinfo().getUserName());
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        txtPlay4Id.setText(jObje_Otherplayer.getString("id"));
                                        txtPlay4Rs.setText(otheruser.getPlayerinfo().getChips());
                                        llPlayer4WaitPlayer.setVisibility(View.GONE);
                                        llPlayer4.setVisibility(View.VISIBLE);
                                    } else if (otheruser.getSlot().equals(txtPlay5Slot.getText().toString())) {

                                        txtPlay5.setText(otheruser.getPlayerinfo().getUserName());
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        txtPlay5Id.setText(jObje_Otherplayer.getString("id"));
                                        txtPlay5Rs.setText(otheruser.getPlayerinfo().getChips());
                                        llPlayer5WaitPlayer.setVisibility(View.GONE);
                                        llPlayer5.setVisibility(View.VISIBLE);
                                    } else if (otheruser.getSlot().equals(txtPlay6Slot.getText().toString())) {
                                        txtPlay6.setText(otheruser.getPlayerinfo().getUserName());
                                        if (!otheruser.getPlayerinfo().getProfilePics().trim().equals(""))

                                        txtPlay6Id.setText(jObje_Otherplayer.getString("id"));
//                                        txtPlay6Rs.setText(otheruser.getPlayerinfo().getChips());
                                        convertedChipformat = Math.round(Double.parseDouble(otheruser.getPlayerinfo().getChips())  * 100.0) / 100.0;

                                        txtPlay6Rs.setText(String.valueOf(convertedChipformat));
                                        SharedPrefs.save(Table6_Activity.this, SharedPrefs.CHIPS, otheruser.getPlayerinfo().getChips());

                                    }
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("errror", e.getMessage());
                        }

                    }
                });

            }
        });

        JSONObject json_userinfo = new JSONObject();
        try {
            json_userinfo.put("userId", SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID));
            json_userinfo.put("tableId", table_id);
            json_userinfo.put("userName", SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USERNAME));
            json_userinfo.put("clientId", SharedPrefs.getString(Table6_Activity.this, SharedPrefs.CLIENT_ID));
            json_userinfo.put("chips", Float.parseFloat(SharedPrefs.getString(Table6_Activity.this, SharedPrefs.CHIPS, "0")));
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
        Log.e(TAG + "emit ", "joinTable " + json_userinfo.toString());
        Log.d("emitShow ", "joinTable " + json_userinfo.toString());
        mSocket.emit("joinTable", json_userinfo, new Emitter.Listener() {
            @Override
            public void call(Object... args) {

            }
        });


    }


    void CloseGame() {
        GameTimerStop = 1;

        if (dNetWork.isShowing())
            dNetWork.dismiss();
        if (timer != null)
            timer.cancel();
        txtGameWait.setVisibility(View.GONE);
        rlGameWait.setVisibility(View.GONE);
        Functions.StopMusic();
//        txtPlayPack.performClick();
        StopTimer = 1;
//        Functions.StopMusic();

//        mSocket.off();
//        mSocket.off("startNew");
//        mSocket.off("tableJoined");

        mSocket.off();
        mSocket.disconnect();
        overridePendingTransition(R.anim.intent_side_left, R.anim.intent_slide_to_right);
        finish();
    }

    @Override
    public void onBackPressed() {

        dCloseExit.show();

    }

    private void StartTimerForProgress(final ProgressBar Progresssbarrr) {

        Log.e("start timer", "start timerrrr.............." + Myturn);
        Progresspl2.setVisibility(View.GONE);
        Progresspl3.setVisibility(View.GONE);
        Progresspl4.setVisibility(View.GONE);
        Progresspl5.setVisibility(View.GONE);
        Progresspl6.setVisibility(View.GONE);
        Progresssbarrr.setVisibility(View.VISIBLE);

        process = 0;
        pStatus = 0;

        if (Timerrr != null) {
            Timerrr.cancel();
        }

        Timerrr = new CountDownTimer(21000, 1000) {

            public void onTick(long millisUntilFinished) {
                process++;
                int seconddd =1000 - (int) (millisUntilFinished / 100);
                Progresssbarrr.setProgress(process);
            }

            public void onFinish() {
                if (MyturnSideShow.equals("true") && Progresssbarrr == Progresspl6) {
                    if (StopTimerSideShow != 1) {
                        txtSlideDecline.performClick();
                    }
                }
            }

        };

        Timerrr.start();


    }


    private void StartTimer(final ProgressBar Progresssbarrr) {
        Log.e("start timer", "start timerrrr.............." + Myturn);
        Progresspl2.setVisibility(View.GONE);
        Progresspl3.setVisibility(View.GONE);
        Progresspl4.setVisibility(View.GONE);
        Progresspl5.setVisibility(View.GONE);
        Progresspl6.setVisibility(View.GONE);
        Progresssbarrr.setVisibility(View.VISIBLE);

        process = 0;
        pStatus = 0;

        if (Timerrr != null) {
            Timerrr.cancel();
        }

        Timerrr = new CountDownTimer(21000, 1000) {

            public void onTick(long millisUntilFinished) {
                process++;
                int seconddd = (int) 1000 - (int) (millisUntilFinished / 100);
                Progresssbarrr.setProgress(process);
            }

            public void onFinish() {
                if (Myturn.equals("true") && Progresssbarrr == Progresspl6) {
                    if (StopTimer != 1) {
                        CloseGame();
                    }
                }
            }

        };

        Timerrr.start();





/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= 100) {

                    if (Myturn.equals("true")) {
                        if (StopTimer == 1) {
                            break;
                        }
                    }


                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            Progresssbarrr.setProgress(pStatus);


                            if (Myturn.equals("true")) {
                                if (pStatus == 99) {
                                    Functions.StopMusic();
                                    txtPlayPack.performLongClick();

                                    StopTimer = 1;
                                    Functions.StopMusic();
                                    mSocket.off();
                                    mSocket.off("startNew");
                                    mSocket.off("tableJoined");
                                    mSocket.disconnect();
                                    overridePendingTransition(R.anim.intent_side_left, R.anim.intent_slide_to_right);
                                    finish();

                                }
                            }

                        }
                    });


                    try {
                        if (Myturn.equals("true")) {
                            Thread.sleep(500);
                        } else {
                            Thread.sleep(560);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();
*/

    }

    private void BindView() {
        imggame_start_2 = findViewById(R.id.imggame_start_2);
        rlGameWait = findViewById(R.id.rlGameWait);
        txtGameWait = findViewById(R.id.txtGameWait);
        rlTable = findViewById(R.id.rlTable);
        txtTips = findViewById(R.id.txtTips);
        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                onBackPressed();
            }
        });
        anim_round = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotateee_ani);
        imgpl2Gift = findViewById(R.id.imgpl2Gift);
        imgpl3Gift = findViewById(R.id.imgpl3Gift);
        imgpl4Gift = findViewById(R.id.imgpl4Gift);
        imgpl5Gift = findViewById(R.id.imgpl5Gift);
        imgpl6Gift = findViewById(R.id.imgpl6Gift);
        imgWinningImage2 = findViewById(R.id.imgWinningImage2);
        imgWinningImage3 = findViewById(R.id.imgWinningImage3);
        imgWinningImage4 = findViewById(R.id.imgWinningImage4);
        imgWinningImage5 = findViewById(R.id.imgWinningImage5);
        imgWinningImage6 = findViewById(R.id.imgWinningImage6);

        anim_round2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.round);
        imgWinningImage2.startAnimation(anim_round2);

        anim_round3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.round);
        imgWinningImage3.startAnimation(anim_round3);

        anim_round4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.round);
        imgWinningImage4.startAnimation(anim_round4);

        anim_round5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.round);
        imgWinningImage5.startAnimation(anim_round5);

        anim_round6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.round);

        anim_startGame = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.round_fast);


        /*anim_round4.setInterpolator(new LinearInterpolator());

        RotateAnimation rotateAnimation1 = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation1.setInterpolator(new LinearInterpolator());
        rotateAnimation1.setDuration(5000);
        rotateAnimation1.setRepeatCount(Animation.INFINITE);*/
        imgWinningImage6.startAnimation(anim_round6);


        imgWinningImage2.setVisibility(View.GONE);
        imgWinningImage3.setVisibility(View.GONE);
        imgWinningImage4.setVisibility(View.GONE);
        imgWinningImage5.setVisibility(View.GONE);
        imgWinningImage6.setVisibility(View.GONE);


       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgWinningImage2.setVisibility(View.VISIBLE);
                imgWinningImage3.setVisibility(View.VISIBLE);
                imgWinningImage4.setVisibility(View.VISIBLE);
                imgWinningImage5.setVisibility(View.VISIBLE);

                imgWinningImage6.startAnimation(anim_round6);
                imgWinningImage6.setVisibility(View.VISIBLE);
            }
        }, 3000);*/


        imgpl2Gift2 = findViewById(R.id.imgpl2Gift2);
        imgpl3Gift2 = findViewById(R.id.imgpl3Gift2);
        imgpl4Gift2 = findViewById(R.id.imgpl4Gift2);
        imgpl5Gift2 = findViewById(R.id.imgpl5Gift2);
        imgpl6Gift2 = findViewById(R.id.imgpl6Gift2);


        imgpl2Gift3 = findViewById(R.id.imgpl2Gift3);
        imgpl3Gift3 = findViewById(R.id.imgpl3Gift3);
        imgpl4Gift3 = findViewById(R.id.imgpl4Gift3);
        imgpl5Gift3 = findViewById(R.id.imgpl5Gift3);
        imgpl6Gift3 = findViewById(R.id.imgpl6Gift3);


        imgpl2Gift4 = findViewById(R.id.imgpl2Gift4);
        imgpl3Gift4 = findViewById(R.id.imgpl3Gift4);
        imgpl4Gift4 = findViewById(R.id.imgpl4Gift4);
        imgpl5Gift4 = findViewById(R.id.imgpl5Gift4);
        imgpl6Gift4 = findViewById(R.id.imgpl6Gift4);


        imgpl2Gift5 = findViewById(R.id.imgpl2Gift5);
        imgpl3Gift5 = findViewById(R.id.imgpl3Gift5);
        imgpl4Gift5 = findViewById(R.id.imgpl4Gift5);
        imgpl5Gift5 = findViewById(R.id.imgpl5Gift5);
        imgpl6Gift5 = findViewById(R.id.imgpl6Gift5);


        txtPl2ChalBlind = findViewById(R.id.txtPl2ChalBlind);
        txtPl3ChalBlind = findViewById(R.id.txtPl3ChalBlind);
        txtPl4ChalBlind = findViewById(R.id.txtPl4ChalBlind);
        txtPl5ChalBlind = findViewById(R.id.txtPl5ChalBlind);
        txtPl2ChalBlind.setText("Blind");
        txtPl3ChalBlind.setText("Blind");
        txtPl4ChalBlind.setText("Blind");
        txtPl5ChalBlind.setText("Blind");

        Progresspl2 = findViewById(R.id.Progresspl2);
        Progresspl3 = findViewById(R.id.Progresspl3);
        Progresspl4 = findViewById(R.id.Progresspl4);
        Progresspl5 = findViewById(R.id.Progresspl5);
        Progresspl6 = findViewById(R.id.Progresspl6);
        Progresspl2.setMax(20);
        Progresspl3.setMax(20);
        Progresspl4.setMax(20);
        Progresspl5.setMax(20);
        Progresspl6.setMax(20);

        Progresspl2.setVisibility(View.GONE);
        Progresspl3.setVisibility(View.GONE);
        Progresspl4.setVisibility(View.GONE);
        Progresspl5.setVisibility(View.GONE);
        Progresspl6.setVisibility(View.GONE);

        imgGift2 = findViewById(R.id.imgGift2);
        imgGift3 = findViewById(R.id.imgGift3);
        imgGift4 = findViewById(R.id.imgGift4);
        imgGift5 = findViewById(R.id.imgGift5);
        imgGift6 = findViewById(R.id.imgGift6);


        txtReplaceCards = findViewById(R.id.txtReplaceCards);
        imgPl2Pic = findViewById(R.id.imgPl2Pic);
        imgPl3Pic = findViewById(R.id.imgPl3Pic);
        imgPl4Pic = findViewById(R.id.imgPl4Pic);
        imgPl5Pic = findViewById(R.id.imgPl5Pic);
        imgPl6Pic = findViewById(R.id.imgPl6Pic);


        llPl6CardDisplay = findViewById(R.id.llPl6CardDisplay);
        imgInformation = findViewById(R.id.imgInformation);
        imgSetting = findViewById(R.id.imgSetting);
        llTimerForPlayer = findViewById(R.id.llTimerForPlayer);
        txtTimerForPlayer = findViewById(R.id.txtTimerForPlayer);

        anim_pl5_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl5);
        anim_pl6_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl6);
        anim_pl5_2_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl5_2);
        anim_pl6_2_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl6_2);
        anim_pl5_3_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl5_3);
        anim_pl6_3_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl6_3);

        anim_pl2_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl2);
        anim_pl3_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl3);
        anim_pl4_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl4);

        anim_pl2_2_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl2_2);
        anim_pl3_2_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl3_2);
        anim_pl4_2_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl4_2);

        anim_pl2_3_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl2_2);
        anim_pl3_3_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl3_2);
        anim_pl4_3_tra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dem_pl4_2);


        txtshowallcards = findViewById(R.id.txtshowallcards);
        if (SharedPrefs.getString(Table6_Activity.this, SharedPrefs.ROLE, "").equals("adminuser")) {
            txtshowallcards.setVisibility(View.VISIBLE);
            txtReplaceCards.setVisibility(View.VISIBLE);
            txtReplaceCards.setAlpha(0.5f);
            txtshowallcards.setAlpha(0.5f);
            txtshowallcards.setEnabled(false);
            txtReplaceCards.setEnabled(false);
        } else {
            txtshowallcards.setVisibility(View.INVISIBLE);
            txtReplaceCards.setVisibility(View.INVISIBLE);
        }
        Rlpl5Card1 = findViewById(R.id.Rlpl5Card1);
        Rlpl5Card2 = findViewById(R.id.Rlpl5Card2);
        Rlpl5Card3 = findViewById(R.id.Rlpl5Card3);
        Rlpl6Card1 = findViewById(R.id.Rlpl6Card1);
        Rlpl6Card2 = findViewById(R.id.Rlpl6Card2);
        Rlpl6Card3 = findViewById(R.id.Rlpl6Card3);
        Rlpl2Card1 = findViewById(R.id.Rlpl2Card1);
        Rlpl2Card2 = findViewById(R.id.Rlpl2Card2);
        Rlpl2Card3 = findViewById(R.id.Rlpl2Card3);
        Rlpl3Card1 = findViewById(R.id.Rlpl3Card1);
        Rlpl3Card2 = findViewById(R.id.Rlpl3Card2);
        Rlpl3Card3 = findViewById(R.id.Rlpl3Card3);
        Rlpl4Card1 = findViewById(R.id.Rlpl4Card1);
        Rlpl4Card2 = findViewById(R.id.Rlpl4Card2);
        Rlpl4Card3 = findViewById(R.id.Rlpl4Card3);


        llPlayer2WaitPlayer = findViewById(R.id.llPlayer2WaitPlayer);
        llPlayer3WaitPlayer = findViewById(R.id.llPlayer3WaitPlayer);
        llPlayer4WaitPlayer = findViewById(R.id.llPlayer4WaitPlayer);
        llPlayer5WaitPlayer = findViewById(R.id.llPlayer5WaitPlayer);

        ProgressTimer = findViewById(R.id.ProgressTimer);
        llViewBottomBarInfoView = findViewById(R.id.llViewBottomBarInfoView);
        txtBottomBootValue = findViewById(R.id.txtBottomBootValue);
        txtBottomPotLimit = findViewById(R.id.txtBottomPotLimit);
        txtBottomMaxValue = findViewById(R.id.txtBottomMaxValue);

        txtBottomPotLimit.setText("Port Limit : " + SelectTable.getPotLimit());
        txtBottomBootValue.setText("Boot Value : " + SelectTable.getBoot());
        txtBottomMaxValue.setText("Max Bet : " + SelectTable.getMaxbet());


        txtCoinTable2 = findViewById(R.id.txtCoinTable2);
        txtCoinTable3 = findViewById(R.id.txtCoinTable3);
        txtCoinTable4 = findViewById(R.id.txtCoinTable4);
        txtCoinTable5 = findViewById(R.id.txtCoinTable5);
        txtCoinTable6 = findViewById(R.id.txtCoinTable6);
        llTableAmountTransfer = findViewById(R.id.llTableAmountTransfer);
        txtTableAmountTransfer = findViewById(R.id.txtTableAmountTransfer);
        llViewBottomBar = findViewById(R.id.llViewBottomBar);
//      llViewBottomBar.setVisibility(View.GONE);

        ani2_back = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.transite2_back);
        ani2_back.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                llCoinTable2.setVisibility(View.GONE);
                llCoinTable3.setVisibility(View.GONE);
                llCoinTable4.setVisibility(View.GONE);
                llCoinTable5.setVisibility(View.GONE);
                llCoinTable6.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ani3_back = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.transite3_back);
        ani3_back.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                llCoinTable2.setVisibility(View.GONE);
                llCoinTable3.setVisibility(View.GONE);
                llCoinTable4.setVisibility(View.GONE);
                llCoinTable5.setVisibility(View.GONE);
                llCoinTable6.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ani4_back = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.transite4_back);
        ani4_back.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                llCoinTable2.setVisibility(View.GONE);
                llCoinTable3.setVisibility(View.GONE);
                llCoinTable4.setVisibility(View.GONE);
                llCoinTable5.setVisibility(View.GONE);
                llCoinTable6.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ani5_back = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.transite5_back);
        ani5_back.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                llCoinTable2.setVisibility(View.GONE);
                llCoinTable3.setVisibility(View.GONE);
                llCoinTable4.setVisibility(View.GONE);
                llCoinTable5.setVisibility(View.GONE);
                llCoinTable6.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ani6_back = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.transite6_back);
        ani6_back.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                llCoinTable2.setVisibility(View.GONE);
                llCoinTable3.setVisibility(View.GONE);
                llCoinTable4.setVisibility(View.GONE);
                llCoinTable5.setVisibility(View.GONE);
                llCoinTable6.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        anim_tab6_win_1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_tab6_win_1);
        anim_tab6_win_2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_tab6_win_2);
        anim_tab6_win_3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_tab6_win_3);
        anim_tab6_win_4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_tab6_win_4);
        anim_tab6_win_5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_tab6_win_5);

        anim_tab6_win_1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llTableAmountTransfer.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim_tab6_win_2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llTableAmountTransfer.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        anim_tab6_win_3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llTableAmountTransfer.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }


        });
        anim_tab6_win_4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llTableAmountTransfer.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        anim_tab6_win_5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llTableAmountTransfer.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        llPlayer6 = findViewById(R.id.llPlayer6);
        llPlayer5 = findViewById(R.id.llPlayer5);
        llPlayer4 = findViewById(R.id.llPlayer4);
        llPlayer3 = findViewById(R.id.llPlayer3);
        llPlayer2 = findViewById(R.id.llPlayer2);

        imgcard3Big = findViewById(R.id.imgcard3Big);
        imgcard1back = findViewById(R.id.imgcard1back);
        imgcard1num = findViewById(R.id.imgcard1num);
        imgcard1icon = findViewById(R.id.imgcard1icon);
        imgcard2back = findViewById(R.id.imgcard2back);
        imgcard2num = findViewById(R.id.imgcard2num);
        imgcard2icon = findViewById(R.id.imgcard2icon);
        imgcard3back = findViewById(R.id.imgcard3back);
        imgcard3num = findViewById(R.id.imgcard3num);
        imgcard3icon = findViewById(R.id.imgcard3icon);

        imgpl3card3Big = findViewById(R.id.imgpl3card3Big);
        imgpl3card1back = findViewById(R.id.imgpl3card1back);
        imgpl3card1num = findViewById(R.id.imgpl3card1num);
        imgpl3card1icon = findViewById(R.id.imgpl3card1icon);
        imgpl3card2back = findViewById(R.id.imgpl3card2back);
        imgpl3card2num = findViewById(R.id.imgpl3card2num);
        imgpl3card2icon = findViewById(R.id.imgpl3card2icon);
        imgpl3card3back = findViewById(R.id.imgpl3card3back);
        imgpl3card3num = findViewById(R.id.imgpl3card3num);
        imgpl3card3icon = findViewById(R.id.imgpl3card3icon);

        imgpl2card3Big = findViewById(R.id.imgpl2card3Big);
        imgpl2card1back = findViewById(R.id.imgpl2card1back);
        imgpl2card1num = findViewById(R.id.imgpl2card1num);
        imgpl2card1icon = findViewById(R.id.imgpl2card1icon);
        imgpl2card2back = findViewById(R.id.imgpl2card2back);
        imgpl2card2num = findViewById(R.id.imgpl2card2num);
        imgpl2card2icon = findViewById(R.id.imgpl2card2icon);
        imgpl2card3back = findViewById(R.id.imgpl2card3back);
        imgpl2card3num = findViewById(R.id.imgpl2card3num);
        imgpl2card3icon = findViewById(R.id.imgpl2card3icon);

        imgpl4card3Big = findViewById(R.id.imgpl4card3Big);
        imgpl4card1back = findViewById(R.id.imgpl4card1back);
        imgpl4card1num = findViewById(R.id.imgpl4card1num);
        imgpl4card1icon = findViewById(R.id.imgpl4card1icon);
        imgpl4card2back = findViewById(R.id.imgpl4card2back);
        imgpl4card2num = findViewById(R.id.imgpl4card2num);
        imgpl4card2icon = findViewById(R.id.imgpl4card2icon);
        imgpl4card3back = findViewById(R.id.imgpl4card3back);
        imgpl4card3num = findViewById(R.id.imgpl4card3num);
        imgpl4card3icon = findViewById(R.id.imgpl4card3icon);

        imgpl5card3Big = findViewById(R.id.imgpl5card3Big);
        imgpl5card1back = findViewById(R.id.imgpl5card1back);
        imgpl5card1num = findViewById(R.id.imgpl5card1num);
        imgpl5card1icon = findViewById(R.id.imgpl5card1icon);
        imgpl5card2back = findViewById(R.id.imgpl5card2back);
        imgpl5card2num = findViewById(R.id.imgpl5card2num);
        imgpl5card2icon = findViewById(R.id.imgpl5card2icon);
        imgpl5card3back = findViewById(R.id.imgpl5card3back);
        imgpl5card3num = findViewById(R.id.imgpl5card3num);
        imgpl5card3icon = findViewById(R.id.imgpl5card3icon);

        txtPlayChaal = findViewById(R.id.txtPlayChaal);
        txtCardSee = findViewById(R.id.txtCardSee);
        txtPlay6Id = findViewById(R.id.txtPlay6Id);
        txtPlay5Id = findViewById(R.id.txtPlay5Id);
        txtPlay4Id = findViewById(R.id.txtPlay4Id);
        txtPlay3Id = findViewById(R.id.txtPlay3Id);
        txtPlay2Id = findViewById(R.id.txtPlay2Id);
        txtPlay2Slot = findViewById(R.id.txtPlay2Slot);
        txtPlay3Slot = findViewById(R.id.txtPlay3Slot);
        txtPlay4Slot = findViewById(R.id.txtPlay4Slot);
        txtPlay5Slot = findViewById(R.id.txtPlay5Slot);
        txtPlay6Slot = findViewById(R.id.txtPlay6Slot);
        txtTableAmount = findViewById(R.id.txtTableAmount);
        txtPlay2 = findViewById(R.id.txtPlay2);
        txtPlay2Rs = findViewById(R.id.txtPlay2Rs);
        txtPlay3 = findViewById(R.id.txtPlay3);
        txtPlay3Rs = findViewById(R.id.txtPlay3Rs);
        txtPlay4 = findViewById(R.id.txtPlay4);
        txtPlay4Rs = findViewById(R.id.txtPlay4Rs);
        txtPlay5 = findViewById(R.id.txtPlay5);
        txtPlay5Rs = findViewById(R.id.txtPlay5Rs);
        txtPlay6 = findViewById(R.id.txtPlay6);
        txtPlay6Rs = findViewById(R.id.txtPlay6Rs);
        txtPlayShow = findViewById(R.id.txtPlayShow);
        txtPlayShowSlideshow = findViewById(R.id.txtPlayShowSlideshow);
        txtPlayBlind = findViewById(R.id.txtPlayBlind);
        txtPlayPack = findViewById(R.id.txtPlayPack);
        txtPlayRs = findViewById(R.id.txtPlayRs);
        txtPlayMinus = findViewById(R.id.txtPlayMinus);
        txtPlayPlue = findViewById(R.id.txtPlayPlue);


        llCoinTable2 = findViewById(R.id.llCoinTable2);
        llCoinTable2.startAnimation(ani2_back);

        llCoinTable3 = findViewById(R.id.llCoinTable3);
        llCoinTable4 = findViewById(R.id.llCoinTable4);
        llCoinTable5 = findViewById(R.id.llCoinTable5);
        llCoinTable6 = findViewById(R.id.llCoinTable6);

        llCoinTable2.setVisibility(View.GONE);
        llCoinTable3.setVisibility(View.GONE);
        llCoinTable4.setVisibility(View.GONE);
        llCoinTable5.setVisibility(View.GONE);
        llCoinTable6.setVisibility(View.GONE);

        ani2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.tab6_card_2);

        ani3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.tab6_card_3);

        ani4 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.tab6_card_4);

        ani5 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.tab6_card_5);

        ani6 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.tab6_card_6);
        ani6.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (iscardstop) {

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        d = new Dialog(Table6_Activity.this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.slideshow_dialog);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        d.getWindow().setGravity(Gravity.BOTTOM);
        d.setCanceledOnTouchOutside(false);
        txtSlideAccept = d.findViewById(R.id.txtSlideAccept);
        txtSlideDecline = d.findViewById(R.id.txtSlideDecline);
        txtSlideDesc = d.findViewById(R.id.txtSlideDesc);

        d.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    d.show();
                }
                return true;
            }
        });


//      TextView txtSlideAccept,txtSlideDecline,txtSlideDesc,txtSlideTitle;


        dNetWork = new Dialog(Table6_Activity.this);
        dNetWork.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dNetWork.setContentView(R.layout.networkdialog);
        dNetWork.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dNetWork.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dNetWork.getWindow().setGravity(Gravity.CENTER);
        dNetWork.setCanceledOnTouchOutside(true);

        dNetWork.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    finish();
                }
                return true;
            }
        });


        dGift = new Dialog(Table6_Activity.this,
                R.style.DialogSlideAnim);
        dGift.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dGift.setContentView(R.layout.dialog_show_gifts);
        dGift.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dGift.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dGift.getWindow().setGravity(Gravity.RIGHT);
        dGift.setCanceledOnTouchOutside(true);

        ImageView imgBackDis = dGift.findViewById(R.id.imgBack);
        imgBackDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));
                dGift.dismiss();
            }
        });

        GiftGrdView = dGift.findViewById(R.id.GiftGrdView);
        AdapterGiftCards = new Adapter_Gifts();
        GiftGrdView.setAdapter(AdapterGiftCards);

        dReplaceCards = new Dialog(Table6_Activity.this);
        dReplaceCards.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dReplaceCards.setContentView(R.layout.dialog_slide_showwwww);

        dReplaceCards.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dReplaceCards.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        dReplaceCards.getWindow().setGravity(Gravity.CENTER);
        dReplaceCards.setCanceledOnTouchOutside(true);

        grdDiaReplaceCard = dReplaceCards.findViewById(R.id.grdDiaReplaceCard);
        AdapterReplaceCard = new Adapter_ReplaceCard();
        grdDiaReplaceCard.setAdapter(AdapterReplaceCard);
        txtDiaReplaceCardClear = dReplaceCards.findViewById(R.id.txtDiaReplaceCardClear);
        txtDiaReplaceCardDone = dReplaceCards.findViewById(R.id.txtDiaReplaceCardDone);

        ImageView imgdiaBack = dReplaceCards.findViewById(R.id.imgdiaBack);
        imgdiaBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));
                dReplaceCards.dismiss();
            }
        });

        ReplaceCurrentCardss = new JSONArray();
        txtDiaReplaceCardClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));
                ReplaceCardselectionnumber = 0;
                txtDiaReplaceCardDone.setAlpha(0.5f);
                txtDiaReplaceCardDone.setEnabled(false);
                ReplaceCurrentCardss = new JSONArray();
                AdapterReplaceCard = new Adapter_ReplaceCard();
                grdDiaReplaceCard.setAdapter(AdapterReplaceCard);

            }
        });

        grdDiaReplaceCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Log.e("replace crad numb", ReplaceCardselectionnumber + "");
                ImageView txtDiaCardNumber = view.findViewById(R.id.txtDiaCardNumber);

                if (txtDiaCardNumber.getVisibility() != View.VISIBLE) {
                    if (ReplaceCardselectionnumber >= 3) {

                    } else {

                        String type = "";
                        String numberrr = "";
                        int numberrrsedsds;
                        numberrrsedsds = (((int) Math.ceil(position / 13)) + 1);
                        if (numberrrsedsds == 1) {
                            type = "spade";
                        } else if (numberrrsedsds == 2) {
                            type = "club";
                        } else if (numberrrsedsds == 3) {
                            type = "heart";
                        } else if (numberrrsedsds == 4) {
                            type = "diamond";
                        }
                        if (position % 13 == 0) {
                            numberrr = "1";
                        } else if (position % 13 == 1) {
                            numberrr = "2";
                        } else if (position % 13 == 2) {
                            numberrr = "3";
                        } else if (position % 13 == 3) {
                            numberrr = "4";
                        } else if (position % 13 == 4) {
                            numberrr = "5";
                        } else if (position % 13 == 5) {
                            numberrr = "6";
                        } else if (position % 13 == 6) {
                            numberrr = "7";
                        } else if (position % 13 == 7) {
                            numberrr = "8";
                        } else if (position % 13 == 8) {
                            numberrr = "9";
                        } else if (position % 13 == 9) {
                            numberrr = "10";
                        } else if (position % 13 == 10) {
                            numberrr = "11";
                        } else if (position % 13 == 11) {
                            numberrr = "12";
                        } else if (position % 13 == 12) {
                            numberrr = "13";
                        }


                        int rank = 0;
                        int priority = 0;
                        String name = "";

                        name = numberrr;
                        priority = (int) Float.parseFloat(numberrr);
                        rank = (int) Float.parseFloat(numberrr);

                        if (numberrr.equals("1")) {
                            name = "A";
                            priority = 14;
                        } else if (numberrr.equals("11")) {
                            name = "J";
                            priority = 11;
                        } else if (numberrr.equals("12")) {
                            name = "Q";
                            priority = 12;
                        } else if (numberrr.equals("13")) {

                            name = "K";
                            priority = 13;
                        }

                        rank = (int) Float.parseFloat(numberrr);

                        try {

                            Log.e("cardsss", CurrentCardss.toString());

                            JSONObject jCurrentCards = CurrentCardss;
                            JSONArray jrrow = jCurrentCards.getJSONArray("cards");

                            JSONObject jjj = jrrow.getJSONObject(ReplaceCardselectionnumber);
                            JSONObject NewObject = new JSONObject();
                            NewObject.put("type", type);
                            NewObject.put("rank", rank);
                            NewObject.put("name", name);
                            NewObject.put("priority", priority);
                            NewObject.put("id", jjj.get("id"));
                            ReplaceCurrentCardss.put(NewObject);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Log.e("cardss", ReplaceCurrentCardss.toString());

                        ReplaceCardselectionnumber++;
                        txtDiaCardNumber.setVisibility(View.VISIBLE);
//                    txtDiaCardNumber.setText(ReplaceCardselectionnumber + "");

                        Log.e("replace num,ber", ReplaceCardselectionnumber + "   ");
                        if (ReplaceCardselectionnumber == 3) {
                            txtDiaReplaceCardDone.setAlpha(1f);
                            txtDiaReplaceCardDone.setEnabled(true);
                        } else {
                            txtDiaReplaceCardDone.setAlpha(0.5f);
                            txtDiaReplaceCardDone.setEnabled(false);
                        }
                    }
                }
            }
        });


        txtDiaReplaceCardDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                JSONObject json = new JSONObject();

                dReplaceCards.dismiss();


                try {

                    json.put("cards", ReplaceCurrentCardss);
                    json.put("current", Json_Current_Player);
                    json.put("players", Json_Player_Info);
                    json.put("playerid", SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID));
                    json.put("tableId", table_id);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Log.e(TAG + " emit ", "ReplaceCard " + json.toString());
                mSocket.emit("ReplaceCard", json, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e(TAG, "emit receive : > seeAllCards " + args[0]);
                    }
                });

                ReplaceCardselectionnumber = 0;
                ReplaceCurrentCardss = new JSONArray();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("calling ", ".............see all cardss.........................");
                        txtshowallcards.performClick();
                    }
                }, 400);

            }
        });

        dCloseExit = new Dialog(Table6_Activity.this, R.style.DialogSlideAnim_Left);
        dCloseExit.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dCloseExit.setContentView(R.layout.dialog_exit_game);
        dCloseExit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dCloseExit.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dCloseExit.getWindow().setGravity(Gravity.LEFT);
        dCloseExit.setCanceledOnTouchOutside(true);
        Button btndClosExit = dCloseExit.findViewById(R.id.btndClosExit);
        btndClosExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));
                dCloseExit.dismiss();
                CloseGame();
            }
        });

        Button btnndSetting = dCloseExit.findViewById(R.id.btnndSetting);
        btnndSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));
                startActivity(new Intent(Table6_Activity.this, UserEdit_Activity.class));
            }
        });

        Button btnndInfo = dCloseExit.findViewById(R.id.btnndInfo);
        btnndInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                txtInfoTableUserConnected.setText("Current User : " + arrcurrentplayer.size() + "/" + SelectTable.getMaxPlayers());
                dInfoValue.show();
            }
        });

        Button btndClosCancel = dCloseExit.findViewById(R.id.btndClosCancel);
        View recharge = dCloseExit.findViewById(R.id.recharge);
        View rechargeBtn = dCloseExit.findViewById(R.id.rechargeBtn);
        btndClosCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));
                dCloseExit.dismiss();
            }
        });

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCashPage();
            }
        });
        rechargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCashPage();
            }
        });

        dInfoValue = new Dialog(Table6_Activity.this);
        dInfoValue.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dInfoValue.setContentView(R.layout.info_dialog);
        dInfoValue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dInfoValue.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dInfoValue.getWindow().setGravity(Gravity.BOTTOM);
        dInfoValue.setCanceledOnTouchOutside(true);

        txtInfoBootValue = dInfoValue.findViewById(R.id.txtInfoBootValue);
        txtInfoMaxBlind = dInfoValue.findViewById(R.id.txtInfoMaxBlind);
        txtInfoChaalLimit = dInfoValue.findViewById(R.id.txtInfoChaalLimit);
        txtInfoPotLimit = dInfoValue.findViewById(R.id.txtInfoPotLimit);
        txtInfoTableType = dInfoValue.findViewById(R.id.txtInfoTableType);
        txtInfoTableType.setText(SelectTable.getName());
        txtInfoTableUserConnected = dInfoValue.findViewById(R.id.txtInfoTableUserConnected);
        imgInfoCancel = dInfoValue.findViewById(R.id.imgInfoCancel);
        imgInfoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));
                dInfoValue.cancel();
            }
        });

        txtInfoBootValue.setText(SelectTable.getBoot());
        txtInfoMaxBlind.setText(SelectTable.getMaximumblind());
        txtInfoChaalLimit.setText(SelectTable.getMaxbet());
        txtInfoPotLimit.setText(SelectTable.getPotLimit());

    }

    private void addCashPage() {
        startActivity(new Intent(Table6_Activity.this, AddcashActivity.class));
        overridePendingTransition(R.anim.intent_side_right, R.anim.intent_slide_to_left);
    }

    private void BindListner() {


        txtTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));
                JSONObject json = new JSONObject();
                try {
                    json.put("tip", 150);
                    json.put("tableId", table_id);
                    json.put("fromId", txtPlay6Id.getText().toString());
                    json.put("current", Json_Current_Player);
                    json.put("players", Json_Player_Info);
                    json.put("player", Json_Current_Player);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e(TAG + "emit", "TipToGirl " + json.toString());
                mSocket.emit("TipToGirl", json, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e(TAG, "emit receive" + args[0]);
                    }
                });

            }
        });

        GiftGrdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                GiftFromId = txtPlay6Id.getText().toString();
                String GiftId = "123455";

                String audioooo = "gift/sound/sou_" + (position + 1) + ".ogg";
                String imageees = "gift/img/img_" + (position + 1) + ".png";
                JSONObject json = new JSONObject();
                try {
                    json.put("current", Json_Current_Player);
                    json.put("players", Json_Player_Info);
                    json.put("tableId", table_id);
                    json.put("toId", GiftToId);
                    json.put("fromId", GiftFromId);
                    json.put("GiftId", GiftId);
                    json.put("price", "10");
                    json.put("GiftAudio", audioooo);
                    json.put("Gifturl", imageees);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dGift.dismiss();

                Log.e(TAG + "emit ", "SendGift " + json.toString());
                mSocket.emit("SendGift", json, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e(TAG, " emit receive" + args[0]);
                    }
                });

            }
        });


        imgGift4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                GiftToId = txtPlay4Id.getText().toString();
                dGift.show();
            }
        });
        imgGift5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                GiftToId = txtPlay5Id.getText().toString();
                dGift.show();
            }
        });
        imgGift3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                GiftToId = txtPlay3Id.getText().toString();
                dGift.show();
            }
        });
        imgGift6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                GiftToId = txtPlay6Id.getText().toString();
                dGift.show();
            }
        });
        imgGift2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                GiftToId = txtPlay2Id.getText().toString();
                dGift.show();
            }
        });

        imgInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                txtInfoTableUserConnected.setText("Current User : " + arrcurrentplayer.size() + "/" + SelectTable.getMaxPlayers());
                dInfoValue.show();
            }
        });
        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                startActivity(new Intent(Table6_Activity.this, UserEdit_Activity.class));

            }
        });
        txtSlideAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));


                d.dismiss();
                JSONObject json = new JSONObject();
                try {
                    NotplayingCount = 0;
                    json.put("players", Json_Player_Info);
                    json.put("player", Json_Current_Player);
                    json.put("table", Json_Table_Info);
                    json.put("tableId", table_id);
                    json.put("placedTo", LastSideshowplaceBy);
                    JSONObject json_userinfo = new JSONObject();
                    json_userinfo.put("amount", Float.parseFloat(txtPlayRs.getText().toString()));
                    json_userinfo.put("blind", false);
                    json.put("bet", json_userinfo);
                    json.put("lastAction", "Accepted");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                StopTimerSideShow = 1;

                Log.e(TAG + "emit ", "respondSideShow " + json.toString());
                mSocket.emit("respondSideShow", json, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e(TAG, "emit receive" + args[0]);
                    }
                });
            }
        });


        txtSlideDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                d.dismiss();
                JSONObject json = new JSONObject();
                try {
                    NotplayingCount = 0;

                    json.put("players", Json_Player_Info);
                    json.put("player", Json_Current_Player);
                    json.put("table", Json_Table_Info);
                    json.put("tableId", table_id);
                    json.put("placedTo", LastSideshowplaceBy);
                    JSONObject json_userinfo = new JSONObject();
                    json_userinfo.put("amount", Float.parseFloat(txtPlayRs.getText().toString()));
                    json_userinfo.put("blind", false);
                    json.put("bet", json_userinfo);
                    json.put("lastAction", "Denied");


                /*    json.put("players", Json_Player_Info);
                    json.put("player", Json_Current_Player);
                    json.put("table", Json_Table_Info);
                    json.put("tableId", table_id);
                    json.put("placedTo", LastSideshowplaceBy);

                    JSONObject json_userinfo = new JSONObject();
                    json_userinfo.put("amount", (int) Float.parseFloat(txtPlayRs.getText().toString()));
                    json_userinfo.put("blind", false);
                    json.put("bet", json_userinfo);
                    json.put("lastAction", "Denied");
*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                StopTimerSideShow = 1;
                Log.e(TAG + "emit ", "respondSideShow " + json.toString());
                mSocket.emit("respondSideShow", json, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e(TAG, "emit receive" + args[0]);
                    }
                });

            }
        });

        txtPlayPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                CurrentAction = "pack";
                if (Myturn.equals("true")) {
//                    Toast.makeText(Table6_Activity.this, "My turn", Toast.LENGTH_LONG).show();
                    JSONObject json = new JSONObject();
                    try {
                        llViewBottomBar.setVisibility(View.GONE);
                        txtPlayShow.setEnabled(false);
                        txtPlayPlue.setEnabled(false);
                        txtPlayMinus.setEnabled(false);
                        txtPlayPack.setEnabled(false);
                        txtPlayBlind.setEnabled(false);
                        txtPlayShowSlideshow.setEnabled(false);
                        json.put("players", Json_Player_Info);
                        json.put("player", Json_Current_Player);
                        json.put("table", Json_Table_Info);
                        json.put("tableId", table_id);

                        JSONObject json_userinfo = new JSONObject();
                        json_userinfo.put("action", "Packed");
                        json_userinfo.put("amount", Float.parseFloat(txtPlayRs.getText().toString()));
                        json_userinfo.put("blind", false);
//                        json_userinfo.put("show", false);
                        json_userinfo.put("tableId", table_id);

                        json.put("bet", json_userinfo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    llPlayer6.setAlpha(0.5f);
                    StopTimer = 1;
                    Log.e(TAG + "emit", "placePack  " + json.toString());
                    mSocket.emit("placePack", json, new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            Log.e(TAG, "emit receive" + args[0]);
                        }
                    });

                    llPlayer6.setAlpha(0.5f);
                    txtPlay6.setText("Packed");
                    txtPlay6Rs.setText("");

                    llViewBottomBar.setVisibility(View.GONE);
//                    llCardpl6.setVisibility(View.GONE);
                } else {
//                    Toast.makeText(Table6_Activity.this, "Not My turn", Toast.LENGTH_LONG).show();
                }
            }
        });
        txtPlayShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                if (Myturn.equals("true")) {

                    if (Float.parseFloat(txtPlayRs.getText().toString()) >= Float.parseFloat(txtPlay6Rs.getText().toString())) {

                        txtPlayPack.performClick();
                        Functions.displayToast("You Don't have enough Money!");

                    } else {
                        CurrentAction = "show";
//                    Toast.makeText(Table6_Activity.this, "My turn", Toast.LENGTH_LONG).show();
                        JSONObject json = new JSONObject();
                        NotplayingCount = 0;
                        llViewBottomBar.setVisibility(View.GONE);
                        txtPlayShow.setEnabled(false);
                        txtPlayPlue.setEnabled(false);
                        txtPlayMinus.setEnabled(false);
                        txtPlayPack.setEnabled(false);
                        txtPlayBlind.setEnabled(false);
                        txtPlayShowSlideshow.setEnabled(false);


                        try {
                            json.put("players", Json_Player_Info);
                            json.put("player", Json_Current_Player);
                            json.put("tableInfo", Json_Table_Info);
                            JSONObject json_userinfo = new JSONObject();

                            json_userinfo.put("action", "Accepted");
                            json_userinfo.put("amount", Float.valueOf(String.valueOf(Math.round(Double.parseDouble(txtPlayRs.getText().toString())  * 100.0) / 100.0)));
                            json_userinfo.put("blind", false);
                            json_userinfo.put("show", true);
                            json_userinfo.put("tableId", table_id);

                            json.put("bet", json_userinfo);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        StopTimer = 1;
                        Log.e(TAG + "emit", "placeBet " + json.toString());
                        mSocket.emit("placeBet", json, new Emitter.Listener() {
                            @Override
                            public void call(Object... args) {
                                Log.e(TAG, "emit receive" + args[0]);
                            }
                        });
                        llViewBottomBar.setVisibility(View.GONE);

                    }
                } else {

//                    Toast.makeText(Table6_Activity.this, "Not My turn", Toast.LENGTH_LONG).show();

                }
            }
        });

        txtPlayShowSlideshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));
                llViewBottomBar.setVisibility(View.GONE);
                try {
                    if (Float.parseFloat(txtPlayRs.getText().toString()) >=  Float.parseFloat(txtPlay6Rs.getText().toString())) {

                        txtPlayPack.performClick();
                        Functions.displayToast("You Don't have enough Money!");

                    } else {
                        Log.e("slideshow click", "click slideshow");
//                if (Myturn.equals("true")) {
                        JSONObject json = new JSONObject();
                        try {
                            NotplayingCount = 0;
                            json.put("placedTo", LastBetPlaceSideShow);
                            json.put("players", Json_Player_Info);
                            json.put("player", Json_Current_Player);
                            json.put("tableId", table_id);
                            json.put("table", Json_Table_Info);

                            JSONObject json_userinfo = new JSONObject();

                            json_userinfo.put("amount",Float.valueOf(String.valueOf(Math.round(Double.parseDouble(txtPlayRs.getText().toString())  * 100.0) / 100.0)));
                            json_userinfo.put("blind", false);

                            json.put("bet", json_userinfo);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        StopTimer = 1;
                        Log.e(TAG + "emit ", "placeSideShow " + json.toString());
                        mSocket.emit("placeSideShow", json, new Emitter.Listener() {
                            @Override
                            public void call(Object... args) {
                                Log.e(TAG, "emit receive" + args[0]);
                            }
                        });


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
              /*  } else {

                }
*/
            }
        });
        txtPlayChaal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                if (Myturn.equals("true")) {

                    if (Float.parseFloat(txtPlayRs.getText().toString()) >=  Float.parseFloat(txtPlay6Rs.getText().toString())) {

                        txtPlayPack.performClick();
                        Functions.displayToast("You Don't have enough Money!");

                    } else {
//                    Toast.makeText(Table6_Activity.this, "My turn", Toast.LENGTH_LONG).show();
                        JSONObject json = new JSONObject();
                        try {
                            Functions.StopMusic();
                            NotplayingCount = 0;
                            llViewBottomBar.setVisibility(View.GONE);
                            txtPlayShow.setEnabled(false);
                            txtPlayPlue.setEnabled(false);
                            txtPlayMinus.setEnabled(false);
                            txtPlayPack.setEnabled(false);
                            txtPlayBlind.setEnabled(false);
                            txtPlayShowSlideshow.setEnabled(false);


                            CurrentAction = "Chaal";

                            json.put("players", Json_Player_Info);
                            json.put("player", Json_Current_Player);
                            json.put("tableInfo", Json_Table_Info);
                            JSONObject json_userinfo = new JSONObject();
                            json_userinfo.put("action", "Chaal");
                            json_userinfo.put("amount", Float.valueOf(String.valueOf(Math.round(Double.parseDouble(txtPlayRs.getText().toString())  * 100.0) / 100.0)));
                            json_userinfo.put("blind", false);
                            json_userinfo.put("show", false);
                            json_userinfo.put("tableId", table_id);

                            json.put("bet", json_userinfo);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        StopTimer = 1;
                        Log.e(TAG + "emit", "placeBet " + json.toString());
                        mSocket.emit("placeBet", json, new Emitter.Listener() {
                            @Override
                            public void call(Object... args) {
                                Log.e(TAG, "emit receive" + args[0]);
                            }
                        });
                        llViewBottomBar.setVisibility(View.GONE);
                    }
                } else {
//                    Toast.makeText(Table6_Activity.this, "Not My turn", Toast.LENGTH_LONG).show();
                }
            }
        });

        txtCardSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnCardToSeen();

            }
        });

        txtReplaceCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                AdapterReplaceCard = new Adapter_ReplaceCard();
                grdDiaReplaceCard.setAdapter(AdapterReplaceCard);

                txtDiaReplaceCardDone.setEnabled(false);
                txtDiaReplaceCardDone.setAlpha(0.5f);
                dReplaceCards.show();

                /*


                JSONArray NewJsonArray = new JSONArray();
                try {
                    Log.e("cardsss", CurrentCardss.toString());

                    JSONObject jCurrentCards = CurrentCardss;
                    JSONArray jrrow = jCurrentCards.getJSONArray("cards");
                    NewJsonArray = new JSONArray();
                    for (int iii = 0; iii < jrrow.length(); iii++) {
                        JSONObject jjj = jrrow.getJSONObject(iii);
                        JSONObject NewObject = new JSONObject();
                        NewObject.put("type", jjj.getString("type"));
                        NewObject.put("rank", "1");
                        NewObject.put("priority", "1");
                        NewObject.put("name", "A");
                        NewObject.put("id", jjj.getString("id"));
                        NewJsonArray.put(NewObject);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                JSONObject json = new JSONObject();
                try {
                    json.put("cards", NewJsonArray);
                    json.put("current", Json_Current_Player);
                    json.put("players", Json_Player_Info);
                    json.put("playerid", SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID));
                    json.put("tableId", table_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                StopTimer = 1;
                Log.e(TAG + " emit ", "ReplaceCard " + json.toString());
                mSocket.emit("ReplaceCard", json, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e(TAG, "emit receive : > seeAllCards " + args[0]);
                    }
                });
*/


            }
        });
        txtshowallcards.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

//                NotplayingCount = 0;
//                iscardseen = "true";
//                CurrentAction = "Chaal";
//                MaximumBlindcount = 100;
//                txtPlayShow.setEnabled(false);
//                txtPlayPlue.setEnabled(false);
//                txtPlayMinus.setEnabled(false);
//                txtPlayPack.setEnabled(false);
//                txtPlayBlind.setEnabled(false);
//                txtPlayShowSlideshow.setEnabled(false);
//
//                txtPlayChaal.setVisibility(View.VISIBLE);
//                txtPlayBlind.setVisibility(View.GONE);


                txtReplaceCards.setAlpha(1f);
                txtshowallcards.setAlpha(1f);
                txtshowallcards.setEnabled(true);
                txtReplaceCards.setEnabled(true);


                JSONObject json = new JSONObject();
                try {
                    json.put("current", Json_Current_Player);
                    json.put("players", JsonSeeAllCardsInfo);
                    json.put("tableId", table_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                StopTimer = 1;
                Log.e(TAG + " emit ", "seeAllCards " + json.toString());
//                mSocket.emit("seeAllCards", json);

                mSocket.emit("seeAllCards", json, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.e(TAG, "emit receive : > seeAllCards " + args[0]);
                    }
                });


            }
        });


        txtPlayBlind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                if (Myturn.equals("true")) {

                    if (Float.parseFloat(txtPlayRs.getText().toString()) >= Float.parseFloat(txtPlay6Rs.getText().toString())) {

                        txtPlayPack.performClick();
                        Functions.displayToast("You Don't have enough Money!");

                    } else {

                        JSONObject json = new JSONObject();
                        try {
                            Functions.StopMusic();
                            NotplayingCount = 0;
                            CurrentAction = "Blind";
                            llViewBottomBar.setVisibility(View.GONE);
                            txtPlayShow.setEnabled(false);
                            txtPlayPlue.setEnabled(false);
                            txtPlayMinus.setEnabled(false);
                            txtPlayPack.setEnabled(false);
                            txtPlayBlind.setEnabled(false);
                            txtPlayShowSlideshow.setEnabled(false);

                            json.put("players", Json_Player_Info);
                            json.put("player", Json_Current_Player);
                            json.put("tableInfo", Json_Table_Info);
                            JSONObject json_userinfo = new JSONObject();
                            json_userinfo.put("action", "Blind");
                            json_userinfo.put("amount",Float.valueOf(String.valueOf(Math.round(Double.parseDouble(txtPlayRs.getText().toString())  * 100.0) / 100.0)));
                            json_userinfo.put("blind", true);
                            json_userinfo.put("show", false);
                            json_userinfo.put("tableId", table_id);


                            json.put("bet", json_userinfo);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        StopTimer = 1;
                        MaximumBlindcount++;
                        Log.e(TAG + "emit", "placeBet " + json.toString());
                        mSocket.emit("placeBet", json, new Emitter.Listener() {
                            @Override
                            public void call(Object... args) {
                                Log.e(TAG, "emit receive" + args[0]);
                            }
                        });
                        llViewBottomBar.setVisibility(View.GONE);
                    }

                } else {
//                    Toast.makeText(Table6_Activity.this, "Not My turn", Toast.LENGTH_LONG).show();

                }

            }
        });
        txtPlayMinus.setAlpha(0.5f);
        txtPlayMinus.setEnabled(false);
        txtPlayPlue.setAlpha(1f);
        txtPlayPlue.setEnabled(true);
        txtPlayMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                float minusamount = Float.parseFloat(txtPlayRs.getText().toString()) / 2;
                float minus = Float.parseFloat(txtPlayRs.getText().toString()) - minusamount;
                txtPlayRs.setText(minus + "");
                txtPlayBlind.setText("Blind\n" + String.valueOf(Math.round(Double.parseDouble(String.valueOf(minus))  * 100.0) / 100.0));
                txtPlayChaal.setText("Chaal\n" +  String.valueOf(Math.round(Double.parseDouble(String.valueOf(minus))  * 100.0) / 100.0));
                if ( Float.parseFloat(lastbet) ==  Float.parseFloat(txtPlayRs.getText().toString())) {
                    txtPlayMinus.setAlpha(0.5f);
                    txtPlayMinus.setEnabled(false);
                    txtPlayPlue.setAlpha(1f);
                    txtPlayPlue.setEnabled(true);
                }
            }
        });

        txtPlayPlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

                float minus = Float.parseFloat(txtPlayRs.getText().toString()) + Float.parseFloat(txtPlayRs.getText().toString());
                txtPlayRs.setText(minus + "");
                txtPlayBlind.setText("Blind\n" + String.valueOf(Math.round(Double.parseDouble(String.valueOf(minus))  * 100.0) / 100.0));
                txtPlayChaal.setText("Chaal\n" + String.valueOf(Math.round(Double.parseDouble(String.valueOf(minus))  * 100.0) / 100.0));
                if ( Float.parseFloat(lastbet) <  Float.parseFloat(txtPlayRs.getText().toString())) {
                    txtPlayMinus.setAlpha(1f);
                    txtPlayMinus.setEnabled(true);
                    txtPlayPlue.setAlpha(0.5f);
                    txtPlayPlue.setEnabled(false);
                }
            }
        });


    }

    private void turnCardToSeen() {
        Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_buttonClick));

        NotplayingCount = 0;
        iscardseen = "true";
        CurrentAction = "Chaal";
        MaximumBlindcount = 100;
        txtPlayShow.setEnabled(true);
        txtPlayPlue.setEnabled(true);
        txtPlayMinus.setEnabled(false);
        txtPlayPack.setEnabled(true);
        txtPlayBlind.setEnabled(true);
        txtPlayShowSlideshow.setEnabled(true);

        txtPlayChaal.setVisibility(View.VISIBLE);
        txtPlayBlind.setVisibility(View.GONE);

        JSONObject json = new JSONObject();
        try {
            json.put("current", Json_Current_Player);
            json.put("players", Json_Player_Info);
            json.put("tableId", table_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StopTimer = 1;
        Log.d(TAG + " emit ", "seeMyCards " + json.toString());
        Log.d("emitShow", "seeMyCards " + json.toString());
        mSocket.emit("seeMyCards", json);
    }

    private void setNewTable() {


        Rlpl2Card1.setAlpha(1f);
        Rlpl2Card2.setAlpha(1f);
        Rlpl2Card3.setAlpha(1f);

        Rlpl3Card1.setAlpha(1f);
        Rlpl3Card2.setAlpha(1f);
        Rlpl3Card3.setAlpha(1f);

        Rlpl4Card1.setAlpha(1f);
        Rlpl4Card2.setAlpha(1f);
        Rlpl4Card3.setAlpha(1f);

        Rlpl5Card1.setAlpha(1f);
        Rlpl5Card2.setAlpha(1f);
        Rlpl5Card3.setAlpha(1f);


        Rlpl6Card1.setAlpha(1f);
        Rlpl6Card2.setAlpha(1f);
        Rlpl6Card3.setAlpha(1f);


        Functions.Stop_Player_Turn = 1;
        txtCardSee.setText("SEE");
        txtCardSee.setClickable(true);
        txtCardSee.setVisibility(View.VISIBLE);


        txtReplaceCards.setAlpha(0.5f);
        txtshowallcards.setAlpha(1f);
        txtshowallcards.setEnabled(true);
        txtReplaceCards.setEnabled(false);


        imgWinningImage2.setAnimation(null);
        imgWinningImage3.setAnimation(null);
        imgWinningImage4.setAnimation(null);
        imgWinningImage5.setAnimation(null);
        imgWinningImage6.setAnimation(null);

        imgWinningImage2.setVisibility(View.GONE);
        imgWinningImage3.setVisibility(View.GONE);
        imgWinningImage4.setVisibility(View.GONE);
        imgWinningImage5.setVisibility(View.GONE);
        imgWinningImage6.setVisibility(View.GONE);


        MaximumBlindcount = 0;
        totalplayer = 0;
        CurrentAction = "Blind";
        arrcurrentplayer = new ArrayList<>();
        iscardseen = "false";

        llPlayer6.setAlpha(1f);
        llPlayer2.setAlpha(1f);
        llPlayer3.setAlpha(1f);
        llPlayer4.setAlpha(1f);
        llPlayer5.setAlpha(1f);

        txtPlayBlind.setVisibility(View.VISIBLE);
        txtCardSee.setText("SEE");
        txtCardSee.setClickable(true);
        txtCardSee.setVisibility(View.VISIBLE);
        txtPlayChaal.setVisibility(View.GONE);
//        txtPlayShowSlideshow.setVisibility(View.GONE);
        txtPlayShowSlideshow.setAlpha(0.5f);
        txtPlayShowSlideshow.setEnabled(false);
        imgcard3Big.setImageResource(R.drawable.greybase);
        imgcard1back.setImageResource(R.drawable.greybase);
        imgcard1num.setImageResource(R.drawable.greybase);
        imgcard1icon.setImageResource(R.drawable.greybase);
        imgcard2back.setImageResource(R.drawable.greybase);
        imgcard2num.setImageResource(R.drawable.greybase);
        imgcard2icon.setImageResource(R.drawable.greybase);
        imgcard3back.setImageResource(R.drawable.greybase);
        imgcard3num.setImageResource(R.drawable.greybase);
        imgcard3icon.setImageResource(R.drawable.greybase);

        imgpl2card3Big.setImageResource(R.drawable.greybase);
        imgpl2card1back.setImageResource(R.drawable.greybase);
        imgpl2card1num.setImageResource(R.drawable.greybase);
        imgpl2card1icon.setImageResource(R.drawable.greybase);
        imgpl2card2back.setImageResource(R.drawable.greybase);
        imgpl2card2num.setImageResource(R.drawable.greybase);
        imgpl2card2icon.setImageResource(R.drawable.greybase);
        imgpl2card3back.setImageResource(R.drawable.greybase);
        imgpl2card3num.setImageResource(R.drawable.greybase);
        imgpl2card3icon.setImageResource(R.drawable.greybase);

        imgpl3card3Big.setImageResource(R.drawable.greybase);
        imgpl3card1back.setImageResource(R.drawable.greybase);
        imgpl3card1num.setImageResource(R.drawable.greybase);
        imgpl3card1icon.setImageResource(R.drawable.greybase);
        imgpl3card2back.setImageResource(R.drawable.greybase);
        imgpl3card2num.setImageResource(R.drawable.greybase);
        imgpl3card2icon.setImageResource(R.drawable.greybase);
        imgpl3card3back.setImageResource(R.drawable.greybase);
        imgpl3card3num.setImageResource(R.drawable.greybase);
        imgpl3card3icon.setImageResource(R.drawable.greybase);

        imgpl4card3Big.setImageResource(R.drawable.greybase);
        imgpl4card1back.setImageResource(R.drawable.greybase);
        imgpl4card1num.setImageResource(R.drawable.greybase);
        imgpl4card1icon.setImageResource(R.drawable.greybase);
        imgpl4card2back.setImageResource(R.drawable.greybase);
        imgpl4card2num.setImageResource(R.drawable.greybase);
        imgpl4card2icon.setImageResource(R.drawable.greybase);
        imgpl4card3back.setImageResource(R.drawable.greybase);
        imgpl4card3num.setImageResource(R.drawable.greybase);
        imgpl4card3icon.setImageResource(R.drawable.greybase);

        imgpl5card3Big.setImageResource(R.drawable.greybase);
        imgpl5card1back.setImageResource(R.drawable.greybase);
        imgpl5card1num.setImageResource(R.drawable.greybase);
        imgpl5card1icon.setImageResource(R.drawable.greybase);
        imgpl5card2back.setImageResource(R.drawable.greybase);
        imgpl5card2num.setImageResource(R.drawable.greybase);
        imgpl5card2icon.setImageResource(R.drawable.greybase);
        imgpl5card3back.setImageResource(R.drawable.greybase);
        imgpl5card3num.setImageResource(R.drawable.greybase);
        imgpl5card3icon.setImageResource(R.drawable.greybase);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("on Resume", "on resume");
        Functions.HideNotification(Table6_Activity.this);
        MyApplication.getInstance().setConnectivityListener(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Functions.ShowNotification(Table6_Activity.this, NotiMessage);
        Log.e("on pause", "on pause");
    }

    class ClientidUpdae_Asynctask extends AsyncTask<String, String, String> {


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
            par.add(new BasicNameValuePair("clientId", Arg_id));
            par.add(new BasicNameValuePair("tableId", table_id));
            par.add(new BasicNameValuePair("userId", SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USER_ID)));

            try {

                String jsonstr = jParser.makeHttpRequest(URLS.Domain + URLS.URL_CLIENT, "POST", par);

                Log.e("update user", URLS.Domain + URLS.URL_CLIENT + " :  " + jsonstr);

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
                if ((int) Float.parseFloat(TotalTablePlayer) > 5) {
                    Functions.displayToast("Table is Full ! Please Try in another Table");
                    CloseGame();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void CardServe() {

        Functions.startplay(Table6_Activity.this, getResources().getString(R.string.mp_cardfilp));

        int delay = 0;

        Rlpl2Card1.setVisibility(View.GONE);
        Rlpl2Card2.setVisibility(View.GONE);
        Rlpl2Card3.setVisibility(View.GONE);

        Rlpl3Card1.setVisibility(View.GONE);
        Rlpl3Card2.setVisibility(View.GONE);
        Rlpl3Card3.setVisibility(View.GONE);

        Rlpl4Card1.setVisibility(View.GONE);
        Rlpl4Card2.setVisibility(View.GONE);
        Rlpl4Card3.setVisibility(View.GONE);

        Rlpl5Card1.setVisibility(View.GONE);
        Rlpl5Card2.setVisibility(View.GONE);
        Rlpl5Card3.setVisibility(View.GONE);

        Rlpl6Card1.setVisibility(View.GONE);
        Rlpl6Card2.setVisibility(View.GONE);
        Rlpl6Card3.setVisibility(View.GONE);


        for (int i = 0; i < arrcurrentplayer.size(); i++) {
            delay++;
            if (arrcurrentplayer.get(i).equals("2")) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl2Card1.setVisibility(View.VISIBLE);
                        Rlpl2Card1.startAnimation(anim_pl2_tra);
                    }
                }, delay);


                anim_pl2_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Rlpl2Card1.setRotation(-20);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            } else if (arrcurrentplayer.get(i).equals("3")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl3Card1.setVisibility(View.VISIBLE);
                        Rlpl3Card1.startAnimation(anim_pl3_tra);
                    }
                }, delay * 200);

                anim_pl3_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Rlpl3Card1.setRotation(-20);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            } else if (arrcurrentplayer.get(i).equals("4")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl4Card1.setVisibility(View.VISIBLE);
                        Rlpl4Card1.startAnimation(anim_pl4_tra);
                    }
                }, delay * 200);


                anim_pl4_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Rlpl4Card1.setRotation(-20);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            } else if (arrcurrentplayer.get(i).equals("5")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl5Card1.setVisibility(View.VISIBLE);
                        Rlpl5Card1.startAnimation(anim_pl5_tra);
                    }
                }, delay * 200);


                anim_pl5_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Rlpl5Card1.setRotation(-20);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            } else if (arrcurrentplayer.get(i).equals("6")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl6Card1.setVisibility(View.VISIBLE);
                        Rlpl6Card1.startAnimation(anim_pl6_tra);

                    }
                }, delay * 200);


                anim_pl6_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Rlpl6Card1.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Rlpl6Card1.setRotation(-20);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }
        }
        for (int i = 0; i < arrcurrentplayer.size(); i++) {
            delay++;
            if (arrcurrentplayer.get(i).equals("2")) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl2Card2.setVisibility(View.VISIBLE);
                        Rlpl2Card2.startAnimation(anim_pl2_2_tra);
                    }
                }, delay * 200);


                anim_pl2_2_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            } else if (arrcurrentplayer.get(i).equals("3")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl3Card2.setVisibility(View.VISIBLE);
                        Rlpl3Card2.startAnimation(anim_pl3_2_tra);
                    }
                }, delay * 200);


                anim_pl3_2_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            } else if (arrcurrentplayer.get(i).equals("4")) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl4Card2.setVisibility(View.VISIBLE);
                        Rlpl4Card2.startAnimation(anim_pl4_2_tra);
                    }
                }, delay * 200);


                anim_pl4_2_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            } else if (arrcurrentplayer.get(i).equals("5")) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl5Card2.setVisibility(View.VISIBLE);
                        Rlpl5Card2.startAnimation(anim_pl5_2_tra);
                    }
                }, delay * 200);


                anim_pl5_2_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

            } else if (arrcurrentplayer.get(i).equals("6")) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl6Card2.setVisibility(View.VISIBLE);
                        Rlpl6Card2.startAnimation(anim_pl6_2_tra);
                    }
                }, delay * 200);


                anim_pl6_2_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }
        }


        for (int i = 0; i < arrcurrentplayer.size(); i++) {
            delay++;
            if (arrcurrentplayer.get(i).equals("2")) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl2Card3.setVisibility(View.VISIBLE);
                        Rlpl2Card3.startAnimation(anim_pl2_3_tra);
                    }
                }, delay * 200);


                anim_pl2_3_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Rlpl2Card3.setRotation(20);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            } else if (arrcurrentplayer.get(i).equals("3")) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl3Card3.setVisibility(View.VISIBLE);
                        Rlpl3Card3.startAnimation(anim_pl3_3_tra);
                    }
                }, delay * 200);


                anim_pl3_3_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Rlpl3Card3.setRotation(20);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

            } else if (arrcurrentplayer.get(i).equals("4")) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl4Card3.setVisibility(View.VISIBLE);
                        Rlpl4Card3.startAnimation(anim_pl4_3_tra);

                    }
                }, delay * 200);


                anim_pl4_3_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Rlpl4Card3.setRotation(20);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

            } else if (arrcurrentplayer.get(i).equals("5")) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl5Card3.setVisibility(View.VISIBLE);
                        Rlpl5Card3.startAnimation(anim_pl5_3_tra);
                    }
                }, delay * 200);


                anim_pl5_3_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Rlpl5Card3.setRotation(20);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });


            } else if (arrcurrentplayer.get(i).equals("6")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Rlpl6Card3.setVisibility(View.VISIBLE);
                        Rlpl6Card3.startAnimation(anim_pl6_3_tra);
                        Log.e("card visible", "card visible..............");
                    }
                }, delay * 200);


                anim_pl6_3_tra.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Rlpl6Card3.setRotation(20);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

            }
        }

        // turnCardToSeen();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Functions.StopMusic();
        Functions.HideNotification(Table6_Activity.this);
        mSocket.disconnect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        instance = this;
    }

    public static Table6_Activity instance() {
        return instance;
    }

    public void NetworkConnected() {
        Log.e("Connect network", "network connection");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dNetWork.isShowing())
                    dNetWork.dismiss();
            }
        });

    }

    public void NetworkNotConnected() {
        Log.e("Connect network", "  no connected");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSocket.connect();
//                mSocket.connect();
//                dNetWork.show();

            }
        });

    }


    class Asynctask_Giftss extends AsyncTask<String, String, String> {


        JSONParser jParser = new JSONParser();
        String Sucess;
        JSONObject json = null;
        String jsonstr = "";
        Dialog dd;
        KProgressHUD kprogress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> par = new ArrayList<NameValuePair>();

            try {

                jsonstr = jParser
                        .makeHttpRequest(URLS.Domain + URLS.URL_GIFTS, "GET", par);

                json = new JSONObject(jsonstr);
//              String jsonstring = Webservice.Send_data(key,"http://18.218.76.134:3000/api/users/login");
                Log.e("response", URLS.Domain + URLS.URL_GIFTS + " =>" + json.toString());

                boolean Sucess = json.getBoolean("success");

                if (Sucess) {
                    JSONArray jsonarr = json.getJSONArray("Gifts");
                    arrGifts = new ArrayList<>();
                    for (int i = 0; i < jsonarr.length(); i++) {
                        JSONObject jrow = jsonarr.getJSONObject(i);
                        Gifts tt = new Gifts();
                        tt.setName(jrow.getString("name"));
                        tt.set_id(jrow.getString("_id"));
                        tt.setPictureUrl(jrow.getString("pictureUrl"));
                        tt.setPrice(jrow.getString("price"));
                        tt.setMp3Url(jrow.getString("mp3Url"));
//                        SharedPrefs.save(Table_Activity.this,SharedPrefs.);
                        arrGifts.add(tt);
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

        }
    }


    class Adapter_Gifts extends BaseAdapter {
        @Override
        public int getCount() {
            return 34;
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
            ImageView imgView;
            TextView txtPrice, txtGiftName;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater vi = (LayoutInflater) getApplicationContext()
                    .getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);
            holder h;
            if (convertView == null) {
                h = new holder();
                convertView = vi.inflate(R.layout.dialog_gift_ddalogg, null);
                h.imgView = convertView.findViewById(R.id.imgView);
                h.txtPrice = convertView.findViewById(R.id.txtPrice);
                h.txtGiftName = convertView.findViewById(R.id.txtGiftName);
                convertView.setTag(h);
            } else {
                h = (holder) convertView.getTag();
            }


            try {
                InputStream ims = getAssets().open("gift/img/img_" + (position + 1) + ".png");
                Drawable drawa = Drawable.createFromStream(ims, null);
                h.imgView.setImageDrawable(drawa);

            } catch (Exception e) {
                e.printStackTrace();
            }


            h.txtPrice.setText("10");


            return convertView;
        }
    }


    class Adapter_ReplaceCard extends BaseAdapter {
        @Override
        public int getCount() {
            return 52;
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
            ImageView imgdialback, imgdialnum, imgdialicon, imgdialBig;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater vi = (LayoutInflater) getApplicationContext()
                    .getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);
            holder h;
            if (convertView == null) {
                h = new holder();
                convertView = vi.inflate(R.layout.dialog_replace_card, null);
                h.imgdialback = convertView.findViewById(R.id.imgdialback);
                h.imgdialnum = convertView.findViewById(R.id.imgdialnum);
                h.imgdialicon = convertView.findViewById(R.id.imgdialicon);
                h.imgdialBig = convertView.findViewById(R.id.imgdialBig);
                convertView.setTag(h);
            } else {
                h = (holder) convertView.getTag();
            }

            String type = "";
            String numberrr = "";
            int numberrrsedsds;
            numberrrsedsds = (((int) Math.ceil(position / 13)) + 1);
            if (numberrrsedsds == 1) {
                type = "spade";
            } else if (numberrrsedsds == 2) {
                type = "club";
            } else if (numberrrsedsds == 3) {
                type = "heart";
            } else if (numberrrsedsds == 4) {
                type = "diamond";
            }
            if (position % 13 == 0) {
                numberrr = "1";
            } else if (position % 13 == 1) {
                numberrr = "2";
            } else if (position % 13 == 2) {
                numberrr = "3";
            } else if (position % 13 == 3) {
                numberrr = "4";
            } else if (position % 13 == 4) {
                numberrr = "5";
            } else if (position % 13 == 5) {
                numberrr = "6";
            } else if (position % 13 == 6) {
                numberrr = "7";
            } else if (position % 13 == 7) {
                numberrr = "8";
            } else if (position % 13 == 8) {
                numberrr = "9";
            } else if (position % 13 == 9) {
                numberrr = "10";
            } else if (position % 13 == 10) {
                numberrr = "11";
            } else if (position % 13 == 11) {
                numberrr = "12";
            } else if (position % 13 == 12) {
                numberrr = "13";
            }


            Log.e("Numberrr", position + "  num : " + numberrr);
            Functions.setcard(Table6_Activity.this, h.imgdialback, h.imgdialnum,
                    h.imgdialicon, h.imgdialBig, "true", type, numberrr);


            return convertView;
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

            par.add(new BasicNameValuePair("tableId", SelectTable.getId()));

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


                if ((int) Float.parseFloat(TotalTablePlayer) > 4) {
                    CloseGame();
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            par.add(new BasicNameValuePair("userName", SharedPrefs.getString(Table6_Activity.this, SharedPrefs.USERNAME, "")));
            par.add(new BasicNameValuePair("deviceId", SharedPrefs.getString(Table6_Activity.this, SharedPrefs.DEVICE_ID, "1234")));

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

                CloseGame();

            } else {

            }
        }

    }


}
