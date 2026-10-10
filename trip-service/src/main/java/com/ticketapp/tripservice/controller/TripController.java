package com.ticketapp.tripservice.controller;

import com.ticketapp.tripservice.domain.Trip;
import com.ticketapp.tripservice.domain.VehicleType;
import com.ticketapp.tripservice.dto.CreateTripRequest;
import com.ticketapp.tripservice.service.TripService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public Trip createTrip(@RequestBody @Valid CreateTripRequest request) {
        return tripService.createTrip(request);

    }

    @GetMapping("/search")
    public List<Trip> searchTrip(@RequestParam String departureCity, @RequestParam String arrivalCity, @RequestParam VehicleType vehicleType, @RequestParam LocalDate date) {
        return tripService.searchTrips(departureCity, arrivalCity, vehicleType, date);

    }

    @PatchMapping("/{id}/cancel")
    public String cancelTrip(@PathVariable Long id) {
        tripService.cancelTrip(id);
        return "Trip has been cancelled";

    }


}
