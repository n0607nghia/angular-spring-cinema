package hu.nghia.cinema.service;

import hu.nghia.cinema.domain.Role;
import hu.nghia.cinema.domain.User;
import hu.nghia.cinema.dto.AuthResponse;
import hu.nghia.cinema.dto.LoginRequest;
import hu.nghia.cinema.repository.UserRepository;
import hu.nghia.cinema.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void login_shouldReturnTokenAndRole_whenCredentialsAreValid() {
        LoginRequest request = new LoginRequest();
        request.setEmail("gia@gmail.com");
        request.setPassword("test1234");

        User user = new User();
        user.setEmail("gia@gmail.com");
        user.setUsername("gia");
        user.setPassword("hashed");
        user.setRole(Role.ADMIN);

        when(userRepository.findByEmail("gia@gmail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("test1234", "hashed")).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("jwt-token");

        AuthResponse response = authService.login(request);

        assertEquals("jwt-token", response.getToken());
        assertEquals("ADMIN", response.getRole());
        assertEquals("gia", response.getUsername());
    }

    @Test
    void login_shouldThrowBadCredentialsException_whenPasswordIsInvalid() {
        LoginRequest request = new LoginRequest();
        request.setEmail("gia@gmail.com");
        request.setPassword("wrong");

        User user = new User();
        user.setEmail("gia@gmail.com");
        user.setPassword("hashed");

        when(userRepository.findByEmail("gia@gmail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "hashed")).thenReturn(false);

        assertThrows(BadCredentialsException.class, () -> authService.login(request));
        verify(jwtService, never()).generateToken(any());
    }

    @Test
    void login_shouldThrowUsernameNotFoundException_whenUserDoesNotExist() {
        LoginRequest request = new LoginRequest();
        request.setEmail("missing@gmail.com");
        request.setPassword("test");

        when(userRepository.findByEmail("missing@gmail.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> authService.login(request));
    }
}
