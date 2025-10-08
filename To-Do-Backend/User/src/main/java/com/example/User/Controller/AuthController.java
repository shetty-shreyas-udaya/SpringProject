package com.example.User.Controller;

import com.example.User.DTO.AuthRequest;
import com.example.User.DTO.AuthResponse;
import com.example.User.DTO.RegisterRequest;
import com.example.User.Model.User;
import com.example.User.Service.UserService;
import com.example.User.Utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    final private UserService userService;

    final private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterRequest req )
    {
        return userService.registerUser(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest req)
    {
        User user = userService.autenticate(req.userName, req.password);
        String token = jwtUtil.generateTokens(user.getUserName());
        return new AuthResponse(token);
    }

    @GetMapping("/me")
    public User getMe(@RequestHeader ("Authorization") String authHeader)
    {
        String token = authHeader.replace("Bearer","").trim();
        String userName = jwtUtil.extractUserName(token);
        return userService.findByUserName(userName);
    }
}
