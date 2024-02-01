package fr.arheee.cinemalegrandcda.salle.dto;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
public class SalleReduitDto {
    private String nom;
    private int capacite;
}
