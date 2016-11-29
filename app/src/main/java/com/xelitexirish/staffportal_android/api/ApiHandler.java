package com.xelitexirish.staffportal_android.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.xelitexirish.staffportal_android.utils.Constants;
import com.xelitexirish.staffportal_android.utils.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by XeliteXirish on 29/11/2016 (www.xelitexirish.com)
 */

public class ApiHandler {

    private static final String TEMP_BASE_URL = "https://portal.scammersublounge.com";

    public static void setupLists(Context context){
        new UpdateReadData(context).execute();
    }

    public static class UpdateReadData extends AsyncTask<Void, Void, Void> {

        private Context mContext;
        private ProgressDialog mProgressDialog;

        public UpdateReadData(Context context){
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.mProgressDialog = new ProgressDialog(mContext);
            this.mProgressDialog.setTitle("Please wait...");
            this.mProgressDialog.setMessage("We are downloading all stop data so you don't have to do it again.");
            this.mProgressDialog.setCancelable(false);
            this.mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String readUrl = TEMP_BASE_URL + Constants.READ_ENDPOINT;

            try {
                JSONParser jsonParser = new JSONParser();
                JSONArray jsonArray = jsonParser.makeHttpRequest(mContext, readUrl, "GET", new HashMap<String, String>());

                for (int x = 0; x < jsonArray.length(); x++){
                    JSONObject jsonItem = jsonArray.getJSONObject(x);
                    System.out.println(jsonItem.getString("offender"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
