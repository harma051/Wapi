package com.wapi.application;

import android.app.Application;
import android.content.Context;

import com.wapi.components.ApplicationComponent;
import com.wapi.components.DaggerApplicationComponent;

/**
 * TODO: Description
 */
public class WapiApp extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerComponentInitializer.init(this);
    }

    public static ApplicationComponent component(Context context) {
        return ((WapiApp) context.getApplicationContext()).component;
    }


    public final static class DaggerComponentInitializer {

        public static ApplicationComponent init(WapiApp app) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(app))
                    .build();
        }
    }
}
