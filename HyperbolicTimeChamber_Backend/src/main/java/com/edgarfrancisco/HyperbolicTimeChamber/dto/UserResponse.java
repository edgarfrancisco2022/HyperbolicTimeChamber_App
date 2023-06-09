package com.edgarfrancisco.HyperbolicTimeChamber.dto;

import com.edgarfrancisco.HyperbolicTimeChamber.model.User;
import com.edgarfrancisco.HyperbolicTimeChamber.model.User;

import java.util.Date;

public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String[] Authorities;

    public UserResponse() {
    }

    public UserResponse(Long id, String firstName, String lastName, String username,
                        String email, String profileImageUrl, Date lastLoginDate,
                        Date displayLastLogin, Date joinDate, String role, String[] authorities,
                        boolean isActive, boolean isLocked) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        Authorities = authorities;
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        Authorities = user.getAuthorities();
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getAuthorities() {
        return Authorities;
    }

    public void setAuthorities(String[] authorities) {
        Authorities = authorities;
    }

}
