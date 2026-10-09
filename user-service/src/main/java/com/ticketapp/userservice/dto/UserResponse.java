package com.ticketapp.userservice.dto;

import com.ticketapp.userservice.entity.Role;
import com.ticketapp.userservice.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserType userType;
    private Role role;
}