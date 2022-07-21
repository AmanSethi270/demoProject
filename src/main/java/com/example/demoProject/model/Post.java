package com.example.demoProject.model;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
    private Long userId;
    private Long postId;
    private String title;
    private String description;

    public  Post(){};

    public Post(Long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getPostId(){
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Column(name = "user_id", nullable = false)
    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    @Column(name = "title", nullable = false)
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    @Column(name = "description", nullable = false)
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
