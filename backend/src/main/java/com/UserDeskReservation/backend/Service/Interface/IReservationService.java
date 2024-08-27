package com.UserDeskReservation.backend.Service.Interface;

import com.UserDeskReservation.backend.DTO.ReservationDTO;

import java.util.List;

public interface IReservationService {
    ReservationDTO createReservation(ReservationDTO reservationDTO);
    ReservationDTO getReservationById(Long reservationId);
    List<ReservationDTO> getAllReservations();
    ReservationDTO updateReservation(Long reservationId, ReservationDTO updatedReservationDTO);
    void deleteReservation(Long reservationId);
    public List<ReservationDTO> getReservationHistoryForLastMonth(Long employeeId);
}