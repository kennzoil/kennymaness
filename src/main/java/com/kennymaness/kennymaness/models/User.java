package com.kennymaness.kennymaness.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    /* Columns */

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    // Username
    @Column
    private String username;

    // Password
    @Column
    private String password;

    // Active Status
    @Column
    private boolean active;

//    // Roles
//    @Column
//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(
//            name = "assigned_Roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private String assignedRole;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAssignedRole() {
        return assignedRole;
    }

    public void setAssignedRole(String assignedRole) {
        this.assignedRole = assignedRole;
    }

    @Builder.Default
    @OneToMany(mappedBy="user")
    private List<Comments> comments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy="user")
    private List<BlogPost> blogPosts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy="user")
    private List<Likes> likes = new ArrayList<>();
}