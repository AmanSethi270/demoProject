package com.example.demoProject.model;

//import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "followers")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long followingId;

    public Follower(){};

    public Follower(Long userId, Long followingId){
        this.userId = userId;
        this.followingId = followingId;
    }


    public Long getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    @Column(name = "following_id",nullable = false)
    public Long getFollowingId(){
        return followingId;
    }

    public void setFollowingId(Long followingId){
        this.followingId = followingId;
    }
}
