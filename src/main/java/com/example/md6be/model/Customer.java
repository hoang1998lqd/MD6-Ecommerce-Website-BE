package com.example.md6be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Comparator;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Comparator<Customer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "email_address")
    private String emailAddress;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false,length = 400)
    private String address;

    @Column(length = 400)
    private String image;

    private Integer status = 1;

    @ManyToMany(targetEntity = Role.class,fetch = FetchType.EAGER)
    private Set<Role> role;


    @Override
    public int compare(Customer customer, Customer t1) {
        return t1.getStatus() - customer.getStatus();
    }
}
