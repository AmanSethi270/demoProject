package com.example.demoProject.controller;

import com.example.demoProject.model.Post;
import com.example.demoProject.model.User;
import com.example.demoProject.repository.PostRepository;
import com.example.demoProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/",allowCredentials ="true")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
   public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

}
