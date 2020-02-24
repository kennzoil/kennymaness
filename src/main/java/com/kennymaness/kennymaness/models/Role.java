package com.kennymaness.kennymaness.models;

import lombok.*;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    /* Columns */

    // Role ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int role_id;

    // Name
    @Column
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}