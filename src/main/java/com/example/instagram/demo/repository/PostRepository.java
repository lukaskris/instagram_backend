package com.example.instagram.demo.repository;


import com.example.instagram.demo.dto.PostResponse;
import com.example.instagram.demo.model.Image;
import com.example.instagram.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p.content, p.createdAt, p.images, " +
            "COUNT(l) as likes, COUNT(c) as comments, " +
            "CASE WHEN COUNT(al) > 0 THEN true ELSE false END) " +
            "FROM Post p " +
            "LEFT JOIN p.likes l " +
            "LEFT JOIN p.comments c " +
            "LEFT JOIN p.likes al ON al.user.username = :username ")
    Optional<List<PostResponse>> findPost(@Param("username") String username);
}