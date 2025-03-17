package com.masterschool;

import java.util.*;

public class DB {
    public static Map<String, User> users = new HashMap<>();
    public static Map<String, UserProgress> progresses = new HashMap<>();
    public static List<Step> flow = AdmissionsFlow.createFlow();

    public static List<Step> getFlow() {
        return flow;
    }

    public static Step getCurrentStepForUser(UserProgress progress) {
        if (progress.currentStep < flow.size()) {
            return flow.get(progress.currentStep);
        }
        return null;
    }

    public static boolean isCurrentStepCompleted(UserProgress progress) {
        Step current = getCurrentStepForUser(progress);
        return current != null && current.isCompleted(progress);
    }

    public static Task findTaskByName(String name, UserProgress progress) {
        for (Step step : flow) {
            for (Task task : step.tasks) {
                if (task.name.equals(name) && task.shouldAppear(progress)) {
                    return task;
                }
            }
        }
        return null;
    }
}
