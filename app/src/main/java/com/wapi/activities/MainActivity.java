package com.wapi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.wapi.R;
import com.wapi.application.WapiApp;
import com.wapi.flux.actions.Action;
import com.wapi.flux.actions.CurrentWeatherAction;
import com.wapi.flux.dispatcher.Dispatcher;
import com.wapi.flux.store.CurrentWeatherStore;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_header_text)
    TextView _headerText;

    @BindView(R.id.rl_screen)
    RelativeLayout _screen;

    @Inject
    CurrentWeatherAction _currentWeatherAction;

    @Inject
    CurrentWeatherStore _currentWeatherStore;

    @Inject
    Dispatcher _dispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        WapiApp.component(this).inject(this);
        ButterKnife.bind(this);
        _currentWeatherAction.getCurrentWeather(45.096969, -93.143088, this);
    }

    @Subscribe
    public void onCurrentWeatherUpdate(CurrentWeatherStore.CurrentWeatherStoreChangeEvent event){
        _headerText.setText(_currentWeatherStore.getTemperature());
        _screen.setBackgroundResource(_currentWeatherStore.getSummaryResourceId());
    }

    /**
     * TODO: Remove in favor of generic attach/detach of views.
     */
    @Override
    protected void onResume() {
        super.onResume();
        _dispatcher.register(this);
        _dispatcher.register(_currentWeatherStore);
    }
    /**
     * TODO: Remove in favor of generic attach/detach of views.
     */
    @Override
    protected void onPause() {
        super.onPause();
        _dispatcher.unregister(this);
        _dispatcher.unregister(_currentWeatherStore);
    }
}
