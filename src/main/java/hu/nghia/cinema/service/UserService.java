package hu.nghia.cinema.service;

import hu.nghia.cinema.domain.Address;
import hu.nghia.cinema.domain.Booking;
import hu.nghia.cinema.domain.User;
import hu.nghia.cinema.dto.AddressDto;
import hu.nghia.cinema.dto.BookingDto;
import hu.nghia.cinema.dto.UserDto;
import hu.nghia.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Integer id) {
        return userRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    public UserDto createUser(UserDto userDto) {
        User user = new User(userDto);
        user = userRepository.save(user);
        return convertToDto(user);
    }

    public UserDto updateUser(Integer id, UserDto userDto) {
        if (!userRepository.existsById(id)) {
            return null;
        }
        User user = new User(userDto);
        user.setId(id);
        user = userRepository.save(user);
        return convertToDto(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAddress(convertToDto(user.getAddress()));
        dto.setBookings(user.getBookings().stream().map(this::convertToDto).collect(Collectors.toList()));
        return dto;
    }

    private AddressDto convertToDto(Address address) {
        if (address == null) return null;
        AddressDto dto = new AddressDto();
        dto.setId(address.getId());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZip(address.getZip());
        dto.setCountry(address.getCountry());
        return dto;
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
