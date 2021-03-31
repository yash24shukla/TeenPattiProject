package com.teenpatti.teenpattipremium.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.kaopiz.kprogresshud.KProgressHUD;
import com.teenpatti.teenpattipremium.R;
import com.teenpatti.teenpattipremium.api.JSONParser;
import com.teenpatti.teenpattipremium.files.DialogProgress;
import com.teenpatti.teenpattipremium.files.SharedPrefs;
import com.teenpatti.teenpattipremium.files.URLS;
import com.teenpatti.teenpattipremium.model.Functions;
import com.teenpatti.teenpattipremium.model.NetworkManager;
import com.teenpatti.teenpattipremium.model.ShowToast;
import com.skydoves.elasticviews.ElasticLayout;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class UserEdit_Activity extends AppCompatActivity {

    Dialog d;
    RelativeLayout RlImageUpload;
    ImageView imgUserImage;
    String ProfilePicPath;
    Switch SwitchNotification, SwitchSound, SwitchVibrate;
    Button btnChangePassword, btnLogout;
    TextView txtUSerName, txtUserChips;

    ElasticLayout llSave;
    String s_username;

    private static final String SAMPLE_CROPPED_IMAGE_NAME = "SampleCropImage";
    String isimageset = "no", imagePath;

    TextView txtAddChips;



    TextView txtUserNm;
    String displayName = "";

    TranslateAnimation mAnimation, mAnimation2,mAnimation3,mAnimation4;
    ImageView imgback1, imgback2, imgback3, imgback4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.useredit_layout);
        BindView();
        BindListner();



        Log.e("adss", "startttingggg");

        // Use an activity context to get the rewarded video instance.


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
    protected void onResume() {
        super.onResume();


        new CheckLogin_Asyctask().execute();

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
            par.add(new BasicNameValuePair("userName", SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.USERNAME, "")));
            par.add(new BasicNameValuePair("deviceId", SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.DEVICE_ID, "1234")));


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

                SharedPrefs.clearPrefs(UserEdit_Activity.this);
                Intent ii = new Intent(UserEdit_Activity.this, Login_Activity.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(ii);
            } else {

            }
        }

    }


    private void BindView() {

        txtUserNm = findViewById(R.id.txtUserNm);
        txtAddChips = findViewById(R.id.txtAddChips);
        llSave = findViewById(R.id.llSave);
        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.startplay(UserEdit_Activity.this, getResources().getString(R.string.mp_buttonClick));

                finish();
            }
        });
        btnLogout = findViewById(R.id.btnLogout);
        txtUserChips = findViewById(R.id.txtUserChips);
        txtUSerName = findViewById(R.id.txtUSerName);
        imgUserImage = findViewById(R.id.imgUserImage);

        txtUserChips.setText(SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.CHIPS));
        txtUserNm.setText("Username : " + SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.USERNAME));
        txtUSerName.setText(SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.DISPLAY_NAME));
        if (!SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.PROFILEPIC).equals(""))
            Picasso.get()
                    .load(SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.PROFILEPIC))
                    .placeholder(R.color.picasso_placeholder)
                    .into(imgUserImage);

        btnChangePassword = findViewById(R.id.btnChangePassword);
        SwitchNotification = findViewById(R.id.SwitchNotification);
        SwitchSound = findViewById(R.id.SwitchSound);
        SwitchVibrate = findViewById(R.id.SwitchVibrate);

        if (SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.NOTIFICATION).equals("yes"))
            SwitchNotification.setChecked(true);
        else
            SwitchNotification.setChecked(false);

        if (SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.ISSOUND).equals("yes"))
            SwitchSound.setChecked(true);
        else
            SwitchSound.setChecked(false);

        if (SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.ISVIBRARE).equals("yes"))
            SwitchVibrate.setChecked(true);
        else
            SwitchVibrate.setChecked(false);

        RlImageUpload = findViewById(R.id.RlImageUpload);
        d = new Dialog(UserEdit_Activity.this,
                R.style.DialogSlideAnim);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.table_info_dialog);
        d.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        d.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        d.getWindow().setGravity(Gravity.BOTTOM);
        d.setCanceledOnTouchOutside(true);

    }

    private void BindListner() {

        txtAddChips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        RlImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Functions.startplay(UserEdit_Activity.this, getResources().getString(R.string.mp_buttonClick));
                if (NetworkManager.isInternetConnected(UserEdit_Activity.this)) {

                    if (ContextCompat.checkSelfPermission(UserEdit_Activity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(UserEdit_Activity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 1);
                    }
                } else {
                    new ShowToast(UserEdit_Activity.this, getResources().getString(R.string.Internet_Connetion_Message), "error");
                }

            }
        });

        llSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Functions.startplay(UserEdit_Activity.this, getResources().getString(R.string.mp_buttonClick));
                displayName = txtUSerName.getText().toString();
                new Asynctas_AddEvents().execute();

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Functions.startplay(UserEdit_Activity.this, getResources().getString(R.string.mp_buttonClick));
                SharedPrefs.clearPrefs(UserEdit_Activity.this);
                Intent ii = new Intent(UserEdit_Activity.this, Login_Activity.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(ii);

            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Functions.startplay(UserEdit_Activity.this, getResources().getString(R.string.mp_buttonClick));
                startActivity(new Intent(UserEdit_Activity.this, ChangePassword_Activity.class));

            }
        });
        SwitchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPrefs.save(UserEdit_Activity.this, SharedPrefs.NOTIFICATION, "yes");
                } else {
                    SharedPrefs.save(UserEdit_Activity.this, SharedPrefs.NOTIFICATION, "no");
                }
            }
        });

        SwitchVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPrefs.save(UserEdit_Activity.this, SharedPrefs.ISVIBRARE, "yes");
                } else {
                    SharedPrefs.save(UserEdit_Activity.this, SharedPrefs.ISVIBRARE, "no");
                }
            }
        });

        SwitchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPrefs.save(UserEdit_Activity.this, SharedPrefs.ISSOUND, "yes");
                } else {
                    SharedPrefs.save(UserEdit_Activity.this, SharedPrefs.ISSOUND, "no");
                }
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                final Uri selectedUri = data.getData();
                if (selectedUri != null) {
                    String destinationFileName = SAMPLE_CROPPED_IMAGE_NAME;
                    destinationFileName += ".jpg";
                    UCrop uCrop = UCrop.of(selectedUri, Uri.fromFile(new File(getCacheDir(), destinationFileName)));
                    uCrop.withAspectRatio(2, 2);
                    UCrop.Options options = new UCrop.Options();
                    options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
                    options.setCompressionQuality(90);
                    options.setHideBottomControls(true);
                    options.setFreeStyleCropEnabled(false);
                    uCrop.withOptions(options);

                    uCrop.start(UserEdit_Activity.this);

                } else {
                    new ShowToast(UserEdit_Activity.this, "", "error");
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                handleCropResult(data);
            }
        }
    }

    private void handleCropResult(@NonNull Intent result) {
        final Uri resultUri = UCrop.getOutput(result);
        if (resultUri != null) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(new File(resultUri.getPath()).getAbsolutePath(), options);
            saveCroppedImage(resultUri);
        } else {

            new ShowToast(UserEdit_Activity.this, getResources().getString(R.string.Something_wronge), "error");
        }
    }

    private void saveCroppedImage(Uri resultUri) {
        Uri imageUri = resultUri;
        if (imageUri != null && imageUri.getScheme().equals("file")) {
            try {
                copyFileToDownloads(imageUri);
            } catch (Exception e) {
                Log.d("YEST", imageUri.toString(), e);
            }
        } else {
            Log.d("TEST", "ERROR");
        }
    }

    private void copyFileToDownloads(Uri croppedFileUri) throws Exception {

//      String downloadsDirectoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() ;

        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/.nomedia/");
        folder.mkdirs();

        String filename = String.format("%d_%s", Calendar.getInstance().getTimeInMillis(), croppedFileUri.getLastPathSegment());
        File saveFile = new File(folder.getParent(), filename);
        FileInputStream inStream = new FileInputStream(new File(croppedFileUri.getPath()));
        FileOutputStream outStream = new FileOutputStream(saveFile);
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inStream.close();
        outStream.close();
        isimageset = "yes";
        imagePath = saveFile.getPath().toString();
        imgUserImage.setImageURI(Uri.parse(imagePath));

//      new Asynctask_ProfilePic().execute();


    }


    private class Asynctas_AddEvents extends AsyncTask<String, Void, String> {

        JSONParser jsonParser = new JSONParser();
        String jsonStr;
        Dialog dd;
        String msg = "";
        Boolean error = false;
        HttpEntity resEntity;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dd = new DialogProgress().DialogProgressInitialoz(UserEdit_Activity.this);
            dd.show();
        }

        @Override
        protected String doInBackground(String... voids) {
            try {

                ArrayList<NameValuePair> values = new ArrayList<>();
                values.add(new BasicNameValuePair("_id", SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.USER_ID, "")));
                values.add(new BasicNameValuePair("userName", SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.USERNAME, "")));
                values.add(new BasicNameValuePair("password", SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.PASSWORD, "")));


                if (SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.ROLE, "").equals("adminuser"))
                    values.add(new BasicNameValuePair("type", "admin"));
                else
                    values.add(new BasicNameValuePair("type", SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.TYPE, "")));

//                values.add(new BasicNameValuePair("chips", SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.CHIPS, "")));
                values.add(new BasicNameValuePair("profilePic", SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.PROFILEPIC, "")));
                values.add(new BasicNameValuePair("displayName", displayName));
                values.add(new BasicNameValuePair("role", SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.ROLE, "")));

                if (isimageset.equals("yes")) {
                    jsonStr = doFileUpload();
                } else {
                    jsonStr = jsonParser.makeHttpRequest(URLS.URL_EDITUSER, "POST", values);
                }

                Log.e("Responce : ", URLS.URL_EDITUSER + " => " + jsonStr);

                if (jsonStr != null) {

                    SharedPrefs.save(UserEdit_Activity.this, SharedPrefs.PROFILEPIC, SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.PROFILEPIC, ""));

                    JSONObject jsonObj = new JSONObject(jsonStr);
                    error = jsonObj.getBoolean("success");
                    msg = jsonObj.getString("msg");


                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("errorr", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            dd.dismiss();

            if (error) {
                SharedPrefs.save(UserEdit_Activity.this, SharedPrefs.DISPLAY_NAME, displayName);
                new ShowToast(UserEdit_Activity.this, "Successfully Saved", "success");
                finish();
            } else {
                new ShowToast(UserEdit_Activity.this, msg, "error");
            }


        }

        private String doFileUpload() {

            String response_str = "";

            String urlString = URLS.URL_EDITUSER;
            Log.e("image ", imagePath);
            Log.e("url", urlString);
            try {
                File file1 = new File(imagePath);
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(urlString);
                FileBody bin1 = new FileBody(file1);

                org.apache.http.entity.mime.MultipartEntity reqEntity = new MultipartEntity();
                reqEntity.addPart("file", bin1);

                reqEntity.addPart("_id", new StringBody(SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.USER_ID, "")));
                reqEntity.addPart("userName", new StringBody(SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.USERNAME, "")));
                reqEntity.addPart("password", new StringBody(SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.PASSWORD, "")));
                reqEntity.addPart("type", new StringBody(SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.TYPE, "")));
//                reqEntity.addPart("chips", new StringBody(SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.CHIPS, "")));
                reqEntity.addPart("role", new StringBody(SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.ROLE, "")));

                if (SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.ROLE, "").equals("adminuser"))
                    reqEntity.addPart("type", new StringBody("admin"));
                else
                reqEntity.addPart("type",new StringBody( SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.TYPE, "")));



                Calendar calendar = Calendar.getInstance();
                long startTime = calendar.getTimeInMillis();


                reqEntity.addPart("profilePic", new StringBody(URLS.PROFILE_BASE + SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.USERNAME, "") + ".jpg?v=" + startTime));


                Log.e("url imge :", URLS.PROFILE_BASE + SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.USERNAME, "") + ".jpg?v=" + startTime + "");
                reqEntity.addPart("displayName", new StringBody(displayName));

                SharedPrefs.save(UserEdit_Activity.this, SharedPrefs.PROFILEPIC, URLS.PROFILE_BASE + SharedPrefs.getString(UserEdit_Activity.this, SharedPrefs.USERNAME, "") + ".jpg?v=" + startTime + "");
                post.setEntity(reqEntity);
                HttpResponse response = client.execute(post);
                resEntity = response.getEntity();
                response_str = EntityUtils.toString(resEntity);
                if (resEntity != null) {
                    Log.i("RESPONSE", response_str);

                }
            } catch (Exception ex) {
                Log.e("Debug", "error: " + ex.getMessage(), ex);
            }
            return response_str;
        }

    }


}
