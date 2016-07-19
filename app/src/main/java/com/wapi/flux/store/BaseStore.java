package com.wapi.flux.store;

import com.wapi.flux.actions.Action;
import com.wapi.flux.dispatcher.Dispatcher;

/**
 * TODO: Description
 */
public abstract class BaseStore {

    public Dispatcher dispatcher;

    public BaseStore(Dispatcher dispatcher1){
        dispatcher = dispatcher1;
    }

    public abstract StoreChangeEvent changeEvent();
    public abstract void onAction(Action action);

    public void emitStoreChange() {
//        dispatcher.toString();
        dispatcher.emitChange(changeEvent());
    }
}
