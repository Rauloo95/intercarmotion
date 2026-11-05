package com.intercar.intercarmotion.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Ej: "León", "Civic", "911"

    private String segment; // Ej: "Compacto", "SUV", "Sedán"

    private Integer yearFrom;

    private Integer yearTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Engine> engines = new ArrayList<>();

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CarImage> images = new ArrayList<>();
}
