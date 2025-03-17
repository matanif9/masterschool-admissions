package com.masterschool;

import com.google.gson.JsonObject;

public class IqTestTask extends Task {
    public IqTestTask() {
        super("iq_test");
    }

    @Override
    public boolean isPassed(UserProgress progress, JsonObject payload) {
        int score = payload.get("score").getAsInt();
        progress.saveScore(name, score);
        return score > 75;
    }
}
