package com.intercar.intercarmotion.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "car_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    private String caption;

    private boolean isPrimary; // true para la foto de portada de la tarjeta

    //relacion con el modelo de coche
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModel carModel;


}
