package com.ivan.thehealthsoftwarecompany.hisv3.service.impl;
import com.ivan.thehealthsoftwarecompany.hisv3.data.dto.UserDTO;
import com.ivan.thehealthsoftwarecompany.hisv3.data.dto.mapper.UserToUserDTO;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.Role;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.User;
import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.mapper.UserDTOToUser;
import com.ivan.thehealthsoftwarecompany.hisv3.data.repository.RoleRepository;
import com.ivan.thehealthsoftwarecompany.hisv3.data.repository.UserRepository;
import com.ivan.thehealthsoftwarecompany.hisv3.exception.UserNotFoundException;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static com.ivan.thehealthsoftwarecompany.hisv3.security.TokenJwtConfig.ROLE_ADMIN;
import static com.ivan.thehealthsoftwarecompany.hisv3.security.TokenJwtConfig.ROLE_USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserToUserDTO userToDTO;

    @Mock
    private UserDTOToUser userDTOToUser;

    @Test //OK, UserServiceImpl coverage 13%
    public void findUserByEmail_Should_Return_User_When_Email_Is_Valid() {
        MockitoAnnotations.openMocks(this);
        String email = "ivan22@mail.com";
        String password = "password";
        String username = "ivan22";

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);

        userRepository.save(user);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));



        User result = userServiceImpl.findUserByEmail(email);

        assertEquals(user, result);
    }

    @Test //OK, UserServiceImpl coverage 16%
    public void findAllPlainUsers_Should_Return_List_Of_Plain_Users() {
        MockitoAnnotations.openMocks(this);
        List<Tuple> tuples = new ArrayList<>();
        Tuple tuple1 = Mockito.mock(Tuple.class);
        Tuple tuple2 = Mockito.mock(Tuple.class);
        when(tuple1.get(0, Long.class)).thenReturn(1L);
        when(tuple2.get(0, Long.class)).thenReturn(2L);
        tuples.add(tuple1);
        tuples.add(tuple2);

        when(userRepository.findAllWithoutRoles(any(Sort.class))).thenReturn(tuples);

        List<User> result = userServiceImpl.findAllPlainUsers();

        assertEquals(2, result.size()); // Check if the size is the same
        assertEquals(1L, result.get(0).getId()); // Check if the IDs match
        assertEquals(2L, result.get(1).getId());
    }


    @Test //OK, UserServiceImpl coverage 61%
    public void deleteUser_Should_Succeed_When_Id_Is_Not_Null() {
        MockitoAnnotations.openMocks(this);
        Long id = 7L;
        doNothing().when(userRepository).deleteById(id);

        userServiceImpl.deleteUser(id);

        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void findAllUsers_Should_Return_List_Of_UserDtos_When_Users_Exist() {
        MockitoAnnotations.openMocks(this);
        List<User> users = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        UserDTO userDTO1 = new UserDTO();
        UserDTO userDTO2 = new UserDTO();
        List<UserDTO> userDTOs = Arrays.asList(userDTO1, userDTO2);

        when(userToDTO.transform(user1)).thenReturn(userDTO1);
        when(userToDTO.transform(user2)).thenReturn(userDTO2);

        List<UserDTO> result = userServiceImpl.findAllUsers();

        assertEquals(userDTOs, result);
    }

    @Test
    public void findAllUsers_Should_Return_EmptyList_When_No_Users_Exist() {
        MockitoAnnotations.openMocks(this);
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = userServiceImpl.findAllUsers();

        assertEquals(users.size(), result.size());
    }

    @Test
    public void getUserById_Should_Return_Null_When_User_Not_Found() {
        MockitoAnnotations.openMocks(this);
        Long id = 7L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserById(id));

        String expectedMessage = "User not found with id:" + id;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test //OK, UserServiceImpl coverage 97%
    public void getUserById_Should_Return_User_When_Id_Is_Valid() {
        MockitoAnnotations.openMocks(this);
        Long id = 7L;
        User user = new User();
        user.setId(id);
        user.setEmail("test@gmail.com");
        user.setUsername("testUser");
        user.setPassword("testPassword");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = userServiceImpl.getUserById(id);

        assertEquals(user, result);
    }



    @Test
    public void saveUser_Should_Save_User_When_UserDTO_Is_Valid() {
        MockitoAnnotations.openMocks(this);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testUser");
        userDTO.setPassword("testPassword");
        userDTO.setEmail("testEmail");

        Role role = new Role();
        role.setName(ROLE_USER);
        role.setId(1L);
        Optional<Role> roleOptional = Optional.of(role);
        when(roleRepository.findByName(ROLE_USER)).thenReturn(roleOptional);

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setEmail("testEmail");
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        when(userDTOToUser.transform(userDTO)).thenReturn(user);

        userServiceImpl.saveUser(userDTO);
        verify(userDTOToUser, times(1)).transform(userDTO);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void saveUser_Should_Save_User_As_Admin_When_UserDTO_Is_Valid() {
        MockitoAnnotations.openMocks(this);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testAdmin");
        userDTO.setPassword("testPassword");
        userDTO.setEmail("testEmail");
        userDTO.setAdmin(true);

        Role roleUser = new Role();
        roleUser.setName(ROLE_USER);
        roleUser.setId(1L);
        Optional<Role> roleUserOptional = Optional.of(roleUser);
        Role roleAdmin = new Role();
        roleAdmin.setName(ROLE_ADMIN);
        roleAdmin.setId(2L);
        Optional<Role> roleAdminOptional = Optional.of(roleAdmin);
        when(roleRepository.findByName(ROLE_USER)).thenReturn(roleUserOptional);
        when(roleRepository.findByName(ROLE_ADMIN)).thenReturn(roleAdminOptional);

        User user = new User();
        user.setUsername("testAdmin");
        user.setPassword("testPassword");
        user.setEmail("testEmail");
        user.setRoles(new HashSet<>(Arrays.asList(roleUser, roleAdmin)));
        when(userDTOToUser.transform(userDTO)).thenReturn(user);

        userServiceImpl.saveUser(userDTO);
        verify(userDTOToUser, times(1)).transform(userDTO);
        verify(userRepository, times(1)).save(user);
    }
}
