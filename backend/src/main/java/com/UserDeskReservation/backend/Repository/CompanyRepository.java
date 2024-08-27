package com.UserDeskReservation.backend.Repository;

import com.UserDeskReservation.backend.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
