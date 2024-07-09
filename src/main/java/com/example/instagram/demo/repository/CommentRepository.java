package com.example.instagram.demo.repository;


import com.example.instagram.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(
            value = "SELECT * FROM comment WHERE post_id = :postId",
            nativeQuery = true
    )
    Optional<List<Comment>> findByPostId(Long postId);
}