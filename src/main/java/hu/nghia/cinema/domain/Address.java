package hu.nghia.cinema.domain;

import hu.nghia.cinema.dto.AddressDto;
import jakarta.persistence.*;

@Entity
@lombok.Getter
@lombok.Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zip")
    private String zip;
    @Column(name = "country")
    private String country;

    public Address() {
    }

    public Address(AddressDto addressDto) {
        this.street = addressDto.getStreet();
        this.city = addressDto.getCity();
        this.state = addressDto.getState();
        this.zip = addressDto.getZip();
        this.country = addressDto.getCountry();
    }
}
