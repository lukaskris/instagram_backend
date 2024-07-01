package com.example.instagram.demo.service;

import com.example.instagram.demo.model.PostLike;
import com.example.instagram.demo.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public PostLike saveLike(PostLike postLike) {
        return likeRepository.save(postLike);
    }

    public List<PostLike> getAllLikes() {
        return likeRepository.findAll();
    }

    public PostLike getLikeById(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }
}