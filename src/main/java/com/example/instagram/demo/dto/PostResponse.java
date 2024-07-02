package com.example.instagram.demo.dto;

import java.util.Date;
import java.util.List;

public interface PostResponse {
    Long getPostId();
    String getContent();
    Date getCreatedAt();
    Long getTotalLikes();
    Long getTotalComments();
    Boolean getLikedByUser();
    List<String> getImages();
}