package com.kennymaness.kennymaness.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

//    public

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer user_id;
    private String first_name;
    private String last_name;
    private String username;
    private String email;

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