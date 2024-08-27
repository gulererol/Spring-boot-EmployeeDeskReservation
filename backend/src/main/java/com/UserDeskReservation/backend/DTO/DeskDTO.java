package com.UserDeskReservation.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class DeskDTO {

    private Long deskNo;
    private String room;
    private Set<LocalDate> unavailableDates = new HashSet<>();
}
