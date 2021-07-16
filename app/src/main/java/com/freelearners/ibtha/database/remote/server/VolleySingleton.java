package com.freelearners.ibtha.database.remote.server;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
//    TODO why it is showing a memory leak?
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private final Context context;

    private VolleySingleton(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }
    public  static  synchronized  VolleySingleton getInstance(Context context){
        if (instance == null){
            instance = new VolleySingleton(context);
        }
        return instance;
    }
    public RequestQueue getRequestQueue(){
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;

    }
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
