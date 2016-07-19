package com.wapi.flux.actions;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Description
 */
public class Action {
    private final ActionType type;
    private final Map<ValueKey, Object> data;

    Action(ActionType type, Map<ValueKey, Object> data) {
        this.type = type;
        this.data = data;
    }

    public static Builder type(ActionType type) {
        return new Builder().with(type);
    }

    public ActionType getType() {
        return type;
    }

    public Map getData() {
        return data;
    }

    public static class Builder {

        private ActionType type;
        private Map<ValueKey, Object> data;

        Builder with(ActionType type) {
            if (type == null) {
                throw new IllegalArgumentException("Type may not be null.");
            }
            this.type = type;
            this.data = new HashMap<>();
            return this;
        }

        public Builder bundle(ValueKey key, Object value) {
            if (key == null) {
                throw new IllegalArgumentException("Key may not be null.");
            }

            if (value == null) {
                throw new IllegalArgumentException("Value may not be null.");
            }
            data.put(key, value);
            return this;
        }

        public Builder bundle(Map<ValueKey, Object> map){
            data = map;
            return this;
        }

        public Action build() {
            if (type == null) {
                throw new IllegalArgumentException("At least one key is required.");
            }
            return new Action(type, data);
        }
    }
}
