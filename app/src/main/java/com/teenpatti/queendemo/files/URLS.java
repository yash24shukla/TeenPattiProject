package com.teenpatti.queendemo.files;

public class URLS {

    public static String urlsocketconnection = "http://chat.socket.io";

    // Change your url in below
    public static String Main_Domain = "http://3.16.200.37";




//    Enable this below line if you are not using ngrok  on port 5050
    public static String Domain = Main_Domain + ":5050";



    public static String CURRENT_VERSION = "1.0";

    public static String URL_CHECKAPPUPDATE = Main_Domain + "/version.json";

    public static String URL_GIFTS = "/gift/getGifts";

    public static String URL_EDITUSER = Main_Domain + "/admin/action_update_user.php";

    public static String URL_PROFILEPICUPDATE = "/user/updateProfilePic";


//    Enable this below line if you are not using ngrok  on port 5050

    public static String URL_REGISTER = Main_Domain + "/admin/action_create_user.php";



    public static String URL_CHANGEPASS = "/user/changePassword";

    public static String PROFILE_BASE = Main_Domain + "/admin/upload/user/";

    public static String URL_LOGIN = "/user/login";

    public static String URL_VERIFY_DEVICE = "/user/verifyDevice";

    public static String URL_GET_USERDETAIL = "/user/getUserDetails";

    public static String URL_LOGIN_FREE = "/user/freeUserLogin";

    public static String URL_CLIENT = "/user/updateUser";

    public static String URL_TOTALPLAYER = "/user/totalplayer";

    public static String URL_TABLE = "/user/fetchTables";

    public static String URL_TABLE_PRIVATE = "/user/getPrivateTables";

    public static String URL_TABLE_BOOTAMOUT = "/user/checkTableBootAmounts";

    public static String URL_TABLE_FINAL_SELECT = "/user/checkTableByPlayerLeft";

    public static final String FORGOT_PASSWORD = URLS.Domain + "/user/forgot_password";

    public static final String WITHDRAWAL_REQUEST = URLS.Domain + "/user/withdrawal_request";

    public static final String ADD_CHIPS = URLS.Domain + "/user/addChips";

    public static final String GET_ALL_POSTS = URLS.Domain + "/user/posts";

}