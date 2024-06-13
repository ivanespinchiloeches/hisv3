package com.ivan.thehealthsoftwarecompany.hisv3.data.dto;


import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotEmpty
    @Size(min=2, max=30)
    private String username;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    private Boolean enabled;

    private boolean admin;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "At least you need to select one Role")
    private Set<Role> roles = new LinkedHashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public boolean isAdmin() {
        return admin;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
