package com.kennymaness.kennymaness.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
class RoleAssignmentKey implements Serializable {

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "role_id")
    private Integer role_id;
}