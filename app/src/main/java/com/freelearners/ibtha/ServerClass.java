package com.freelearners.ibtha;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerClass {
    final static String TAG = ServerClass.class.getName();

    public void sendPOSTRequestToServer(Context context, JSONObject jsonObject, String URL, final ServerResponseCallback serverResponseCallback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                jsonObject,
                response -> {
                    Log.d(TAG, "onResponse: " + response.toString());
                    serverResponseCallback.onJSONResponse(response);

                },
                error -> {
                    Log.e(TAG, "onErrorResponse: ", error);
                    serverResponseCallback.onError(error);
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void sendGETRequestToServer(Context context, String URL, final ServerResponseCallback serverResponseCallback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                response -> {
                    Log.d(TAG, "onResponse: " + response.toString());
                    try {
                        serverResponseCallback.onJSONArrayResponse(response);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                },
                error -> {
                    Log.e(TAG, "onErrorResponse: ", error);
                    serverResponseCallback.onError(error);

                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    public void sendPOSTArrayRequestToServer(Context context, String URL, final ServerResponseCallback serverResponseCallback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                URL,
                null,
                response -> {
                    Log.d(TAG, "onResponse: sendPOSTArrayRequestToServer" + response.toString());
                    try {
                        serverResponseCallback.onJSONArrayResponse(response);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                },
                error -> {
                    Log.e(TAG, "onErrorResponse: ", error);
                    serverResponseCallback.onError(error);

                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
