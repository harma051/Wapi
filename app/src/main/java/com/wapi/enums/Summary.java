package com.wapi.enums;

import com.wapi.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Description
 */
public enum Summary {
    CLEAR_DAY (R.mipmap.clear_day, "clear-day"),
    CLEAR_NIGHT (R.mipmap.clear_night, "clear-night"),
    RAIN (R.mipmap.rain, "rain"),
    SNOW (R.mipmap.snow, "snow"),
    SLEET (R.mipmap.sleet, "sleet"),
    WIND (R.mipmap.wind, "wind"),
    FOG (R.mipmap.fog, "fog"),
    CLOUDY (R.mipmap.cloudy, "cloudy"),
    PARTLY_CLOUDY_DAY (R.mipmap.partly_cloudy_day, "partly-cloudy-day"),
    PARTLY_CLOUDY_NIGHT (R.mipmap.partly_cloudy_night, "partly-cloudy-night"),
    DEFAULT (R.mipmap.clear_day, "default");

    private int imageID;
    private String key;
    private static final Map<String, Summary> LOOKUP_MAP;

    static {
        Map<String, Summary> temp = new HashMap<>();
        for (Summary summary : Summary.values()) {
            temp.put(summary.getKey(), summary);
        }
        LOOKUP_MAP = Collections.unmodifiableMap(temp);
    }

    public static Summary getTypeByKey(String key){
        if (LOOKUP_MAP.containsKey(key)){
            return LOOKUP_MAP.get(key);
        }
        return DEFAULT;
    }

    Summary(int correspondingImage, String jsonKey) {
        imageID = correspondingImage;
        key = jsonKey;
    }

    public int getImageID() {
        return imageID;
    }

    public String getKey() {
        return key;
    }
}
