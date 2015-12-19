package com.example.bebi2.popularmovies.Network;

import org.json.JSONObject;

/**
 * Created by bebi2 on 7/27/2015.
 */
public class CheckJson {
    public static boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key) ? true : false;
    }
}
