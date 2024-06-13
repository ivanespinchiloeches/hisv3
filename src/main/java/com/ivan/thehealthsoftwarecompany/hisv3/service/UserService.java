package com.ivan.thehealthsoftwarecompany.hisv3.service;

import com.ivan.thehealthsoftwarecompany.hisv3.data.dto.UserDTO;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    void saveUser(UserDTO userDto);

    User findUserByEmail(String email);

    List<UserDTO> findAllUsers();

    List<User> findAllPlainUsers();

    void deleteUser(Long id);


}
