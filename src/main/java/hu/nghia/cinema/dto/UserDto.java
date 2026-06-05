package hu.nghia.cinema.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private AddressDto address;
    private List<BookingDto> bookings = new ArrayList<>();

    public UserDto() {
    }

    public void addBooking(BookingDto booking) {
        this.bookings.add(booking);
    }
}
