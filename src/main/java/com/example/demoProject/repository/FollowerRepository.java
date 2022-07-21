package com.example.demoProject.repository;

import com.example.demoProject.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
    List<Follower> findByUserId(Long id);
    List<Follower> findByFollowingId(Long followingId);
    Follower findByUserIdAndFollowingId(Long userId,Long followingId);

}
