package com.example.danamonmobile;

public class User {
    public String id;
    public String userName;
    public String email;
    public String password;
    public String role;

    public User(String id, String userName, String email, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}