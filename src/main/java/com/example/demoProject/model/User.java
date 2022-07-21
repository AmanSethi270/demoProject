package com.example.demoProject.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String password;

    public User(){};

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
//        String hashedPassword = encoder.encode(password);
//        this.password = hashedPassword;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Column(name="first_name", nullable = false)
    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    @Column(name="last_name", nullable = false)
    public String getLastName(){
    return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Column(name="email", nullable = false)
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Column(name="password", nullable = false)
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
