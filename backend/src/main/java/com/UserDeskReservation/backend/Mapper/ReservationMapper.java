package com.UserDeskReservation.backend.Mapper;

import com.UserDeskReservation.backend.DTO.ReservationDTO;
import com.UserDeskReservation.backend.Entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "employee.empId", target = "empId")
    @Mapping(source = "desk.deskNo", target = "deskNo")
    ReservationDTO mapToReservationDTO(Reservation reservation);

    @Mapping(source = "empId", target = "employee.empId")
    @Mapping(source = "deskNo", target = "desk.deskNo")
    Reservation mapToReservation(ReservationDTO reservationDTO);
}