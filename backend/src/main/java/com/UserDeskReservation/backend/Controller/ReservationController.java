package com.UserDeskReservation.backend.Controller;

import com.UserDeskReservation.backend.DTO.ReservationDTO;
import com.UserDeskReservation.backend.Service.Implementation.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reserve")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long reservationId) {
        ReservationDTO reservationDTO = reservationService.getReservationById(reservationId);
        return ResponseEntity.ok(reservationDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/history/{employeeId}")
    public ResponseEntity<List<ReservationDTO>> getReservationHistoryForLastMonth(@PathVariable Long employeeId) {
        List<ReservationDTO> reservationHistory = reservationService.getReservationHistoryForLastMonth(employeeId);
        return ResponseEntity.ok(reservationHistory);
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long reservationId, @RequestBody ReservationDTO updatedReservation) {
        ReservationDTO reservationDTO = reservationService.updateReservation(reservationId, updatedReservation);
        return ResponseEntity.ok(reservationDTO);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }
}