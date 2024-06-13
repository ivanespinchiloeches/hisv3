package com.ivan.thehealthsoftwarecompany.hisv3.data.entity.mapper;


import com.ivan.thehealthsoftwarecompany.hisv3.data.dto.UserDTO;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUser {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserDTOToUser(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public User transform(@NotNull UserDTO userDTO) {

        User user = new User();
        if(userDTO.getId() != null)
        {
            user.setId(userDTO.getId());
        }

        user.setUsername(userDTO.getUsername());
        if (userDTO.getPassword() != null) {
            String passwordEncoded = passwordEncoder.encode(userDTO.getPassword());
            user.setPassword(passwordEncoded);
        }


        user.setEnabled(userDTO.getEnabled() != null ? userDTO.getEnabled() : false);
        user.setEmail(userDTO.getEmail());

        user.setRoles(userDTO.getRoles());


        return user;
    }
}
