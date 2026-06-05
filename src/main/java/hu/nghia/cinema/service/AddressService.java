package hu.nghia.cinema.service;

import hu.nghia.cinema.domain.Address;
import hu.nghia.cinema.dto.AddressDto;
import hu.nghia.cinema.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<AddressDto> getAllAddresses() {
        return addressRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public AddressDto getAddressById(Integer id) {
        return addressRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    public AddressDto createAddress(AddressDto addressDto) {
        Address address = new Address(addressDto);
        address = addressRepository.save(address);
        return convertToDto(address);
    }

    public AddressDto updateAddress(Integer id, AddressDto addressDto) {
        if (!addressRepository.existsById(id)) {
            return null;
        }
        Address address = new Address(addressDto);
        address.setId(id);
        address = addressRepository.save(address);
        return convertToDto(address);
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
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
}
