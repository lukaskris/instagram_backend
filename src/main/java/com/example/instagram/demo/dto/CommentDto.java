package com.example.instagram.demo.dto;

public class CommentDto {
    private Long postId;
    private String text;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CommentDto(Long postId, String text) {
        this.postId = postId;
        this.text = text;
    }
}