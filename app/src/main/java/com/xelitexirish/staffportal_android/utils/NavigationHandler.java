package com.xelitexirish.staffportal_android.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.xelitexirish.staffportal_android.R;
import com.xelitexirish.staffportal_android.ui.FragmentHome;

/**
 * Created by XeliteXirish on 29/11/2016 (www.xelitexirish.com)
 */

public class NavigationHandler {

    private static final String TAG = NavigationHandler.class.getSimpleName();

    public static final int idHome = 1;
    public static final int idPunishmentsList = 2;
    public static final int idSettings = 20;

    public static void handleClick(Context context, IDrawerItem drawerItem) {
        long id = drawerItem.getIdentifier();

        if (id == idHome) {
            FragmentHome homeFragment = new FragmentHome();
            switchScreen(context, homeFragment);

        } else if (id == idPunishmentsList) {


        } else if (id == idSettings) {

        }
    }

    public static void switchScreen(Context context, Object screen) {
        if (screen instanceof Fragment) {
            try {
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, (Fragment) screen).addToBackStack(null).commit();
            } catch (ClassCastException e) {
                Log.e(TAG, "Can't get fragment manager");
            }

        } else if (screen instanceof Intent) {
            context.startActivity((Intent) screen);
        }
    }
}
