package com.kennymaness.kennymaness.models;

import javax.persistence.*;

@Entity
public class RoleAssignment {

    /* Columns */

    // Primary Key
    @EmbeddedId
    RoleAssignmentKey id;

    // User
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    // Role
    @ManyToOne
    @MapsId("role_id")
    @JoinColumn(name = "role_id")
    Role role;
}