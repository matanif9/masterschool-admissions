package com.masterschool;

import com.google.gson.JsonObject;

public abstract class Task {
    public String name;

    public Task(String name) {
        this.name = name;
    }

    public boolean shouldAppear(UserProgress progress) {
        return true;
    }

    public abstract boolean isPassed(UserProgress progress, JsonObject payload);
}
