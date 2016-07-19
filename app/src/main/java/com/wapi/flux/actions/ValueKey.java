package com.wapi.flux.actions;

/**
 * TODO: Description
 */
public enum ValueKey {
    CURRENT_TEMPERATURE ("current"),
    CURRENT_SUMMARY ("icon");

    private String key;

    ValueKey(String jsonKey) {
        key = jsonKey;
    }

    public String getKey() {
        return key;
    }
}
