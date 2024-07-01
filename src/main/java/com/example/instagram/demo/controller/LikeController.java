package com.example.instagram.demo.controller;
import com.example.instagram.demo.model.PostLike;
import com.example.instagram.demo.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping
    public PostLike createLike(@RequestBody PostLike postLike) {
        return likeService.saveLike(postLike);
    }

    @GetMapping
    public List<PostLike> getAllLikes() {
        return likeService.getAllLikes();
    }

    @GetMapping("/{id}")
    public PostLike getLikeById(@PathVariable Long id) {
        return likeService.getLikeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
    }
}