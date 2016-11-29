package com.xelitexirish.staffportal_android.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by XeliteXirish on 29/11/2016 (www.xelitexirish.com)
 */

public class JSONParser {

    String charset = "UTF-8";
    HttpURLConnection httpURLConnection;
    DataOutputStream outputStream;
    StringBuilder result;
    URL urlObj;
    JSONArray jsonObject = null;
    StringBuilder stringBuilder;
    String paramsString;

    public JSONArray makeHttpRequest(Context context, String url, String method, HashMap<String, String> params) {
        if (NetworkUtils.hasNetworkConnection(context)) {
            stringBuilder = new StringBuilder();
            int i = 0;
            for (String key : params.keySet()) {
                try {
                    if (i != 0) {
                        stringBuilder.append("&");
                    }
                    stringBuilder.append(key).append("=").append(URLEncoder.encode(params.get(key), charset));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                i++;
            }

            if (method.equals("POST")) {
                // request method is POST
                try {
                    urlObj = new URL(url);

                    httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Accept-Charset", charset);
                    httpURLConnection.setReadTimeout(10000);
                    httpURLConnection.setConnectTimeout(15000);

                    httpURLConnection.connect();

                    paramsString = stringBuilder.toString();

                    outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    outputStream.writeBytes(paramsString);
                    outputStream.flush();
                    outputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (method.equals("GET")) {
                // request method is GET

                if (stringBuilder.length() != 0) {
                    url += "?" + stringBuilder.toString();
                }

                try {
                    urlObj = new URL(url);
                    httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                    httpURLConnection.setDoOutput(false);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Accept-Charset", charset);
                    httpURLConnection.setConnectTimeout(15000);

                    httpURLConnection.connect();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            try {
                //Receive the response from the server
                InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                Log.d("JSON Parser", "result: " + result.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

            httpURLConnection.disconnect();

            // try parse the string to a JSON object
            try {
                jsonObject = new JSONArray(result.toString());
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
                e.printStackTrace();
            }

            // return JSON Object
            return jsonObject;
        }else {
            NetworkUtils.showNoInternetMsg(context);
        }
        return new JSONArray();
    }
}
