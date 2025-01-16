package com.example.user_management.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type User dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    @JsonProperty("userId")
    private Long userId;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @JsonProperty("username")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    @JsonProperty("email")
    private String email;

    @Size(max = 50, message = "First name must not be greater than 50 characters")
    @JsonProperty("firstName")
    private String firstName;

    @Size(max = 50, message = "Last name must not be greater than 50 characters")
    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

}
