package com.example.instagram.demo.dto;

import java.util.Date;
import java.util.List;

public class PostResponse {

    private String content;
    private Date createdAt;
    private List<String> images;
    private int likes;
    private int comments;
    private boolean alreadyLike;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public boolean isAlreadyLike() {
        return alreadyLike;
    }

    public void setAlreadyLike(boolean alreadyLike) {
        this.alreadyLike = alreadyLike;
    }
}
