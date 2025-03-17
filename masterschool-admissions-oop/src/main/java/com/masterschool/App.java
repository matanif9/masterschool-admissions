package com.masterschool;

import static spark.Spark.*;
import com.google.gson.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        port(4567);
        Gson gson = new Gson();

        post("/user", (req, res) -> {
            String email = gson.fromJson(req.body(), JsonObject.class).get("email").getAsString();
            String id = UUID.randomUUID().toString();
            User user = new User(id, email);
            DB.users.put(id, user);
            DB.progresses.put(id, new UserProgress(id));
            return gson.toJson(Map.of("user_id", id));
        });

        get("/flow", (req, res) -> gson.toJson(DB.getFlow()));

        get("/user/:id/current", (req, res) -> {
            String id = req.params(":id");
            UserProgress progress = DB.progresses.get(id);
            if (progress == null) return "User not found";
            return gson.toJson(DB.getCurrentStepForUser(progress));
        });

        put("/user/:id/complete", (req, res) -> {
            String id = req.params(":id");
            JsonObject payload = gson.fromJson(req.body(), JsonObject.class);
            String taskName = payload.get("task_name").getAsString();
            String timestamp = payload.has("timestamp") ? payload.get("timestamp").getAsString() : null;

            UserProgress progress = DB.progresses.get(id);
            if (progress == null) return "User not found";

            Task task = DB.findTaskByName(taskName, progress);
            if (task == null || !task.shouldAppear(progress)) return "Task not allowed";
            if (!task.isPassed(progress, payload)) {
                DB.users.get(id).status = "rejected";
                return "User failed task: " + taskName;
            }

            progress.completedTasks.add(taskName);
            if (timestamp != null) {
                progress.taskTimestamps.put(taskName, timestamp);
            }

            if (DB.isCurrentStepCompleted(progress)) {
                progress.currentStep++;
                if (progress.currentStep >= DB.flow.size()) {
                    DB.users.get(id).status = "accepted";
                }
            }

            return "Task marked as completed.";
        });

        get("/user/:id/status", (req, res) -> {
            String id = req.params(":id");
            User user = DB.users.get(id);
            if (user == null) return "User not found";
            return gson.toJson(Map.of("status", user.status));
        });

        awaitInitialization();
        System.out.println("Server is running on http://localhost:4567");
    }
}
