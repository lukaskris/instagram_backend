package com.example.instagram.demo.repository;


import com.example.instagram.demo.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<PostLike, Long> {
}