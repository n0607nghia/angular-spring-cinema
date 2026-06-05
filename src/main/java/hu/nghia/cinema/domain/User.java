package hu.nghia.cinema.domain;

import hu.nghia.cinema.dto.UserDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@lombok.Setter
@lombok.Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "name")
    private String Username;
    @Column(name = "password")
    private String Password;
    @Column(name = "email",  unique = true)
    private String Email;
    @Column(name = "phone")
    private String Phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address Address;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Booking> Bookings;

    public User() {
    }

    public User(UserDto userDto) {
        this.Bookings = userDto.getBookings().stream().map(booking -> new Booking(booking)).toList();
        this.Address = new Address(userDto.getAddress());
        this.Email = userDto.getEmail();
        this.Phone = userDto.getPhone();
        this.Username = userDto.getUsername();
    }
}
