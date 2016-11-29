package com.xelitexirish.staffportal_android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by XeliteXirish on 29/11/2016 (www.xelitexirish.com)
 */
public class NetworkUtils {

    public static boolean hasNetworkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showNoInternetMsg(Context context) {
        Toast.makeText(context, "No internet connection found..", Toast.LENGTH_SHORT).show();
    }
}
