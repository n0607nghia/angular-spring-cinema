package hu.nghia.cinema.service;

import hu.nghia.cinema.domain.Booking;
import hu.nghia.cinema.dto.BookingDto;
import hu.nghia.cinema.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public BookingDto getBookingById(Integer id) {
        return bookingRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    public BookingDto createBooking(BookingDto bookingDto) {
        Booking booking = new Booking(bookingDto);
        booking = bookingRepository.save(booking);
        return convertToDto(booking);
    }

    public BookingDto updateBooking(Integer id, BookingDto bookingDto) {
        if (!bookingRepository.existsById(id)) {
            return null;
        }
        Booking booking = new Booking(bookingDto);
        booking.setId(id);
        booking = bookingRepository.save(booking);
        return convertToDto(booking);
    }

    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }

    private BookingDto convertToDto(Booking booking) {
        if (booking == null) return null;
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setSeats(booking.getSeats());
        dto.setDate(booking.getDate());
        dto.setUser(booking.getUser());
        dto.setScreening(booking.getScreening());
        return dto;
    }
}
