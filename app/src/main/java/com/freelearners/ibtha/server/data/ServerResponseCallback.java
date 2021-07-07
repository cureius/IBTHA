package com.freelearners.ibtha.server.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface ServerResponseCallback {
    public void onJSONResponse(JSONObject jsonObject);
    public void onJSONArrayResponse(JSONArray jsonArray) throws JSONException;
    public void onError(Exception e);
}
