package com.masterschool;

import com.google.gson.JsonObject;

public class SimpleTask extends Task {
    public SimpleTask(String name) {
        super(name);
    }

    @Override
    public boolean isPassed(UserProgress progress, JsonObject payload) {
        return true;
    }
}
