package com.example.user_management;

import com.example.user_management.exception.UserNotFoundException;
import com.example.user_management.model.User;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.service.UserService;
import com.example.user_management.service.dto.UserDTO;
import com.example.user_management.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The type User service test.
 */
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User mockUser;
    private UserDTO mockUserDTO;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        userService = new UserService(userMapper, userRepository);
        setupMockUsers();
    }

    /**
     * Test get user by id.
     */
    @Test
    public void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userMapper.toUserDTO(mockUser)).thenReturn(mockUserDTO);
        UserDTO user = userService.getUserById(1L);
        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
    }

    /**
     * Test get user by id user not found.
     */
    @Test
    public void testGetUserById_UserNotFound() {
        when(userRepository.findById(5L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(5L));
        assertEquals("User with ID 5 not found", exception.getMessage());
        verify(userRepository, times(1)).findById(5L);
    }

    /**
     * Test create user.
     */
    @Test
    public void testCreateUser() {
        when(userMapper.toUser(mockUserDTO)).thenReturn(mockUser);
        when(userRepository.save(mockUser)).thenReturn(mockUser);
        when(userMapper.toUserDTO(mockUser)).thenReturn(mockUserDTO);
        UserDTO savedUser = userService.createUser(mockUserDTO);
        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
        verify(userRepository, times(1)).save(mockUser);
    }

    /**
     * Test get all users.
     */
    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(mockUser));
        when(userMapper.toUserDTO(mockUser)).thenReturn(mockUserDTO);
        List<UserDTO> users = userService.getAllUsers();
        assertNotNull(users, "Users list should not be null");
        assertEquals(1, users.size(), "Users list should contain 1 users");
        verify(userRepository, times(1)).findAll();
    }

    /**
     * Test update user.
     */
    @Test
    public void testUpdateUser() {
        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.setUserId(1L);
        updatedUserDTO.setUsername("updateduser");
        updatedUserDTO.setEmail("updated@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userMapper.toUser(updatedUserDTO)).thenReturn(mockUser);
        when(userRepository.save(mockUser)).thenReturn(mockUser);
        when(userMapper.toUserDTO(mockUser)).thenReturn(updatedUserDTO);

        UserDTO updateUser = userService.updateUser(1L, updatedUserDTO);

        assertNotNull(updateUser, "Updated user should not be null");
        assertEquals("updateduser", updateUser.getUsername(), "Username should be updated");
        assertEquals("updated@example.com", updateUser.getEmail(), "Email should be updated");
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(mockUser);
    }

    /**
     * Test delete user.
     */
    @Test
    public void testDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        doNothing().when(userRepository).delete(mockUser);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(mockUser);
    }

    private void setupMockUsers() {
        mockUser = new User();
        mockUser.setUserId(1L);
        mockUser.setUsername("testuser");
        mockUser.setEmail("test@example.com");
        mockUser.setFirstName("Test");
        mockUser.setLastName("User");

        mockUserDTO = new UserDTO();
        mockUserDTO.setUserId(1L);
        mockUserDTO.setUsername("testuser");
        mockUserDTO.setEmail("test@example.com");
        mockUserDTO.setFirstName("Test");
        mockUserDTO.setLastName("User");
    }
}
