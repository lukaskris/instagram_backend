package com.example.instagram.demo.repository;


import com.example.instagram.demo.dto.PostResponse;
import com.example.instagram.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT p.id as postId, p.content as content, p.created_at as createdAt, " +
            "COUNT(DISTINCT l.id) as totalLikes, COUNT(DISTINCT c.id) as totalComments, " +
            "CASE WHEN COUNT(DISTINCT al.id) > 0 THEN true ELSE false END as likedByUser, " +
            "GROUP_CONCAT(i.url SEPARATOR ', ') as images " +
            "FROM post p " +
            "LEFT JOIN post_like l ON l.post_id = p.id " +
            "LEFT JOIN comment c ON c.post_id = p.id " +
            "LEFT JOIN post_like al ON al.post_id = p.id AND al.user_id = :userId " +
            "LEFT JOIN image i ON i.post_id = p.id " +
            "GROUP BY p.id, p.content, p.created_at",
            nativeQuery = true)
    Optional<List<PostResponse>> findPost(Long userId);

    @Query(value = "SELECT *" +
            "FROM post " +
            "WHERE id = :postId ",
            nativeQuery = true)
    Optional<Post> findPostById(Long postId);

    @Query(value = "SELECT p.id as postId, p.content as content, p.created_at as createdAt, " +
            "COUNT(DISTINCT l.id) as totalLikes, COUNT(DISTINCT c.id) as totalComments, " +
            "CASE WHEN COUNT(DISTINCT al.id) > 0 THEN true ELSE false END as likedByUser, " +
            "GROUP_CONCAT(i.url SEPARATOR ', ') as images " +
            "FROM post p " +
            "LEFT JOIN post_like l ON l.post_id = p.id " +
            "LEFT JOIN comment c ON c.post_id = p.id " +
            "LEFT JOIN post_like al ON al.post_id = p.id AND al.user_id = :userId " +
            "LEFT JOIN image i ON i.post_id = p.id " +
            "WHERE p.id = :postId " +
            "GROUP BY p.id, p.content, p.created_at",
            nativeQuery = true)
    Optional<PostResponse> findPost(Long userId, Long postId);
}