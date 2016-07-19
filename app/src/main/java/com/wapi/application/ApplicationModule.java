package com.wapi.application;

import android.content.Context;

import com.squareup.otto.Bus;
import com.wapi.data_access.WeatherDataAccess;
import com.wapi.flux.dispatcher.Dispatcher;
import com.wapi.flux.store.CurrentWeatherStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * TODO: Description
 */
@Module
public class ApplicationModule {

    private final WapiApp application;

    public ApplicationModule(WapiApp application) {
        this.application = application;
    }

    @Provides
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    Dispatcher provideDispatcher() {
        return new Dispatcher(provideBus());
    }

    @Provides
    @Singleton
    CurrentWeatherStore provideWapiStore(Dispatcher dispatcher) {
        return new CurrentWeatherStore(dispatcher);
    }

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    WeatherDataAccess provideWeatherApi() {
        return new WeatherDataAccess();
    }
}
