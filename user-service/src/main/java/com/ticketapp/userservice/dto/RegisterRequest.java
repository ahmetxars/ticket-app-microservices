package com.ticketapp.userservice.dto;

import com.ticketapp.userservice.entity.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;
    @Size(min = 8)
    @NotBlank
    private String password;

    private String phoneNumber;
    @NotNull
    private UserType userType;

}
