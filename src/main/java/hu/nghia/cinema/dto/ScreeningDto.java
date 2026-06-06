package hu.nghia.cinema.dto;

import hu.nghia.cinema.domain.Booking;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ScreeningDto {
    private Integer id;
    private String movieTitle;
    private String description;
    private LocalDateTime screenTime;
    private Integer seats;
    private List<Booking> booking;
    private String image;
}
