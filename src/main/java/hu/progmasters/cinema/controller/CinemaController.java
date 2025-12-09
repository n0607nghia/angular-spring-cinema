package hu.progmasters.cinema.controller;

import hu.progmasters.cinema.dto.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CinemaController {

    private static final Logger logger = LoggerFactory.getLogger(CinemaController.class);

    private CinemaService cinemaService;
    private MovieScreeningValidator movieScreeningValidator;
    private ReservationValidator reservationValidator;

    @Autowired
    public CinemaController(CinemaService cinemaService, MovieScreeningValidator movieScreeningValidator, ReservationValidator reservationValidator) {
        this.cinemaService = cinemaService;
        this.movieScreeningValidator = movieScreeningValidator;
        this.reservationValidator = reservationValidator;
    }

    @InitBinder("movieScreeningCreationCommand")
    protected void initScreeningBinder(WebDataBinder binder) {
        binder.addValidators(movieScreeningValidator);
    }

    @InitBinder("reservationCreationCommand")
    protected void initReservationBinder(WebDataBinder binder) {
        binder.addValidators(reservationValidator);
    }

    @GetMapping("/reservations/screeningData")
    public ResponseEntity<List<ScreeningItemForReservationForm>> newTransferData() {
        List<ScreeningItemForReservationForm> screeningItems =
                cinemaService.listMovieScreenings().stream().map(ScreeningItemForReservationForm::new).collect(Collectors.toList());
        return new ResponseEntity<>(screeningItems, HttpStatus.OK);
    }

    @PostMapping("/screenings")
    public ResponseEntity createMovieScreening(@RequestBody @Valid MovieScreeningCreationCommand command) {
        cinemaService.createMovieScreening(command);
        logger.info("New screening added");
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/screenings")
    public ResponseEntity<List<MovieScreeningListItem>> getMovieScreenings() {
        List<MovieScreeningListItem> movieScreenings = cinemaService.listMovieScreenings();
        logger.info("Screenings page requested");
        return new ResponseEntity<>(movieScreenings, HttpStatus.OK);
    }

    @PostMapping("/reservations")
    public ResponseEntity createReservation(@RequestBody @Valid ReservationCreationCommand command) {
        cinemaService.createReservation(command);
        logger.info("Ticket(s) reserved");
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationListItem>> getReservations() {
        logger.info("Reservations page requested");
        return new ResponseEntity<>(cinemaService.listReservations(), HttpStatus.OK);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<SummaryListItem>> getSummary() {
        List<SummaryListItem> summaries = cinemaService.getSummaryList();
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<SummaryListItem>> getMovieSummary() {
        List<SummaryListItem> summaryListItems = cinemaService.getSummaryList();
        logger.info("Movie summary page requested");
        return new ResponseEntity<>(summaryListItems, HttpStatus.OK);
    }

}
