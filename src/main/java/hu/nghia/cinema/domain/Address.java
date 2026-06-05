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
    private String Street;
    @Column(name = "city")
    private String City;
    @Column(name = "state")
    private String State;
    @Column(name = "zip")
    private String Zip;
    @Column(name = "country")
    private String Country;

    public Address() {
    }

    public Address(AddressDto addressDto) {
        this.Street = addressDto.getStreet();
        this.City = addressDto.getCity();
        this.State = addressDto.getState();
        this.Zip = addressDto.getZip();
        this.Country = addressDto.getCountry();
    }
}
