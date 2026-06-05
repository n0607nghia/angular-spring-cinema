package hu.nghia.cinema.controller;

import hu.nghia.cinema.dto.BookingDto;
import hu.nghia.cinema.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        logger.info("Fetching all bookings");
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Integer id) {
        logger.info("Fetching booking by id: {}", id);
        BookingDto bookingDto = bookingService.getBookingById(id);
        return bookingDto != null ? ResponseEntity.ok(bookingDto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        logger.info("Creating new booking");
        return ResponseEntity.status(201).body(bookingService.createBooking(bookingDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Integer id, @RequestBody BookingDto bookingDto) {
        logger.info("Updating booking with id: {}", id);
        BookingDto updatedBooking = bookingService.updateBooking(id, bookingDto);
        return updatedBooking != null ? ResponseEntity.ok(updatedBooking) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        logger.info("Deleting booking with id: {}", id);
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
