package com.ticketapp.userservice.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    @Size(min = 8, message="Password must be at least 8 characters")
    @NotBlank(message = "Password cannot be empty")
    private String password;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private Role role;


}
