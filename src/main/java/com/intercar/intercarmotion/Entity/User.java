package com.intercar.intercarmotion.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    protected String name;
    @Column(nullable = false)
    private String surname;
    private String password;
    @Column(nullable = false, unique = true)
    private String email;

    // Muchos usuarios pueden tener muchos coches favoritos (tabla intermedia limpia)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "car_model_id")
    )
    @Builder.Default
    private Set<CarModel> favoriteCars = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // Como vas a iniciar sesión con email, devolvemos el email aquí para Spring Security
    @Override
    public String getUsername() {
        return this.email;
    }


}
