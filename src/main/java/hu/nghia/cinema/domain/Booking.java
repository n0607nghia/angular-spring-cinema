package hu.nghia.cinema.domain;

import hu.nghia.cinema.dto.BookingDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@lombok.Setter
@lombok.Getter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer seats;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    public Booking() {
    }

    public Booking(BookingDto bookingDto) {
        this.seats = bookingDto.getSeats();
        this.date = bookingDto.getDate();
        this.user = bookingDto.getUser();
        this.screening = bookingDto.getScreening();
    }
}
