package com.keshar.firebasedomo;

public class User {
    private String firstName;
    private String lastname;
    private int age;
    private String address;

    public User(String firstName, String lastname, int age, String address) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.age = age;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}
