package com.kennymaness.kennymaness.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
public class Comments {

    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private int id;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @NotNull
    private String comment;

    public Date getDate() {
        return date;
    }

    private Date date;

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    @ManyToOne
    @JoinColumn(name="blogPost_id", nullable=false)
    private BlogPost blogPost;

    public Comments(String comment){
        this.comment = comment;
        this.date = new Date();
    }

    public Comments(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public String dateFormat(){
        String pattern = "dd MMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern,new Locale("en", "US"));
        String dat = simpleDateFormat.format(date);
        return dat;
    }

}