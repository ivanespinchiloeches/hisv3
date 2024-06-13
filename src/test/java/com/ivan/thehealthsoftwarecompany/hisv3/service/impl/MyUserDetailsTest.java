package com.ivan.thehealthsoftwarecompany.hisv3.service.impl;

import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.Role;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.List;

import static com.ivan.thehealthsoftwarecompany.hisv3.security.TokenJwtConfig.ROLE_ADMIN;
import static com.ivan.thehealthsoftwarecompany.hisv3.security.TokenJwtConfig.ROLE_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyUserDetailsTest {

    @Test
    public void testGetAuthorities() {
        Role role1 = new Role();
        role1.setName(ROLE_ADMIN);
        role1.setId(1L);

        Role role2 = new Role();
        role2.setName(ROLE_USER);
        role2.setId(2L);

        HashSet<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);

        User user = new User();
        user.setRoles(roles);

        MyUserDetails myUserDetails = new MyUserDetails(user);

        List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) myUserDetails.getAuthorities();

        assertEquals(2, authorities.size());
        assertEquals(ROLE_USER, authorities.get(0).getAuthority());
        assertEquals(ROLE_ADMIN, authorities.get(1).getAuthority());
    }
    @Test
    public void testGetPassword() {
        User user = new User();
        user.setPassword("myPassword");
        MyUserDetails myUserDetails = new MyUserDetails(user);
        assertEquals("myPassword", myUserDetails.getPassword());
    }

    @Test
    public void testGetUsername() {
        User user = new User();
        user.setUsername("myUsername");
        MyUserDetails myUserDetails = new MyUserDetails(user);
        assertEquals("myUsername", myUserDetails.getUsername());
    }
}
