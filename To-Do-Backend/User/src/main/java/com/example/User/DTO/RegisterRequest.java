package com.example.User.DTO;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank(message = "username is required")
    public String userName;
    @NotBlank(message ="Email is required")
    public String email;
    @NotBlank(message = "password is required")
    public String password;
}
