package com.kennymaness.kennymaness.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Likes {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private BlogPost blogPost;

    public Likes(User user, BlogPost blogPost) {
        this.user = user;
        this.blogPost = blogPost;
    }

    public Likes() {
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setAnimePost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    public boolean equals(Likes o) {

        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o.getClass() != getClass()) {
            return false;
        }

        Likes like = (Likes) o;
        return (like.getUser() == getUser() && like.getBlogPost() == getBlogPost());
    }
}