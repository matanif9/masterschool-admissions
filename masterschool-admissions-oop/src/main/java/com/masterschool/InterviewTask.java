package com.masterschool;

import com.google.gson.JsonObject;

public class InterviewTask extends Task {
    public InterviewTask() {
        super("perform_interview");
    }

    @Override
    public boolean isPassed(UserProgress progress, JsonObject payload) {
        return "passed_interview".equals(payload.get("decision").getAsString());
    }
}
