package com.example.instagram.demo.controller;

import com.example.instagram.demo.dto.PostResponse;
import com.example.instagram.demo.model.Image;
import com.example.instagram.demo.model.Post;
import com.example.instagram.demo.model.User;
import com.example.instagram.demo.service.FileStorageService;
import com.example.instagram.demo.service.JwtService;
import com.example.instagram.demo.service.PostService;
import com.example.instagram.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(consumes = {"multipart/form-data"})
    public Post createPost(
            @RequestPart("user") String username,
            @RequestPart("content") String content,
            @RequestPart("images") List<MultipartFile> images) {
        User user = userService.getUserByUsername(username);

        Post post = new Post();
        post.setUser(user);
        post.setContent(content);

        List<Image> imageEntities = new ArrayList<>();
        for (MultipartFile file : images) {
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = "/uploads/" + fileName;

            Image image = new Image();
            image.setUrl(fileDownloadUri);
            image.setPost(post);
            imageEntities.add(image);
        }
        post.setImages(imageEntities);

        return postService.savePost(post);
    }


    @GetMapping
    public List<PostResponse> getAllPosts(@PathVariable String username, @RequestHeader("Authorization") String token) {
        String user = jwtService.extractUsername(token);
        return postService.getAllPosts(user);
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}