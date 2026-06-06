package hu.nghia.cinema.domain;

import hu.nghia.cinema.dto.UserDto;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@lombok.Setter
@lombok.Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "name")
    private String Username;
    @Column(name = "password")
    private String Password;
    @Column(name = "email", unique = true)
    private String Email;
    @Column(name = "phone")
    private String Phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address Address;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> Bookings;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.USER;

    public User() {
    }

    public User(UserDto userDto) {
        this.Bookings = userDto.getBookings().stream().map(Booking::new).toList();
        this.Bookings.forEach(booking -> booking.setUser(this));
        this.Address = new Address(userDto.getAddress());
        this.Email = userDto.getEmail();
        this.Phone = userDto.getPhone();
        this.Username = userDto.getUsername();
        if (userDto.getRole() != null) this.role = userDto.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override public String getPassword() { return Password; }
    @Override public String getUsername() { return Email; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
