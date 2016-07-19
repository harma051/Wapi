package com.wapi.components;

import android.content.SharedPreferences;

import com.wapi.activities.MainActivity;
import com.wapi.application.ApplicationModule;
import com.wapi.application.WapiApp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * TODO: Description
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(WapiApp wapiApp);
    void inject(MainActivity mainActivity);
//    SharedPreferences sharedPreferences();
}
