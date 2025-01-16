package com.example.user_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * The type User.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Instantiates a new User.
     */
    public User() {
        this.createdAt = LocalDateTime.now();
    }

}
