package hu.nghia.cinema.config;

import hu.nghia.cinema.domain.Address;
import hu.nghia.cinema.domain.Role;
import hu.nghia.cinema.domain.User;
import hu.nghia.cinema.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeederConfig {

    @Bean
    public CommandLineRunner seedAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("gia@gmail.com").isEmpty()) {
                Address address = new Address();
                address.setStreet("123 Cinema Lane");
                address.setCity("Budapest");
                address.setState("HU");
                address.setZip("1011");
                address.setCountry("Hungary");

                User adminUser = new User();
                adminUser.setUsername("gia");
                adminUser.setEmail("gia@gmail.com");
                adminUser.setPassword(passwordEncoder.encode("test1234"));
                adminUser.setPhone("+3630111111");
                adminUser.setRole(Role.ADMIN);
                adminUser.setAddress(address);

                userRepository.save(adminUser);
                System.out.println("✓ Admin user 'gia@gmail.com' seeded successfully");
            }
        };
    }
}
