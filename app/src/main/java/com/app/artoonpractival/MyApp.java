package com.app.artoonpractival;

import android.app.Application;
import android.content.Context;

import com.app.artoonpractival.CommonUtils.PrefKeys;
import com.app.artoonpractival.CommonUtils.Prefs;

public class MyApp extends Application {
    public static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        new Prefs.Builder().
                setContext(this).
                setMode(Context.MODE_PRIVATE).
                setUseDefaultSharedPreference(true).build();


    }

    public static MyApp getMyApp() {
        return myApp;
    }

    public static boolean isLogin() {
        return Prefs.getBoolean(PrefKeys.PREF_IS_LOGIN, false);
    }
}
