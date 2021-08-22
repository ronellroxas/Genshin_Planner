package com.mobdeve.s13.g26.genshinplanner.models;

public class User {
    private String email;
    private String name;
    private String uid;
    private String main;

    public User(String email, String name, String uid, String main) {
        this.email = email;
        this.name = name;
        this.uid = uid;
        this.main = main;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
