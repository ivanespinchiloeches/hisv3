package com.ivan.thehealthsoftwarecompany.hisv3.data.dto.mapper;

import com.ivan.thehealthsoftwarecompany.hisv3.data.dto.UserDTO;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDTO {

    public UserDTO transform(@NotNull User user) {

        UserDTO userDto = new UserDTO();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setEnabled(user.getEnabled());
        if(user.getPassword() != null) {
            userDto.setPassword(user.getPassword());
        }
        else {
            userDto.setPassword("");
        }


        //Set<Role> roles = user.getRoles();

        //boolean isAdmin = roles.stream().anyMatch(role -> ROLE_ADMIN.equals(role.getName()));
        userDto.setRoles(user.getRoles());
        userDto.setAdmin(false);


        return userDto;
    }
}
