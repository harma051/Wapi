package com.wapi.flux.dispatcher;

import com.annimon.stream.Stream;
import com.squareup.otto.Bus;
import com.wapi.flux.actions.Action;
import com.wapi.flux.actions.ActionType;
import com.wapi.flux.actions.ValueKey;
import com.wapi.flux.store.StoreChangeEvent;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * TODO: Description
 */
public class Dispatcher {

    @Inject
    Bus bus;

    @Inject
    public Dispatcher(Bus bus1){
        bus = bus1;
    }

    public void register(Object cls){
        try {
            bus.register(cls);
        } catch (IllegalArgumentException e){

        }
    }

    public void unregister(Object cls){
        bus.unregister(cls);
    }

    public void emitChange(StoreChangeEvent e){
        bus.post(e);
    }

    public void dispatch(ActionType type, Object... data) {
        if (type == null) {
            throw new IllegalArgumentException("Type must not be empty");
        }

        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("Data must be a valid list of key,value pairs");
        }

        Action.Builder actionBuilder = Action.type(type);
        int i = 0;
        while (i < data.length) {
            ValueKey key =  (ValueKey)data[i++];
            Object value = data[i++];
            actionBuilder.bundle(key, value);
        }
        post(actionBuilder.build());
    }

    public void dispatch(ActionType type, Map<ValueKey, Object> map){
        Action.Builder actionBuilder = Action.type(type);
        actionBuilder.bundle(map);
        post(actionBuilder.build());
    }


    private boolean isEmpty(String type) {
        return type == null || type.isEmpty();
    }

    private void post(final Object event) {
        bus.post(event);
    }
}
