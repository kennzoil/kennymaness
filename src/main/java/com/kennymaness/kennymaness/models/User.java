package com.kennymaness.kennymaness.models;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Table(name = "user")
public class User {

    /* Columns */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @Column(name = "roles")
//    @ManyToMany
    private HashSet<Role> roles;

    /* Instantiation */

    // Default Instantiation
    public User(){}

    // Custom Instantiation
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.active = true;
//        this.roles = "USER";
    }

    /* Getters & Setters */

    // access user_id
    public Integer getId() { return user_id; }

    // access username
    public String getUsername() { return username; }
    public void setUsername(String newUsername) { this.username = newUsername; }

    // access password
    public String getPassword() { return password; }
    public void setPassword(String newPassword) { this.password = newPassword; }

    // access activation status
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // access roles
    public HashSet<Role> getRoles() { return roles; }
    public void setRoles(HashSet<Role> roles) { this.roles = roles; }
}