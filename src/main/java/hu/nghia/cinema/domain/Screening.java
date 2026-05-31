package hu.nghia.cinema.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@lombok.Setter
@lombok.Getter
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "movie_title")
    private String movieTitle;
    @Column(name = "despription")
    private String description;
    private LocalDateTime screenTime;
    @Column(name = "seats")
    private Integer seats;
    @OneToMany(mappedBy = "screening")
    private List<Booking> booking;
    private String pictureUrl;
}
