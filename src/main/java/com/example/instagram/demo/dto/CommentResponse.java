package com.example.instagram.demo.dto;


import java.util.Date;

public class CommentResponse {
    private String text;
    private Long id;
    private Date createdAt;
    private String username;
    private String profilePicture;

    public CommentResponse(String text, Long id, Date createdAt, String username, String profilePicture) {
        this.text = text;
        this.id = id;
        this.createdAt = createdAt;
        this.username = username;
        this.profilePicture = profilePicture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

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
}