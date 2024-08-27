package com.UserDeskReservation.backend.Mapper;

import com.UserDeskReservation.backend.DTO.DeskDTO;
import com.UserDeskReservation.backend.Entity.Desk;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeskMapper {

    DeskMapper INSTANCE = Mappers.getMapper(DeskMapper.class);

    DeskDTO mapToDeskDTO(Desk desk);

    Desk mapToDesk(DeskDTO deskDTO);
}