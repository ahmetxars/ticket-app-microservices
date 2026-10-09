package com.ticketapp.userservice.service;


import com.ticketapp.userservice.dto.LoginRequest;
import com.ticketapp.userservice.dto.RegisterRequest;
import com.ticketapp.userservice.dto.UserResponse;
import com.ticketapp.userservice.entity.Role;
import com.ticketapp.userservice.entity.User;
import com.ticketapp.userservice.event.UserRegisteredEvent;
import com.ticketapp.userservice.exception.InvalidCredentialsException;
import com.ticketapp.userservice.messaging.UserEventPublisher;
import com.ticketapp.userservice.repository.UserRepository;
import com.ticketapp.userservice.security.JwtService;
import com.ticketapp.userservice.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordUtil passwordUtil;
    private final JwtService jwtService;
    private final UserEventPublisher userEventPublisher;


    public UserService(UserRepository userRepository, PasswordUtil passwordUtil, JwtService jwtService,  UserEventPublisher userEventPublisher) {
        this.userRepository = userRepository;
        this.passwordUtil = passwordUtil;
        this.jwtService = jwtService;
        this.userEventPublisher = userEventPublisher;

    }

    public String registerUser(RegisterRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return "Email already in use";
        }

        if (registerRequest.getPhoneNumber() != null
                && !registerRequest.getPhoneNumber().isBlank()
                && userRepository.existsByPhoneNumber(registerRequest.getPhoneNumber())) {

            return "Phone number already in use";
        }

        String hashedPassword =
                passwordUtil.hashPassword(registerRequest.getPassword());

        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .password(hashedPassword)
                .userType(registerRequest.getUserType())
                .role(Role.USER)
                .build();

        User SavedUser= userRepository.save(user);


        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .userId(SavedUser.getId())
                .email(SavedUser.getEmail())
                .firstName(SavedUser.getFirstName())
                .lastName(SavedUser.getLastName())
                .build();

        userEventPublisher.publishUserRegistered(event);
        return "User registered successfully";
    }



    public String loginUser(LoginRequest loginRequest) {


        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new InvalidCredentialsException("Invalid email or password"));

        String password = loginRequest.getPassword();
        String hashedPassword = passwordUtil.hashPassword(password);

        if(!hashedPassword.equals(user.getPassword())){
            throw new InvalidCredentialsException("Invalid email or password");


        }

        return jwtService.generateToken(user.getEmail());





    }

    public UserResponse getUserByEmail(String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new InvalidCredentialsException("User not found")
                );

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .userType(user.getUserType())
                .role(user.getRole())
                .build();
    }
}





