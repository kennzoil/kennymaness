package com.kennymaness.kennymaness.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* Columns */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
//    @ManyToMany
    private String role;

    /* Instantiation */

    // Default Instantiation
    public Role() {}

    // Custom Instantiation
    public Role(String role) {
        this.role = role;
    }

    /* Getters & Setters */

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
