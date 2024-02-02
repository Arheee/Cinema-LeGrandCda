package fr.arheee.cinemalegrandcda.ticket.dto;

import lombok.Data;

@Data
public class TicketReduitDto {
    private Integer id;
    private String nomClient;
    private int nombrePlaces;
}
