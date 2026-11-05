package com.intercar.intercarmotion.Entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "engines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Ej: "1.5 TSI", "2.0 TDI", "Dual Motor AWD"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    private Integer horsepower; // CV

    private Integer displacementCc; // Cilindrada (null si es 100% eléctrico)

    private Integer torqueNm;

    private String transmission; // Manual 6v, DSG 7v.

    private Double acceleration0100; // Segundos

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModel carModel;
}
