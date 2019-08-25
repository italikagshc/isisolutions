package com.example.italika.Request;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.italika.Interfaces.CallbackRequest;

import org.json.JSONObject;


public final class Request {

    private static RequestQueue mRequestQueue;

    public static void context(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static void context(Activity context) {
            Italika.setActivity(context);
            mRequestQueue = Volley.newRequestQueue(context);

    }

    public static synchronized void execute(String url, final CallbackRequest callback) {
        url = Italika.urlDB+url;
        Log.e("italika", url);
        JsonObjectRequest objectRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, url.replace(" ", "%20").trim(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (callback != null) try {
                    callback.onResult(response);
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                    callback.onError();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse","#####################"+error.getMessage());
                if (callback != null) callback.onError();
            }
        });
        mRequestQueue.add(objectRequest);

    }

    public static synchronized void executeTimeout(String url, final CallbackRequest callback) {
        url = Italika.urlDB+url;
        Log.e("italika", url);
        JsonObjectRequest objectRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, url.replace(" ", "%20"), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (callback != null) try {
                    callback.onResult(response);
                } catch (Exception ignored) {
                    callback.onError();
                    Log.e("Error",  "E - - - - - -  " + ignored.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callback != null) callback.onError();
                error.printStackTrace();
            }
        });
        objectRequest.setShouldCache(false);
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(objectRequest);
    }

    public static synchronized void execute(String url) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, url.replaceAll(" ", "%20"), null, null, null);
        mRequestQueue.add(objectRequest);
    }

    public static RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }
}
