package com.example.demoProject.controller;

import com.example.demoProject.exception.DoesNotFollowException;
import com.example.demoProject.exception.NoUserExitsException;
import com.example.demoProject.model.Follower;
import com.example.demoProject.model.User;
import com.example.demoProject.repository.FollowerRepository;
import com.example.demoProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FollowerController {
    @Autowired
    private FollowerRepository followerRepository;
    private UserRepository userRepository;

    @GetMapping("/followings")
    public List<Follower> getAllFollowings(){
        return followerRepository.findAll();
    }

    @GetMapping("/users/id/{userId}/following")
    public List<Follower> getAllFollowings(@PathVariable(value = "userId")Long userId){
//        User user = userRepository.findById(id).orElseThrow(()-> new NoUserExitsException("No user with this id"));
        return followerRepository.findByUserId(userId);
    }

    @GetMapping("/users/id/{id}/followers")
    public List<Follower>getAllFollowers(@PathVariable(value = "id")Long id){
//        User user = userRepository.findById(id).orElseThrow(()-> new NoUserExitsException("No user with this id"));
        return followerRepository.findByFollowingId(id);
    }

    @GetMapping("/users/id/{id}/following/followingId/{followingId}")
    public Follower getFollower(@PathVariable(value = "id")Long id, @PathVariable(value = "followingId") Long followingId){
//        User user = userRepository.findById(id).orElseThrow(()-> new NoUserExitsException("No user with this id"));
  //      User followedUser = userRepository.findById(followingId).orElseThrow(()-> new NoUserExitsException("No user with this id"));
        Follower following =followerRepository.findByUserIdAndFollowingId(id,followingId);
//
        if(following==null){
            throw new DoesNotFollowException("User is not following this user");
        }

        return following;


    }

    @PostMapping("/users/id/{id}/following/followingId/{followingId}")
    public Follower followUser(@PathVariable(value = "id")Long id, @PathVariable(value = "followingId")Long followingId){
//        User user = userRepository.findById(id).orElseThrow(()-> new NoUserExitsException("No user with this id"));
//        User followedUser = userRepository.findById(followingId).orElseThrow(()-> new NoUserExitsException("No user with this id"));

        Follower following = new Follower(id,followingId);

        followerRepository.save(following);

        return following;
    }

    @DeleteMapping("/users/id/{id}/following/followingId/{followingId}")
    public Map<String, Boolean>  removeFollowing(@PathVariable(value = "id")Long id, @PathVariable(value = "followingId")Long followingId){
        Follower following =followerRepository.findByUserIdAndFollowingId(id,followingId);
//
        if(following==null){
            throw new DoesNotFollowException("User is not following this user");
        }

        followerRepository.delete(following);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
