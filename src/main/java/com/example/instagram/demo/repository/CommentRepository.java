package com.example.instagram.demo.repository;


import com.example.instagram.demo.model.Comment;
import com.example.instagram.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}