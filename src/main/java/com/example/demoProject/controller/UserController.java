package com.example.demoProject.controller;

import com.example.demoProject.exception.IncorrectPasswordException;
import com.example.demoProject.exception.NoPostFoundException;
import com.example.demoProject.exception.NoUserExitsException;
import com.example.demoProject.exception.UserAlreadyExistsException;
import com.example.demoProject.model.Post;
import com.example.demoProject.model.User;
import com.example.demoProject.repository.PostRepository;
import com.example.demoProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id)
        throws NoUserExitsException{
        User user = userRepository.findById(id).orElseThrow(()-> new NoUserExitsException("No user with this id"));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users/email/{email}")
    public User getUserByEmail(@PathVariable(value = "email") String email){
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new NoUserExitsException("no user with this email");
        }

        return user;
    }



    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User user){

        User temp = userRepository.findByEmail(user.getEmail());

         if(temp!=null) {

             throw new UserAlreadyExistsException("User exits with this email");
         }

        String hashed = encoder.encode(user.getPassword());
        user.setPassword(hashed);
        userRepository.save(user);

        User res = new User();

        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setEmail(user.getEmail());

        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){

         User temp  = userRepository.findByEmail(user.getEmail());

         if(temp==null){
             throw new NoUserExitsException("No user found");
         }

         if(!encoder.matches(user.getPassword(), temp.getPassword())) {
             throw new IncorrectPasswordException("Incorrect Password");
         }
        User res = new User();
        res.setEmail(temp.getEmail());
        res.setFirstName(temp.getFirstName());
        res.setLastName(temp.getLastName());

        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/users/id/{id}/posts")
    public List<Post> getAllPostsByUser(@PathVariable(value = "id") Long id){
        return postRepository.findByUserId(id);
    }

    @GetMapping("/users/id/{id}/posts/{postId}")
    public Post getPostById(@PathVariable(value = "id")Long id,@PathVariable(value = "postId") Long postId){
        User user = userRepository.findById(id).orElseThrow(()-> new NoUserExitsException("No user with this id"));
        Post post = postRepository.findById(postId).orElseThrow(()-> new NoPostFoundException("No post found with this post id"));
        return postRepository.findById(postId).get();

    }

    @PostMapping("/users/id/{id}/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable(value = "id") Long id){

        User user = userRepository.findById(id).orElseThrow(()-> new NoUserExitsException("No user with this id"));
        post.setUserId(id);
        postRepository.save(post);
        return ResponseEntity.ok().body(post);
    }

    @PutMapping("/users/id/{id}/posts/{postId}")
    public Post updatePost(@PathVariable(value = "id") Long id, @PathVariable(value = "postId")Long postId, @RequestBody Post post){
        User user = userRepository.findById(id).orElseThrow(()-> new NoUserExitsException("No user with this id"));
        Post updatedPost = postRepository.findById(postId).orElseThrow(()-> new NoPostFoundException("No post found with this post id"));

        updatedPost.setTitle(post.getTitle());
        updatedPost.setDescription(post.getDescription());

        Post finalPost = postRepository.save(updatedPost);

        return finalPost;

    }

    @DeleteMapping("/users/id/{id}/posts/{postId}")
    public Map<String, Boolean> deletePost(@PathVariable(value = "id") Long id, @PathVariable(value = "postId") Long postId){
        User user = userRepository.findById(id).orElseThrow(()-> new NoUserExitsException("No user with this id"));
        Post post = postRepository.findById(postId).orElseThrow(()-> new NoPostFoundException("No post found with this post id"));
        postRepository.delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }










}
