package com.livecode.livecode.controller;

import com.livecode.livecode.models.Reservation;
import com.livecode.livecode.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> addReservation(@RequestParam String customerName, @RequestParam String date) {
        LocalDate reservationDate = LocalDate.parse(date);
        String result = reservationService.addReservation(customerName, reservationDate);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/week")
    public List<Reservation> getWeeklyReservations() {
        return reservationService.getReservationsForWeek();
    }
}