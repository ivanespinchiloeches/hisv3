package com.ivan.thehealthsoftwarecompany.hisv3.service.impl;

import com.ivan.thehealthsoftwarecompany.hisv3.data.dto.UserDTO;
import com.ivan.thehealthsoftwarecompany.hisv3.data.dto.mapper.UserToUserDTO;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.User;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.mapper.UserDTOToUser;
import com.ivan.thehealthsoftwarecompany.hisv3.data.repository.RoleRepository;
import com.ivan.thehealthsoftwarecompany.hisv3.data.repository.UserRepository;
import com.ivan.thehealthsoftwarecompany.hisv3.exception.UserNotFoundException;
import com.ivan.thehealthsoftwarecompany.hisv3.service.UserService;
import jakarta.persistence.Tuple;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserToUserDTO userToDTOMapper;
    private final UserDTOToUser userDTOToUserMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserToUserDTO userToDTOMapper, UserDTOToUser userDTOToUserMapper) {
        this.userRepository = userRepository;
        this.userToDTOMapper = userToDTOMapper;
        this.userDTOToUserMapper = userDTOToUserMapper;
    }

    @Override
    public void saveUser(@NotNull UserDTO userDto) {
        User user = userDTOToUserMapper.transform(userDto);

        user.setEnabled(true);

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email)
    {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public List<User> findAllPlainUsers()
    {
        Sort sortByIdASC = Sort.by(Sort.Direction.ASC, "id");
        List<Tuple> tuples = userRepository.findAllWithoutRoles(sortByIdASC);


        List<User> users = tuples.stream()
                .filter(Objects::nonNull)
                .map(tuple -> {
                    User user = new User();
                    user.setId(tuple.get(0, Long.class));
                    user.setEmail(tuple.get(1, String.class));
                    user.setEnabled(tuple.get(2, Boolean.class));
                    user.setUsername(tuple.get(3, String.class));
                    return user;
                })
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> list = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = userToDTOMapper.transform(user);
            list.add(userDTO);
        }
        return list;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        else {
            throw new UserNotFoundException("User not found with id:" + id);
        }

    }

}
