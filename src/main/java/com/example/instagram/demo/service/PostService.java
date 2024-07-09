package com.example.instagram.demo.service;


import com.example.instagram.demo.dto.PostResponse;
import com.example.instagram.demo.model.Post;
import com.example.instagram.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public List<PostResponse> getAllPosts(Long userId) {
        return postRepository.findPost(userId).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public PostResponse getPostById(Long userId, Long id) {
        return postRepository.findPost(userId, id).orElse(null);
    }

    public Post getPostById(Long postId) {
        return postRepository.findPostById(postId).orElse(null);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}