package com.ivan.thehealthsoftwarecompany.hisv3.data.dto.mapper;

import com.ivan.thehealthsoftwarecompany.hisv3.data.dto.UserDTO;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.User;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserToUserDTOTest {

    @Test
    public void testTransform() {
        // Initialise the User object
        User user = new User();
        user.setId(123L);
        user.setUsername("Username");
        user.setEmail("email@example.com");
        user.setPassword("Password");
        user.setEnabled(true);
        user.setRoles(new HashSet<>());

        // Initialise the transformer
        UserToUserDTO userToUserDTO = new UserToUserDTO();

        // Call the method under test
        UserDTO userDTO = userToUserDTO.transform(user);

        // Assert the results
        assertNotNull(userDTO, "Transformed UserDTO should not be null");
        assertEquals(user.getId(), userDTO.getId(), "Mismatch in id");
        assertEquals(user.getUsername(), userDTO.getUsername(), "Mismatch in username");
        assertEquals(user.getEmail(), userDTO.getEmail(), "Mismatch in email");
        assertEquals(user.getPassword(), userDTO.getPassword(), "Mismatch in password");
        assertEquals(user.getEnabled(), userDTO.getEnabled(), "Mismatch in enabled status");
        assertEquals(user.getRoles(), userDTO.getRoles(), "Mismatch in roles");
        assertEquals(false, userDTO.isAdmin(), "Admin should be false by default");
    }

}