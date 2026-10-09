package com.ticketapp.notificationservice.event;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredEvent {

    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
}
