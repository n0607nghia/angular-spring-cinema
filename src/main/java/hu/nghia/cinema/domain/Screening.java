package hu.nghia.cinema.domain;

import hu.nghia.cinema.dto.ScreeningDto;
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

    public Screening() {
    }

    public Screening(ScreeningDto screeningDto) {
        this.movieTitle = screeningDto.getMovieTitle();
        this.description = screeningDto.getDescription();
        this.screenTime = screeningDto.getScreenTime();
        this.seats = screeningDto.getSeats();
        this.pictureUrl = screeningDto.getPictureUrl();
    }
}
