package com.UserDeskReservation.backend.Repository;

import com.UserDeskReservation.backend.Entity.Employee;
import com.UserDeskReservation.backend.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByEmployeeAndDeskDate(Employee employee, LocalDate deskDate);
    List<Reservation> findByEmployeeAndResDateAfter(Employee employee, LocalDate oneMonthAgo);
}