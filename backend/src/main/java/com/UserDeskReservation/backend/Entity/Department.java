package com.UserDeskReservation.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depId;

    @Column(name = "department_name")
    private String depName;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
