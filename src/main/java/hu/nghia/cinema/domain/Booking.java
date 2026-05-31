package hu.nghia.cinema.domain;

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
}
