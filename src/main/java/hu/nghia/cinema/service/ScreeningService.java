package hu.nghia.cinema.service;

import hu.nghia.cinema.domain.Screening;
import hu.nghia.cinema.dto.ScreeningDto;
import hu.nghia.cinema.repository.ScreeningRepository;
import hu.nghia.cinema.util.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final FileUploadUtil fileUploadUtil;

    public ScreeningService(ScreeningRepository screeningRepository, FileUploadUtil fileUploadUtil) {
        this.screeningRepository = screeningRepository;
        this.fileUploadUtil = fileUploadUtil;
    }

    public List<ScreeningDto> getAllScreenings() {
        return screeningRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ScreeningDto getScreeningById(Integer id) {
        return screeningRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    public ScreeningDto createScreening(ScreeningDto screeningDto, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            screeningDto.setImage(fileUploadUtil.uploadImage(image));
        }
        Screening screening = new Screening(screeningDto);
        return convertToDto(screeningRepository.save(screening));
    }

    public ScreeningDto updateScreening(Integer id, ScreeningDto screeningDto, MultipartFile image) {
        if (!screeningRepository.existsById(id)) return null;
        if (image != null && !image.isEmpty()) {
            screeningDto.setImage(fileUploadUtil.uploadImage(image));
        }
        Screening screening = new Screening(screeningDto);
        screening.setId(id);
        return convertToDto(screeningRepository.save(screening));
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
        dto.setImage(screening.getImage());
        return dto;
    }
}
