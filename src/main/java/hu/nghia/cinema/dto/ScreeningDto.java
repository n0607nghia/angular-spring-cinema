package hu.nghia.cinema.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScreeningDto {
    private Integer id;
    private String movieTitle;
    private String description;
    private LocalDateTime screenTime;
    private Integer seats;
    private String image;
}
