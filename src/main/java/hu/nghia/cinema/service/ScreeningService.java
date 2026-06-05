package hu.nghia.cinema.service;

import hu.nghia.cinema.domain.Screening;
import hu.nghia.cinema.dto.ScreeningDto;
import hu.nghia.cinema.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreeningService {

    @Autowired
    private ScreeningRepository screeningRepository;

    public List<ScreeningDto> getAllScreenings() {
        return screeningRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ScreeningDto getScreeningById(Integer id) {
        return screeningRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    public ScreeningDto createScreening(ScreeningDto screeningDto) {
        Screening screening = new Screening(screeningDto);
        screening = screeningRepository.save(screening);
        return convertToDto(screening);
    }

    public ScreeningDto updateScreening(Integer id, ScreeningDto screeningDto) {
        if (!screeningRepository.existsById(id)) {
            return null;
        }
        Screening screening = new Screening(screeningDto);
        screening.setId(id);
        screening = screeningRepository.save(screening);
        return convertToDto(screening);
    }

    public void deleteScreening(Integer id) {
        screeningRepository.deleteById(id);
    }

    private ScreeningDto convertToDto(Screening screening) {
        if (screening == null) return null;
        ScreeningDto dto = new ScreeningDto();
        dto.setId(screening.getId());
        dto.setMovieTitle(screening.getMovieTitle());
        dto.setDescription(screening.getDescription());
        dto.setScreenTime(screening.getScreenTime());
        dto.setSeats(screening.getSeats());
        dto.setBooking(screening.getBooking());
        dto.setPictureUrl(screening.getPictureUrl());
        return dto;
    }
}
