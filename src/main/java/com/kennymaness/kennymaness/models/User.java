package com.kennymaness.kennymaness.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class User {

    /* Columns */

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer user_id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    /* Instantiation */

    // Default Instantiation
    public User(){}

    // Custom Instantiation
    public User(String first_name, String last_name, String username, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
    }

    /* Getters & Setters */

    // access user_id
    public Integer getId() { return user_id; }

    // access first_name, last_name
    public String getFirstName() { return first_name; }
    public void setFirstName(String newFirstName) { this.first_name = newFirstName; }

    public String getLastName() { return last_name; }
    public void setLastName(String newLastName) { this.last_name = newLastName; }

    public String getFullName() { return first_name + " " + last_name; }

    // access username
    public String getUsername() { return username; }
    public void setUsername(String newUsername) { this.username = newUsername; }

    // access email
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

}