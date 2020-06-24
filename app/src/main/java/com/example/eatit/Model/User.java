package com.example.eatit.Model;

public class User {

    private String Name;
    private String Password;
    private String Phone;


    public User() {
    }

    public User(String name, String password, String phone) {
        Name = name;
        Password = password;
        Phone = phone;
    }

    public User(String toString, String toString1) {
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        Name = Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        Password = Password;
    }
}