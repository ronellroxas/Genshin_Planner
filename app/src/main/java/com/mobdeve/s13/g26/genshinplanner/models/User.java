package com.mobdeve.s13.g26.genshinplanner.models;

public class User {
    private String userId;
    private String email;
    private String name;
    private String username;
    private String uid; //genshin uid
    private String main;

    public User() { }

    public User(String userId, String email, String name, String uid, String main) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.uid = uid;
        this.main = main;
    }

    public String getUserId() {
        return userId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return  "email: " + this.email + "\n" +
                "name: " + this.name + "\n" +
                "uid: " + this.uid + "\n" +
                "main: " + this.main;

    }
}
