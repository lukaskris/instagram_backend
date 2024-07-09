package com.example.instagram.demo.controller;

import com.example.instagram.demo.dto.ApiResponse;
import com.example.instagram.demo.dto.CommentDto;
import com.example.instagram.demo.dto.CommentResponse;
import com.example.instagram.demo.model.Comment;
import com.example.instagram.demo.model.Post;
import com.example.instagram.demo.model.User;
import com.example.instagram.demo.service.CommentService;
import com.example.instagram.demo.service.JwtService;
import com.example.instagram.demo.service.PostService;
import com.example.instagram.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ApiResponse<CommentResponse> createComment(@RequestBody CommentDto comment, @RequestHeader("Authorization") String token) {
        String username = jwtService.extractUsername(token.replace("Bearer ", ""));
        User user = userService.getUserByUsername(username);
        Post post = postService.getPostById(comment.getPostId());

        Comment commentData = new Comment();
        commentData.setText(comment.getText());
        commentData.setPost(post);
        commentData.setUser(user);

        Comment result = commentService.saveComment(commentData);

        CommentResponse response = new CommentResponse(
                result.getText(),
                result.getId(),
                result.getCreatedAt(),
                result.getUser().getUsername(),
                result.getUser().getProfilePicture()
        );
        return new ApiResponse<>("", response);
    }

    @GetMapping
    public ApiResponse<List<CommentResponse>> getAllComments() {
        List<CommentResponse> comments = commentService.getAllComments().stream().map(comment -> new CommentResponse(
                comment.getText(),
                comment.getId(),
                comment.getCreatedAt(),
                comment.getUser().getUsername(),
                comment.getUser().getProfilePicture()

        )).toList();
        return new ApiResponse<>("", comments);
    }

    @GetMapping("/{id}")
    public ApiResponse<List<CommentResponse>> getCommentById(@PathVariable Long id) {
        List<CommentResponse> comments = commentService.getCommentByPostId(id).stream().map(
                comment -> new CommentResponse(
                        comment.getText(),
                        comment.getId(),
                        comment.getCreatedAt(),
                        comment.getUser().getUsername(),
                        comment.getUser().getProfilePicture()
                )
        ).toList();
        return new ApiResponse<>("", comments);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}