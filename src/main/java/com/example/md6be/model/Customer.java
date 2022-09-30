package com.example.md6be.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "email_address")
    private String emailAddress;

    private String password;


    private String name;

    @Column(nullable = false, unique = true, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false,length = 400)
    private String address;

    @Column(length = 400)
    private String image;

    private Integer status;

    @ManyToMany(targetEntity = Role.class,fetch = FetchType.EAGER)
    private Set<Role> role;


}
