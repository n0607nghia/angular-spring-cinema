package hu.nghia.cinema.controller;

import hu.nghia.cinema.dto.AddressDto;
import hu.nghia.cinema.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        logger.info("Fetching all addresses");
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Integer id) {
        logger.info("Fetching address by id: {}", id);
        AddressDto addressDto = addressService.getAddressById(id);
        return addressDto != null ? ResponseEntity.ok(addressDto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        logger.info("Creating new address");
        return ResponseEntity.status(201).body(addressService.createAddress(addressDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        logger.info("Updating address with id: {}", id);
        AddressDto updatedAddress = addressService.updateAddress(id, addressDto);
        return updatedAddress != null ? ResponseEntity.ok(updatedAddress) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
        logger.info("Deleting address with id: {}", id);
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
