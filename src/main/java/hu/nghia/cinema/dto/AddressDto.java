package hu.nghia.cinema.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private Integer id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
