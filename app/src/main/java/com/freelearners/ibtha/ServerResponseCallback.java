package com.freelearners.ibtha;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ServerResponseCallback {
    public void onJSONResponse(JSONObject jsonObject);
    public void onJSONArrayResponse(JSONArray jsonArray);
    public void onError(Exception e);
}
