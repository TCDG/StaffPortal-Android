package com.xelitexirish.staffportal_android.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by XeliteXirish on 03/12/2016 (www.xelitexirish.com)
 */
public class IntroManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context _context;

    private static final String KEY_IS_FIRST_LAUNCH = "KEY_IS_FIRST_LAUNCH";

    public IntroManager(Context context){
        this._context = context;
        this.preferences = context.getSharedPreferences(KEY_IS_FIRST_LAUNCH, Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime){
        this.editor.putBoolean(KEY_IS_FIRST_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch(){
        return preferences.getBoolean(KEY_IS_FIRST_LAUNCH, true);
    }
}
