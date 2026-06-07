package hu.nghia.cinema.controller;

import hu.nghia.cinema.dto.AuthResponse;
import hu.nghia.cinema.dto.LoginRequest;
import hu.nghia.cinema.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Login attempt for email: {}", loginRequest.getEmail());
        AuthResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
