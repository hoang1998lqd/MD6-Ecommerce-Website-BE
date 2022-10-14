package com.example.md6be.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerPrinciple implements UserDetails {
    private Long id;
    private String emailAddress;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    private String image;
    private Integer status;
    private Collection<? extends GrantedAuthority> roles;

    public CustomerPrinciple(Long id, String emailAddress, String password, String name, String phoneNumber, String address, String image, Integer status, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.image = image;
        this.status = status;
        this.roles = roles;
    }

    public static CustomerPrinciple build(Customer customer){
        List<GrantedAuthority> authorities = customer.getRole().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return new CustomerPrinciple(
                customer.getId(),
                customer.getEmailAddress(),
                customer.getPassword(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getImage(),
                customer.getStatus(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
