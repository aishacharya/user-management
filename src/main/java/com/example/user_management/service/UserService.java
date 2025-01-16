package com.example.user_management.service;

import com.example.user_management.exception.UserNotFoundException;
import com.example.user_management.model.User;
import com.example.user_management.service.dto.UserDTO;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type User service.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Instantiates a new User service.
     *
     * @param userMapper     the user mapper
     * @param userRepository the user repository
     */
    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDTO).collect(Collectors.toList());
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    /**
     * Gets user by user name.
     *
     * @param username the username
     * @return the user by user name
     */
    public UserDTO getUserByUserName(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));

    }


    /**
     * Create user user dto.
     *
     * @param userDTO the user dto
     * @return the user dto
     */
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        user = userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    /**
     * Update user user dto.
     *
     * @param id          the id
     * @param updatedUser the updated user
     * @return the user dto
     */
    public UserDTO updateUser(Long id, UserDTO updatedUser) {
        User user = findByUserId(id);
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user = userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    /**
     * Delete user.
     *
     * @param id the id
     */
    public void deleteUser(Long id) {
        User user = findByUserId(id);
        userRepository.delete(user);
    }

    /**
     * Find by user id user.
     *
     * @param id the id
     * @return the user
     */
    public User findByUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }
}
