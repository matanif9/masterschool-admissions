package com.masterschool;

public class User {
    public String id;
    public String email;
    public int currentStep = 0;
    public String status = "in_progress";

    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }
}
