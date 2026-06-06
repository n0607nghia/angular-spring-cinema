package hu.nghia.cinema.controller;

import hu.nghia.cinema.dto.ScreeningDto;
import hu.nghia.cinema.service.ScreeningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {

    private static final Logger logger = LoggerFactory.getLogger(ScreeningController.class);

    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping
    public ResponseEntity<List<ScreeningDto>> getAllScreenings() {
        logger.info("Fetching all screenings");
        return ResponseEntity.ok(screeningService.getAllScreenings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreeningDto> getScreeningById(@PathVariable Integer id) {
        logger.info("Fetching screening by id: {}", id);
        ScreeningDto dto = screeningService.getScreeningById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ScreeningDto> createScreening(
            @RequestPart("screening") ScreeningDto screeningDto,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        logger.info("Admin creating new screening: {}", screeningDto.getMovieTitle());
        return ResponseEntity.status(201).body(screeningService.createScreening(screeningDto, image));
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ScreeningDto> updateScreening(
            @PathVariable Integer id,
            @RequestPart("screening") ScreeningDto screeningDto,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        logger.info("Admin updating screening id: {}", id);
        ScreeningDto updated = screeningService.updateScreening(id, screeningDto, image);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteScreening(@PathVariable Integer id) {
        logger.info("Admin deleting screening id: {}", id);
        screeningService.deleteScreening(id);
        return ResponseEntity.noContent().build();
    }
}
