package hu.nghia.cinema.security;

import hu.nghia.cinema.domain.Role;
import hu.nghia.cinema.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = JwtService.class,
        properties = {
                "jwt.secret=1234567890123456789012345678901234567890123456789012345678901234",
                "jwt.expiration-ms=60000"
        }
)
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void classLevelFields_shouldBeInjectedFromProperties() {
        Object secret = ReflectionTestUtils.getField(jwtService, "jwtSecret");
        Object expiration = ReflectionTestUtils.getField(jwtService, "jwtExpirationMs");

        assertNotNull(secret);
        assertEquals("1234567890123456789012345678901234567890123456789012345678901234", secret);
        assertNotNull(expiration);
        assertEquals(60000L, expiration);
    }

    @Test
    void generateAndParseToken_shouldUseInjectedClassFields() {
        User user = new User();
        user.setEmail("gia@gmail.com");
        user.setRole(Role.ADMIN);

        String token = jwtService.generateToken(user);

        assertNotNull(token);
        assertTrue(jwtService.validateToken(token));
        assertEquals("gia@gmail.com", jwtService.extractEmail(token));
        assertEquals("ADMIN", jwtService.extractRole(token));
    }
}
