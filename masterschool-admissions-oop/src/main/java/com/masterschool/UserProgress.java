package com.masterschool;

import java.util.*;

public class UserProgress {
    public String userId;
    public Set<String> completedTasks = new HashSet<>();
    public Map<String, Integer> taskScores = new HashMap<>();
    public Map<String, String> taskTimestamps = new HashMap<>();
    public int currentStep = 0;

    public UserProgress(String userId) {
        this.userId = userId;
    }

    public void saveScore(String taskName, int score) {
        taskScores.put(taskName, score);
    }

    public Integer getScore(String taskName) {
        return taskScores.get(taskName);
    }
}
