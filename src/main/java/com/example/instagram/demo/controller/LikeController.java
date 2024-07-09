package com.example.instagram.demo.controller;

import com.example.instagram.demo.dto.ApiResponse;
import com.example.instagram.demo.dto.LikeDto;
import com.example.instagram.demo.dto.PostResponse;
import com.example.instagram.demo.model.Post;
import com.example.instagram.demo.model.PostLike;
import com.example.instagram.demo.model.User;
import com.example.instagram.demo.service.JwtService;
import com.example.instagram.demo.service.LikeService;
import com.example.instagram.demo.service.PostService;
import com.example.instagram.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ApiResponse<PostResponse> createLike(@RequestBody LikeDto likeDto, @RequestHeader("Authorization") String token) {
        String username = jwtService.extractUsername(token.replace("Bearer ", ""));
        User user = userService.getUserByUsername(username);
        PostLike userLike = likeService.getLikeByUser(user.getId());

        if (userLike != null) {
            likeService.deleteLike(userLike.getId());
            PostResponse post = postService.getPostById(user.getId(), likeDto.getPostId());
            return new ApiResponse<>("", post);
        } else {
            Post post = postService.getPostById(likeDto.getPostId());
            PostLike postLike = new PostLike();

            postLike.setUser(user);
            postLike.setPost(post);

            likeService.saveLike(postLike);
            PostResponse postResponse = postService.getPostById(user.getId(), likeDto.getPostId());
            return new ApiResponse<>("", postResponse);
        }
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