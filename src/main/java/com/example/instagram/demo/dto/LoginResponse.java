package com.example.instagram.demo.dto;


public class LoginResponse {
    private String username;
    private String profilePicture;
    private String token;
    private long expired;

    public LoginResponse(String username, String profilePicture, String token, long expired) {
        this.username = username;
        this.profilePicture = profilePicture;
        this.token = token;
        this.expired = expired;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpired() {
        return expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
    }
}