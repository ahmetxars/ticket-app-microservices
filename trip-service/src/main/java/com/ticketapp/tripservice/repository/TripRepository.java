package com.ticketapp.tripservice.repository;

import com.ticketapp.tripservice.domain.Trip;
import com.ticketapp.tripservice.domain.TripStatus;
import com.ticketapp.tripservice.domain.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByDepartureCityAndArrivalCityAndVehicleTypeAndDepartureTimeBetweenAndStatus(
            String departureCity,
            String arrivalCity,
            VehicleType vehicleType,
            LocalDateTime start,
            LocalDateTime end,
            TripStatus status
    );
}
