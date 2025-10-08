package com.example.User.Service;

import com.example.User.DTO.RegisterRequest;
import com.example.User.Model.User;
import com.example.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder ;

    public User registerUser(RegisterRequest req)
    {
        if(userRepository.existsByEmail(req.email) || userRepository.existsByUserName(req.userName))
        {
            throw new RuntimeException("User already exists");
        }

        User user = User.builder()
                .userName(req.userName)
                .email(req.email)
                .passwordHash(encoder.encode(req.password))
                .build();
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id)
    {
        return userRepository.findById(id);
    }

    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }

    public User autenticate(String userName, String password)
    {
        User user = userRepository.findByUserName(userName);

        if(user == null || !encoder.matches(password,user.getPasswordHash()))
        {
            throw new RuntimeException("Invalid uermame or pssword");
        }
        return user;
    }

    public User findByUserName(String userName)
    {
        return userRepository.findByUserName(userName);
    }

}
