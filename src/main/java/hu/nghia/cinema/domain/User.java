package hu.nghia.cinema.domain;

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
    private String Name;
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
    private List<Booking> bookings;

}
