package com.freelearners.ibtha.server.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.freelearners.ibtha.server.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerClass {
//    TODO make functions context independent.

    private static final String TAG = "ServerClass";
    public void sendPOSTRequestToServer(Context context, JSONObject jsonObject, String URL, final ServerResponseCallback serverResponseCallback) {
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
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void sendGETRequestToServer(Context context, String URL, final ServerResponseCallback serverResponseCallback) {
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
        VolleySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);

    }
    public void sendPOSTArrayRequestToServer(Context context, String URL, final ServerResponseCallback serverResponseCallback) {
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
        VolleySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);

    }
}
