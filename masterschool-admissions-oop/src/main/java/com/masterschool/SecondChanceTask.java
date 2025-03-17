package com.masterschool;

import com.google.gson.JsonObject;

public class SecondChanceTask extends Task {
    public SecondChanceTask() {
        super("second_chance_test");
    }

    @Override
    public boolean shouldAppear(UserProgress progress) {
        Integer score = progress.getScore("iq_test");
        return score != null && score >= 60 && score <= 75;
    }

    @Override
    public boolean isPassed(UserProgress progress, JsonObject payload) {
        int score = payload.get("score").getAsInt();
        progress.saveScore(name, score);
        if (score > 75) {
            progress.completedTasks.add("iq_test");
            return true;
        }
        return false;
    }
}
