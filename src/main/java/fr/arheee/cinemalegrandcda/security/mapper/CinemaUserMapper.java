package fr.arheee.cinemalegrandcda.security.mapper;

import fr.arheee.cinemalegrandcda.security.dto.CinemaUserDto;
import fr.arheee.cinemalegrandcda.security.entities.CinemaUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel ="spring")
public interface CinemaUserMapper {
    public CinemaUserMapper INSTANCE = Mappers.getMapper(CinemaUserMapper.class);
    CinemaUserDto toDto(CinemaUser entity);

    CinemaUser toEntity(CinemaUserDto dto);
}
