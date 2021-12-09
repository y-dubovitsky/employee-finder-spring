package ru.ydubovitsky.engineerblog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.engineerblog.dto.PostDto;
import ru.ydubovitsky.engineerblog.entity.Post;
import ru.ydubovitsky.engineerblog.service.PostService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> all = postService.getAllPosts();
        return ResponseEntity.ok(all);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<Object> addPost(
            @RequestBody PostDto postDto,
            Principal principal
    ) {
        Post post = postService.addPost(postDto, principal);
        return ResponseEntity.ok(post);
    }
}
