package com.ticketapp.tripservice.service;

import com.ticketapp.tripservice.domain.Trip;
import com.ticketapp.tripservice.domain.TripStatus;
import com.ticketapp.tripservice.domain.VehicleType;
import com.ticketapp.tripservice.dto.CreateTripRequest;
import com.ticketapp.tripservice.exception.TripNotFoundException;
import com.ticketapp.tripservice.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip createTrip(CreateTripRequest request) {


        if(!request.getArrivalTime().isAfter(request.getDepartureTime())) {
            throw new IllegalArgumentException("Arrival time must be after departure time");

        }

        if(request.getArrivalCity().equalsIgnoreCase(request.getDepartureCity())) {
            throw new IllegalArgumentException("Arrival city mustn't be the same");
        }


        if(request.getVehicleType()==VehicleType.BUS && request.getCapacity()>45){
            throw new IllegalArgumentException("Bus capacity cannot exceed 45");
        }

        if(request.getVehicleType()==VehicleType.PLANE && request.getCapacity()>189){
            throw new IllegalArgumentException("Plane capacity cannot exceed 189");
        }



            Trip trip = Trip.builder()
                .vehicleType(request.getVehicleType())
                .departureCity(request.getDepartureCity())
                .arrivalCity(request.getArrivalCity())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .price(request.getPrice())
                .capacity(request.getCapacity())
                .availableSeats(request.getCapacity())
                .status(TripStatus.ACTIVE)
                .build();
        return tripRepository.save(trip);
    }

    public List<Trip> searchTrips(
            String departureCity,
            String arrivalCity,
            VehicleType vehicleType,
            LocalDate date
    ) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1)
                .atStartOfDay()
                .minusNanos(1);

        return tripRepository
                .findByDepartureCityAndArrivalCityAndVehicleTypeAndDepartureTimeBetweenAndStatus(
                        departureCity,
                        arrivalCity,
                        vehicleType,
                        start,
                        end,
                        TripStatus.ACTIVE
                );

    }

    public Trip cancelTrip(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow( ()-> new TripNotFoundException(("Trip not found")));
                trip.setStatus(TripStatus.CANCELLED);
                return tripRepository.save(trip);

    }


}
