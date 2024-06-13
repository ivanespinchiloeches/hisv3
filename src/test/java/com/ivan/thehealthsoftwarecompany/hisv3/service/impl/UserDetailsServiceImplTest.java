package com.ivan.thehealthsoftwarecompany.hisv3.service.impl;

import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.User;
import com.ivan.thehealthsoftwarecompany.hisv3.data.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserDetailsServiceImplTest {

    @Test
    void loadUserByUsername_userExists_ReturnsMyUserDetails() {
        String username = "testUsername";

        User user = new User();
        user.setUsername(username);

        UserRepository mockRepository = mock(UserRepository.class);
        when(mockRepository.findByUsername(username)).thenReturn(Optional.of(user));

        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setUserRepository(mockRepository);

        assertDoesNotThrow(
            () -> {
                userDetailsService.loadUserByUsername(username);
            },
            "Expected loadUserByUsername() to not throw, but it did"
        );

        verify(mockRepository, times(1)).findByUsername(username);
    }

    @Test
    void loadUserByUsername_userNotExists_ThrowsUsernameNotFoundException() {
        String username = "nonExistentUsername";

        UserRepository mockRepository = mock(UserRepository.class);
        when(mockRepository.findByUsername(username)).thenReturn(Optional.empty());

        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setUserRepository(mockRepository);

        assertThrows(
            UsernameNotFoundException.class,
            () -> {
                userDetailsService.loadUserByUsername(username);
            },
            "Expected loadUserByUsername() to throw UsernameNotFoundException, but it didn't"
        );

        verify(mockRepository, times(1)).findByUsername(username);
    }
}