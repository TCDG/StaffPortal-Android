package com.xelitexirish.staffportal_android.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.xelitexirish.staffportal_android.utils.Constants;
import com.xelitexirish.staffportal_android.utils.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by XeliteXirish on 29/11/2016 (www.xelitexirish.com)
 */

public class ApiHandler {

    private static final String TEMP_BASE_URL = "https://portal.scammersublounge.com";
    private static List<PunishmentObject> punishments = new ArrayList<>();

    public static void setupLists(Context context) {
        new UpdateReadData(context).execute();
    }

    public static List<PunishmentObject> getPunishments() {
        return punishments;
    }

    public static class UpdateReadData extends AsyncTask<Void, Void, Void> {

        private Context mContext;
        private ProgressDialog mProgressDialog;

        public UpdateReadData(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();/*
            this.mProgressDialog = new ProgressDialog(mContext);
            this.mProgressDialog.setTitle("Please wait...");
            this.mProgressDialog.setMessage("We are downloading all stop data so you don't have to do it again.");
            this.mProgressDialog.setCancelable(false);
            this.mProgressDialog.show();*/
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String readUrl = TEMP_BASE_URL + Constants.READ_ENDPOINT + "?token=FE32An3I@-naq3_*eJ";

            try {
                URL url = new URL(readUrl);

                if (readUrl.startsWith("http://")) {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(false);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
                    httpURLConnection.setConnectTimeout(15000);

                    httpURLConnection.connect();

                    if (httpURLConnection.getResponseCode() == 301) {

                        InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        httpURLConnection.disconnect();

                        try {
                            JSONArray jsonArray = new JSONArray(result.toString());
                            handleData(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(httpURLConnection.getInputStream().toString() + " : " + httpURLConnection.getResponseCode());
                    }


                } else if (readUrl.startsWith("https://")) {

                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setDoOutput(false);
                    httpsURLConnection.setRequestMethod("GET");
                    httpsURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
                    httpsURLConnection.setConnectTimeout(15000);

                    httpsURLConnection.connect();

                    if (httpsURLConnection.getResponseCode() == 200) {
                        InputStream in = new BufferedInputStream(httpsURLConnection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        httpsURLConnection.disconnect();

                        try {
                            JSONArray jsonArray = new JSONArray(result.toString());
                            handleData(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {

                        System.out.println(httpsURLConnection.getResponseCode());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private void handleData(JSONArray jsonObject) {

            if (jsonObject != null) {
                try {
                    for (int x = 0; x < jsonObject.length(); x++) {
                        JSONObject jsonItem = jsonObject.getJSONObject(x);

                        int ID = jsonItem.getInt("ID");
                        String date = jsonItem.getString("date");
                        String offender = jsonItem.getString("offender");
                        String admin = jsonItem.getString("admin");
                        String reason = jsonItem.getString("reason");
                        String info = jsonItem.getString("info");
                        String proof = jsonItem.getString("proof");
                        String expire = jsonItem.getString("expire");

                        PunishmentObject punishmentObject = new PunishmentObject(ID, date, offender, admin, reason, info, proof, expire);
                        punishments.add(punishmentObject);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }
    }
}
