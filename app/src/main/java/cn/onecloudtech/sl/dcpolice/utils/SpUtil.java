package cn.onecloudtech.sl.dcpolice.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import cn.onecloudtech.sl.dcpolice.model.User;

/**
 * Created by Administrator on 2016/4/5.
 */
public class SpUtil {
    static SharedPreferences prefs;
    public static final String REMEMBER_PASSWORD = "remPassWd";
    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean getRememberPassWd(){
        return prefs.getBoolean(REMEMBER_PASSWORD, false);
    }

    public static User getUser() {
        return new Gson().fromJson(prefs.getString("user", ""), User.class);
    }

    public static void setUser(User user) {
        prefs.edit().putString("user", new Gson().toJson(user)).commit();
    }
}
