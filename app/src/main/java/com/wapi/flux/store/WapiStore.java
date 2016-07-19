package com.wapi.flux.store;

import com.squareup.otto.Subscribe;
import com.wapi.enums.Summary;
import com.wapi.flux.actions.Action;
import com.wapi.flux.actions.ValueKey;
import com.wapi.flux.dispatcher.Dispatcher;

import javax.inject.Inject;

/**
 * TODO: Description
 */
public class WapiStore extends BaseStore {

    @Inject
    Dispatcher dispatcher;

    public double temperature = 10;
    public int summaryResourceId = 0;

    @Inject
    public WapiStore(Dispatcher dispatcher){
        super(dispatcher);
        this.dispatcher = dispatcher;
        dispatcher.register(this);
    }

    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
            case CURRENT_WEATHER_UPDATE:
                temperature = (Double.valueOf(String.valueOf(action.getData().get(ValueKey.CURRENT_TEMPERATURE))));
                setSummaryResourceId(String.valueOf(action.getData().get(ValueKey.CURRENT_SUMMARY)));
                break;
        }
        emitStoreChange();
    }

    private void setSummaryResourceId(String currentSummary){
        summaryResourceId = Summary.getTypeByKey(currentSummary).getImageID();
    }

    public void emitStoreChange() {
        dispatcher.emitChange(changeEvent());
    }

    public StoreChangeEvent changeEvent() {
        return new WapiStore.WapiStoreChangeEvent();
    }

    public class WapiStoreChangeEvent implements StoreChangeEvent { }

    public double getTemperature() {
        return temperature;
    }

    public int getSummaryResourceId() {
        return summaryResourceId;
    }
}
