package com.masterschool;

import java.util.ArrayList;
import java.util.List;

public class Step {
    public String name;
    public List<Task> tasks;

    public Step(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    public List<Task> getVisibleTasks(UserProgress progress) {
        List<Task> visible = new ArrayList<>();
        for (Task task : tasks) {
            if (task.shouldAppear(progress)) {
                visible.add(task);
            }
        }
        return visible;
    }

    public boolean isCompleted(UserProgress progress) {
        for (Task task : getVisibleTasks(progress)) {
            if (!progress.completedTasks.contains(task.name)) return false;
        }
        return true;
    }
}
