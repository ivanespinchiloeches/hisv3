package com.ivan.thehealthsoftwarecompany.hisv3.data.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.Role;

import java.util.LinkedHashSet;
import java.util.Set;

public class UserDTOTest {
  
    /**
     * Test the `getId` method in the `UserDTO` class.
     * This method should return the ID of the created UserDTO object.
     */
    @Test
    public void testGetId() {
    
        // Given
        Long expectedId = 1L;
        UserDTO userDTO = new UserDTO();

        // When
        userDTO.setId(expectedId);

        // Then
        Long actualId = userDTO.getId();
        assertEquals(expectedId, actualId, "The expected ID does not match the actual ID.");
    }

    /**
     * Test the `getUsername` method in the `UserDTO` class.
     * This method should return the Username of the created UserDTO object.
     */
    @Test
    public void testGetUsername() {

        // Given
        String expectedUsername = "TestUser";
        UserDTO userDTO = new UserDTO();

        // When
        userDTO.setUsername(expectedUsername);

        // Then
        String actualUsername = userDTO.getUsername();
        assertEquals(expectedUsername, actualUsername, "The expected username does not match the actual username.");
    }

    /**
     * Test the `getPassword` method in the `UserDTO` class.
     * This method should return the password of the created UserDTO object.
     */
    @Test
    public void testGetPassword() {

        // Given
        String expectedPassword = "Password123";
        UserDTO userDTO = new UserDTO();

        // When
        userDTO.setPassword(expectedPassword);

        // Then
        String actualPassword = userDTO.getPassword();
        assertEquals(expectedPassword, actualPassword, "The expected password does not match the actual password.");
    }
    /**
     * Test the `getEnabled` method in the `UserDTO` class.
     * This method should return the enabled status of the created UserDTO object.
     */
    @Test
    public void testGetEnabled() {

        // Given
        Boolean expectedEnabled = true;
        UserDTO userDTO = new UserDTO();

        // When
        userDTO.setEnabled(expectedEnabled);

        // Then
        Boolean actualEnabled = userDTO.getEnabled();
        assertEquals(expectedEnabled, actualEnabled, "The expected enabled status does not match the actual enabled status.");
    }
    /**
     * Test the `getRoles` method in the `UserDTO` class.
     * This method should return the roles of the created UserDTO object.
     */
    @Test
    public void testGetRoles() {

        // Given
        Set<Role> expectedRoles = new LinkedHashSet<>();
        expectedRoles.add(new Role(1L, "Test Role"));
        UserDTO userDTO = new UserDTO();

        // When
        userDTO.setRoles(expectedRoles);

        // Then
        Set<Role> actualRoles = userDTO.getRoles();
        assertEquals(expectedRoles, actualRoles, "The expected roles do not match the actual roles.");
    }
    /**
     * Test the `getEmail` method in the `UserDTO` class.
     * This method should return the email of the created UserDTO object.
     */
    @Test
    public void testGetEmail() {

        // Given
        String expectedEmail = "test.user@gmail.com";
        UserDTO userDTO = new UserDTO();

        // When
        userDTO.setEmail(expectedEmail);

        // Then
        String actualEmail = userDTO.getEmail();
        assertEquals(expectedEmail, actualEmail, "The expected email does not match the actual email.");
    }
}
