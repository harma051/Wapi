package com.wapi.flux.actions;

import java.util.HashMap;

/**
 * TODO: Description
 */
public class Action {
    private final ActionType type;
    private final HashMap<ValueKey, Object> data;

    Action(ActionType type, HashMap<ValueKey, Object> data) {
        this.type = type;
        this.data = data;
    }

    public static Builder type(ActionType type) {
        return new Builder().with(type);
    }

    public ActionType getType() {
        return type;
    }

    public HashMap getData() {
        return data;
    }

    public static class Builder {

        private ActionType type;
        private HashMap<ValueKey, Object> data;

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

        public Action build() {
            if (type == null) {
                throw new IllegalArgumentException("At least one key is required.");
            }
            return new Action(type, data);
        }
    }
}
