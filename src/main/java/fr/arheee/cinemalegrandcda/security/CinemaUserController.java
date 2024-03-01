package fr.arheee.cinemalegrandcda.security;

import fr.arheee.cinemalegrandcda.security.dto.CinemaUserDto;
import fr.arheee.cinemalegrandcda.security.entities.CinemaUser;
import fr.arheee.cinemalegrandcda.security.mapper.CinemaUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CinemaUserController {

    private final CinemaUserService cinemaUserService;
    private final CinemaUserMapper mapper;

    @PostMapping("/register")
    public  CinemaUser save(@RequestBody CinemaUserDto user){
        return cinemaUserService.save(mapper.toEntity(user));
    }
}
