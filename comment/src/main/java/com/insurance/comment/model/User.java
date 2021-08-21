package com.insurance.comment.model;

import com.insurance.comment.model.enumeration.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USER")
@Data
public class User extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", unique = true)
    private String username;

    private String password;

    private String mobileNumber;

    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;
}
