package com.intercar.intercarmotion.Config;

import com.intercar.intercarmotion.Entity.*;
import com.intercar.intercarmotion.Repository.BrandRepository;
import com.intercar.intercarmotion.Repository.CarModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final CarModelRepository carModelRepository;

    @Override
    public void run(String... args) {
        if (brandRepository.count() > 0) return;

        // 1. Porsche
        Brand porsche = Brand.builder()
                .name("Porsche")
                .country("Alemania")
                .logoUrl("https://images.unsplash.com/photo-1614162692292-7ac56d7f7f1e?auto=format&fit=crop&w=400&q=80")
                .build();
        brandRepository.save(porsche);

        CarModel porsche911 = CarModel.builder()
                .name("911 Carrera")
                .segment("Deportivo")
                .yearFrom(2019)
                .yearTo(2024)
                .brand(porsche)
                .build();

        Engine porscheEngine1 = Engine.builder()
                .name("3.0 Bóxer Biturbo")
                .fuelType(FuelType.GASOLINE)
                .horsepower(385)
                .displacementCc(2981)
                .torqueNm(450)
                .transmission("PDK 8 velocidades")
                .acceleration0100(4.2)
                .carModel(porsche911)
                .build();

        CarImage porscheImg1 = CarImage.builder()
                .url("https://images.unsplash.com/photo-1503376780353-7e6692767b70?auto=format&fit=crop&w=1200&q=80")
                .caption("Porsche 911 Frontal")
                .isPrimary(true)
                .carModel(porsche911)
                .build();

        CarImage porscheImg2 = CarImage.builder()
                .url("https://images.unsplash.com/photo-1614162692292-7ac56d7f7f1e?auto=format&fit=crop&w=1200&q=80")
                .caption("Porsche 911 Trasera")
                .isPrimary(false)
                .carModel(porsche911)
                .build();

        porsche911.setEngines(List.of(porscheEngine1));
        porsche911.setImages(List.of(porscheImg1, porscheImg2));
        carModelRepository.save(porsche911);

        // 2. Cupra
        Brand cupra = Brand.builder()
                .name("Cupra")
                .country("España")
                .logoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Cupra_logo.svg/440px-Cupra_logo.svg.png")
                .build();
        brandRepository.save(cupra);

        CarModel formentor = CarModel.builder()
                .name("Formentor")
                .segment("Crossover / SUV")
                .yearFrom(2020)
                .yearTo(2025)
                .brand(cupra)
                .build();

        Engine formentorTsi = Engine.builder()
                .name("1.5 TSI")
                .fuelType(FuelType.GASOLINE)
                .horsepower(150)
                .displacementCc(1498)
                .torqueNm(250)
                .transmission("DSG 7v")
                .acceleration0100(8.9)
                .carModel(formentor)
                .build();

        Engine formentorVz = Engine.builder()
                .name("VZ5 2.5 TSI 4Drive")
                .fuelType(FuelType.GASOLINE)
                .horsepower(390)
                .displacementCc(2480)
                .torqueNm(480)
                .transmission("DSG 7v 4Drive")
                .acceleration0100(4.2)
                .carModel(formentor)
                .build();

        CarImage formentorImg1 = CarImage.builder()
                .url("https://images.unsplash.com/photo-1542282088-72c9c27ed0cd?auto=format&fit=crop&w=1200&q=80")
                .caption("Cupra Formentor")
                .isPrimary(true)
                .carModel(formentor)
                .build();

        formentor.setEngines(List.of(formentorTsi, formentorVz));
        formentor.setImages(List.of(formentorImg1));
        carModelRepository.save(formentor);
    }
}
