package com.example.library.model;

public class Author {
    private String fullName;
    private String phone;
    private String email;
    private double rating;

    public Author() {
    }

    public Author(String fullName, String phone, String email, double rating) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.rating = rating;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}