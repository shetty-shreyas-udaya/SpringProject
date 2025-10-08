package com.example.User.DTO;
import jakarta.validation.constraints.NotBlank;


public class AuthRequest {
    @NotBlank(message = "Username is required")
    public String userName;
    @NotBlank(message = "password is required")
    public String password;
}
