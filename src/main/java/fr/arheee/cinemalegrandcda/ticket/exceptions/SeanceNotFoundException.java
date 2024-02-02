package fr.arheee.cinemalegrandcda.ticket.exceptions;

public class SeanceNotFoundException extends RuntimeException {
    public SeanceNotFoundException(Integer IdSeance) {
        super("Pas de seance avec cet ID : " + IdSeance);
    }


}
