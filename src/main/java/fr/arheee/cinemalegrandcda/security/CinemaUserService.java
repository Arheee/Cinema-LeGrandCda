package fr.arheee.cinemalegrandcda.security;


import fr.arheee.cinemalegrandcda.security.entities.CinemaUser;
import fr.arheee.cinemalegrandcda.security.entities.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CinemaUserService implements UserDetailsService {

    private final CinemaUserRepository cinemaUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * SAVE
     * @param user
     * @return
     */
    public CinemaUser save(CinemaUser user){

        //verifier si l'utilisateur existe deja avec isPresent
        if( cinemaUserRepository.findByUsername(user.getUsername()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cet username est déjà pris");
        }
        //entité de role créer
        Role role = new Role();
        role.setAuthority("USER");
        //recup le mdp et ecrase le mdp par le mdp encoder
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        //affecte le role a l'utilisateur
        user.setRole(role);
        return cinemaUserRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CinemaUser user = cinemaUserRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("il existe pas d'utilisateur de ce nom"));
        return User.builder().username(user.getUsername()).password(user.getPassword()).roles(user.getRole().getAuthority()).build();
    }
}
