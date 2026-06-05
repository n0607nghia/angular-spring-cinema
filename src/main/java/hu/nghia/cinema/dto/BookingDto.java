package hu.nghia.cinema.dto;

import hu.nghia.cinema.domain.Screening;
import hu.nghia.cinema.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDto {
    private Integer id;
    private Integer seats;
    private LocalDateTime date;
    private User user;
    private Screening screening;

    public BookingDto() {
    }

    public BookingDto(Integer seats, LocalDateTime date, User user, Screening screening) {
        this.seats = seats;
        this.date = date;
        this.user = user;
        this.screening = screening;
    }
}
