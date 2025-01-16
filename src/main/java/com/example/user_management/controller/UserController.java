package com.example.user_management.controller;

import com.example.user_management.service.dto.UserDTO;
import com.example.user_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @Operation(summary = "Get a list of all users", description = "Returns a list of all registered users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of users"),
    })
    @GetMapping
    public List<UserDTO> getAllUsers() {
        logger.info("Fetching all users");
        return userService.getAllUsers();
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    @Operation(summary = "Get user by ID", description = "Returns a user by their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Gets user by user name.
     *
     * @param username the username
     * @return the user by user name
     */
    @Operation(summary = "Get user by username", description = "Returns a user by their username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("username/{username}")
    public ResponseEntity<UserDTO> getUserByUserName(@PathVariable String username) {
        logger.info("Fetching user with username: {}", username);
        return ResponseEntity.ok(userService.getUserByUserName(username));
    }

    /**
     * Create user response entity.
     *
     * @param userDTO the user dto
     * @return the response entity
     */
    @Operation(summary = "Create a new user", description = "Creates a new user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid user data")
    })
    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        logger.info("Creating new user: {}", userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userDTO));
    }

    /**
     * Update user response entity.
     *
     * @param id      the id
     * @param userDTO the user dto
     * @return the response entity
     */
    @Operation(summary = "Update user by ID", description = "Updates an existing user by their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid user data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    /**
     * Delete user response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "Delete user by ID", description = "Deletes a user by their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User successfully deleted.");
    }
}
