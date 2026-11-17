package com.intercar.intercarmotion.Service;



import com.intercar.intercarmotion.Entity.User;
import com.intercar.intercarmotion.Repository.UserRepository;
import com.intercar.intercarmotion.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Map<String, String> register(String name, String surname, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("El email ya está registrado");
        }

        User user = User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return Map.of("token", token, "name", user.getName(), "email", user.getEmail());
    }

    public Map<String, String> login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(user);

        return Map.of("token", token, "name", user.getName(), "email", user.getEmail());
    }
}
