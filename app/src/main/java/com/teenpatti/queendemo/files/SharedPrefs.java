package com.teenpatti.queendemo.files;

import android.content.Context;
import android.content.SharedPreferences;

//SharedPreferences manager class
public class SharedPrefs {

    //    public static final java.lang.String NOTIFICATION_ON_OFF = "notification" ;
    //SharedPreferences file name
    private static String SHARED_PREFS_FILE_NAME = "Sharedpref";

    public static final String NOTIFICATION= "sound";
    public static final String ROLE= "roleee";
    public static final String ISSOUND = "sound";
    public static final String ISVIBRARE = "vibrate";
    //here you can centralize all your shared prefs keys
    public static final String USER_ID = "user id";
    public static final String CLIENT_ID = "display name";
    public static final String USERNAME = "username";
    public static final String GUID = "guid";
    public static final String DEFAULTAMT = "0";
    public static final String TOTALBET = "0.0";
    public static final String PASSWORD = "password";
    public static final String CHIPS = "chips";
    public static final String TYPE = "client id";
    public static final String PROFILEPIC = "profilePic";
    public static final String DISPLAY_NAME = "displatname";
    public static final String DEVICE_ID = "DEVICE_ID";
    public static final String NOTIFICATION_ON_OFF_TEST = "notification_on_off";


    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASSWORD = "user_password";

    public static final String USER_COUNTRY = "country";
    public static final String USER_CITY = "city";
    public static final String USER_SEX = "sex";

    public static final String USER_MOBILE = "mobileno";


    public static final String USER_COMPLETE_EXAM = "user_complete_exam";
    public static final String USER_COMPLETE_PRE_INTE = "user_complete_pre_inte";
    public static final String USER_COMPLETE_PRE_SCOPE = "user_complete_pre_scope";
    public static final String USER_COMPLETE_PRE_TIME = "user_complete_pre_time";
    public static final String USER_COMPLETE_PRE_COST = "user_complete_pre_cost";
    public static final String USER_COMPLETE_PRE_QUALITTY = "user_complete_pre_quality";
    public static final String USER_COMPLETE_PRE_HUMAN = "user_complete_pre_human";
    public static final String USER_COMPLETE_PRE_COMMU = "user_complete_pre_commu";
    public static final String USER_COMPLETE_PRE_RISK = "user_complete_pre_risk";
    public static final String USER_COMPLETE_PRE_PROCUME = "user_complete_pre_procume";
    public static final String USER_COMPLETE_PRE_STAKE = "user_complete_pre_stake";



    public static final String USER_AGE = "age";
    public static final String USER_STATE = "state";

    public static final String INTEGRATION_MANAGEMENT = "Integration Management";
    public static final String SCOPE_MANAGEMENT = "Scope Management";
    public static final String TIME_MANAGEMENT = "Time Management";
    public static final String COST_MANAGEMENT = "Cost Management";
    public static final String QUALITY_MANAGEMENT = "Quality Management";
    public static final String HUMAN_RESOURCE_MANAGEMENT = "Human Resource Management";
    public static final String COMMUNICATION_MANAGEMENT = "Communications Management";
    public static final String RISK_MANAGEMENT = "Risk Management";
    public static final String PROCUEMENT_MANAGEMENT = "Procurement Management";
    public static final String STAKEHOLDERMANAGEMENT = "Stakeholder Management";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void clearPrefs(Context context) {

        //TODO : Set KEY_IS_LOGIN=false if clear pref
//        if (getBoolean(context, KEY_IS_LOGIN))
        getPrefs(context).edit().clear().apply();
    }

    public static boolean contain(Context context, String key) {
        return getPrefs(context).contains(key);
    }

    //Save Booleans
    public static void savePref(Context context, String key, boolean value) {
        getPrefs(context).edit().putBoolean(key, value).apply();
    }

    //Get Booleans
    public static boolean getBoolean(Context context, String key) {
        return getPrefs(context).getBoolean(key, false);
    }

    //Get Booleans if not found return a predefined default value
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getPrefs(context).getBoolean(key, defaultValue);
    }

    //Strings
    public static void save(Context context, String key, String value) {
        getPrefs(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        return getPrefs(context).getString(key, "");
    }

    public static String getString(Context context, String key, String defaultValue) {
        return getPrefs(context).getString(key, defaultValue);
    }

    //Integers
    public static void save(Context context, String key, int value) {
        getPrefs(context).edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key) {
        return getPrefs(context).getInt(key, 0);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getPrefs(context).getInt(key, defaultValue);
    }

    //Floats
    public static void save(Context context, String key, float value) {
        getPrefs(context).edit().putFloat(key, value).apply();
    }

    public static float getFloat(Context context, String key) {
        return getPrefs(context).getFloat(key, 0);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        return getPrefs(context).getFloat(key, defaultValue);
    }

    //Longs
    public static void save(Context context, String key, long value) {
        getPrefs(context).edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key) {
        return getPrefs(context).getLong(key, 0);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        return getPrefs(context).getLong(key, defaultValue);
    }

}