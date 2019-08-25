package com.example.italika.Interfaces;

import org.json.JSONObject;


public interface CallbackRequest {
    void onResult(JSONObject response) throws Exception;

    void onError();
}
