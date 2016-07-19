package com.wapi.flux.actions;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wapi.flux.dispatcher.Dispatcher;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


/**
 * TODO: Description
 */
public class CurrentWeatherAction {

    private Dispatcher _dispatcher;

    @Inject
    public CurrentWeatherAction(Dispatcher dispatcher) {
        _dispatcher = dispatcher;
    }

    private static final String url = "https://api.forecast.io/forecast/";

    public void getCurrentWeather(double latitude, double longitude, Context context) {

        String fullUrl = url + ApiKey.FORECAST.getKey() + "/" + String.valueOf(latitude) + "," + String.valueOf(longitude);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, fullUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Map<ValueKey, Object> data = formatCurrentData(response);
                _dispatcher.dispatch(ActionType.CURRENT_WEATHER_UPDATE, data);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        Volley.newRequestQueue(context).add(request);
    }
    private Map<ValueKey, Object> formatCurrentData(JSONObject data){
        HashMap<ValueKey, Object> map = new HashMap<>();
        try {
            JSONObject currently = (JSONObject) data.get("currently");
            map.put(ValueKey.CURRENT_TEMPERATURE, (currently.getString("temperature")));
            map.put(ValueKey.CURRENT_SUMMARY, String.valueOf(data.get("icon")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }
}
