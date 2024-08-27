package com.UserDeskReservation.backend.Repository;

import com.UserDeskReservation.backend.Entity.Desk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeskRepository extends JpaRepository<Desk,Long> {
}
