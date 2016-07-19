package com.wapi.data_access;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * TODO: Description
 */
public class WeatherDataAccess {

    private static final String key = "b63802cffc7acf6bc2e0ea6e36687c5d";
    private static final String url = "https://api.forecast.io/forecast/";


    public void getBlock(double latitude, double longitude, Context context) {

        String fullUrl = url + key + "/" + String.valueOf(latitude) + "," + String.valueOf(longitude);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, fullUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Wapi response: " + response.toString());
//                try {
//                    currentWeatherUpdateActionCreator.updateCurrentWeather(response);
//                    JSONObject test = (JSONObject) response.get("currently");
//                    test.get("temperature");
//                    currentWeatherUpdateActionCreator.updateCurrentTemperature((Double) test.get("temperature"));
//

//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        Volley.newRequestQueue(context).add(request);
    }
}
