package com.ticketapp.tripservice.dto;

import com.ticketapp.tripservice.domain.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTripRequest {
    @NotNull
    private VehicleType vehicleType;
    @NotBlank
    private String departureCity;
    @NotBlank
    private String arrivalCity;
    @NotNull
    private LocalDateTime departureTime;
    @NotNull
    private LocalDateTime arrivalTime;
    @NotNull
    @Positive
    private BigDecimal price;
    @Positive
    private int capacity;

}
