package com.UserDeskReservation.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {
    private Long resId;
    private Long empId;
    private Long deskNo;
    private LocalDate deskDate;
}
