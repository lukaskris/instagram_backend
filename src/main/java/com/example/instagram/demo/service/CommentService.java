package com.example.instagram.demo.service;


import com.example.instagram.demo.model.Comment;
import com.example.instagram.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<Comment> getCommentByPostId(Long id) {
        return commentRepository.findByPostId(id).orElse(null);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}