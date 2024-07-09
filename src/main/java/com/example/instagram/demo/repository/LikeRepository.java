package com.example.instagram.demo.repository;


import com.example.instagram.demo.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<PostLike, Long> {

    @Query(
            value = "SELECT * FROM post_like WHERE user_id = :userId",
            nativeQuery = true
    )
    Optional<PostLike> findLikeByUsername(Long userId);
}