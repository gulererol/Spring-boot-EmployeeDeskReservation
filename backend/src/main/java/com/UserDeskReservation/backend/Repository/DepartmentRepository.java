package com.UserDeskReservation.backend.Repository;

import com.UserDeskReservation.backend.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
